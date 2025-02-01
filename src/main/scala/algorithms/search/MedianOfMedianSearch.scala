package algorithms.search

/// Deterministic Median of Medians Search returns the median of the Array A
/// Used for a deterministic O(n) pivot of QuickSort
/// This runs in O(n) because T(n) <= T(n/5) + T(7n / 10) + O(n)

def MdMSelect(A: Array[Int], k: Int): Int = {
  if (A.length <= 25) return bruteForceMedian(A, k) // Use sorting for small arrays

  // Divide A into groups of 5 and find medians
  val groups = A.grouped(5).toArray
  val Medians = groups.map(group => bruteForceMedian(group, group.length / 2))

  // Recursively find median of medians
  val mdm = MdMSelect(Medians, Medians.length / 2)

  // Partition around mdm
  val (less, equal, greater) = A.foldLeft((List[Int](), List[Int](), List[Int]())) {
    case ((less, equal, greater), elem) =>
      if (elem < mdm) (elem :: less, equal, greater)
      else if (elem > mdm) (less, equal, elem :: greater)
      else (less, elem :: equal, greater)
  }

  if (less.length >= k) {
    MdMSelect(less.toArray, k)
  } else if (less.length + equal.length >= k) {
    mdm
  } else {
    MdMSelect(greater.toArray, k - less.length - equal.length)
  }
}
def bruteForceMedian(A: Array[Int], k: Int): Int = {
  A.sorted.apply(k)
}
