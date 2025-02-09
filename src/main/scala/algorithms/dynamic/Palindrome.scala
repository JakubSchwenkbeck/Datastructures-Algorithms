package algorithms.dynamic

/// Compute the length of the longest palindromic subsequence for a given string.
/// A subsequence is a sequence derived from the original string by deleting some or no characters
/// without changing the order of the remaining characters.
///
///   - dp(i)(j) represents the length of the longest palindromic subsequence in s(i..j).
/// The recurrence relation is:
///   - If s(i) == s(j) then:
///         dp(i)(j) = dp(i+1)(j-1) + 2
///         (if the substring length is 2, then dp(i)(j) = 2)
///   - Otherwise:
///         dp(i)(j) = max(dp(i+1)(j), dp(i)(j-1))
/// Base case:
///   - dp(i)(i) = 1 (each individual character is a palindrome of length 1)
///
/// Time Complexity: O(n²) – Each cell of the DP table is computed once.

def longestPalindromicSubsequence(s: String): (Int, Array[Array[Int]]) = {
  val n: Int = s.length
  // Initialize DP table with zeros.
  val dp: Array[Array[Int]] = Array.fill(n, n)(0)

  // Base Case: every single character is a palindrome of length 1.
  for (i <- 0 until n) {
    dp(i)(i) = 1
  }

  // Build the DP table for substrings of length 2 to n.
  for (len <- 2 to n) {
    for (i <- 0 to n - len) {
      val j = i + len - 1
      if (s(i) == s(j)) {
        dp(i)(j) = if (len == 2) 2 else dp(i + 1)(j - 1) + 2
      } else {
        dp(i)(j) = Math.max(dp(i + 1)(j), dp(i)(j - 1))
      }
    }
  }

  (dp(0)(n - 1), dp)
}

@main
def mainLongestPalindromicSubsequence(): Unit = {
  val inputString: String = "character"
  val (lpsLength, dpMatrix) = longestPalindromicSubsequence(inputString)
  println(s"Longest Palindromic Subsequence length for '$inputString' is: $lpsLength")
  println("DP Matrix:")
  dpMatrix.foreach { row =>
    println(row.mkString("  "))
  }
}
