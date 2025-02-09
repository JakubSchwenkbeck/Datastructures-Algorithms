package algorithms.dynamic

/// Subset Sum / Knapsack Problem (without values)
/// Computes the maximal reachable weight (i.e. the largest sum of selected items) that does not exceed the capacity W.
/// Given an array of positive integers `weights` (each > 0) and a capacity `W`,
/// the function determines the greatest weight sum obtainable by a subset of the items such that the sum is ≤ W.
///
/// Uses a 1D boolean DP array where:
///   - dp(w) is true if weight w is reachable using a subset of the items.
/// The recurrence is:
///   - Base Case: dp(0) = true (the empty subset)
///   - For each item weight and for each w from W down to that weight:
///         if dp(w - weight) is true then dp(w) becomes true.
///         
/// Time Complexity: O(n * W), where n is the number of items and W is the capacity.
def maxReachableWeight(weights: Array[Int], W: Int): (Int, Array[Boolean]) = {
  // Initialize DP array: only 0 is reachable at the start.
  val dp: Array[Boolean] = Array.fill(W + 1)(false)
  dp(0) = true

  // Process each weight (each item is used at most once)
  for (weight <- weights) {
    // Loop backwards to avoid using the same item more than once
    for (w <- W to weight by -1) {
      if (dp(w - weight)) {
        dp(w) = true
      }
    }
  }

  // Find the maximum reachable weight ≤ W by scanning from W down to 0.
  for (w <- W to 0 by -1) {
    if (dp(w)) return (w, dp)
  }
  // In any case, dp(0) is true, so the function always returns within the loop.
  (0, dp)
}

@main
def mainMaxReachableWeight(): Unit = {
  // Example instance: four objects with weights 2, 5, 3, and 6, and capacity W = 12.
  val weights = Array(2, 5, 3, 6)
  val W = 12
  val (maxWeight, dp) = maxReachableWeight(weights, W)

  println(s"Maximum reachable weight not exceeding $W is: $maxWeight")
  println("DP Array (Reachable Weights):")
  // Display the reachability status for weights 0 to W.
  for (w <- 0 to W) {
    println(s"Weight $w: ${if (dp(w)) "reachable" else "not reachable"}")
  }
}
