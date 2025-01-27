package datastructures.heap

import munit.FunSuite

class MinHeapTest extends FunSuite {

  test("MinHeap: insert elements and maintain min-heap property") {
    val heap = new MinHeap[Int]()
    heap.insert(10)
    heap.insert(5)
    heap.insert(20)
    heap.insert(3)

    assertEquals(heap.peekMin(), Some(3))
  }

  test("MinHeap: extractMin should return elements in sorted order") {
    val heap = new MinHeap[Int]()
    heap.insert(10)
    heap.insert(5)
    heap.insert(20)
    heap.insert(3)

    assertEquals(heap.extractMin(), Some(3))
    assertEquals(heap.extractMin(), Some(5))
    assertEquals(heap.extractMin(), Some(10))
    assertEquals(heap.extractMin(), Some(20))
    assertEquals(heap.extractMin(), None)
  }

  test("MinHeap: peekMin should return the smallest element without removing it") {
    val heap = new MinHeap[Int]()
    heap.insert(8)
    heap.insert(2)
    heap.insert(5)

    assertEquals(heap.peekMin(), Some(2))
    assertEquals(heap.peekMin(), Some(2)) // Ensure it does not remove the element
  }

  test("MinHeap: isEmpty should return true for empty heap") {
    val heap = new MinHeap[Int]()
    assert(heap.isEmpty)
  }

  test("MinHeap: size should return the correct number of elements") {
    val heap = new MinHeap[Int]()
    heap.insert(10)
    heap.insert(5)
    heap.insert(1)

    assertEquals(heap.size, 3)
    heap.extractMin()
    assertEquals(heap.size, 2)
  }
}
