package algorithms.sort

import algorithms.sort.insertionSortInPlace
import algorithms.sort.insertionSortNotInPlace

import munit.*

class insertionSortInPlaceTest extends FunSuite {
  test("insertion-sort sorts arrays correctly") {
    val arr = Array(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    insertionSortInPlace(arr)
    assertEquals(arr.toSeq, Array.range(1, 13).toSeq)
  }
  test("insertion-sort sorts arrays  with negatives correctly") {
    val arr = Array(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    insertionSortInPlace(arr)
    assertEquals(arr.toSeq, Array.range(-4, 13).toSeq)
  }

  test("insertion-sort sorts arrays with duplicates") {
    val arr = Array(4, 2, 1, 5, 3, 2)
    insertionSortInPlace(arr)
    assertEquals(arr.toSeq, Array(1, 2, 2, 3, 4, 5).toSeq)
  }

  test("insertion-sort handles singleton arrays") {
    val arr = Array(1)
    insertionSortInPlace(arr)
    assertEquals(arr.toSeq, Array(1).toSeq)
  }
  test("insertion-sort handles empty arrays") {
    val arr = Array.empty[Int]
    insertionSortInPlace(arr)
    assertEquals(arr.toSeq, Array.empty[Int].toSeq)
  }

}

class InsertionSortNotInPlaceTest extends FunSuite {
  test("insertion-sort (not in-place) sorts arrays correctly") {
    val arr = List(4, 2, 1, 5, 6, 3, 7, 12, 8, 10, 9, 11)
    val sortedArr = insertionSortNotInPlace(arr)
    assert(sortedArr == List.range(1, 13))
  }

  test("insertion-sort (not in-place) sorts arrays with negatives correctly") {
    val arr = List(-1, 4, 2, 1, -4, 5, 6, 3, 0, 7, 12, -3, 8, 10, 9, -2, 11)
    val sortedArr = insertionSortNotInPlace(arr)
    assert(sortedArr == List.range(-4, 13))
  }

  test("insertion-sort (not in-place) sorts arrays with duplicates") {
    val arr = List(4, 2, 1, 5, 3, 2)
    val sortedArr = insertionSortNotInPlace(arr)
    assert(sortedArr == List(1, 2, 2, 3, 4, 5))
  }

  test("insertion-sort (not in-place) handles singleton arrays") {
    val arr = List(1)
    val sortedArr = insertionSortNotInPlace(arr)
    assert(sortedArr == List(1))
  }

  test("insertion-sort (not in-place) handles empty arrays") {
    val arr = List.empty[Int]
    val sortedArr = insertionSortNotInPlace(arr)
    assert(sortedArr.isEmpty)
  }
}
