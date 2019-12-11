# Kafka, Scala clients

## To start
- Run ZooKeeper and Kafka server
https://kafka.apache.org/quickstart
- create a topic
```$xslt
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```
- check the topic on both consumer and producer
- for consumer check group 
- list groups
```$xslt
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```



#### Notes
- Any message produced by producer will be consumed by consumer. 
So if you don't see anything on consumer side it doesn't mean the consumer hasn't consumed the message. 
- https://stackoverflow.com/questions/44760866/simple-kafka-consumer-not-receiving-messages

#### Content
- Monix 
    - observable pattern
    - basically both producer and consumer will quit from main thread w/o any blocking sleep. For consumer the sleep needs to be long