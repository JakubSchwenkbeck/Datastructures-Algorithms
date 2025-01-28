package algorithms.sort

import munit.FunSuite
import algorithms.sort.radixSort

class RadixSortTest extends FunSuite {
  test("radixSort sorts arrays correctly") {
    val arr = Array(4, 1, 3, 1, 2, 4, 6)
    val res = radixSort(arr, 1)
    assertEquals(res.toSeq, Array(1, 1, 2, 3, 4, 4, 6).toSeq)
  }
  test("radixSort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val res = radixSort(arr, 2)
    assertEquals(res.toSeq, Array.range(1, 13).toSeq)
  }
  test("radixSort sorts arrays correctly") {
    val arr = Array(400, 223, 1454, 522, 611, 32, 73, 124, 81, 120, 922, 1122, 23, 211, 23, 1, 45, 6, 7)
    val res = radixSort(arr, 4)
    assertEquals(
      res.toSeq,
      Array(1, 6, 7, 23, 23, 32, 45, 73, 81, 120, 124, 211, 223, 400, 522, 611, 922, 1122, 1454).toSeq
    )
  }
}
