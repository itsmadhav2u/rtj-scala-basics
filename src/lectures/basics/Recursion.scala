package lectures.basics

import scala.annotation.tailrec

object Recursion extends App{

  //JVm uses Stack to store partial results
  def factorial(n:Int):Int ={
    if (n <= 1) 1
    else {
      //println(s"computing factorial of $n first i need factorial of " + (n-1))
      val result = n * factorial(n-1)
      //println("Computed factorial of ",n)
      result
    }
  }
  println(factorial(5))

  def anotherFactorial(n:Int):BigInt ={
    @tailrec
    def factHelper(x:Int,accumulator:BigInt):BigInt ={
      if (x <= 1) accumulator
      else factHelper( x-1, x * accumulator ) // TAIL RECURSION = use recursive call as the LAST expression

    }
    factHelper(n,1)
  }

  /*
   anotherFactorial(10) = factHelper(10, 1)
   = factHelper(9, 10 * 1)
   = factHelper(8, 9 * 10 * 1)
   = factHelper(7, 8 * 9 * 10 * 1)
   = ...
   = factHelper(2, 3 * 4 * ... * 10 * 1)
   = factHelper(1, 1 * 2 * 3 * 4 * ... * 10)
   = 1 * 2 * 3 * 4 * ... * 10
  */

  println(anotherFactorial(10))

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

  //Concatenate a string n times
  def concatenateTailrec(str:String,n:Int,accumulator:String):String ={
    if(n <=0) accumulator
    else concatenateTailrec(str,n-1,accumulator+str)
  }

  println(concatenateTailrec("hello",5,""))

  // IsPrime function tail recursive
  def isPrime(n:Int):Boolean = {
    @tailrec
    def isPrimeTailRec(t:Int,isStillPrime:Boolean):Boolean = {
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeTailRec(t-1, n%t !=0 && isStillPrime)
    }
    isPrimeTailRec(n/2,true)
  }

  //Fibonacci function, tail recursive.
  def fibonacci(n: Int): Int = {
    def fiboTailrec(i:Int,last:Int,nextLast:Int):Int ={
      if(i >= n) last
      else fiboTailrec(i+1,last+nextLast,last)
    }
    if(n <= 2) 1
    else fiboTailrec(2,1,1)
  }

  println(fibonacci(8))
  


}
