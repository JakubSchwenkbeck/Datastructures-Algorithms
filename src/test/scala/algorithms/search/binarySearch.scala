package algorithms.search

import algorithms.search.binarySearch
import munit.*

class binarySearchTest extends FunSuite {

  test("binary search works for finding in simple Lists") {
    val lst = List(1, 2, 3, 4, 5)

    assertEquals(binarySearch(lst, 3), Option(2))
  }
  test("binary search works for not finding simple Lists") {
    val lst = List(1, 2, 3, 4, 5)

    assertEquals(binarySearch(lst, 6), None)
  }
  test("binary search works for finding in singleton Lists") {
    val lst = List(1)

    assertEquals(binarySearch(lst, 1), Option(0))
  }
  test("binary search works for empty Lists") {
    val lst = List()

    assertEquals(binarySearch(lst, 3), None)
  }

}
