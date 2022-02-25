spark-submit  --class $1 --master spark://localhost:7077 --conf spark.executor.memory=8g --conf spark.cores.max=8 --jars `ls lib/* | xargs echo | tr ' ' ,`  ./target/scala-2.12/streaming_2.12-0.1.jar
