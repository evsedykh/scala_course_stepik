val res1 = for {x <- List(1, 2, 3, 4)} x
val res2 = for { x <- Some(1) ; y <- None } yield (x, y)
val res3 = for { (k,v) <- Map("a" -> 1, "b" -> 2) } yield k
//val res4 = for { x <- Some(1) ; y <- Left("year") } yield x + y
//val res5 = for { x <- Some(2); y <- List(1,2,3,4) } yield x + y
println(res3)