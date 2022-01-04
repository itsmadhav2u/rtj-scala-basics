package lectures.fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int,Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(17))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function[String,Int] {
    override def apply(element: String): Int = element.toInt
  }
  println(stringToIntConverter("17") + 22)
  //((Int, Int) => Int) is syntactic sugar for Function2[Int, Int, Int]
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  println(adder(17,22))

  // Function types Function2[A, B, R] === (A,B) => R
  // ***** ALL SCALA FUNCTIONS ARE OBJECTS *****
  // or instances of classes deriving from function 1, function 2 etc

  /*
   1.  a function which takes 2 strings and concatenates them
   2.  transform the MyPredicate and MyTransformer into function types
   3.  define a function which takes an int and returns another function which takes an int and returns an int
       - what's the type of this function
       - how to do it
  */

  val concatenator:(String,String) => String = new Function2[String,String,String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenator("hello ","scala"))

  val superAdder:(Int => ((Int) => Int)) = new Function[Int,Function1[Int,Int]] {
    override def apply(v1: Int): Int => Int = new Function[Int,Int] {
      override def apply(v2: Int): Int = v1 +v2
    }
  }
  val adder1 = superAdder(17)
  println(adder1(22))
  println(superAdder(17)(22)) // curried function
}

trait MyFunction[A,B]{
  def apply(element:A): B
}