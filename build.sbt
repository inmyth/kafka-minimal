name := "kafka-minimal"

version := "0.1"

scalaVersion := "2.13.1"

// https://mvnrepository.com/artifact/io.monix/monix-kafka-10
libraryDependencies += "io.monix" %% "monix-kafka-1x" % "1.0.0-RC5"
dependencyOverrides += "org.apache.kafka" % "kafka" % "2.3.1"
