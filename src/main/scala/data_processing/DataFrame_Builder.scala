package data_processing

import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object DataFrame_Builder {

  def loadData(env: String,
               spark: SparkSession,
               debunkFilePath: String,
               hoaxFilePath: String,
               filesFormate: String
              ): (DataFrame, DataFrame) = {


    val debunkDF = spark.read
      .format(filesFormate)
      .load(debunkFilePath)

    val hoaxDF = spark.read
      .format(filesFormate)
      .load(hoaxFilePath)

    (debunkDF, hoaxDF)
  }
}
