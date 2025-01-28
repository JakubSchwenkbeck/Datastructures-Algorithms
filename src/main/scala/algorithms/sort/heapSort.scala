package algorithms.sort

import datastructures.heap.MinHeap

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
