package workflow

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WorkflowController @Inject()(
  val controllerComponents: ControllerComponents,
  workflowRepository: WorkflowRepository
)(using ec: ExecutionContext) extends BaseController:

  private val orderForm = Form(
    mapping(
      "description" -> nonEmptyText
    )(WorkflowOrderForm.apply)(wf => Some(wf.description))
  )

  private val transitionForm = Form(
    mapping(
      "orderId" -> longNumber,
      "action" -> nonEmptyText,
      "data" -> text
    )(TransitionForm.apply)(tf => Some(tf.orderId, tf.action, tf.data))
  )

  def showBoard() = Action.async { implicit request: Request[AnyContent] =>
    workflowRepository.list().map { orders =>
      val enriched = orders.map { order =>
        val status = WorkflowEngine.parseStatus(order.status, order.statusData)
        (order, status, WorkflowEngine.nextActions(status), WorkflowEngine.statusDetail(status))
      }
      Ok(views.html.workflowBoard(orderForm, enriched, request.flash.get("error")))
    }
  }

  def createOrder() = Action.async { implicit request: Request[AnyContent] =>
    orderForm.bindFromRequest().fold(
      _ => Future.successful(Redirect(routes.WorkflowController.showBoard())),
      data =>
        workflowRepository.create(data.description).map { _ =>
          Redirect(routes.WorkflowController.showBoard()).flashing("success" -> "Order created!")
        }
    )
  }

  def transition() = Action.async { implicit request: Request[AnyContent] =>
    transitionForm.bindFromRequest().fold(
      _ => Future.successful(Redirect(routes.WorkflowController.showBoard())),
      data =>
        workflowRepository.findById(data.orderId).flatMap {
          case None => Future.successful(
            Redirect(routes.WorkflowController.showBoard()).flashing("error" -> "Order not found")
          )
          case Some(order) =>
            val currentStatus = WorkflowEngine.parseStatus(order.status, order.statusData)
            val extraData = data.data.split(",").flatMap { kv =>
              kv.split("=", 2) match
                case Array(k, v) => Some(k.trim -> v.trim)
                case _ => None
            }.toMap
            WorkflowEngine.transition(currentStatus, data.action, extraData) match
              case Right(newStatus) =>
                val (statusStr, statusData) = WorkflowEngine.serializeStatus(newStatus)
                workflowRepository.updateStatus(data.orderId, statusStr, statusData).map { _ =>
                  Redirect(routes.WorkflowController.showBoard()).flashing("success" -> s"Transitioned to $statusStr")
                }
              case Left(error) =>
                Future.successful(
                  Redirect(routes.WorkflowController.showBoard()).flashing("error" -> error)
                )
        }
    )
  }
