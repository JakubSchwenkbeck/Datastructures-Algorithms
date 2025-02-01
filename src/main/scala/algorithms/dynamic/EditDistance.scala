package algorithms.dynamic

/// Edit Distance Algorithm (Levenshtein Distance)
/// Computes the minimum number of operations (insertions, deletions, and substitutions)
/// required to convert one string into another.
///
/// Time Complexity: O(m * n), where m and n are the lengths of the input strings.
def editDistance(X: Array[Char], Y: Array[Char]): (Array[Array[Int]], Int) = {

  val n: Int = Y.length
  val m: Int = X.length

  val Edit: Array[Array[Int]] = Array.fill(m, n)(0)

  for (i <- 0 until n - 1) {
    Edit(0)(i) = i
  }
  for (i <- 1 until m) {
    Edit(i)(0) = i

    for (j <- 1 until n) {
      val ins: Int = Edit(i)(j - 1) + 1
      val del: Int = Edit(i - 1)(j) + 1
      var rep: Int = Int.MaxValue
      if (X(i) == Y(j)) {
        rep = Edit(i - 1)(j - 1)
      } else {
        rep = Edit(i - 1)(j - 1) + 1
      }
      Edit(i)(j) = Math.min(ins, Math.min(del, rep))
    }

  }
  (Edit, Edit(m - 1)(n - 1))
}

@main
def editDistanceMain(): Unit = {
  val x: Array[Char] = Array('A', 'L', 'G', 'O', 'R', 'I', 'T', 'H', 'M')
  val y: Array[Char] = Array('A', 'L', 'T', 'R', 'U', 'I', 'S', 'T', 'I', 'C')

  val (mat, res) = editDistance(x, y)
  mat.foreach(row => println(row.mkString(" ")))
  println(s" The shortest edit distance is : $res ")

}
