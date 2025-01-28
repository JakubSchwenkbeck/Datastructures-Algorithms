package algorithms.dynamic

/// Compute the number of valid paths for a king from (1,1) to (n,n) on an n×n chessboard
///
/// The king starts in the bottom-left corner (1,1) and must reach the top-right corner (n,n).
/// A valid move must always bring the king closer to (n,n). The possible moves are:
///   - Right: (x, y) → (x + 1, y)
///   - Up: (x, y) → (x, y + 1)
///   - Diagonal: (x, y) → (x + 1, y + 1)
///
/// We use **dynamic programming** to calculate the number of paths to each cell (x, y).
/// The recurrence relation is:
///   num(x, y) = num(x-1, y) + num(x, y-1) + num(x-1, y-1)
///
/// Time Complexity: O(n²) – We compute each cell once.
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
  val (num, mat) = numberOfValidMoves(n)
  println(s"Number of moves for $n is : $num")
  // Print DP matrix for visualization
  mat.foreach { row =>
    println(row.mkString("          "))
  }

}
