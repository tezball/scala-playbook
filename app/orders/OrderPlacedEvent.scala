package orders

import play.api.libs.json.{Format, Json}

import java.time.Instant

case class OrderPayload(id: Long, userName: String, itemName: String, quantity: Int)

object OrderPayload:
  given Format[OrderPayload] = Json.format[OrderPayload]

case class OrderPlacedEvent(eventType: String, timestamp: String, order: OrderPayload)

object OrderPlacedEvent:
  given Format[OrderPlacedEvent] = Json.format[OrderPlacedEvent]

  def fromOrder(order: Order): OrderPlacedEvent =
    OrderPlacedEvent(
      eventType = "OrderPlaced",
      timestamp = Instant.now().toString,
      order = OrderPayload(order.id, order.userName, order.itemName, order.quantity)
    )
