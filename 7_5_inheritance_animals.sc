trait Animal {
  val sound: String

  def voice(): Unit = println("voice: " + sound)
}

class Cat extends Animal {
  val sound = "Meow!"
}

class Dog extends Animal {
  val sound = "Woof!"
}

class Fish extends Animal {
  val sound = ""

  override def voice(): Unit = println("Fishes are silent!")
}

val animals = Seq(new Cat, new Dog, new Fish)
animals.foreach(_.voice())
