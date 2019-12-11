package me.mbcu.kafka.minimal.default
import java.util.Properties

import org.apache.kafka.clients.producer._
object Producer extends App{



  writeToKafka("my-topic")

  def writeToKafka(topic: String): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val record = new ProducerRecord[String, String](topic, "key", "abccba")

    producer.send(record)

    producer.close()

  }

}
