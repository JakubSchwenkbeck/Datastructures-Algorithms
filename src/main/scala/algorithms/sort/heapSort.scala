package algorithms.sort

import datastructures.heap.MinHeap

/// Sorting an integer array using a MinHeap, in place of the argument Array
/// The array needs to be mutable for in-place sorting
///
/// Example:
/// val Arr = Array(5, 3, 8, 1, 2)
/// heapSort(Arr)
/// println(Arr) // Returns ( 1, 2, 3, 5, 8 )
///
/// Heap Sort runs in O(n log n) due to heap insertions and deletions
def heapSort(A: Array[Int]): Array[Int] = {
  val n = A.length
  val result = new Array[Int](n)
  val H = new MinHeap[Int]()

  H.buildHeap(A)

  for (i <- 0 until n) {
    H.extractMin() match {
      case Some(x) => result(i) = x
      case None    => throw new IndexOutOfBoundsException()
    }
  }

  result
}
