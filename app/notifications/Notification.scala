package notifications

import java.time.Instant

case class Notification(channel: String, recipient: String, message: String, metadata: Map[String, String] = Map.empty)

case class NotificationForm(channel: String, recipient: String, message: String)

case class PipelineStep(name: String, before: Notification, after: Notification)
