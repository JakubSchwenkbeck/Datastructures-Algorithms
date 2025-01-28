package datastructures

import datastructures.*
import datastructures.basic.{ArrayQueue, Queue}
import munit.*

class ArrayQueueTest extends FunSuite {

  test("ArrayQueue[Int]: enqueue and dequeue") {
    val queue = new ArrayQueue[Int](5)
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)

    assertEquals(queue.dequeue(), Some(1))
    assertEquals(queue.dequeue(), Some(2))
    assertEquals(queue.dequeue(), Some(3))
  }

  test("ArrayQueue[Int]: peek should return front item without removing it") {
    val queue = new ArrayQueue[Int](5)
    queue.enqueue(1)
    queue.enqueue(2)

    assertEquals(queue.peek(), Some(1))
    assertEquals(queue.dequeue(), Some(1))
    assertEquals(queue.peek(), Some(2))
  }

  test("ArrayQueue[Int]: isEmpty should return true for empty queue") {
    val queue = new ArrayQueue[Int](5)
    assert(queue.isEmpty)
  }

  test("ArrayQueue[Int]: isEmpty should return false after enqueueing items") {
    val queue = new ArrayQueue[Int](5)
    queue.enqueue(1)
    assert(!queue.isEmpty)
  }

  test("ArrayQueue[Int]: enqueue beyond capacity should throw IllegalStateException") {
    val queue = new ArrayQueue[Int](2)
    queue.enqueue(1)
    queue.enqueue(2)
    try {
      queue.enqueue(3)
      assert(false) // this should never be reached
    } catch {
      case e: IllegalStateException => assert(true)
    }
  }

  test("ArrayQueue[Int]: dequeue from empty queue should return None") {
    val queue = new ArrayQueue[Int](3)
    assertEquals(queue.dequeue(), None)
  }

  test("ArrayQueue[Int]: size should return the correct number of elements") {
    val queue = new ArrayQueue[Int](5)
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    assertEquals(queue.size, 3)
    queue.dequeue()
    assertEquals(queue.size, 2)
  }
}
import munit.*

class QueueTest extends FunSuite {

  test("Queue[Int]: enqueue and dequeue") {
    val queue = new Queue[Int]
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)

    assertEquals(queue.dequeue(), Some(1))
    assertEquals(queue.dequeue(), Some(2))
    assertEquals(queue.dequeue(), Some(3))
  }

  test("Queue[Int]: peek should return front item without removing it") {
    val queue = new Queue[Int]
    queue.enqueue(1)
    queue.enqueue(2)

    assertEquals(queue.peek(), Some(1))
    assertEquals(queue.dequeue(), Some(1))
    assertEquals(queue.peek(), Some(2))
  }

  test("Queue[Int]: isEmpty should return true for empty queue") {
    val queue = new Queue[Int]
    assert(queue.isEmpty)
  }

  test("Queue[Int]: isEmpty should return false after enqueueing items") {
    val queue = new Queue[Int]
    queue.enqueue(1)
    assert(!queue.isEmpty)
  }

  test("Queue[Int]: dequeue from empty queue should return None") {
    val queue = new Queue[Int]
    assertEquals(queue.dequeue(), None)
  }

  test("Queue[Int]: enqueue and dequeue with different types of data") {
    val queue = new Queue[String]
    queue.enqueue("Hello")
    queue.enqueue("World")

    assertEquals(queue.dequeue(), Some("Hello"))
    assertEquals(queue.dequeue(), Some("World"))
  }

  test("Queue[Int]: size should return the correct number of elements") {
    val queue = new Queue[Int]
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    assertEquals(queue.size, 3)
    queue.dequeue()
    assertEquals(queue.size, 2)
  }
}
