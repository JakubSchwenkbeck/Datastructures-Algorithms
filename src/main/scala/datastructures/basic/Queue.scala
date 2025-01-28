package datastructures.basic

import datastructures.basic.QueueTrait
import datastructures.basic.LinkedNode

// Queue trait: defines basic operations for a queue
trait QueueTrait[T] {
  def enqueue(item: T): Unit
  def dequeue(): Option[T]
  def peek(): Option[T]
  def isEmpty: Boolean
  def size: Int
}

/// Array-based queue implementation with a fixed capacity.
/// Operates with constant time enqueue and dequeue, and supports wraparound of elements.
/// Example usage:
/// val queue = new ArrayQueue[Int].enqueue(1) // Queue: [1]
/// queue.enqueue(2)   // Queue: [1, 2]
/// queue.dequeue()    // Returns Some(1), Queue: [2]
/// queue.peek()       // Returns Some(2), Queue remains unchanged
/// queue.isEmpty      // Returns false
/// All operations (enqueue, dequeue, peek, isEmpty, size) are O(1) time complexity.
class ArrayQueue[T](capacity: Int) extends QueueTrait[T] {
  private val array = new Array[Any](capacity)
  private var front = 0
  private var rear = 0
  private var count = 0

  // Enqueue an item into the queue
  def enqueue(item: T): Unit = {
    if (count == capacity) {
      throw new IllegalStateException("Queue is full")
    }
    array(rear) = item
    rear = (rear + 1) % capacity
    count += 1
  }

  // Dequeue an item from the queue
  def dequeue(): Option[T] = {
    if (isEmpty) None
    else {
      val item = array(front).asInstanceOf[T]
      front = (front + 1) % capacity
      count -= 1
      Some(item)
    }
  }

  // Peek at the front item without removing it
  def peek(): Option[T] = {
    if (isEmpty) None
    else Some(array(front).asInstanceOf[T])
  }

  // Check if the queue is empty
  def isEmpty: Boolean = count == 0

  // Get the size of the queue
  def size: Int = count
}

/// Linked list-based queue implementation with dynamic sizing (no capacity limits).
/// Supports enqueue and dequeue operations in constant time.
/// Example usage:
/// val queue = new Queue[Int]
/// queue.enqueue(1)   // Queue: [1]
/// queue.enqueue(2)   // Queue: [1, 2]
/// queue.dequeue()    // Returns Some(1), Queue: [2]
/// queue.peek()       // Returns Some(2), Queue remains unchanged
/// queue.isEmpty      // Returns false
/// All operations (enqueue, dequeue, peek, isEmpty, size) are O(1) time complexity.
class Queue[T] extends QueueTrait[T] {
  private var front: Option[LinkedNode[T]] = None
  private var rear: Option[LinkedNode[T]] = None
  private var count: Int = 0

  // Enqueue an item into the queue
  def enqueue(item: T): Unit = {
    val newNode = new LinkedNode(item)
    if (rear.isEmpty) {
      front = Some(newNode)
      rear = Some(newNode)
    } else {
      rear.get.next = Some(newNode)
      rear = rear.get.next
    }
    count += 1
  }

  // Dequeue an item from the queue
  def dequeue(): Option[T] = {
    if (isEmpty) None
    else {
      val item = front.get.value
      front = front.get.next
      if (front.isEmpty) rear = None
      count -= 1
      Some(item)
    }
  }

  // Peek at the front item without removing it
  def peek(): Option[T] = {
    front.map(_.value)
  }

  // Check if the queue is empty
  def isEmpty: Boolean = front.isEmpty

  // Get the size of the queue
  def size: Int = count
}
