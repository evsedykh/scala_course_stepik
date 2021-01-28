import scala.annotation.tailrec
import scala.collection.immutable._

object Example {

  def main(args: Array[String]): Unit = {

    def swap[T](list: List[T], p: Int, q: Int): List[T] = {
      if (list.size < 2) list
      else List.concat(list.take(p) :+ list.apply(q), list.slice(p + 1, q), list.apply(p) +: list.drop(q + 1))
    }

    def findPivot(list: List[Int]): Option[Int] = {
      val firstKey = list.headOption
      if (firstKey.isDefined) {
        val otherKeys = list.tail.dropWhile(_ == list.head)
        otherKeys match {
          case head :: _ if head > firstKey.get => Some(list(list.size - otherKeys.size))
          case _ => Some(list.head)
        }
      } else None
    }

    require(findPivot(List(1, 1, 1)).contains(1))
    require(findPivot( List(1, 2, 2)).contains(2))
    require(findPivot( List(2, 2, 1)).contains(2))
    require(findPivot( List()).isEmpty)

    @tailrec
    def partition(list: List[Int], pivot: Int): (List[Int], Int) = {
      val good_left: List[Int] = list.takeWhile(_ < pivot)
      val good_right: List[Int] = list.reverse.takeWhile(_ >= pivot).reverse
      val l = good_left.size
      val r = list.size - good_right.size
      val bad = list.slice(l, r)
      val bad_swapped = swap(bad, 0, bad.size - 1)
      val better = good_left ++ bad_swapped ++ good_right
      if (l >= r) (better, l) else partition(better, pivot)
    }

    @tailrec
    def kOrder(list: List[Int], k: Int): Int = {
      val maybePivot = findPivot(list)
      if(findPivot(list).isEmpty) return list.head
      val (p, m) = partition(list, maybePivot.get)
      if( k == m) {
        if (k > 0)  p(k - 1) else p.head
      }
      else if (k < m) kOrder(p.slice(0, m), k)
      else kOrder(p.slice(m + 1, list.size), k  - m - 1)
    }

    val k: Int = 3
    //val list: List[Int] = List(3, 8, 1, 12, 23)
    //val list: List[Int] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3)
    val list: List[Int] = List(4, 7, 6, 5, 12, 9, 5)

    //val list: List[Int] = readLine().split(" ").map(_.toInt).toList

    println(kOrder(list, k))
  }
}