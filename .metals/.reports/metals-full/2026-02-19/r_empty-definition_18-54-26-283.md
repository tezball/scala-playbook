error id: file://<WORKSPACE>/app/controllers/UserController.scala:
file://<WORKSPACE>/app/controllers/UserController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject.
	 -javax/inject#
	 -javax/inject().
	 -play/api/data/Forms.
	 -play/api/data/Forms#
	 -play/api/data/Forms().
	 -play/api/mvc.
	 -play/api/mvc#
	 -play/api/mvc().
	 -scala/Predef.
	 -scala/Predef#
	 -scala/Predef().
offset: 1044
uri: file://<WORKSPACE>/app/controllers/UserController.scala
text:
```scala
package controllers

import events.UserCreatedEvent
import play.api.Logging

import javax.inject.*
import models.UserForm
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*
import repositories.UserRepository
import services.KafkaProducerService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents,
  userRepository: UserRepository,
  kafkaProducer: KafkaProducerService
)(using ec: ExecutionContext) extends BaseController with Logging:

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
    userForm.bindFromRequest().fold@@(
      formWithErrors =>
        userRepository.list().map { users =>
          BadRequest(views.html.userForm(formWithErrors, users))
        },
      userData =>
        userRepository.create(userData.name, userData.email, userData.phone).map { user =>
          val event = UserCreatedEvent.fromUser(user)
          kafkaProducer.publishUserCreated(event).recover {
            case ex => logger.error(s"Failed to publish Kafka event for user ${user.id}", ex)
          }
          Redirect(routes.UserController.showForm()).flashing("success" -> "User saved successfully!")
        }
    )
  }

```


#### Short summary: 

empty definition using pc, found symbol in pc: 