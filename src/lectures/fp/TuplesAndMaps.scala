package lectures.fp

object TuplesAndMaps extends App{
  // tuples = finite ordered "lists"
  val a = new Tuple2(1,"hello")// Tuple2[Int, String] = (Int, String)
  val a2 =  Tuple2(1,"hello")
  val aTuple = (2, "hello, Scala",22,17)
  //Tuples can group at most 22 elements of different types

  println(aTuple._4) //17
  println(aTuple.copy(_1 = 12)) //(12,hello, Scala,22,17)
  println(a.swap) //(hello,1)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("AB", 555), "RS" -> 789)
  // a -> b is sugar for (a, b)
  println(phonebook)
  // map ops
  println(phonebook.contains("AB"))
  println(phonebook("RS"))  //Throws error if any key is not present

  //Map with a default value if we try to get a key which is not there in list
  val phonebookWIthDefault = Map(("AB", 555), "RS" -> 789).withDefaultValue(-1)
  println(phonebookWIthDefault("AG"))

  //Add a pairing
  val newPair = "AG" -> 1722
  val newPhonebook = phonebook + newPair
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(newPhonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(newPhonebook.filterKeys(x => x.startsWith("A")))
  //Mapvalues
  println(newPhonebook.mapValues(x => x * 10))

  //Conversions
  println(newPhonebook.toList) //List((AB,555), (RS,789), (AG,1722))
  println(List(("AG",1722)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  //Map(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))

  /*
    1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900
        !!! careful with mapping keys.
    2.  Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend
        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is a social connection between two people (direct or not)
   */
  val phonebook2 = Map(("AB", 555), "RS" -> 789,"ab" -> 17)
  println(phonebook2)  //Map(AB -> 555, RS -> 789, ab -> 17)
  println(phonebook2.map(pair => pair._1.toLowerCase -> pair._2)) //Map(ab -> 17, rs -> 789)

  def add(network:Map[String,Set[String]], person:String):Map[String,Set[String]] =
    network + (person -> Set())

  def friend(network:Map[String,Set[String]], a:String, b:String):Map[String,Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + ( a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network:Map[String,Set[String]],person:String):Map[String,Set[String]] ={
    def removeaux(friends:Set[String], networkAuc:Map[String,Set[String]]):Map[String,Set[String]]={
      if (friends.isEmpty) networkAuc
      else removeaux(friends.tail,unfriend(networkAuc,person,friends.head))
    }
    val unfriended = removeaux(network(person),network)
    unfriended - person
  }

  val empty:Map[String,Set[String]] = Map()
  val network = add(add(add(empty,"Mad"),"Ag"),"Ro")
  println(network)
  println(friend(network,"Mad","Ag"))
  println(friend(network,"Ro","Mad"))
  println(unfriend(friend(network,"Mad","Ro"),"Mad","Ro"))
  println(remove(friend(network,"Ro","Mad"),"Ro"))


  // Ab,Ro,rj
  val people = add(add(add(empty, "Ab"), "Ro"), "Rj")
  val RoRj = friend(people, "Ro", "Rj")
  val testNet = friend(RoRj, "Rj", "Ab")
  println(testNet)

  def nFriends(network:Map[String,Set[String]],person:String):Int =
    if(!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet,"Rj"))

  def maxFriends(network:Map[String,Set[String]]):String =
    network.maxBy(pair => pair._2.size)_1

  println(maxFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
    //or network.filter(pair => pair._2.isEmpty).size
    //or network.filterKeys(k => network(k).isEmpty).size
  }

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network:Map[String,Set[String]],a:String,b:String):Boolean = {
    def bfs(target:String, consideredPeople:Set[String], discoveredPeople:Set[String]):Boolean ={
      if(discoveredPeople.isEmpty) false
      else{
        val person = discoveredPeople.head
        if(person == target) true
        else if (consideredPeople.contains(person)) bfs(target,consideredPeople, discoveredPeople.tail)
        else bfs(target,consideredPeople+person,discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b,Set(),network(a)+a)
  }

  println(socialConnection(testNet,"Ro","Ab"))
  println(socialConnection(network,"Mad","Ag"))
}
