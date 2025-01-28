package algorithms.sort

/// Sorting an integer array by countingSort
/// The function returns a sorted array rather than modifying the original.
/// Counting Sort runs in O(n + k) time complexity.
/// Example:
/// val Arr = Array(4, 1, 3, 1, 2, 4, 6)
/// val result = countingSort(Arr, 6)
/// println(result) // Returns Array(1, 1, 2, 3, 4, 4, 6)
def countingSort(A: Array[Int], k: Int): Array[Int] = {
  val n = A.length
  val B: Array[Int] = Array.fill(k + 1)(0)
  val C: Array[Int] = new Array(n)

  //  Count occurrences
  for (i <- 0 until n) {
    B(A(i)) += 1
  }

  // Compute cumulative sums
  for (i <- 1 to k) {
    B(i) += B(i - 1)
  }

  // Place elements in sorted order
  for (i <- (n - 1) to 0 by -1) {
    C(B(A(i)) - 1) = A(i) // Correct indexing
    B(A(i)) -= 1
  }

  C
}
