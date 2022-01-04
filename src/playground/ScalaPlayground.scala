package playground

object ScalaPlayground {
  def main(args: Array[String]): Unit = {

    println("I love Scala")

    val a=1
    val b=2

    def ab(i:Int)= i match {
      case a =>"one"
      case b =>"two"
    }
    println(ab(1))
    println(ab(2))
  }
}
