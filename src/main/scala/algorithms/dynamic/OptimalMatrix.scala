package algorithms.dynamic

case class Matrix(n: Int, m: Int)

def optimalMatrixParentheses(lst: Array[Matrix]): (Int, Array[Array[Int]]) = {
  val length = lst.length
  val dp: Array[Array[Int]] = Array.fill(length, length)(0)
  val s: Array[Array[Int]] = Array.fill(length, length)(0)

  for (i <- 1 until length) {
    for (j <- 0 until length - i) {
      val index = j + i
      dp(j)(index) = Int.MaxValue
      for (k <- j until index) {
        val q = dp(j)(k) + dp(k + 1)(index) + lst(j).n * lst(k).m * lst(index).m
        if (q < dp(j)(index)) {
          dp(j)(index) = q
          s(j)(index) = k
        }
      }
      // println(s"${dp(j)(index)}")
    }
  }

  def printSolution(s: Array[Array[Int]], i: Int, j: Int): Unit = {
    if (i == j) {
      print(s" A $i  ")
    } else {
      print(" ( ")
      printSolution(s, i, s(i)(j))
      printSolution(s, s(i)(j) + 1, j)
      print(" ) ")
    }
  }

  printSolution(s, 0, length - 1)
  println("\n")

  (dp(0)(length - 1), dp)
}

@main
def MatrixMain(): Unit = {
  val matrixList = Array(Matrix(5, 10), Matrix(10, 3), Matrix(3, 12), Matrix(12, 5), Matrix(5, 60), Matrix(60, 6))
  val (num, res) = optimalMatrixParentheses(matrixList)
  res.foreach { row =>
    println(row.mkString("          "))
  }

  println(s"The minimal number of scalars is $num ")
}

def MatrixTest(): Unit = {
  val matrixList = Array(Matrix(3, 5), Matrix(5, 1), Matrix(1, 4), Matrix(4, 2))
  val (num, res) = optimalMatrixParentheses(matrixList)
  res.foreach { row =>
    println(row.mkString("          "))
  }

  println(s"The minimal number of scalars is $num ")
}
