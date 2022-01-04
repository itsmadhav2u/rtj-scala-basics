package lectures.oop

import playground.{Cinderella => AliasName, PrinceCharming }

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App{
  //package members are accessible by their simple name
  val writer = new Writer("arjun", "ro", 2020)

  //import the package
  val princess = new AliasName
  //val princess2 = new playground.Cinderella
  // if we don't want to import the package can add fully qualified name

  // packages are in hierarchy
  // matching folder structure.

  // package object
  sayHello
  println(someNum)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ names
  // 2. use aliasing
  val date =  new Date()
  //val date3 = new java.sql.Date(2021,5,17)
  val date2 = new SqlDate(2021,5,17)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
