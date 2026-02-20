package orders

import kafka.KafkaProducerService
import play.api.libs.json.Json
import play.api.{Configuration, Logging}

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderController @Inject()(
  val controllerComponents: ControllerComponents,
  orderRepository: OrderRepository,
  kafkaProducer: KafkaProducerService,
  kafkaConsumer: OrderEventConsumer,
  config: Configuration
)(using ec: ExecutionContext) extends BaseController with Logging:

  private val orderPlacedTopic = config.get[String]("kafka.topics.order-placed")

  private val orderForm = Form(
    mapping(
      "userName" -> nonEmptyText,
      "itemName" -> nonEmptyText,
      "quantity" -> number(min = 1)
    )(OrderForm.apply)(of => Some(of.userName, of.itemName, of.quantity))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    orderRepository.list().map { orders =>
      Ok(views.html.orderForm(orderForm, orders, kafkaConsumer.consumedEvents))
    }
  }

  def placeOrder() = Action.async { implicit request: Request[AnyContent] =>
    orderForm.bindFromRequest().fold(
      formWithErrors =>
        orderRepository.list().map { orders =>
          BadRequest(views.html.orderForm(formWithErrors, orders, kafkaConsumer.consumedEvents))
        },
      orderData =>
        orderRepository.create(orderData.userName, orderData.itemName, orderData.quantity).map { order =>
          val event = OrderPlacedEvent.fromOrder(order)
          kafkaProducer.publish(orderPlacedTopic, order.id.toString, Json.toJson(event).toString()).recover {
            case ex => logger.error(s"Failed to publish Kafka event for order ${order.id}", ex)
          }
          Redirect(routes.OrderController.showForm()).flashing("success" -> "Order placed successfully!")
        }
    )
  }
