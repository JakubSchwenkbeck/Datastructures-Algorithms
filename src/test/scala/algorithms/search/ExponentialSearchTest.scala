package algorithms.search

import algorithms.search.exponentialSearch
import munit._

class ExponentialSearchTest extends FunSuite {

  test("ExponentialSearch: Element found in the array") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = exponentialSearch(Arr, 5)
    assertEquals(index, 4) // Element 5 is at index 4
  }

  test("ExponentialSearch: Element not found in the array") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = exponentialSearch(Arr, 15)
    assertEquals(index, -1) // Element 15 is not in the array
  }

  test("ExponentialSearch: First element is the target") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = exponentialSearch(Arr, 1)
    assertEquals(index, 0) // Element 1 is at index 0
  }

  test("ExponentialSearch: Last element is the target") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = exponentialSearch(Arr, 10)
    assertEquals(index, 9) // Element 10 is at index 9
  }

  test("ExponentialSearch: Empty array should return -1") {
    val Arr = Array.empty[Int]
    val index = exponentialSearch(Arr, 5)
    assertEquals(index, -1) // Empty array, element not found
  }

  test("ExponentialSearch: Searching for an element in a single-element array") {
    val Arr = Array(5)
    val index = exponentialSearch(Arr, 5)
    assertEquals(index, 0) // Single element array, element is found at index 0
  }

  test("ExponentialSearch: Searching for a non-existing element in a single-element array") {
    val Arr = Array(5)
    val index = exponentialSearch(Arr, 10)
    assertEquals(index, -1) // Single element array, element not found
  }

}
