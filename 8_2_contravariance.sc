case class A(value: String)

 class B(override val value: String) extends A(value)

 val objB = new B("It is a B.value")
 val objA = A("It is a A.value")

 class FunctionPrint[-T <: A](prefix: String) {
   def apply(t: T): Unit = println(prefix + " " + t.value)
 }
 object FunctionPrint {
   def apply[T <: A](prefix: String) = new FunctionPrint[T](prefix)
 }

 def methodPrint[T <: A](f: FunctionPrint[T], obj: T): Unit = {
   f(obj)
 }

 val printA = FunctionPrint[A]("A-value:")
 val printB = FunctionPrint[B]("B-value:")

 methodPrint(printB, objB)
 methodPrint(printA, objB)