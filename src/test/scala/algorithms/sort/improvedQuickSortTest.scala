package algorithms.sort

import munit.FunSuite
import algorithms.sort.randomizedQuickSort
import algorithms.sort.deterministicQuickSort

class improvedQuickSortTest extends FunSuite {
  test("randomizedQuickSort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val res = randomizedQuickSort(arr)
    assertEquals(res.toSeq, Array.range(1, 13).toSeq)
  }
  test("randomizedQuickSort sorts arrays  with negatives correctly") {
    val arr = Array(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val res = randomizedQuickSort(arr)
    assertEquals(res.toSeq, Array.range(-4, 13).toSeq)
  }

  test("randomizedQuickSort sorts arrays with duplicates") {
    val arr = Array(4, 2, 1, 5, 3, 2)
    val res = randomizedQuickSort(arr)
    assertEquals(res.toSeq, Array(1, 2, 2, 3, 4, 5).toSeq)
  }

  test("randomizedQuickSort handles singleton arrays") {
    val arr = Array(1)
    randomizedQuickSort(arr)
    assertEquals(arr.toSeq, Array(1).toSeq)
  }
  test("randomizedQuickSort-sort handles empty arrays") {
    val arr = Array.empty[Int]
    randomizedQuickSort(arr)
    assertEquals(arr.toSeq, Array.empty[Int].toSeq)
  }

  class deterministicQuickSortTest extends FunSuite {
    test("deterministicQuickSort sorts arrays correctly") {
      val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
      deterministicQuickSort(arr)
      assertEquals(arr.toSeq, Array.range(1, 13).toSeq)
    }
    test("deterministicQuickSort sorts arrays  with negatives correctly") {
      val arr = Array(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
      deterministicQuickSort(arr)
      assertEquals(arr.toSeq, Array.range(-4, 13).toSeq)
    }

    test("deterministicQuickSort sorts arrays with duplicates") {
      val arr = Array(4, 2, 1, 5, 3, 2)
      deterministicQuickSort(arr)
      assertEquals(arr.toSeq, Array(1, 2, 2, 3, 4, 5).toSeq)
    }

    test("deterministicQuickSort handles singleton arrays") {
      val arr = Array(1)
      deterministicQuickSort(arr)
      assertEquals(arr.toSeq, Array(1).toSeq)
    }
    test("deterministicQuickSort-sort handles empty arrays") {
      val arr = Array.empty[Int]
      deterministicQuickSort(arr)
      assertEquals(arr.toSeq, Array.empty[Int].toSeq)
    }

  }
}
