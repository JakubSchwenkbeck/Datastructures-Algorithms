package datastructures

import munit.FunSuite
import datastructures.heap.MaxHeap

class MaxHeapTest extends FunSuite {

  test("MaxHeap: insert elements and maintain max-heap property") {
    val heap = new MaxHeap[Int]()
    heap.insert(10)
    heap.insert(20)
    heap.insert(5)
    heap.insert(30)

    assertEquals(heap.peekMax(), Some(30))
  }

  test("MaxHeap: extractMax should return elements in descending order") {
    val heap = new MaxHeap[Int]()
    heap.insert(10)
    heap.insert(20)
    heap.insert(5)
    heap.insert(30)

    assertEquals(heap.extractMax(), Some(30))
    assertEquals(heap.extractMax(), Some(20))
    assertEquals(heap.extractMax(), Some(10))
    assertEquals(heap.extractMax(), Some(5))
    assertEquals(heap.extractMax(), None)
  }

  test("MaxHeap: peekMax should return the largest element without removing it") {
    val heap = new MaxHeap[Int]()
    heap.insert(8)
    heap.insert(25)
    heap.insert(15)

    assertEquals(heap.peekMax(), Some(25))
    assertEquals(heap.peekMax(), Some(25)) // Ensure it does not remove the element
  }

  test("MaxHeap: isEmpty should return true for empty heap") {
    val heap = new MaxHeap[Int]()
    assert(heap.isEmpty)
  }

  test("MaxHeap: size should return the correct number of elements") {
    val heap = new MaxHeap[Int]()
    heap.insert(10)
    heap.insert(5)
    heap.insert(25)

    assertEquals(heap.size, 3)
    heap.extractMax()
    assertEquals(heap.size, 2)
  }
}
