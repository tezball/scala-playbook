package notifications

import java.time.Instant
import scala.util.chaining.*

object NotificationPipeline:

  // Individual pipeline stages as functions
  val sanitize: Notification => Notification = n =>
    n.copy(message = n.message.replaceAll("<[^>]*>", "").trim)

  val addTimestamp: Notification => Notification = n =>
    n.copy(metadata = n.metadata + ("sentAt" -> Instant.now.toString))

  val truncate: Notification => Notification = n =>
    if n.message.length > 100 then n.copy(message = n.message.take(97) + "...") else n

  // andThen - compose left to right
  val pipeline: Notification => Notification = sanitize
    .andThen(addTimestamp)
    .andThen(truncate)

  // PartialFunction for routing by channel
  val emailHandler: PartialFunction[Notification, String] =
    case n if n.channel == "email" => s"Sending email to ${n.recipient}: ${n.message}"

  val smsHandler: PartialFunction[Notification, String] =
    case n if n.channel == "sms" => s"Sending SMS to ${n.recipient}: ${n.message.take(160)}"

  val pushHandler: PartialFunction[Notification, String] =
    case n if n.channel == "push" => s"Sending push notification to ${n.recipient}: ${n.message.take(80)}"

  val route: PartialFunction[Notification, String] =
    emailHandler
      .orElse(smsHandler)
      .orElse(pushHandler)
      .orElse { case n => s"Unknown channel '${n.channel}' for ${n.recipient}" }

  // Curried function for reusable formatters
  def formatForChannel(maxLength: Int)(prefix: String)(n: Notification): Notification =
    n.copy(message = s"$prefix ${n.message}".take(maxLength))

  val formatForSms: Notification => Notification = formatForChannel(160)("[SMS]")
  val formatForEmail: Notification => Notification = formatForChannel(5000)("[Email]")
  val formatForPush: Notification => Notification = formatForChannel(80)("[Push]")

  // Process with step-by-step tracking
  def processWithSteps(input: Notification): (Seq[PipelineStep], String) =
    var current = input
    val steps = scala.collection.mutable.ListBuffer[PipelineStep]()

    // Step 1: sanitize
    val afterSanitize = sanitize(current)
    steps += PipelineStep("sanitize", current, afterSanitize)
    current = afterSanitize

    // Step 2: addTimestamp
    val afterTimestamp = addTimestamp(current)
    steps += PipelineStep("addTimestamp", current, afterTimestamp)
    current = afterTimestamp

    // Step 3: truncate
    val afterTruncate = truncate(current)
    steps += PipelineStep("truncate", current, afterTruncate)
    current = afterTruncate

    // Step 4: channel-specific formatting (curried function)
    val formatter = current.channel match
      case "sms"  => formatForSms
      case "push" => formatForPush
      case _      => formatForEmail

    val afterFormat = formatter(current)
    steps += PipelineStep(s"formatForChannel(${current.channel})", current, afterFormat)
    current = afterFormat

    // Step 5: route (PartialFunction)
    val routed = route(current)

    (steps.toSeq, routed)
