package algorithms.sort

import algorithms.sort.quickSort
import munit.*

class QuickSortTest extends FunSuite {
  test("quick-sort (not in-place) sorts arrays correctly") {
    val arr = List(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val sortedArr = quickSort(arr)
    assertEquals(sortedArr, List.range(1, 13))
  }

  test("quick-sort (not in-place) sorts arrays with negatives correctly") {
    val arr = List(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val sortedArr = quickSort(arr)
    assertEquals(sortedArr, List.range(-4, 13))
  }

  test("quick-sort (not in-place) sorts arrays with duplicates") {
    val arr = List(4, 2, 1, 5, 3, 2)
    val sortedArr = quickSort(arr)
    assertEquals(sortedArr, List(1, 2, 2, 3, 4, 5))
  }

  test("quick-sort (not in-place) handles singleton arrays") {
    val arr = List(1)
    val sortedArr = quickSort(arr)
    assertEquals(sortedArr, List(1))
  }

  test("quick-sort (not in-place) handles empty arrays") {
    val arr = List.empty[Int]
    val sortedArr = quickSort(arr)
    assert(sortedArr.isEmpty)
  }
}

class GenericQuickSortTest extends FunSuite {
  test("generic-quick-sort (not in-place) sorts arrays correctly") {
    val arr = List(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val sortedArr = quickSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1))
  }

  test("generic-quick-sort (not in-place) sorts arrays with negatives correctly") {
    val arr = List(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val sortedArr = quickSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4))
  }

  test("generic-quick-sort (not in-place) handles singleton arrays") {
    val arr = List(1)
    val sortedArr = quickSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(1))
  }

  test("generic-quick-sort (not in-place) handles empty arrays") {
    val arr = List.empty[Int]
    val sortedArr = quickSort(arr, (x: Int, y: Int) => x >= y)
    assert(sortedArr.isEmpty)
  }
}
