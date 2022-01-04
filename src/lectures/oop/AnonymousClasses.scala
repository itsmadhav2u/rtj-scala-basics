package lectures.oop

object AnonymousClasses extends App{
  abstract class Animal{
    def eat:Unit
  }

  // anonymous class
  val funnyAnimal:Animal = new Animal{
    override def eat:Unit = println("NOC")
  }
  //In the background scala will create an anonymous class with that implementation and create an instance of it
  /* equivalent with
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahahahaah")
    }
    val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */
  funnyAnimal.eat
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  val john = new Person("John")
  jim.sayHi
  john.sayHi
  println(jim.getClass)
  println(john.getClass)
}
