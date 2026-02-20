package flows

import cart.{CartCalculator, CartItem}
import coupons.{CouponRepository, CouponValidator}
import kafka.KafkaProducerService
import notifications.{Notification, NotificationPipeline}
import orders.OrderRepository
import play.api.data.Form
import play.api.data.Forms.*
import play.api.data.format.Formats.doubleFormat
import play.api.libs.json.Json
import play.api.{Configuration, Logging}
import users.UserRepository

import java.time.Instant
import java.util.UUID
import javax.inject.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class FlowController @Inject()(
  val controllerComponents: ControllerComponents,
  userRepository: UserRepository,
  couponRepository: CouponRepository,
  orderRepository: OrderRepository,
  kafkaProducer: KafkaProducerService,
  flowEventConsumer: FlowEventConsumer,
  config: Configuration
)(using ec: ExecutionContext) extends BaseController with Logging:

  private val checkoutTopic = config.get[String]("kafka.topics.checkout-email")
  private val resetTopic = config.get[String]("kafka.topics.password-reset")

  // --- Form definitions ---

  private val selectUserForm = Form(
    mapping("userId" -> longNumber)(SelectUserForm.apply)(f => Some(f.userId))
  )

  private val addToCartForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "category" -> nonEmptyText,
      "unitPrice" -> of[Double],
      "quantity" -> number(min = 1)
    )(AddToCartForm.apply)(f => Some((f.name, f.category, f.unitPrice, f.quantity)))
  )

  private val applyCouponForm = Form(
    mapping("code" -> nonEmptyText)(ApplyCouponForm.apply)(f => Some(f.code))
  )

  private val enterEmailForm = Form(
    mapping("email" -> nonEmptyText)(EnterEmailForm.apply)(f => Some(f.email))
  )

  private val confirmResetForm = Form(
    mapping(
      "token" -> nonEmptyText,
      "newPassword" -> nonEmptyText(minLength = 4)
    )(ConfirmResetForm.apply)(f => Some((f.token, f.newPassword)))
  )

  // --- Session helpers ---

  private def parseCartItems(session: Session): Seq[CartItem] =
    session.get("flow-checkout-cart").filter(_.nonEmpty).map { raw =>
      raw.split("\\|").toSeq.flatMap { entry =>
        entry.split(",", 4) match
          case Array(name, category, price, qty) =>
            scala.util.Try(CartItem(name, category, price.toDouble, qty.toInt)).toOption
          case _ => None
      }
    }.getOrElse(Seq.empty)

  private def serializeCartItems(items: Seq[CartItem]): String =
    items.map(i => s"${i.name},${i.category},${i.unitPrice},${i.quantity}").mkString("|")

  private def checkoutStep(session: Session): Int =
    session.get("flow-checkout-step").flatMap(_.toIntOption).getOrElse(1)

  private def recoveryStep(session: Session): Int =
    session.get("flow-recovery-step").flatMap(_.toIntOption).getOrElse(1)

  private def buildCheckoutState(session: Session, userOpt: Option[users.User], kafkaEvents: Seq[FlowEvent]): CheckoutState =
    CheckoutState(
      step = checkoutStep(session),
      selectedUser = userOpt,
      cartItems = parseCartItems(session),
      couponDiscount = session.get("flow-checkout-discount").flatMap(_.toDoubleOption),
      couponCode = session.get("flow-checkout-coupon"),
      orderId = session.get("flow-checkout-order").flatMap(_.toLongOption),
      kafkaEvents = kafkaEvents
    )

  private def buildRecoveryState(session: Session, userOpt: Option[users.User], kafkaEvents: Seq[FlowEvent]): RecoveryState =
    RecoveryState(
      step = recoveryStep(session),
      email = session.get("flow-recovery-email"),
      user = userOpt,
      token = session.get("flow-recovery-token"),
      tokenValid = session.get("flow-recovery-valid").map(_ == "true"),
      kafkaEvents = kafkaEvents
    )

  // --- Main page ---

  def showFlows() = Action.async { implicit request: Request[AnyContent] =>
    val session = request.session
    for
      users <- userRepository.list()
      checkoutUser <- session.get("flow-checkout-user").flatMap(_.toLongOption)
        .map(id => userRepository.findById(id))
        .getOrElse(Future.successful(None))
      recoveryUser <- session.get("flow-recovery-email")
        .map(email => userRepository.findByEmail(email))
        .getOrElse(Future.successful(None))
    yield
      val kafkaEvents = flowEventConsumer.consumedEvents
      val checkoutState = buildCheckoutState(session, checkoutUser, kafkaEvents)
      val recoveryState = buildRecoveryState(session, recoveryUser, kafkaEvents)
      val activeTab = request.getQueryString("tab").getOrElse("checkout")
      Ok(views.html.flows(users, checkoutState, recoveryState, activeTab))
  }

  // --- Checkout Flow ---

  def selectUser() = Action.async { implicit request: Request[AnyContent] =>
    selectUserForm.bindFromRequest().fold(
      _ => Future.successful(Redirect(routes.FlowController.showFlows())),
      form =>
        userRepository.findById(form.userId).map {
          case Some(user) =>
            Redirect(routes.FlowController.showFlows())
              .withSession(request.session
                + ("flow-checkout-step" -> "2")
                + ("flow-checkout-user" -> user.id.toString))
              .flashing("success" -> s"Selected user: ${user.name}")
          case None =>
            Redirect(routes.FlowController.showFlows())
              .flashing("error" -> "User not found")
        }
    )
  }

  def addToCart() = Action { implicit request: Request[AnyContent] =>
    addToCartForm.bindFromRequest().fold(
      _ => Redirect(routes.FlowController.showFlows())
        .flashing("error" -> "Invalid cart item"),
      form =>
        val existing = parseCartItems(request.session)
        val newItem = CartItem(form.name, form.category, form.unitPrice, form.quantity)
        val updated = existing :+ newItem
        Redirect(routes.FlowController.showFlows())
          .withSession(request.session
            + ("flow-checkout-cart" -> serializeCartItems(updated))
            + ("flow-checkout-step" -> "2"))
          .flashing("success" -> s"Added ${form.name} to cart")
    )
  }

  def advanceToStep(step: Int) = Action { implicit request: Request[AnyContent] =>
    val currentStep = checkoutStep(request.session)
    if step <= currentStep + 1 then
      Redirect(routes.FlowController.showFlows())
        .withSession(request.session + ("flow-checkout-step" -> step.toString))
    else
      Redirect(routes.FlowController.showFlows())
        .flashing("error" -> "Complete the current step first")
  }

  def applyCoupon() = Action.async { implicit request: Request[AnyContent] =>
    applyCouponForm.bindFromRequest().fold(
      _ => Future.successful(
        Redirect(routes.FlowController.showFlows())
          .flashing("error" -> "Please enter a coupon code")),
      form =>
        val cartItems = parseCartItems(request.session)
        val total = CartCalculator.grandTotal(cartItems)
        couponRepository.findByCode(form.code.trim.toUpperCase).map { couponOpt =>
          val result = CouponValidator.validate(form.code, couponOpt, total)
          result.discount match
            case Some(discount) =>
              Redirect(routes.FlowController.showFlows())
                .withSession(request.session
                  + ("flow-checkout-step" -> "4")
                  + ("flow-checkout-coupon" -> form.code.trim.toUpperCase)
                  + ("flow-checkout-discount" -> f"$discount%.2f"))
                .flashing("success" -> s"Coupon applied! Discount: $$${f"$discount%.2f"}")
            case None =>
              val errorMsg = result.steps.find(!_.passed).map(_.detail).getOrElse("Invalid coupon")
              Redirect(routes.FlowController.showFlows())
                .withSession(request.session + ("flow-checkout-step" -> "3"))
                .flashing("error" -> s"Coupon failed: $errorMsg")
        }
    )
  }

  def skipCoupon() = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.FlowController.showFlows())
      .withSession(request.session + ("flow-checkout-step" -> "4"))
      .flashing("success" -> "Skipped coupon step")
  }

  def placeOrder() = Action.async { implicit request: Request[AnyContent] =>
    val session = request.session
    val cartItems = parseCartItems(session)
    val userIdOpt = session.get("flow-checkout-user").flatMap(_.toLongOption)

    userIdOpt match
      case None =>
        Future.successful(Redirect(routes.FlowController.showFlows())
          .flashing("error" -> "No user selected"))
      case Some(userId) =>
        userRepository.findById(userId).flatMap {
          case None =>
            Future.successful(Redirect(routes.FlowController.showFlows())
              .flashing("error" -> "User not found"))
          case Some(user) =>
            if cartItems.isEmpty then
              Future.successful(Redirect(routes.FlowController.showFlows())
                .flashing("error" -> "Cart is empty"))
            else
              val itemSummary = cartItems.map(i => s"${i.name} x${i.quantity}").mkString(", ")
              val totalQty = cartItems.map(_.quantity).sum
              orderRepository.create(user.name, itemSummary, totalQty).map { order =>
                Redirect(routes.FlowController.showFlows())
                  .withSession(session
                    + ("flow-checkout-step" -> "5")
                    + ("flow-checkout-order" -> order.id.toString))
                  .flashing("success" -> s"Order #${order.id} placed!")
              }
        }
  }

  def confirmCheckout() = Action.async { implicit request: Request[AnyContent] =>
    val session = request.session
    val userIdOpt = session.get("flow-checkout-user").flatMap(_.toLongOption)
    val orderIdOpt = session.get("flow-checkout-order").flatMap(_.toLongOption)

    (userIdOpt, orderIdOpt) match
      case (Some(userId), Some(orderId)) =>
        userRepository.findById(userId).map {
          case Some(user) =>
            // Build notification and run through pipeline
            val notification = Notification(
              channel = "email",
              recipient = user.email,
              message = s"Order #$orderId confirmed! Thank you for your purchase, ${user.name}."
            )
            val (_, routed) = NotificationPipeline.processWithSteps(notification)

            // Publish Kafka event
            val event = FlowEvent("CheckoutEmail", Instant.now().toString, s"Order #$orderId for ${user.name} (${user.email}): $routed")
            kafkaProducer.publish(checkoutTopic, orderId.toString, Json.toJson(event).toString()).recover {
              case ex => logger.error(s"Failed to publish checkout email event", ex)
            }

            Redirect(routes.FlowController.showFlows())
              .withSession(session + ("flow-checkout-step" -> "6"))
              .flashing("success" -> s"Confirmation email sent! Pipeline result: $routed")
          case None =>
            Redirect(routes.FlowController.showFlows())
              .flashing("error" -> "User not found")
        }
      case _ =>
        Future.successful(Redirect(routes.FlowController.showFlows())
          .flashing("error" -> "Missing order or user information"))
  }

  def resetCheckout() = Action { implicit request: Request[AnyContent] =>
    val cleaned = request.session.data.filterNot(_._1.startsWith("flow-checkout-"))
    Redirect(routes.FlowController.showFlows())
      .withSession(Session(cleaned))
      .flashing("success" -> "Checkout flow reset")
  }

  // --- Recovery Flow ---

  def enterEmail() = Action.async { implicit request: Request[AnyContent] =>
    enterEmailForm.bindFromRequest().fold(
      _ => Future.successful(
        Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
          .flashing("error" -> "Please enter an email")),
      form =>
        userRepository.findByEmail(form.email).map {
          case Some(user) =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .withSession(request.session
                + ("flow-recovery-step" -> "2")
                + ("flow-recovery-email" -> form.email))
              .flashing("success" -> s"Found user: ${user.name}")
          case None =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .withSession(request.session + ("flow-recovery-step" -> "1"))
              .flashing("error" -> s"No user found with email: ${form.email}")
        }
    )
  }

  def generateToken() = Action { implicit request: Request[AnyContent] =>
    val token = UUID.randomUUID().toString.take(8).toUpperCase
    Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
      .withSession(request.session
        + ("flow-recovery-step" -> "3")
        + ("flow-recovery-token" -> token))
      .flashing("success" -> s"Reset token generated: $token")
  }

  def sendResetEmail() = Action.async { implicit request: Request[AnyContent] =>
    val session = request.session
    val emailOpt = session.get("flow-recovery-email")
    val tokenOpt = session.get("flow-recovery-token")

    (emailOpt, tokenOpt) match
      case (Some(email), Some(token)) =>
        userRepository.findByEmail(email).map {
          case Some(user) =>
            // Build notification and run through pipeline
            val notification = Notification(
              channel = "email",
              recipient = email,
              message = s"Password reset for ${user.name}. Use token: $token to reset your password."
            )
            val (_, routed) = NotificationPipeline.processWithSteps(notification)

            // Publish Kafka event
            val event = FlowEvent("PasswordReset", Instant.now().toString, s"Reset email for ${user.name} ($email) with token $token: $routed")
            kafkaProducer.publish(resetTopic, email, Json.toJson(event).toString()).recover {
              case ex => logger.error(s"Failed to publish password reset event", ex)
            }

            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .withSession(session + ("flow-recovery-step" -> "4"))
              .flashing("success" -> s"Reset email sent! Pipeline result: $routed")
          case None =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .flashing("error" -> "User not found")
        }
      case _ =>
        Future.successful(Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
          .flashing("error" -> "Missing email or token"))
  }

  def confirmReset() = Action { implicit request: Request[AnyContent] =>
    confirmResetForm.bindFromRequest().fold(
      _ => Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
        .flashing("error" -> "Please fill in all fields"),
      form =>
        val storedToken = request.session.get("flow-recovery-token")
        storedToken match
          case Some(token) if token == form.token =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .withSession(request.session
                + ("flow-recovery-step" -> "5")
                + ("flow-recovery-valid" -> "true"))
              .flashing("success" -> "Password reset successful!")
          case Some(_) =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .withSession(request.session + ("flow-recovery-valid" -> "false"))
              .flashing("error" -> "Invalid token! Tokens don't match.")
          case None =>
            Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
              .flashing("error" -> "No token found. Please generate a token first.")
    )
  }

  def resetRecovery() = Action { implicit request: Request[AnyContent] =>
    val cleaned = request.session.data.filterNot(_._1.startsWith("flow-recovery-"))
    Redirect(routes.FlowController.showFlows().url + "?tab=recovery")
      .withSession(Session(cleaned))
      .flashing("success" -> "Recovery flow reset")
  }
