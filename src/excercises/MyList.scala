package excercises

abstract class MyList[+A]{
  def head:A
  def tail:MyList[A]
  def isEmpty:Boolean
  def add[B >:A](n:B):MyList[B]
  def printElements :String
  // polymorphic call
  override def toString:String = "[" + printElements + "]"

  // higher-order functions
  def map[B](transformer:A =>B):MyList[B]
  def flatMap[B](transformer:A =>MyList[B]):MyList[B]
  def filter(Predicate: A => Boolean) :MyList[A]

  //Concatenation
  def ++[B >: A](list:MyList[B]):MyList[B]

  //HOFs
  def foreach(f:A=>Unit):Unit
  def sort(compare:(A,A)=>Int):MyList[A]
  def zipWith[B,C](list:MyList[B],zip:(A,B)=>C):MyList[C]
  def fold[B](start:B)(operator:(B,A)=>B):B
}

case object Empty extends MyList[Nothing]{
  def head:Nothing = throw new NoSuchElementException
  def tail:MyList[Nothing] = throw new NoSuchElementException
  def isEmpty:Boolean = true
  def add[B >: Nothing](n:B):MyList[B] = new Cons(n, Empty)
  def printElements:String = ""
  def map[B](transformer:Nothing => B):MyList[B] = Empty
  def flatMap[B](transformer:Nothing => MyList[B]):MyList[B] =Empty
  def filter(Predicate: Nothing => Boolean) :MyList[Nothing] = Empty

  def ++[B >:Nothing](list:MyList[B]):MyList[B] =list
  //HOF
  def foreach(f:Nothing=>Unit):Unit = ()
  def sort(compare:(Nothing,Nothing) =>Int)= Empty
  def zipWith[B,C](list:MyList[B],zip:(Nothing, B)=>C):MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start:B)(operator:(B,Nothing)=>B):B = start
}

case class Cons[+A](h:A, t:MyList[A]) extends MyList[A]{
  def head:A = h
  def tail:MyList[A] = t
  def isEmpty:Boolean = false
  def add[B >:A](n:B):MyList[B] = new Cons(n,this)
  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }
  /*
      [1,2,3].filter(n % 2 == 0) =
        [2,3].filter(n % 2 == 0) =
        = new Cons(2, [3].filter(n % 2 == 0))
        = new Cons(2, Empty.filter(n % 2 == 0))
        = new Cons(2, Empty)
     */
  def filter(predicate:A => Boolean):MyList[A] = {
  if(predicate.apply(h)) new Cons(h, t.filter(predicate))
  else t.filter(predicate)
  }
  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
   */
  def map[B](transformer:A => B):MyList[B]={
    new Cons(transformer.apply(h),t.map(transformer))
  }

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def ++[B >: A](list:MyList[B]):MyList[B]= new Cons(h, t++list)
  /*
  [1,2].flatMap(n => [n, n+1])
  = [1,2] ++ [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,2,3]
 */
  def flatMap[B](transformer:A => MyList[B]):MyList[B] =
    transformer.apply(h) ++ t.flatMap(transformer)

  //HOFs
  def foreach(f:A=>Unit):Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare:(A,A) =>Int):MyList[A] ={
    def insert(x:A,sortedList:MyList[A]):MyList[A]={
        if(sortedList.isEmpty) new Cons(x,Empty)
        else if(compare(x,sortedList.head) <= 0 ) new Cons(x,sortedList)
        else new Cons(sortedList.head, insert(x,sortedList.tail))
    }
   val sortedTail = t.sort(compare)
    insert(h,sortedTail)
  }

  def zipWith[B,C](list:MyList[B],zip:(A, B) =>C):MyList[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h,list.head),t.zipWith(list.tail, zip))
  }

  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+) =
    = [].fold(6)(+)
    = 6
   */
  def fold[B](start:B)(operator:(B,A)=>B):B ={
    val newStart = operator(start,h)
    t.fold(newStart)(operator)
  }


}

/*trait MyPredicate[-T]{  //t =>Boolean
  def test(elem:T):Boolean
}

trait MyTransformer[-A,B]{ //A => B
  def transform(elem:A):B
}*/



object ListTest extends App{
  val list = new Cons(1,Empty)
  println(list.head)

  val list2 = new Cons(1,new Cons(2,new Cons(3,  Empty)))
  println(list2.tail.head)
  val list3 = list2.add(17)
  println(list3.toString)

  val list4:MyList[String] = new Cons("m",new Cons("a",new Cons("d", Empty)))
  println(list4.toString)
  val list5:MyList[Int] = new Cons(1,Empty)

  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

  //AnonymousClasses
  println(list2.map(new Function1[Int,Int] {
    def apply(elem:Int):Int =  elem*2
  }).toString)

  //Anonymous functions
  println(list2.map(elem => elem * 2).toString)

  println(list2.filter(new Function1[Int,Boolean] {
    def apply(elem:Int):Boolean =  elem % 2 ==0
  }).toString)

  println(list2.filter(elem => elem%2 ==0).toString)

  println(list2 ++ anotherListOfIntegers)
  println(list2.flatMap(new Function1[Int, MyList[Int]] {
    def apply(elem:Int):MyList[Int] = new Cons(elem, new Cons(elem+1, Empty))
  }))
  println(list2.flatMap(elem => new Cons(elem, new Cons(elem+1, Empty))))
  val copylist2 = new Cons(1,new Cons(2,new Cons(3,  Empty)))
  println(list2 == copylist2)

  list2.foreach(x => println(x))
  list2.foreach(println)

  println(list2.sort((x,y) => y-x))
  println(list2.zipWith[String,String](list4, _ +"-"+ _))
  println(list2.fold(0)(_ + _))

  // for comprehensions
  val combinations = for{
    n <- list2
    string <- list4
  } yield n + "-" + string
  println(combinations)

}


