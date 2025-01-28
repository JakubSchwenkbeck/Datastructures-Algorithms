package datastructures.basic

import datastructures.heap.MaxHeap

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
