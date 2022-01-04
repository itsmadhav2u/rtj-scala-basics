package lectures.basics

object StringOps extends App{

  val str:String = "Hello, I am learning scala"

  //Java Functions
  println(str.charAt(3))
  println(str.substring(7,11)) //Beginning index in inclusive and ending index is exclusive
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.length)
  println(str.toLowerCase)

  val aNumString:String = "17"
  val num:Int = aNumString.toInt
  println('a' +: aNumString +: "z")
  println(str.take(4))
  println(str.reverse)

  //Scala specific :String Interpolators
  //S - Interpollation
  val name = "Rohit"
  val age = 34
  val greeting = s"my name is $name and I am $age years old"
  val anotherGreeting = s"my name is $name and I am turing ${age + 1} years old"
  println(anotherGreeting )

  //F- Interpollation
  val speed = 2.2f
  val myth = f"$name can eat $speed%2.2f in a minute"
  println(myth)

  //raw - interpollation
  println(raw"this comes \n in a single line ")
  val escaped = "This is a \n new line"
  println(raw"$escaped") //here the \n in escaped variable will be escaped from raw interpollator

}
