package lectures.basics
import scala.annotation.tailrec

//Lesson 3, 4
object Functions extends App{

  def aFunction(a:String,b:Int):String={
    a + " " + b
  }
  println(aFunction("hello",17))

  def aParameterlessFunction():Int= 22
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(a:String, b:Int):String ={
    if(b ==1) a
    else a + aRepeatedFunction(a,b-1)
  }
  println(aRepeatedFunction("Hello",3))

  //WHEN YOU NEED LOOPS, USE RECURSION.
  //For a normal function return type is not mandatory but for a recursive unction we need to specify return type

  def aFunctionWithSideEffects(a:String) :Unit = println(a)

  def aBigFunction(a:Int):Int={
    def aSmallFunction(b:Int,c:Int):Int = b + c
    aSmallFunction(a,a-1)
  }

  def greet(name:String,age:Int):Unit = println(s"Hi, My name is $name and I am $age years old")
  greet("abd",37)

  /*
   Recursive : function calling itself.
  */
  def factorial(n:Int):Int={
    if (n <= 0) 1
    else n * factorial(n-1)
  }
  println(factorial(5))
  println(factorial(10))
  println(factorial(100))
  println(factorial(8000))


  def fibonacci(n:Int):Int = {
    if(n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
    //1 1 2 3 5 8 13 21....
  }
  println(fibonacci(8))

  /*
  Tail Recursive :
  A recursive function is said to be tail recursive if the recursive call is the last thing done by the function.
   There is no need to keep record of the previous state.
   */
  def isPrime(n:Int):Boolean={
    def isPrimeUntil(a:Int):Boolean={
      if(a <= 1) true
      else n % a != 0 && isPrimeUntil(a-1)
    }
    isPrimeUntil(n/2)
  }
  println(isPrime(17))

  def factorial2(n: Int): Int = {
    // Using tail recursion
    @tailrec
    def factorialAcc(acc: Int, n: Int): Int =
    {
      if (n <= 1) acc
      else factorialAcc(n * acc, n - 1)
    }
    factorialAcc(1, n)
  }
  println(factorial2(8))
}
