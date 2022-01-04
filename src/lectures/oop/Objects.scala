package lectures.oop

object Objects extends App{
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  //And objects do not receive parameters
  object Person{  // type and also its only instance
    // "static"/"class" - level functionality
    val N_Eyes = 2
    def canFly:Boolean = false

    // factory method : its sole purpose is to build a person's given some parameters.
    def apply(mother:Person, father:Person):Person = new Person("arjun")
  }
  class Person(val name:String){
    //instance-level functionality
  }
  // COMPANIONS as there are having same name and in same scope

  println(Person.N_Eyes)
  println(Person.canFly)
  //Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println(mary == john) //True both are pointing to a single instance of Person object
  val mary1 = new Person("Mary")
  val john2 = new Person("John")
  println(mary1 == john2) //False as both are pointing to 2 different instances of Person class

  val arjun = Person(mary1,john2)
  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
}

