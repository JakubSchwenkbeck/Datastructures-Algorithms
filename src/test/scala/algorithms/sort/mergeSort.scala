package algorithms.sort

import algorithms.sort.mergeSort
import munit.*

class MergeSortTest extends FunSuite {
  test("merge-sort (not in-place) sorts arrays correctly") {
    val arr = List(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val sortedArr = mergeSort(arr)
    assertEquals(sortedArr, List.range(1, 13))
  }

  test("merge-sort (not in-place) sorts arrays with negatives correctly") {
    val arr = List(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val sortedArr = mergeSort(arr)
    assertEquals(sortedArr, List.range(-4, 13))
  }

  test("merge-sort (not in-place) sorts arrays with duplicates") {
    val arr = List(4, 2, 1, 5, 3, 2)
    val sortedArr = mergeSort(arr)
    assertEquals(sortedArr, List(1, 2, 2, 3, 4, 5))
  }

  test("merge-sort (not in-place) handles singleton arrays") {
    val arr = List(1)
    val sortedArr = mergeSort(arr)
    assertEquals(sortedArr, List(1))
  }

  test("merge-sort (not in-place) handles empty arrays") {
    val arr = List.empty[Int]
    val sortedArr = mergeSort(arr)
    assert(sortedArr.isEmpty)
  }
}

class GenericMergeSortTest extends FunSuite {
  test("generic-merge-sort (not in-place) sorts arrays correctly") {
    val arr = List(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val sortedArr = mergeSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1))
  }

  test("generic-merge-sort (not in-place) sorts arrays with negatives correctly") {
    val arr = List(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val sortedArr = mergeSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4))
  }

  test("generic-merge-sort (not in-place) sorts arrays with duplicates") {
    val arr = List(4, 2, 1, 5, 3, 2)
    val sortedArr = mergeSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(5, 4, 3, 2, 1))
  }

  test("generic-merge-sort (not in-place) handles singleton arrays") {
    val arr = List(1)
    val sortedArr = mergeSort(arr, (x: Int, y: Int) => x >= y)
    assertEquals(sortedArr, List(1))
  }

  test("generic-merge-sort (not in-place) handles empty arrays") {
    val arr = List.empty[Int]
    val sortedArr = mergeSort(arr, (x: Int, y: Int) => x >= y)
    assert(sortedArr.isEmpty)
  }
}
