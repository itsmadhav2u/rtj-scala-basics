package lectures.oop

object MethodNotations extends App{
  class Person(val name:String,val favoriteMovie:String,val age:Int = 0){
    def likes(movie:String) = favoriteMovie == movie
    def +(person: Person):String = s"${this.name} is hanging out with ${person.name} "
    def +(nickName:String):Person = new Person(s"$name + ($nickName)",favoriteMovie,age)
    def unary_! :String = s"$name, but why"
    def unary_+ :Person = new Person(name,favoriteMovie,age+1)
    def isAlive:String ="He think so"
    def learns(str:String):String = s"$name learning about $str"
    def learnsLife:String = this learns "life"
    def apply():String = s"Hi, I am ${this.name} and i like $favoriteMovie"
    def apply(n:Int) = s"$name watched $favoriteMovie $n times"


  }
  val mad = new Person("Madhav","Ironman",24)
  //Infix notation /  operator notation (syntactic sugar)
  println(mad.likes("Ironman"))
  println(mad likes "Ironman")

  // "operators" in Scala
  val arjun = new Person("Arjun","Avengers",23)
  println(mad+(arjun))
  println(mad + arjun)

  // ALL OPERATORS ARE METHODS.
  println(1+2)
  println(1+(2))
  println(1.+(2))

  // prefix notation
  val a = -17 // equivalent with 17.unary_-
  val b = 17.unary_-
  println(a + " " + b)
  // unary_ prefix only works with - + ~ !

  println(!mad)
  println(mad.unary_!)

  // postfix notation
  println(mad.isAlive)
  println(mad isAlive)

  //Apply method
  println(mad.apply())
  println(mad()) // equivalent

  println((mad + "Mad")())
  println((+mad).age)
  println(mad learns "life")
  println(mad(3))



}
