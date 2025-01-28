package algorithms.search

import scala.annotation.tailrec

/// Binary search algorithm to find an element in a sorted array
/// It searches within a specified range [left, right].
///
/// Example:
/// val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
/// val index = binarySearch(Arr, 5, 0, Arr.length - 1)
/// println(index) // Returns 4 (0-based index)
///
/// Binary Search runs in O(log n) time complexity
def binarySearch(A: Array[Int], target: Int, left: Int, right: Int): Int = {
  var low = left
  var high = right

  // Perform iterative binary search
  while (low <= high) {
    val mid = low + (high - low) / 2 // Prevents overflow

    // Check if the middle element is the target
    if (A(mid) == target) return mid

    // If target is smaller, search in the left half
    else if (A(mid) > target) high = mid - 1

    // If target is larger, search in the right half
    else low = mid + 1
  }

  // Return -1 if element is not found
  -1
}

// searching in a sorted List
// O(log n)
def binarySearch[A](lst: List[A], elem: A)(implicit ord: Ordering[A]): Option[Int] = {
  @tailrec
  def helper(xs: List[A], target: A, left: Int, right: Int): Option[Int] = {
    if (left > right) return None // Base case: Element not found

    val mid = left + (right - left) / 2 // Avoids overflow
    val midElem = xs(mid)

    if (midElem == target) Some(mid) // Found element, return index
    else if (ord.lt(midElem, target)) helper(xs, target, mid + 1, right) // Search right half
    else helper(xs, target, left, mid - 1) // Search left half
  }

  helper(lst, elem, 0, lst.length - 1)
}
