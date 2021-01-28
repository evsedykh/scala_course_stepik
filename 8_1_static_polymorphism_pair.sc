case class Pair[T, S] (first: T, second: S) {
  def swap(): Pair[S, T] = Pair(second, first)
}

val pair = Pair(123, "Oleg").swap()
require(pair.first ==  "Oleg")
require(pair.second == 123)