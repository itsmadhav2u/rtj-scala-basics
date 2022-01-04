package lectures.pm

import excercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  // 1 - constants
  val x:Any ="Scala"
  val constants = x match {
    case 1 => "The num"
    case "Scala" => "THE Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }
  println(constants) //THE Scala

  // 2 - match anything
  //2.1 wildcard
  val matchAnything = x match{
    case _ => "match all"
  }
  println(matchAnything) //match all

  //2.2 Variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }
  println(matchAVariable) //I've found Scala

  //3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) => "It is (1,1)"
    case (something,2) =>  s"I've found $something and 2"
  }
  println(matchATuple) //I've found 1 and 2

  val nestedTuple = (7,(17,22))
  val matchNestedTuple = nestedTuple match {
    case (_,(17,v)) => s"the values in nestes tuple are and 17 and $v"
  }
  // PMs can be NESTED!
  println(matchNestedTuple) //the values in nestes tuple are and 17 and 22

  // 4 - case classes - constructor pattern
  //PMs can be nested with CCs as well
  val aList:MyList[Int] = new Cons(1, Cons(2,Empty))
  val matchAList = aList match {
    case Empty => "An empty List"
    case Cons(head, Cons(subhead, subtail)) => s"head is $head and subhead is $subhead and subtail $subtail"
  }
  println(matchAList) //head is 1 and subhead is 2 and subtail []

  //5 - list pattern
  val aStandardList = List(1,2,3,4)
  val standardListMatching = aStandardList match {
    case List(1, 2, 3) :+ 4 => "List(1,2,3) :+ 4" // infix pattern
    case 1 :: List(_*) => "Its 1 :: List(_*)" // infix pattern
    case List(1,_,_,_) => "Its (1,_,_,_)" //extractor - advanced
    case List(1,_*) => "Its (1,_*)" // list of arbitrary length - advanced
    case List(_,_,_,_) => "its (_,_,_,_)"
    case List(1,2,3,4) => "its (1,2,3,4)"
  }
  println(standardListMatching)

  //6 - type specifiers
  val unknown:Any = 17
  val unknownMatch = unknown match {
    case list:List[Int] => "Its a list"
    case a:Int => "it is an int"
    case _ => "other"
  }
  println(unknownMatch) //it is an int

  // 7 - name binding
  val nameBindingMatch = aList match {
    case emptyList @ Empty => "enpty list"
    // name binding => use the name later(here)
    case nonEmptyList @ Cons(h, t) =>  s"$nonEmptyList head is $h and tail is ${t.head}"
    // name binding inside nested patterns
    case Cons(h1, rest @ Cons(h2, t2)) =>  s"$rest head is $h1 and $h2 and tail is $t2"
  }
  println(nameBindingMatch)

  // 8 - multi-patterns
  val multiPattern = aList match {
    // compound pattern (multi-pattern)
    case Empty | Cons(_,_) => "It is matched at Or condition"
    case _ => "No match"
  }
  println(multiPattern) //It is matched at Or condition

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_ , Cons(specialElement, _)) if specialElement % 2 == 0 => "Special element is even"
    case _ => "Not even"
  }
  println(secondElementSpecial) //Special element is even

  /*
    Question.
   */

  val newList = List(17,22)
  val newListMatch = newList match {
    case a:List[String] => "List of String"
    case a:List[Int] => "List of Int"
  }
  println(newListMatch) //List of String
  //Due to JVM limitations on generics it matches only with List
}

