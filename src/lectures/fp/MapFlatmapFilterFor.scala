package lectures.fp

object MapFlatmapFilterFor extends App{
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  //Map
  println(list.map(_ + 2))
  println(list.map(_ + " string"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flastMap
  val toPair =(x:Int) => List(x,x+1)
  println(list.map(toPair))
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers= List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // List("a1", "a2"... "d4")

  // "iterating"
  val combinations = chars.flatMap(c => numbers.map(n => "" + c + n))
  println(combinations)

  val combinations3 = numbers.filter(_%2 == 0).flatMap(n => chars.flatMap(c => colors.map(cl => "" + c + n +"-"+cl)))
  println(combinations3)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forComprehensions = for{
    n <- numbers if n%2 == 0
    c <- chars
    cl <- colors
  } yield ""+c+n+"-"+cl

  println(forComprehensions)
  for{
    n <- numbers
  } println(n)

  val a = list.map{ x =>
    x*2
  }
  println(a)

  /*
     1.  MyList supports for comprehensions?
         map(f: A => B) => MyList[B]
         filter(p: A => Boolean) => MyList[A]
         flatMap(f: A => MyList[B]) => MyList[B]
     2.  A small collection of at most ONE element - Maybe[+T]
         - map, flatMap, filter
    */
}
