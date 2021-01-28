def service1: Either[String, Double] = Right(3.14)
def service2(res1: Double): Either[String, Int] = Right(1)
def service3: String = "Svc4"
def service4(res1: Double, res2: Int, res3: String): Either[String, String] = Right("OK")

val result = for {
  r1 <- service1
  r2 <- service2(r1)
  r4 <- service4(r1, r2, service3)
} yield r4

println(result)