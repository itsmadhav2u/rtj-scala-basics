package lectures.fp

import scala.util.Random

object Sequences extends App{
  //seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  //prints in the form of List bcz  Seq companion object actually has an apply factory method that can construct subclasses of sequence.
  println(aSequence.reverse)
  println(aSequence(3))
  println(aSequence ++Seq(5,6,7,8))
  println(aSequence.sorted)

  //Range
  val aRange:Seq[Int] = 1 to 5
  val aRange2:Seq[Int] = 1 until 5 //5 is non  inclusive
  aRange.foreach(println)
  aRange2.foreach(println)
  (1 to 3).foreach(x => println("NOC"))

  //Lists
  val aList = List(1,2,3,4)
  val prepended = 17 +: aList //or 17 :: aList
  val appended = prepended :+ 22
  println(appended)
  val fill = List.fill(3)("NOC") //fill is a curried function which takes a number and a value to fill in a list
  println(fill)
  println(fill.mkString("-"))

  //Arrays
  val aArray = Array(1,2,3,4)
  val ThreeElem = Array.ofDim[Int](3) //adds default values based on type
  println(ThreeElem)
  ThreeElem.foreach(println) //prints defaults values for Int type (0 for Int and null for string type)

  //mutation
  aArray(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(aArray.mkString(","))

  // arrays and seq
  val numbersSeq: Seq[Int] = aArray  // implicit conversion
  println(numbersSeq) //prints as 'WrappedArray(1, 2, 0, 4)'

  //Vectors   Ote: Holds 32 elements at any one level
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // pros : keeps reference to tail
  // cons :updating an element in the middle takes long
  println(getWriteTime(numbersList)) // 1.42798955E7 approx nano sec
  // pros : depth of the tree is small
  // cons : needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector)) //4307.7 approx nano sec 



}
