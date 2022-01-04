package lectures.fp

object AnonymousFunctions extends App{

  val doubler = new Function1[Int,Int] {
    override def apply(v1: Int):Int = v1 * 2
  }
  // anonymous function (LAMBDA)
  val doubler2 = (x:Int) => x*2
  // (x:Int) => x*2 is an instance of Function1
  val doubler4: Int => Int = x => x*2

  // multiple params in a lambda
  val adder:(Int, Int)=>Int = (x:Int,y:Int) => x+y

  // no params
  val justDoSomething = () => 3
  val justDoSomething2: () => Int = () => 3

  // careful
  println(justDoSomething) // prints function itself
  println(justDoSomething()) // call function and prints the value

  // curly braces with lambdas
  val stringToInt = {(str:String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */
  val specialAdd= (x:Int) => (y:Int) => x+y
  println(specialAdd(17)(22))

}
