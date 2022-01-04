package lectures.oop

object Inheritance extends App{
  // single class inheritance
  class Animal{
    val creatureType = "wild"
    def eat = println("eat in Animal")
    private def eat2 = println("private eat in animal")
    protected def eat3 = println("protected eat in animal")
  }
  class Cat extends Animal{
    def crunch ={
      eat3
      println("cat crunch method")
    }

  }

  val c1 = new Cat
  c1.eat
  //Sub class only inherit non private members of the super class
   //c1.eat2// will give you error as it is private
  //If a member is Protected the accessible only from inside subclass
  c1.crunch

  //Constructors
  class Person(name:String, age:Int)
  class Adult(name:String, age :Int, Idcard:String) extends Person(name, age)

  class Person2(name:String, age:Int){
    def this(name:String) = this(name,0)
  }
  class Adult2(name:String, age :Int, Idcard:String) extends Person2(name)
  //calling auxiliary Constructor of parent class

  //Overriding
  class Dog(override val creatureType: String) extends Animal{
    //override val creatureType: String = "domestic"
    override def eat = {
      //Super : calling method in super class
      super.eat
      println("eat method is overridden in dog")
    }
  }
  val d1 = new Dog("Domestic")
  d1.eat
  println(d1.creatureType)

  //type substitution (broad: polymorphism)
  val unknown:Animal = new Dog("k9")
  println(unknown.creatureType)
  unknown.eat  //It will use Dog's method

  /*
  preventing overrides
  1. use final keyword on member
  2. use final on class itself i.e create a final class  which prevents the extending of a class
  3. seal the class (new) - can extend the class in this file only but prevents extension in other
    ex: sealed class Animal
   */



}


