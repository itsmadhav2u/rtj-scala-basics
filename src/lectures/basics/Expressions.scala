package lectures.basics

object Expressions extends App {

  val x =22 + 17 //Expression
  println(x)

  println(17 + 22 * 8)
  // +, -, *, /, & (bit wise AND), | (bit wise OR), ^ (bit wise exclusive OR)
  // << (bit wise left shift), >> (bit wise right shift), >>> (right shift with zero extension)

  println(x == 17)
  // ==, !=, >, >=, < ,<=

  println(!(8 == x))
  //! (Unary), && and || (binary)

  var aVariable = 17
  aVariable += 2 //We also have +=-=, , *=, /= and works with variables only and comes with side effects

  //Instructions (Do) Vs Expressions (which evaluates or having a Value and a type)

  //IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 17 else 22 //IF expression
  println(aConditionedValue)
  println(if(aCondition) 17 else 22)
  println(22 + 17)

  //Loops (but don't write these)
  var a = 1
  while(a <= 10){
    println(a)
    a += 1
  }

  val aWeirdValue = (aVariable = 45) //Type of aWeirdValue is Unit == Void
  println(aWeirdValue)
  //Side effects in scala is also the expressions returning unit as Everything in scala is an expression
  //Ex: printing to console, whiles, reassigning of Vars

  //Code Block
  val aCodeBlock = {
    val x = 5
    val y = x + 2
    //both x, y are local to this code block we cannot access them outside of this
    if (x > y) "Hi" else "Bye"
  }


  // 1. difference between "hello world" vs println("hello world")?
  // 2.

  val someValue = {
    2 < 3
  }
  println(someValue)

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)

}
