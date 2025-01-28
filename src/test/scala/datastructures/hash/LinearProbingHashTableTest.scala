package datastructures.hash

import datastructures.hash.LinearProbingHashTable
import munit.FunSuite

class LinearProbingHashTableTest extends FunSuite {

  test("LinearProbingHashTable: insert and get") {
    val table = new LinearProbingHashTable[Int, String]()
    table.insert(1, "One")
    table.insert(2, "Two")
    table.insert(3, "Three")

    assertEquals(table.get(1), Some("One"))
    assertEquals(table.get(2), Some("Two"))
    assertEquals(table.get(3), Some("Three"))
  }

  test("LinearProbingHashTable: remove key") {
    val table = new LinearProbingHashTable[Int, String]()
    table.insert(1, "One")
    table.remove(1)

    assertEquals(table.get(1), None)
  }

  test("LinearProbingHashTable: contains key") {
    val table = new LinearProbingHashTable[Int, String]()
    table.insert(1, "One")

    assert(table.contains(1))
    assert(!table.contains(2))
  }
}
