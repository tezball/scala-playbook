package notifications

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

@Singleton
class NotificationController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController:

  private val notificationForm = Form(
    mapping(
      "channel" -> nonEmptyText,
      "recipient" -> nonEmptyText,
      "message" -> nonEmptyText
    )(NotificationForm.apply)(nf => Some(nf.channel, nf.recipient, nf.message))
  )

  def showForm() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.notificationPipeline(notificationForm, None))
  }

  def sendNotification() = Action { implicit request: Request[AnyContent] =>
    notificationForm.bindFromRequest().fold(
      formWithErrors =>
        BadRequest(views.html.notificationPipeline(formWithErrors, None)),
      data =>
        val notification = Notification(data.channel, data.recipient, data.message)
        val (steps, routed) = NotificationPipeline.processWithSteps(notification)
        Ok(views.html.notificationPipeline(notificationForm.fill(data), Some((steps, routed))))
    )
  }
