package workflow

import java.time.Instant

// ADT for order states - each state can carry different data
sealed trait OrderStatus
case object Pending extends OrderStatus
case class Approved(approvedBy: String, approvedAt: Instant) extends OrderStatus
case class Shipped(trackingNumber: String, carrier: String) extends OrderStatus
case class Delivered(deliveredAt: Instant, signature: String) extends OrderStatus
case class Cancelled(reason: String, cancelledAt: Instant) extends OrderStatus

object WorkflowEngine:

  // State machine - exhaustive matching enforces handling every state
  def nextActions(status: OrderStatus): List[String] = status match
    case Pending        => List("approve", "cancel")
    case Approved(_, _) => List("ship", "cancel")
    case Shipped(_, _)  => List("deliver", "cancel")
    case Delivered(_, _) => List() // terminal state
    case Cancelled(_, _) => List() // terminal state

  // Transition validation
  def transition(current: OrderStatus, action: String, data: Map[String, String]): Either[String, OrderStatus] =
    (current, action) match
      case (Pending, "approve")       => Right(Approved(data.getOrElse("by", "system"), Instant.now))
      case (Approved(_, _), "ship")   => Right(Shipped(data.getOrElse("tracking", "N/A"), data.getOrElse("carrier", "Standard")))
      case (Shipped(_, _), "deliver") => Right(Delivered(Instant.now, data.getOrElse("signature", "unsigned")))
      case (_, "cancel")              => Right(Cancelled(data.getOrElse("reason", "No reason"), Instant.now))
      case (state, act)               => Left(s"Cannot '$act' from state ${statusName(state)}")

  def statusName(status: OrderStatus): String = status match
    case Pending        => "Pending"
    case Approved(_, _) => "Approved"
    case Shipped(_, _)  => "Shipped"
    case Delivered(_, _) => "Delivered"
    case Cancelled(_, _) => "Cancelled"

  def statusDetail(status: OrderStatus): String = status match
    case Pending               => ""
    case Approved(by, at)      => s"by $by at $at"
    case Shipped(tracking, c)  => s"tracking: $tracking, carrier: $c"
    case Delivered(at, sig)    => s"at $at, signed: $sig"
    case Cancelled(reason, at) => s"reason: $reason at $at"

  // Parse status from DB string + data
  def parseStatus(status: String, data: String): OrderStatus =
    val parts = data.split("\\|", -1)
    status match
      case "Pending"   => Pending
      case "Approved"  => Approved(parts.lift(0).getOrElse(""), Instant.parse(parts.lift(1).getOrElse(Instant.now.toString)))
      case "Shipped"   => Shipped(parts.lift(0).getOrElse(""), parts.lift(1).getOrElse(""))
      case "Delivered" => Delivered(Instant.parse(parts.lift(0).getOrElse(Instant.now.toString)), parts.lift(1).getOrElse(""))
      case "Cancelled" => Cancelled(parts.lift(0).getOrElse(""), Instant.parse(parts.lift(1).getOrElse(Instant.now.toString)))
      case _           => Pending

  // Serialize status to DB
  def serializeStatus(status: OrderStatus): (String, String) = status match
    case Pending               => ("Pending", "")
    case Approved(by, at)      => ("Approved", s"$by|$at")
    case Shipped(tracking, c)  => ("Shipped", s"$tracking|$c")
    case Delivered(at, sig)    => ("Delivered", s"$at|$sig")
    case Cancelled(reason, at) => ("Cancelled", s"$reason|$at")
