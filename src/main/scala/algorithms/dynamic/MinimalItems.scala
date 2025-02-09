package algorithms.dynamic

/// Minimal Items Knapsack Problem
/// Given an array of positive weights and a target weight W,
/// this function computes the minimum number of items needed to exactly reach weight W using dynamic programming.
/// Each item may be used at most once.
///
/// dp(x) represents the minimal number of items required to achieve weight x.
///   - Base case: dp(0) = 0 (no item is needed to achieve weight 0)
///   - Recurrence: For each item with weight w and for each x from W down to w:
///         if dp(x - w) is not INF then
///             dp(x) = min(dp(x), dp(x - w) + 1)
///
/// Time Complexity: O(n * W), where n is the number of items.
def minimalItems(weights: Array[Int], W: Int): (Int, Array[Int]) = {
  // Define INF as a value greater than any possible number of items (here: W + 1)
  val INF = W + 1
  // Initialize dp array: dp(x) stores the minimal number of items to reach weight x.
  val dp: Array[Int] = Array.fill(W + 1)(INF)
  dp(0) = 0

  // Process each weight (item) exactly once.
  for (w <- weights) {
    // Iterate backwards to ensure each item is used only once.
    for (x <- W to w by -1) {
      if (dp(x - w) != INF) {
        dp(x) = math.min(dp(x), dp(x - w) + 1)
      }
    }
  }

  // If dp(W) is still INF, there is no solution.
  val result = if (dp(W) == INF) -1 else dp(W)
  (result, dp)
}

@main
def mainMinimalItems(): Unit = {
  // Example: Items with weights 2, 5, 3, 6 and target weight W = 10.
  val weights: Array[Int] = Array(2, 5, 3, 6)
  val W = 10
  val (minItems, dp) = minimalItems(weights, W)

  if (minItems == -1)
    println(s"Cannot exactly reach weight $W with the given items.")
  else
    println(s"Minimum number of items to exactly reach weight $W: $minItems")

  println("DP Array (minimal number of items for each weight):")
  dp.zipWithIndex.foreach { case (items, weight) =>
    val info = if (items == W + 1) "not reachable" else items.toString
    println(s"Weight $weight: $info")
  }
}
