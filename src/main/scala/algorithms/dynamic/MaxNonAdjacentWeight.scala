package algorithms.dynamic

/// Calculate the maximum weight sum of non-adjacent elements in an array.
/// The function also returns the subsequence of selected elements.
///
/// Example:
/// val arr = Array(3, 2, 7, 10)
/// val (maxWeight, maxSequence) = maxNonAdjacentWeight(arr)
/// println(s"Max weight: $maxWeight")  // Returns (13)
/// println(s"Sequence: $maxSequence") // Returns (3, 10)
///
/// Time Complexity: O(n) – The DP table is filled once iteratively.
def maxNonAdjacentWeight(a: Array[Int]): (Int, List[Int]) = {
  val n = a.length
  if (n == 0) return (0, List())
  if (n == 1) return (a(0), List(a(0)))

  val dp = Array.fill(n)(0) // DP table for max weight
  val sequence = Array.fill(n)(List[Int]()) // Store selected subsequence

  // Base cases
  dp(0) = a(0)
  sequence(0) = List(a(0))

  if (n > 1) {
    dp(1) = Math.max(a(0), a(1))
    sequence(1) = if (a(1) > a(0)) List(a(1)) else List(a(0))
  }

  // Fill DP table iteratively
  for (i <- 2 until n) {
    if (dp(i - 1) > dp(i - 2) + a(i)) {
      dp(i) = dp(i - 1)
      sequence(i) = sequence(i - 1) // Take the previous sequence
    } else {
      dp(i) = dp(i - 2) + a(i)
      sequence(i) = sequence(i - 2) :+ a(i) // Append current element
    }
  }

  (dp(n - 1), sequence(n - 1)) // Return max weight and sequence
}

@main
def runMaxNonAdjacentWeight(): Unit = {
  val a = Array(3, 2, 7, 10) // Example input
  val (maxWeight, maxSequence) = maxNonAdjacentWeight(a)

  println(s"Maximum weight: $maxWeight")
  println(s"Selected sequence: $maxSequence")
}
