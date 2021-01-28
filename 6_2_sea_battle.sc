import scala.annotation.tailrec
import scala.io.StdIn._

//object Example {

type Point = (Int, Int)
type Field = Vector[Vector[Boolean]]
type Ship = List[Point]
type Fleet = Map[String, Ship]
type Game = (Field, Fleet)

def miny(ship: Ship): Int = ship.map(_._1).min

def maxy(ship: Ship): Int = ship.map(_._1).max

def minx(ship: Ship): Int = ship.map(_._2).min

def maxx(ship: Ship): Int = ship.map(_._2).max

def height(ship: Ship): Int = maxy(ship) - miny(ship)

def width(ship: Ship): Int = maxx(ship) - minx(ship)

def validateShape(ship: Ship): Boolean = height(ship) * width(ship) == 0

def validateLength(ship: Ship): Boolean = height(ship) < 4 && width(ship) < 4

def validateShip(ship: Ship): Boolean = { // определить, подходит ли корабль по своим характеристикам
  validateShape(ship) && validateLength(ship)
}

def markShipVicinity(fleet: Fleet): Ship = {
  val ship: Ship = fleet.values.flatten.toList
  if (ship.isEmpty) List()
  val result = for (point <- ship)
    yield for (x <- point._1 - 1 to point._1 + 1; y <- point._2 - 1 to point._2 + 1) yield (x, y)
  result.flatten.filter(_._1 >= 0).filter(_._2 >= 0).filter(_._1 < 10).filter(_._2 < 10).distinct
}

def markUsedCell(field: Field, point: Point): Field = {
  field.updated(point._1, field(point._1).updated(point._2, true))
}

@tailrec
def markUsedCells(field: Field, ship: Ship): Field = // пометить клетки, которые занимает добавляемый корабль
{
  ship match {
    case Nil => field
    case one :: Nil => markUsedCell(field, one)
    case head :: tail => markUsedCells(markUsedCell(field, head), tail)
  }
}
def markFleetVicinity(fleet: Fleet): Field = {
  val vic: Ship = markShipVicinity(fleet)
  val field = Vector.fill(10, 10)(false)
  markUsedCells(field, vic)
}

def validatePosition(ship: Ship, fleet: Fleet): Boolean = // определить, можно ли его поставить
{
  val vicinity = markFleetVicinity(fleet)
  for (point <- ship) {
    if (vicinity(point._1)(point._2)) {
      return false
    }
  }
  true
}

def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = // добавить корабль во флот
{
  println(name)
  fleet.updated(name, ship)
}

def tryAddShip(game: Game, name: String, ship: Ship): Game = // логика вызовов методов выше
{
  if (validateShip(ship) && validatePosition(ship, game._2)) (markUsedCells(game._1, ship), enrichFleet(game._2, name, ship))
  else game
}

@tailrec
def addNextShip(game: Game, fleet: Fleet): Game = {
  if (fleet.nonEmpty) addNextShip(tryAddShip(game, fleet.head._1, fleet.head._2), fleet.tail)
  else game
}

def printField(field: Field): Unit = {
  println
  for (line <- field) println(line.mkString("< ", " ", " >"))
}

/*
  def validatePosition(ship: Ship, field: Field): Boolean = ??? // определить, можно ли его поставить
*/

@tailrec
def inputShip(ship: Ship, boxes: Int): Ship = {
  if (boxes == 0) ship
  else {
    print("Enter x and y: ")
    val x_y = readLine().split(" ")
    val xy = (x_y.head.toInt, x_y.last.toInt)
    inputShip(xy :: ship, boxes - 1)
  }
}

@tailrec
def inputFleet(fleet: Fleet, ships: Int): Fleet = {
  if (ships == 0) fleet
  else {
    print("Enter Name and Size of the ship: ")
    val name_size = readLine().split(" ")
    val name = name_size.head
    val boxes = name_size.last.toInt
    inputFleet(fleet + (name -> inputShip(Nil, boxes)), boxes - 1)
  }
}

/*print("Enter number of ships: ")
val n_ships = readInt()
val fleet = inputFleet(Map(), n_ships)*/

val black_pearl = "BlackPearl" -> List((1, 6), (1, 7), (1, 8))
val millennium_falcon = "MilleniumFalcon" -> List((2, 5), (3, 5), (4, 5), (5, 5))
val varyag = "Varyag" -> List((9, 9))
val fleet: Fleet = Map(black_pearl, millennium_falcon, varyag)

val game: Game = (Vector.fill(10, 10)(false), Map())
val updated_game = addNextShip(game, fleet)
printField(updated_game._1)

//}