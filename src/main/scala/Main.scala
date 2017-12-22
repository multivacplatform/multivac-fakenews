import data_processing.DataFrame_Builder

object Main {
  def main(args: Array[String]) {
    val env =args(0)
    val spark = SessionBuilder.buildSession(env)

    val (debunkDF, hoaxDF) = DataFrame_Builder.loadData(env, spark, "./data/decodex/debunk_lines.json", "./data/decodex/hoax_lines.json", "json")


  }
}