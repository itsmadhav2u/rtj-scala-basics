package playground

import scala.util.{Failure, Success, Try}

object test extends App {

  val bool: Try[Int] = Try {
    val b = false
    b
    12 / 0
  }

  bool match {
    case Success(b) => println(b)
    case Failure(b) => //print(b)
  }

  val str: Try[String] = for {
    _ <- Try {
      val b = false
      b
      //12/0
    }
  } yield "success22"

  println(str)
  for (e <- str) {
    println(e)
  }

  val number = 20
  val num = 10
  val result = Try(number / 0 match { case 10 => "10" case _ => None }).getOrElse("unknown")
  val res1 = Try(number match { case 10 => "10" case _ => None }).getOrElse("unknown")
  val res2 = Try(num match { case 10 => "10" case _ => None }).getOrElse("unknown")
  println(result)
  println(res1)
  println(res2)

}
