package lectures.fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super failure"))
  println(aSuccess)
  println(aFailure)

  // Try objects via the apply method
  def unsafeMethod:String = throw new RuntimeException("No string")
  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) //false for Failure and true for Success

  def backupMethod:String = "NOC"
  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod:Try[String] = Failure(new RuntimeException)
  def betterBackupMethod:Try[String] = Success("NOC")
  val betterFallback = betterUnsafeMethod orElse betterBackupMethod
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_*2)) //Success(6)
  println(aSuccess.flatMap(x => Success(x*3))) //Success(9)
  println(aSuccess.filter(_ >= 10)) //Failure(java.util.NoSuchElementException: Predicate does not hold for 3)
  // => for-comprehensions

  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection{
    def get(url :String):String ={
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>....</html>"
      else throw new RuntimeException("Connection fail")
    }

    def getSafe(url:String):Try[String] = Try(get(url))
  }

  object HttpService{
    val random = new Random(System.nanoTime())
    def getConnection(host:String, port:String):Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Failed to get connection")
    }

    def getSafeConnection(host:String, port:String):Try[Connection] = Try(getConnection(host,port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host,port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(host,port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension version
  for{
    connection <- HttpService.getSafeConnection(host,port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  /*
   try {
     val connection = HttpService.getConnection(host, port)
     try {
       val page = connection.get("/home")
       renderHTML(page)
     } catch (some other exception) {
     }
   } catch (exception) {
   }
  */

}
