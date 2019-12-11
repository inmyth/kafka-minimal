package me.mbcu.kafka.minimal.monix

import monix.execution.Ack
import monix.execution.Ack.Continue
import monix.kafka._
import monix.reactive.Observer

object Consumer extends App{
  import monix.execution.Scheduler.Implicits.global

  val consumerCfg = KafkaConsumerConfig.default.copy(
    bootstrapServers = List("127.0.0.1:9092"),
    groupId = "console-consumer-32727"
    // you can use this settings for At Most Once semantics:
    // observableCommitOrder = ObservableCommitOrder.BeforeAck
  )

  val observable =
    KafkaConsumerObservable[String,String](consumerCfg, List("my-topic"))
      .take(10000)
      .map(_.value())

  val pConsumer: monix.reactive.Consumer[String,String] =
    monix.reactive.Consumer.create[String,String] { (scheduler, cancelable, callback) =>
      new Observer.Sync[String] {

        def onNext(elem: String): Ack = {
          println(elem)
          Continue
        }

        def onComplete(): Unit = {
          // We are done so we can signal the final result
          println(s"O is complete")
        }

        def onError(ex: Throwable): Unit = {
          // Error happened, so we signal the error
          callback.onError(ex)
        }
      }
    }
  observable.consumeWith(pConsumer).runToFuture.foreach(println)
  Thread.sleep(200000)

}
