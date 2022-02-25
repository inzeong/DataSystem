import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.sql._
import org.apache.spark.sql.streaming._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Consumer {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Consumer").getOrCreate()

    import spark.implicits._

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("kafka.max.partition.fetch.bytes", "1073741824")
      .option("subscribe", "house")
      .option("failOnDataLoss", "false")
      .load()

    val jsonDf = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")

    val value_struct = new StructType()
      .add("prediction", DataTypes.StringType)
      .add("load", DataTypes.IntegerType)
      .add("date", DataTypes.StringType)
      .add("home_num", DataTypes.IntegerType)
      .add("familyID", DataTypes.StringType)
      .add("receivingAccumulatedActiveEnergy", DataTypes.IntegerType)

    val textNestedDf = jsonDf.select(
      from_json($"value", value_struct).as("value")
    )

    val resultDf = textNestedDf.selectExpr(
      "value.prediction",
      "value.load",
      "value.date",
      "value.home_num",
      "value.familyID",
      "value.receivingAccumulatedActiveEnergy"
    )

    resultDf.printSchema()

    // // hdfs에 입력
    val h_query = resultDf.writeStream
      .format("parquet")
      .option(
        "checkpointLocation",
        "hdfs://localhost:9000/user/jungin/data/house_data/checkpoint"
      )
      .trigger(Trigger.ProcessingTime("10 seconds"))
      .option("path", "hdfs://localhost:9000/user/jungin/data/house_data")
      .start()

    // 콘솔에 출력
    val p_query = resultDf.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", "false")
      .start()

    spark.streams.awaitAnyTermination()
  }
}
