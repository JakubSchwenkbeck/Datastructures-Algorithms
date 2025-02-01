package algorithms.dynamic

/// Maze Path Counting Algorithm
/// Computes the number of unique paths from the top-left corner to the bottom-right
/// of an n x m grid, moving only right or down.
///
/// Uses dynamic programming to store intermediate results in a 2D table.
///
/// Time Complexity: O(n * m), where n and m are the grid dimensions.
def maze(n: Int, m: Int): (Array[Array[Int]], Int) = {
  val Mem: Array[Array[Int]] = Array.fill(n, m)(0)

  for (i <- 0 until n) {
    Mem(i)(0) = 1
  }
  for (j <- 0 until m) {
    Mem(0)(j) = 1
  }

  for (i <- 1 until n) {
    for (j <- 1 until m) {
      Mem(i)(j) = Mem(i - 1)(j) + Mem(i)(j - 1)

    }
  }

  (Mem, Mem(n - 1)(m - 1))
}

@main
def mazeMain(): Unit = {
  val n: Int = 18
  val m: Int = 6

  val (mat, res) = maze(n, m)
  mat.foreach(row => println(row.mkString(" ")))
  println(s" The number of paths is: $res ")

}
