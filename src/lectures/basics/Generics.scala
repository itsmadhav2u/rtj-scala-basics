package lectures.basics

object Generics extends App{

  //MyList is a generic class and type A here is name of the type parameter
  //traits also be type parameterised
  class MyList[+A]{
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
    So with a new type parameter B, which is a super type of A,
    the parameter is of type B and the type of result is also a type B
      A = Cat
      B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfInt = new MyList[Int]
  val listOfString = new MyList[String]

  //Generic methods
  object MyList {
    def empty[A]:MyList[A] = ??? //returns nothing
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal


  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  //+A : means its a covariance list
  class CovarianceList[+A]
  val animal:Animal = new Cat
  val animalList:CovarianceList[Animal] = new CovarianceList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //Bounded types
  //allow you to use your generic classes only for certain types that are either a subclass
  // <: lower bound accepts only subtype, >: upper bound accepts only sub type
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  // generic type needs proper bounded type
  //  val newCage = new Cage(new Car)

}
