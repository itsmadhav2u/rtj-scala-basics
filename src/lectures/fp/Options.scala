package lectures.fp

import java.util.Random

object Options extends App{

  val myFirstOption:Option[Int] = Some(4)
  val noOption:Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod()) // WRONG bcz it is like Some(null). Some should always have a valid value
  val result = Option(unsafeMethod())  //Some or None
  //apply method from companion object Option will take care to build Some or None depending on the value

  println(result)

  //Chained Methods
  def backupMethod:String = "NOC"
  val newResult = Option(unsafeMethod()).getOrElse(backupMethod)
  println(newResult)

  // DESIGN unsafe APIs
  val betterUnsafeMethod:Option[String] = None
  val betterBackupMethod:Option[String] = Some("NOC")
  val newResults2 = betterUnsafeMethod orElse betterBackupMethod
  println(newResults2)

  //functions on options
  println(myFirstOption.isEmpty) //false
  println(myFirstOption.get) //4 but this is unsafe do not use this
  println(noOption.isEmpty) //true
  //println(noOption.get) //gives NoSuchElementException

  //map, flatmap, filter
  println(myFirstOption.map(_ * 2)) //Some(8)
  println(myFirstOption.filter(x => x > 10)) //None
  println(myFirstOption.flatMap(x => Option(x*10))) //Some(40)

  /*
    Exercise.
   */

  val config: Map[String,String]= Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
    )

  class Connection {
    def connect = "connected"
  }

  object Connection{
    val random = new Random(System.nanoTime())

    def apply(host:String, port:String): Option[Connection] ={
      if(random.nextBoolean()) Some(new Connection())
      else None
    }
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  val connection =host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map((c => c.connect))
  // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
  println(connectionStatus)
  /*
   if (status != null)
     println(status)
  */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host,port))
      .map(connection =>connection.connect))
    .foreach(println)

  //for comprehensions
  val forConnectionStatus = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host,port)
  } yield connection.connect

  forConnectionStatus.foreach(println)






}
