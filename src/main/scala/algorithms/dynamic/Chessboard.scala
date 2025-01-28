package algorithms.dynamic

def numberOfValidMoves(n: Int): (Int, Array[Array[Int]]) = {
  val num: Array[Array[Int]] = Array.fill(n, n)(0)

  num(0)(0) = 1

  for (i <- 0 until n) {
    for (j <- 0 until n) {
      if (i > 0) num(i)(j) += num(i - 1)(j)
      if (j > 0) num(i)(j) += num(i)(j - 1)
      if (i > 0 && j > 0) num(i)(j) += num(i - 1)(j - 1)
    }
  }

  (num(n - 1)(n - 1), num)
}

@main
def MainNumberOfValidMoves(): Unit = {
  val n: Int = 5
  var (num, mat) = numberOfValidMoves(n)
  println(s"Number of moves for $n is : $num")
  mat.foreach { row =>
    row foreach print; println
  }

}
