package users

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents,
  userRepository: UserRepository
)(using ec: ExecutionContext) extends BaseController:

  private val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "phone" -> nonEmptyText
    )(UserForm.apply)(uf => Some(uf.name, uf.email, uf.phone))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    userRepository.list().map { users =>
      Ok(views.html.userForm(userForm, users))
    }
  }

  def saveUser() = Action.async { implicit request: Request[AnyContent] =>
    userForm.bindFromRequest().fold(
      formWithErrors =>
        userRepository.list().map { users =>
          BadRequest(views.html.userForm(formWithErrors, users))
        },
      userData =>
        userRepository.create(userData.name, userData.email, userData.phone).map { _ =>
          Redirect(routes.UserController.showForm()).flashing("success" -> "User saved successfully!")
        }
    )
  }
