package lectures.basics

object SparkBootstrap {
  val masterKey: String = "spark.master"
  val checkpointKey: String = "spark.checkpoint.dir"
  val safeClusters: Seq[String] = Seq("local")
}
object test extends App{
  println(Some("local").filter(SparkBootstrap.safeClusters.contains))
  println(SparkBootstrap.safeClusters.contains("local"))
}
