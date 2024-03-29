package me.mbcu.kafka.minimal.default

import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer

import java.util.Properties

import scala.collection.JavaConverters._


object Consumer extends App{

  consumeFromKafka("my-topic")

  def consumeFromKafka(topic: String) = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("auto.offset.reset", "latest")

    props.put("group.id", "console-consumer-32727")

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Arrays.asList(topic))

    while (true) {

      val record = consumer.poll(1000).asScala

      for (data <- record.iterator){
        println(data.value())
      }


    }

  }
}
