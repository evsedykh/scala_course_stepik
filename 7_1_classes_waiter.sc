class Waiter(val name: String, order: List[String] = Nil) {
  if (order == Nil) println(s"My name ${name}")

  def giveMe(dish: String): Waiter = new Waiter(name, dish :: order)

  def complete(): List[String] = order.reverse
}

val waiter = new Waiter("Ivan")
val positions = waiter.giveMe("борщ").giveMe("хлеб").complete()
println("Order: " + positions.mkString(","))