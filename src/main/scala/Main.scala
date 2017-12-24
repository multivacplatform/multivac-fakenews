import com.typesafe.config.ConfigFactory
import data_processing.DataFrame_Builder

object Main {
  def main(args: Array[String]) {
    val env =args(0)
    val spark = SessionBuilder.buildSession(env)
    val hoaxJSONFilePath = ConfigFactory.load().getString("data.decodexHoax")
    val debunkJSONFilePath = ConfigFactory.load().getString("data.decodexHoax")


    val (debunkDF, hoaxDF) = DataFrame_Builder.loadData(
      env, spark, debunkJSONFilePath, hoaxJSONFilePath, "json"
    )

  }
}