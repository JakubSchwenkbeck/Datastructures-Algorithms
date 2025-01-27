package algorithms.search

import scala.annotation.tailrec

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
