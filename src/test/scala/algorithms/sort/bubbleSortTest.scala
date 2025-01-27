package algorithms.sort

import algorithms.sort.bubbleSort

import munit.*

class bubbleSortTest extends FunSuite {
  test("bubbleSort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    bubbleSort(arr)
    assertEquals(arr.toSeq, Array.range(1, 13).toSeq)
  }
  test("bubbleSort sorts arrays  with negatives correctly") {
    val arr = Array(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    bubbleSort(arr)
    assertEquals(arr.toSeq, Array.range(-4, 13).toSeq)
  }

  test("bubbleSort sorts arrays with duplicates") {
    val arr = Array(4, 2, 1, 5, 3, 2)
    bubbleSort(arr)
    assertEquals(arr.toSeq, Array(1, 2, 2, 3, 4, 5).toSeq)
  }

  test("bubbleSort handles singleton arrays") {
    val arr = Array(1)
    bubbleSort(arr)
    assertEquals(arr.toSeq, Array(1).toSeq)
  }
  test("bubbleSort-sort handles empty arrays") {
    val arr = Array.empty[Int]
    bubbleSort(arr)
    assertEquals(arr.toSeq, Array.empty[Int].toSeq)
  }

}
