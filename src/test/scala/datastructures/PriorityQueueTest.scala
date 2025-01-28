package datastructures

import datastructures.basic.PriorityQueue
import munit._

class PriorityQueueTest extends FunSuite {

  test("PriorityQueue[Int]: enqueue and dequeue") {
    val pq = new PriorityQueue[Int]()
    pq.enqueue(10)
    pq.enqueue(30)
    pq.enqueue(20)

    assertEquals(pq.dequeue(), Some(30))
    assertEquals(pq.dequeue(), Some(20))
    assertEquals(pq.dequeue(), Some(10))
  }

  test("PriorityQueue[Int]: peek should return the highest-priority item without removing it") {
    val pq = new PriorityQueue[Int]()
    pq.enqueue(10)
    pq.enqueue(50)
    pq.enqueue(20)

    assertEquals(pq.peek(), Some(50))
    assertEquals(pq.dequeue(), Some(50))
    assertEquals(pq.peek(), Some(20))
  }

  test("PriorityQueue[Int]: isEmpty should return true for an empty queue") {
    val pq = new PriorityQueue[Int]()
    assert(pq.isEmpty)
  }

  test("PriorityQueue[Int]: isEmpty should return false after enqueueing items") {
    val pq = new PriorityQueue[Int]()
    pq.enqueue(5)
    assert(!pq.isEmpty)
  }

  test("PriorityQueue[Int]: dequeue from an empty queue should return None") {
    val pq = new PriorityQueue[Int]()
    assertEquals(pq.dequeue(), None)
  }

  test("PriorityQueue[Int]: enqueue and dequeue with different types of data") {
    val pq = new PriorityQueue[String]()
    pq.enqueue("apple")
    pq.enqueue("banana")
    pq.enqueue("cherry")

    assertEquals(pq.dequeue(), Some("cherry")) // Lexicographical order (max priority)
    assertEquals(pq.dequeue(), Some("banana"))
    assertEquals(pq.dequeue(), Some("apple"))
  }

  test("PriorityQueue[Int]: size should return the correct number of elements") {
    val pq = new PriorityQueue[Int]()
    pq.enqueue(10)
    pq.enqueue(20)
    pq.enqueue(30)

    assertEquals(pq.size, 3)
    pq.dequeue()
    assertEquals(pq.size, 2)
  }
}
