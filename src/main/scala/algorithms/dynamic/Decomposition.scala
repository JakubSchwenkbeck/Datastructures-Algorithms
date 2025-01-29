package algorithms.dynamic

// Bottom-up dynamic programming approach
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
