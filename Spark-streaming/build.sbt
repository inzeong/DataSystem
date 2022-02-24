name := "streaming"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.2" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "3.1.2" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.1.2"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.5.0"

// libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.2"
