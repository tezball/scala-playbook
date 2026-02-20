package flows

import cart.CartItem
import play.api.libs.json.{Format, Json}
import users.User

case class FlowEvent(eventType: String, timestamp: String, detail: String)

object FlowEvent:
  given Format[FlowEvent] = Json.format[FlowEvent]

// Checkout session state reconstructed from session cookies
case class CheckoutState(
  step: Int,
  selectedUser: Option[User],
  cartItems: Seq[CartItem],
  couponDiscount: Option[Double],
  couponCode: Option[String],
  orderId: Option[Long],
  kafkaEvents: Seq[FlowEvent]
)

// Recovery session state
case class RecoveryState(
  step: Int,
  email: Option[String],
  user: Option[User],
  token: Option[String],
  tokenValid: Option[Boolean],
  kafkaEvents: Seq[FlowEvent]
)

// Form bindings
case class SelectUserForm(userId: Long)
case class AddToCartForm(name: String, category: String, unitPrice: Double, quantity: Int)
case class ApplyCouponForm(code: String)
case class EnterEmailForm(email: String)
case class ConfirmResetForm(token: String, newPassword: String)
