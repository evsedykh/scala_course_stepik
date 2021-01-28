val list1 = List(1, 3, 5, 7)
val list2 = List(2, 4, 6, 8)
val list3 = List(1, 3, 5, 8, 10, 12, 14)

for {
  x <- list1
  y <- list2 if x != y
  xy <- list3 if x * y == xy
} println(x, y)