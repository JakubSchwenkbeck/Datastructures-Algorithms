package algorithms.sort

import algorithms.sort.countingSort

import munit.*

class CountingSortTest extends FunSuite {
  test("countingSort sorts arrays correctly") {
    val arr = Array(4, 1, 3, 1, 2, 4, 6)
    val res = countingSort(arr, 6)
    assertEquals(res.toSeq, Array(1, 1, 2, 3, 4, 4, 6).toSeq)
  }
  test("countingSort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val res = countingSort(arr, 12)
    assertEquals(res.toSeq, Array.range(1, 13).toSeq)
  }
}
