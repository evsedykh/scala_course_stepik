// не работает в Scala worksheet

import Example.Vect.Aux

object Vect {
  type Aux[I] = Vect {type Item = I}
}

trait Vect extends Any {
  type Item

  def length: Int

  def get(index: Int): Item

  def set(index: Int, item: Item): Aux[Item]
}

final case class StringVect(str: String) extends AnyVal with Vect {
  type Item = Char

  def length: Int = str.length

  def get(index: Int): Char = str.charAt(index)

  def set(index: Int, item: Char): Aux[Char] = StringVect(str.updated(index, item))
}

final case class BoolVect64(values: Long) extends AnyVal with Vect {
  type Item = Boolean

  def length = 64

  def get(index: Int): Boolean = ((values >> index) & 1) == 1

  def set(index: Int, item: Boolean): Aux[Boolean] = {
    if (item) BoolVect64((values | 1L << index))
    else BoolVect64((values & ~(1L << index)))
  }
}

final case class BoolVect8(values: Byte) extends AnyVal with Vect {
  type Item = Boolean

  def length = 8

  override def toString: String = values.toString

  def get(index: Int): Boolean = ((values >> index) & 1) == 1

  def set(index: Int, item: Boolean): Aux[Boolean] = {
    if (item) BoolVect8((values | 1 << index).toByte)
    else BoolVect8((values & ~(1 << index)).toByte)
  }
}

def toList(vect: Vect): List[vect.Item] = {
  val list = for {
    i <- 0 until vect.length
  } yield vect.get(i)
  list.toList
}

require(StringVect("SCALA").set(4, 'T').get(4) == 'T')
println(toList(StringVect("SCALA")))
println(toList(StringVect("")))

require(BoolVect8(0).set(5, true).get(5))
require(BoolVect8(0).set(5, true).set(1, true).get(5))
require(!BoolVect8(0).set(5, true).set(5, false).get(5))
println(toList(BoolVect8(0.toByte)))
println(toList(BoolVect8(120.toByte)))
require(BoolVect8(120.toByte).set(0, true).get(0))
println(127.toByte)

for (i <- 0 until 64) {
  println(i + ": " + toList(BoolVect64(65535).set(i, true)))
}

require(BoolVect64(0L).set(12, true).get(12))
require(BoolVect64(0L).set(12, true).set(1, true).get(12))

println(toList(BoolVect64(1023L).set(5, false)))
require(!BoolVect64(1110L).set(12, true).set(12, false).get(12))
println(toList(BoolVect64(0)))
println(toList(BoolVect64(1023)))
