package lectures.basics

object DefaultArgs extends App{

  def trFact(n:Int,acc:Int =1):Int ={
    if(n <=1) acc
    else trFact(n-1,n*acc)
  }
  println(trFact(5))

  def savePicture(format:String="jpg",height:Int=1920,width:Int=1080):Unit = println("picture saved")

  savePicture()
  /*
  1. pass in every leading argument
  2. name the arguments
 */
  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "bmp")

}
