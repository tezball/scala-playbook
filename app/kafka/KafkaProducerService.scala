package kafka

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import play.api.inject.ApplicationLifecycle
import play.api.{Configuration, Logging}

import java.util.Properties
import javax.inject.{Inject, Singleton}
import scala.concurrent.{Future, Promise}

@Singleton
class KafkaProducerService @Inject()(
  config: Configuration,
  lifecycle: ApplicationLifecycle
) extends Logging:

  private val bootstrapServers = config.get[String]("kafka.bootstrap-servers")

  private val producer: KafkaProducer[String, String] =
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    new KafkaProducer[String, String](props)

  lifecycle.addStopHook { () =>
    Future.successful(producer.close())
  }

  def publish(topic: String, key: String, value: String): Future[RecordMetadata] =
    val record = new ProducerRecord[String, String](topic, key, value)
    val promise = Promise[RecordMetadata]()
    producer.send(record, (metadata: RecordMetadata, exception: Exception) =>
      if exception != null then
        logger.error(s"Failed to publish to $topic with key=$key", exception)
        promise.failure(exception)
      else
        logger.info(s"Published to ${metadata.topic()}:${metadata.partition()}@${metadata.offset()}")
        promise.success(metadata)
    )
    promise.future
