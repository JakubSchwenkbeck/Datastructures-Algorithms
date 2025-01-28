package algorithms.sort

import algorithms.sort.heapSort

import munit.*

class heapSortTest extends FunSuite {
  test("heap-sort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val res = heapSort(arr)
    assertEquals(res.toSeq, Array.range(1, 13).toSeq)
  }
  test("heap-sort sorts arrays  with negatives correctly") {
    val arr = Array(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val res = heapSort(arr)
    assertEquals(res.toSeq, Array.range(-4, 13).toSeq)
  }

  test("heap-sort sorts arrays with duplicates") {
    val arr = Array(4, 2, 1, 5, 3, 2)
    val res = heapSort(arr)
    assertEquals(res.toSeq, Array(1, 2, 2, 3, 4, 5).toSeq)
  }

  test("heap-sort handles singleton arrays") {
    val arr = Array(1)
    val res = heapSort(arr)
    assertEquals(res.toSeq, Array(1).toSeq)
  }
  test("heap-sort handles empty arrays") {
    val arr = Array.empty[Int]
    val res = heapSort(arr)
    assertEquals(res.toSeq, Array.empty[Int].toSeq)
  }

}
