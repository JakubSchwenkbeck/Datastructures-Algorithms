package algorithms.search

import algorithms.search.binarySearch

/// Exponential search algorithm to find an element in a sorted array
/// It first finds a range where the target might be located and then
/// performs binary search within that range.
///
/// Example:
/// val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
/// val index = exponentialSearch(Arr, 5)
/// println(index) // Returns 4 (0-based index)
///
/// Exponential Search runs in O(log n) time complexity
def exponentialSearch(A: Array[Int], target: Int): Int = {
  val n = A.length

  // Check if the first element is the target
  if (n == 0) return -1
  if (A(0) == target) return 0

  // Find the range where the target might be
  var bound = 1
  while (bound < n && A(bound) <= target) {
    bound *= 2
  }

  // Perform binary search within the found range
  val left = bound / 2
  val right = Math.min(bound, n - 1)

  binarySearch(A, target, left, right) // Use binary search on the narrowed range
}
