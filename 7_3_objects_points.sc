
class Point(val x: Double, val y: Double, val z: Double)

object Point {
  def apply(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) = new Point(x, y, z)

  def average(points: List[Point]): Point = {
    if (points.isEmpty) Point() else {
      val tuple = points.map(p => (p.x, p.y, p.z)).unzip3
      val n = points.size
      Point(tuple._1.sum / n, tuple._2.sum / n, tuple._3.sum / n)
    }
  }

  def show(point: Point): String = s"${point.x} ${point.y} ${point.z}"
}

val points1 = List(Point(1, 2.5, 4), Point(4, 3.5, 6))
val points2 = List(Point(1, 2, 3), Point(4, 5, 6), Point(7, 8, 9))
println(Point.show(Point.average(points1)))
println(Point.show(Point.average(points2)))
val point = Point()
println(Point.show(point))
println(Point.show(Point.average(List())))
