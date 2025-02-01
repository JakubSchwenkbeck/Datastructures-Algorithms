package algorithms.sort

import algorithms.search.quickSelect
import algorithms.search.MdMSelect

/// Randomized QuickSort implementation using QuickSelect for pivot selection
/// This achieves an expected runtime of O(n log n) due to the randomized pivot.
/// The worst-case runtime remains O(n^2), but it is highly unlikely in practice
///
/// The algorithm partitions the array around the pivot and recursively sorts both halves.
def randomizedQuickSort(A: Array[Int]): Array[Int] = {
  if (A.length < 2) return A

  val p: Int = quickSelect(A, A.length / 2) // Randomized pivot selection

  val (less, equal, greater) = A.foldLeft((List[Int](), List[Int](), List[Int]())) {
    case ((less, equal, greater), elem) =>
      if (elem < p) (elem :: less, equal, greater)
      else if (elem > p) (less, equal, elem :: greater)
      else (less, elem :: equal, greater)
  }

  randomizedQuickSort(less.toArray) ++ equal ++ randomizedQuickSort(greater.toArray)
}

/// Deterministic QuickSort implementation using the Median of Medians pivot selection
/// This guarantees an O(n log n) runtime in all cases, including the worst-case scenario.
///
/// The pivot is selected deterministically via MdMSelect, making this more robust
/// than the randomized version for adversarial inputs.
def deterministicQuickSort(A: Array[Int]): Array[Int] = {
  if (A.length < 2) return A

  val p: Int = MdMSelect(A, A.length / 2) // Deterministic pivot selection

  val (less, equal, greater) = A.foldLeft((List[Int](), List[Int](), List[Int]())) {
    case ((less, equal, greater), elem) =>
      if (elem < p) (elem :: less, equal, greater)
      else if (elem > p) (less, equal, elem :: greater)
      else (less, elem :: equal, greater)
  }

  deterministicQuickSort(less.toArray) ++ equal ++ deterministicQuickSort(greater.toArray)
}
