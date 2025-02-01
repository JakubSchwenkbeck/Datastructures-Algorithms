package algorithms.search

import scala.annotation.tailrec
import scala.util.Random

/// randomized QuickSelect searches the k-smallest Number in a set of unordered numbers
/// This is a more generalized approach to the Median search for QuickSort
///
/// Due to a randomized pick of the pivot, the Runtime is O(n):
/// T(n) <= T(3/4 * n) + O(n)

@tailrec
def quickSelect(A: Array[Int], k: Int): Int = {
  require(A.nonEmpty, "Array must not be empty")
  if (A.length == 1) return A(0)

  // Random pivot selection
  val pivot = A(Random.nextInt(A.length))

  // Partition elements efficient with Lists
  val (less, equal, greater) = A.foldLeft((List[Int](), List[Int](), List[Int]())) {
    case ((less, equal, greater), elem) =>
      if (elem < pivot) (elem :: less, equal, greater)
      else if (elem > pivot) (less, equal, elem :: greater)
      else (less, elem :: equal, greater)
  }

  if (less.length >= k) {
    quickSelect(less.toArray, k)
  } else if (less.length + equal.length >= k) {
    pivot
  } else {
    quickSelect(greater.toArray, k - less.length - equal.length)
  }
}
