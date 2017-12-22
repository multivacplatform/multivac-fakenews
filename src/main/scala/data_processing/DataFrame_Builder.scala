package data_processing

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._


object DataFrame_Builder {

  def loadData(env: String,
               spark: SparkSession,
               debunkFilePath: String,
               hoaxFilePath: String,
               filesFormate: String
              ): (DataFrame, DataFrame) = {


    val debunkDF = spark.read
      .format(filesFormate)
      .option("header", "true")
      .load(debunkFilePath)

    val hoaxDF = spark.read
      .format(filesFormate)
      .option("header", "true")
      .load(hoaxFilePath)

    (debunkDF, hoaxDF)
  }
}
