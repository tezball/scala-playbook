package orders

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import play.api.inject.ApplicationLifecycle
import play.api.libs.json.Json
import play.api.{Configuration, Logging}

import java.time.Duration
import java.util.{Collections, Properties}
import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.*

@Singleton
class OrderEventConsumer @Inject()(
  config: Configuration,
  lifecycle: ApplicationLifecycle
)(using ec: ExecutionContext) extends Logging:

  private val bootstrapServers = config.get[String]("kafka.bootstrap-servers")
  private val topic = config.get[String]("kafka.topics.order-placed")

  private val events: ListBuffer[OrderPlacedEvent] = ListBuffer.empty
  @volatile private var running = true

  private val consumer: KafkaConsumer[String, String] =
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "scala-playbook-order-events")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    new KafkaConsumer[String, String](props)

  private val pollThread = new Thread(() =>
    consumer.subscribe(Collections.singletonList(topic))
    while running do
      try
        val records = consumer.poll(Duration.ofMillis(500))
        for record <- records.asScala do
          Json.parse(record.value()).validate[OrderPlacedEvent].fold(
            errors => logger.warn(s"Failed to parse OrderPlaced record: $errors"),
            event =>
              events.synchronized { events += event }
              logger.info(s"Consumed OrderPlaced event for order ${event.order.id}")
          )
      catch
        case _: org.apache.kafka.common.errors.WakeupException => ()
        case ex: Exception => logger.error("Error polling order events", ex)
  , "order-event-consumer")
  pollThread.setDaemon(true)
  pollThread.start()

  lifecycle.addStopHook { () =>
    Future.successful {
      running = false
      consumer.wakeup()
      pollThread.join(5000)
      consumer.close()
    }
  }

  def consumedEvents: Seq[OrderPlacedEvent] =
    events.synchronized { events.toSeq }
