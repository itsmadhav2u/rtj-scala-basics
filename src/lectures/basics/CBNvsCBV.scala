package lectures.basics

object CBNvsCBV extends App{

  def callByVale(x:Long):Unit = {
    println("call by value : "+x)
    println("call by value : "+x)
  }

  def callByName(x: =>Long):Unit = {
    println("call by name : "+x)
    println("call by name : "+x)
  }

  callByVale(System.nanoTime())
  callByName(System.nanoTime())

  def infinite():Int = + infinite()
  def printFirst(x:Int,y: =>Int):Unit = println(x)

  printFirst(17,infinite())

}
