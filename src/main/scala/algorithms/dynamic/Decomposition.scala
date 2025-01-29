package algorithms.dynamic

// Bottom-up dynamic programming approach
/// Compute the maximum revenue from rod decomposition using dynamic programming.
///
/// Given a rod of length `n` and an array `prices` where `prices(i)` represents the price of a rod of length `i+1`,
/// the goal is to determine the optimal way to cut the rod to maximize revenue.
///
/// We use a **bottom-up dynamic programming** approach:
///   - Define `dp(i)` as the maximum revenue obtainable for a rod of length `i`.
///   - The recurrence relation is:
///       dp(j) = max(prices(i) + dp(j - i - 1)) for all valid cuts i.
///   - We iterate from length `1` to `n`, computing the best revenue for each length.
///
/// Time Complexity: **O(n²)** – We compute the maximum for each length `j` by iterating through all possible cuts `i`.
def bottomUpDecomposition(prices: Array[Int], n: Int): Int = {
  val dp = Array.fill(n + 1)(0) // dp[i] stores the max revenue for length i

  for (j <- 1 to n) {
    var maxVal = Int.MinValue
    for (i <- 0 until j) {
      maxVal = Math.max(maxVal, prices(i) + dp(j - i - 1))
    }
    dp(j) = maxVal
  }

  dp(n) // Return the max value for the full length
}

@main
def DecMain(): Unit = {
  val length = 4
  val prices = Array(1, 5, 8, 9)
  // Bottom-up approach
  val bottomUpResult = bottomUpDecomposition(prices, length)
  println(s"Bottom-up result: $bottomUpResult")
}
