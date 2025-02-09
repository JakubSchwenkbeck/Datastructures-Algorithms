package datastructures.basic

import datastructures.heap.MaxHeap

/// A priority queue implemented using a max-heap. Supports enqueue, dequeue, peek, size, and isEmpty operations.
///
/// Time Complexity:
/// - Enqueue (insert): O(log n)
/// - Dequeue (extractMax): O(log n)
/// - Peek (peekMax): O(1)
/// - isEmpty: O(1)
/// - Size: O(1)
class PriorityQueue[T](implicit ord: Ordering[T]) {
  private val maxHeap = new MaxHeap[T]()

  // Insert an element into the priority queue
  def enqueue(value: T): Unit = {
    maxHeap.insert(value)
  }

  // Remove and return the highest-priority element
  def dequeue(): Option[T] = {
    maxHeap.extractMax()
  }

  // Peek at the highest-priority element without removing it
  def peek(): Option[T] = {
    maxHeap.peekMax()
  }

  // Check if the queue is empty
  def isEmpty: Boolean = maxHeap.isEmpty

  // Get the number of elements in the queue
  def size: Int = maxHeap.size

  // Debugging utility: Print the queue elements
  def printQueue(): Unit = maxHeap.printHeap()
}
