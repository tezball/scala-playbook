package users

import kafka.KafkaProducerService
import play.api.libs.json.Json
import play.api.{Configuration, Logging}

import javax.inject.*
import play.api.data.Form
import play.api.data.Forms.*
import play.api.mvc.*

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents,
  userRepository: UserRepository,
  kafkaProducer: KafkaProducerService,
  kafkaConsumer: UserEventConsumer,
  config: Configuration
)(using ec: ExecutionContext) extends BaseController with Logging:

  private val userCreatedTopic = config.get[String]("kafka.topics.user-created")

  private val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "phone" -> nonEmptyText
    )(UserForm.apply)(uf => Some(uf.name, uf.email, uf.phone))
  )

  def showForm() = Action.async { implicit request: Request[AnyContent] =>
    userRepository.list().map { users =>
      Ok(views.html.userForm(userForm, users, kafkaConsumer.consumedEvents))
    }
  }

  def saveUser() = Action.async { implicit request: Request[AnyContent] =>
    userForm.bindFromRequest().fold(
      formWithErrors =>
        userRepository.list().map { users =>
          BadRequest(views.html.userForm(formWithErrors, users, kafkaConsumer.consumedEvents))
        },
      userData =>
        userRepository.create(userData.name, userData.email, userData.phone).map { user =>
          val event = UserCreatedEvent.fromUser(user)
          kafkaProducer.publish(userCreatedTopic, user.id.toString, Json.toJson(event).toString()).recover {
            case ex => logger.error(s"Failed to publish Kafka event for user ${user.id}", ex)
          }
          Redirect(routes.UserController.showForm()).flashing("success" -> "User saved successfully!")
        }
    )
  }
