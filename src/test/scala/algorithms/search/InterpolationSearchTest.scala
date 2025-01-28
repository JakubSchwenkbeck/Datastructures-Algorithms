package algorithms.search

import algorithms.search.interpolationSearch
import munit._

class InterpolationSearchTest extends FunSuite {

  test("InterpolationSearch: Element found in the array") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = interpolationSearch(Arr, 5)
    assertEquals(index, 4) // Element 5 is at index 4
  }

  test("InterpolationSearch: Element not found in the array") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = interpolationSearch(Arr, 15)
    assertEquals(index, -1) // Element 15 is not in the array
  }

  test("InterpolationSearch: First element is the target") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = interpolationSearch(Arr, 1)
    assertEquals(index, 0) // Element 1 is at index 0
  }

  test("InterpolationSearch: Last element is the target") {
    val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val index = interpolationSearch(Arr, 10)
    assertEquals(index, 9) // Element 10 is at index 9
  }

  test("InterpolationSearch: Empty array should return -1") {
    val Arr = Array.empty[Int]
    val index = interpolationSearch(Arr, 5)
    assertEquals(index, -1) // Empty array, element not found
  }

  test("InterpolationSearch: Searching for an element in a single-element array") {
    val Arr = Array(5)
    val index = interpolationSearch(Arr, 5)
    assertEquals(index, 0) // Single element array, element is found at index 0
  }

  test("InterpolationSearch: Searching for a non-existing element in a single-element array") {
    val Arr = Array(5)
    val index = interpolationSearch(Arr, 10)
    assertEquals(index, -1) // Single element array, element not found
  }

}
