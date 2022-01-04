package lectures.oop

object AbstractDataTypes extends App {
  //abstract
  abstract class Animal{
    val creatureType:String
    val someV:String = "some value in animal"
    def eat:Unit
  }

  class Dog extends Animal{
    //we can implement abstarct with or without override keyword
    override val creatureType: String = "Domestic"
    override val someV: String = "new value in dog"
    def eat = println("eat in dog")
  }
  // traits : Ultimate data types
  trait Carnivore{
    def eat(animal:Animal):Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType: String = "croc"
    def eat = println("eat in crocodile")
    def eat(animal:Animal) = println(s"i am a croc and i eat ${animal.creatureType}")
  }

  val d1 = new Dog
  val c1 = new Crocodile
  println(c1.creatureType)
  c1.eat
  c1.eat(d1)

  /*traits vs abstract classes
  both can have abstarct and non abstract members
  1 - traits do not have constructor parameters
  2 - multiple traits may be inherited by the same class i.e multiple inheritance (not possible to inherit multiple classes)
  3 - traits = behavior / what they do, abstract class = "thing"/ what they are*/

}
