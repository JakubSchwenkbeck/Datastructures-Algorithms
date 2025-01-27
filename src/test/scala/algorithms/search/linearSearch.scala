package algorithms.search

import algorithms.search.linearSearch
import munit.*

class linearSearchTest extends FunSuite {

  test("linear search works for finding in simple Lists") {
    val lst = List(1, 2, 3, 4, 5)

    assertEquals(linearSearch(lst, 3), Option(2))
  }
  test("linear search works for not finding simple Lists") {
    val lst = List(1, 2, 3, 4, 5)

    assertEquals(linearSearch(lst, 6), None)
  }
  test("linear search works for finding in singleton Lists") {
    val lst = List(1)

    assertEquals(linearSearch(lst, 1), Option(0))
  }
  test("linear search works for empty Lists") {
    val lst = List()

    assertEquals(linearSearch(lst, 3), None)
  }

}
