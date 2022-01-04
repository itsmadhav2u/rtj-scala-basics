package lectures.oop

object CaseClasses extends App{
  /*
    equals, hashCode, toString
   */

  case class Person(name:String, age:Int)

  // 1. class parameters are fields
  val ro = new Person("Rohit",34)
  println(ro.name)

  //2. Sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(ro.toString)
  println(ro)

  // 3. equals and hashCode implemented OOTB
  val ro2 = new Person("Rohit",34)
  println(ro == ro2)

  // 4. CCs have handy copy method
  val ro3 = ro2.copy("Hitman")
  val ro4 = ro2.copy(age=33)
  val ro5 = ro2.copy()
  println(ro3)

  // 5. CCs have companion objects
  val thePerson = Person
  //companion objects also have some handy factory methods
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  /*case object :
  case objects have the same property as case classes,
  except they don't get companion objects because they are their own companion objects. */
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}
