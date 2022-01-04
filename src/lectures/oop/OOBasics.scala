package lectures.oop

object OOBasics extends App{
  //a class organizes data and behavior that is code and instantiation means concrete realizations and memory.
  //That is actual memory spaces that conform to the code and the data structure that the class describes.
  val a = new Person("Rohit",34)
  //println(a.age) //gives error as Person don't have any class fields only class parameters

  val b = new Person2("Ab",36)
  println(b.age)
  println(b.x )
  b.greet("Rohit")
  b.greet

  val c  = new Person2("Arjun")
  println(c.age)

  val d = new Person2
  println(d.age)

  val author = new Writer("firstN","surN",1997)
  val author2 = new Writer("firstN","surN",1997)
  val novel = new Novel("something",2020,author)
  println(author.fullname)
  println(novel.authorAge)
  println(novel.isWrittenByauthor(author))
  println(novel.isWrittenByauthor(author2))
  //Even though author and author2 has same values as novel is created by author object we will get false for author2

  val n:Counter = new Counter(17)
  n.print
  n.inc.print
  n.dec.print
  n.inc(8).print
  n.print
  n.dec(2).print
  n.print
}

class Person(name:String, age:Int) //Constructor
//here name and age are class parameter and not class fields so we cannot access through a.name
//add val before the parameter to make it as  class fields

//constructor
class Person2(name:String, val age:Int){
  val x= 17 //This is a class field
  println("Inside Person class")
  //Method
  def greet(name:String):Unit = println(s"${this.name} says : Hi, $name")
  //Using 'this' keyword we can access class parameters also

  //Over Loading:Having same method name but different signatures
  // diff num of parameters or parameter types, diff return type is not valid to have method overloading
  def greet():Unit ={
    //If any method doesn't have same as class parameter name then it $name is same as ${this.name}
    println(s"Hi, $name");
    println(s"${this.name}")
  }

  //Multiple Constructors
  //implementation of a secondary constructor has to be another constructor and nothing else.
  def this(name:String) = this(name,0)
  def this() = this("suriya")
}

class Writer(firstName:String, surName:String, val yearOfBirth:Int){
  def fullname:String = firstName + " " + surName
}

class Novel(val name:String, val yearOfrelease:Int, val author:Writer ){
  def authorAge:Int = yearOfrelease - author.yearOfBirth
  def isWrittenByauthor(author :Writer) = author == this.author
  def copy(newYearofRelease:Int):Novel = new Novel(name, newYearofRelease,author)

}

class Counter(val n:Int=0){
  def inc ={
    println("incrementing")
    new Counter(n+1) // immutability : Creating new instance with incremented value instead of using old
  }

  def dec = {
    println("decrementing")
    new Counter(n - 1)
  }

  def inc(t:Int):Counter ={
    if (t <= 0) this
    else inc.inc(t-1)
  }

  def dec(t:Int):Counter ={
    if (t <= 0) this
    else dec.dec(t-1)
  }

  def print = println(n)
}


