package algorithms.dynamic

/// Longest Common Subsequence (LCS) Algorithm
/// Computes the length and reconstructs the LCS of two sequences using dynamic programming.
///
/// Uses a 2D table to store LCS values for substrings of A and B.
///
/// Time Complexity: O(n * m), where n and m are the lengths of the input sequences.

def LCS(A: Array[Char], B: Array[Char]): (Array[Array[Int]], Int, String) = {
  val n = A.length
  val m = B.length

  val L: Array[Array[Int]] = Array.fill(n + 1, m + 1)(0)

  // Fill the DP table
  for (i <- 1 to n) {
    for (j <- 1 to m) {
      if (A(i - 1) == B(j - 1)) {
        L(i)(j) = L(i - 1)(j - 1) + 1
      } else {
        L(i)(j) = Math.max(L(i - 1)(j), L(i)(j - 1))
      }
    }
  }

  // LCS Length
  val lcsLength = L(n)(m)

  // Reconstruct the LCS string
  val lcsBuilder = new StringBuilder()
  var i = n
  var j = m
  while (i > 0 && j > 0) {
    if (A(i - 1) == B(j - 1)) {
      lcsBuilder.append(A(i - 1))
      i -= 1
      j -= 1
    } else if (L(i - 1)(j) > L(i)(j - 1)) {
      i -= 1
    } else {
      j -= 1
    }
  }

  (L, lcsLength, lcsBuilder.reverse.toString)
}

@main
def lcsMain(): Unit = {
  val x: Array[Char] = Array('A', 'L', 'G', 'O', 'R', 'I', 'T', 'H', 'M')
  val y: Array[Char] = Array('A', 'L', 'T', 'R', 'U', 'I', 'S', 'T', 'I', 'C')

  val (mat, res, lcs) = LCS(x, y)
  mat.foreach(row => println(row.mkString(" ")))
  println(s" The Longest subsequence has length : $res ")
  println(s" The Longest subsequence is : $lcs")
}
