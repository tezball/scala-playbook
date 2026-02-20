package users

import play.api.libs.json.{Format, Json}

import java.time.Instant

case class UserPayload(id: Long, name: String, email: String, phone: String)

object UserPayload:
  given Format[UserPayload] = Json.format[UserPayload]

case class UserCreatedEvent(eventType: String, timestamp: String, user: UserPayload)

object UserCreatedEvent:
  given Format[UserCreatedEvent] = Json.format[UserCreatedEvent]

  def fromUser(user: User): UserCreatedEvent =
    UserCreatedEvent(
      eventType = "UserCreated",
      timestamp = Instant.now().toString,
      user = UserPayload(user.id, user.name, user.email, user.phone)
    )
