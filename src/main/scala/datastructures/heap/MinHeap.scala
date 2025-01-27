package datastructures.heap

import scala.collection.mutable.ArrayBuffer

class MinHeap[T](implicit ord: Ordering[T]) {
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
    while (i > 0 && ord.lt(heap(i), heap(parent(i)))) {
      swap(i, parent(i))
      i = parent(i)
    }
  }

  private def heapifyDown(index: Int): Unit = {
    var i = index
    while (true) {
      val left = leftChild(i)
      val right = rightChild(i)
      var smallest = i

      if (left < heap.size && ord.lt(heap(left), heap(smallest))) smallest = left
      if (right < heap.size && ord.lt(heap(right), heap(smallest))) smallest = right

      if (smallest == i) return
      swap(i, smallest)
      i = smallest
    }
  }

  def insert(value: T): Unit = {
    heap.append(value)
    heapifyUp(heap.size - 1)
  }

  def extractMin(): Option[T] = {
    if (heap.isEmpty) return None
    val min = heap.head
    heap(0) = heap.last
    heap.dropRightInPlace(1)
    if (heap.nonEmpty) heapifyDown(0)
    Some(min)
  }

  def peekMin(): Option[T] = heap.headOption

  def size: Int = heap.size

  def isEmpty: Boolean = heap.isEmpty

  def printHeap(): Unit = println(heap.mkString(", "))
}
