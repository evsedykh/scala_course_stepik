val list = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150)

val filtered = list.takeWhile(_ < 100).filter(_ % 4 == 0).init.map(_ / 4)

filtered.foreach(println)
