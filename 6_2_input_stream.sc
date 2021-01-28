import scala.collection.immutable.LazyList

val input = io.Source.stdin.mkString.split("\n").toList.takeWhile(_ != "END").map(_.toInt)
val zero_one: LazyList[Int] = 0 #:: 2 #:: zero_one.zip(zero_one.tail).map(n => if (n._1 == 0) 0 else 2)

println(input.zip(zero_one).map(n => n._1 * n._2).sum)