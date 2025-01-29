package datastructures.heap

import scala.collection.mutable.ArrayBuffer

/// A max-heap implementation. Supports insert, extractMax, peekMax, and heap size operations.
///
/// Time Complexity:
/// - Insert: O(log n)
/// - ExtractMax: O(log n)
/// - PeekMax: O(1)
/// - Size: O(1)
/// - IsEmpty: O(1)
class MaxHeap[T](implicit ord: Ordering[T]) {
  private val heap: ArrayBuffer[T] = ArrayBuffer.empty[T]

  private def parent(i: Int): Int = (i - 1) / 2
  private def leftChild(i: Int): Int = 2 * i + 1
  private def rightChild(i: Int): Int = 2 * i + 2

  private def swap(i: Int, j: Int): Unit = {
    val temp = heap(i)
    heap(i) = heap(j)
    heap(j) = temp
  }

  private def heapifyUp(index: Int): Unit = {
    var i = index
    while (i > 0 && ord.gt(heap(i), heap(parent(i)))) {
      swap(i, parent(i))
      i = parent(i)
    }
  }

  private def heapifyDown(index: Int): Unit = {
    var i = index
    while (true) {
      val left = leftChild(i)
      val right = rightChild(i)
      var largest = i

      if (left < heap.size && ord.gt(heap(left), heap(largest))) largest = left
      if (right < heap.size && ord.gt(heap(right), heap(largest))) largest = right

      if (largest == i) return
      swap(i, largest)
      i = largest
    }
  }

  def insert(value: T): Unit = {
    heap.append(value)
    heapifyUp(heap.size - 1)
  }

  def extractMax(): Option[T] = {
    if (heap.isEmpty) return None
    val max = heap.head
    heap(0) = heap.last
    heap.dropRightInPlace(1)
    if (heap.nonEmpty) heapifyDown(0)
    Some(max)
  }

  def peekMax(): Option[T] = heap.headOption

  def size: Int = heap.size

  def isEmpty: Boolean = heap.isEmpty

  def printHeap(): Unit = println(heap.mkString(", "))
}
