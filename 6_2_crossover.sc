import scala.annotation.tailrec

def crossover(p: (List[Char], List[Char]), point: Int): (List[Char], List[Char]) = {
  (p._1.take(point) ::: p._2.drop(point), p._2.take(point) ::: p._1.drop(point))
}

@tailrec
def crossover_rec(p: (List[Char], List[Char]), points: List[Int]): (List[Char], List[Char]) = {
  points match {
    case Nil => p
    case _ =>
      val s = crossover(p, points.head)
      crossover_rec(s, points.tail)
  }
}

// Case 1
val points: List[Int] = List(1, 3)
val chr1: List[Char] = List.fill(5)('x')
val chr2: List[Char] = List.fill(5)('y')

/* // Case 2
  val points: List[Int] = List(2, 4, 5)
  val chr1: List[Char] = List.fill(7)('a')
  val chr2: List[Char] = List.fill(7)('d')
  */

val s = crossover_rec((chr1, chr2), points)

println(s._1.mkString(""))
println(s._2.mkString(""))
