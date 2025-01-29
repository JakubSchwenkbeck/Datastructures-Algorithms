package datastructures.hash

import scala.collection.mutable.ArrayBuffer

/// A hash table implementation using linear probing for collision resolution.
///
/// Time Complexity:
/// - Insert: O(1) average, O(n) worst-case (when resizing)
/// - Get: O(1) average, O(n) worst-case (due to probing)
/// - Remove: O(1) average, O(n) worst-case (due to probing)
/// - Contains: O(1) average, O(n) worst-case (due to probing)
/// - Resize: O(n) when triggered
class LinearProbingHashTable[K, V](initialSize: Int = 16) {
  private var size = 0
  private var table: ArrayBuffer[Option[(K, V)]] = ArrayBuffer.fill(initialSize)(None)

  private def hash(key: K): Int = key.hashCode % table.size

  private def resize(): Unit = {
    val newSize = table.size * 2
    val newTable: ArrayBuffer[Option[(K, V)]] = ArrayBuffer.fill(newSize)(None) // Ensure correct type
    table.foreach {
      case Some((key, value)) => insertIntoTable(newTable, key, value)
      case None               => // No action needed for empty slots
    }
    table = newTable
  }

  private def insertIntoTable(newTable: ArrayBuffer[Option[(K, V)]], key: K, value: V): Unit = {
    var index = hash(key)
    while (newTable(index).isDefined) {
      index = (index + 1) % newTable.size // Linear Probing
    }
    newTable(index) = Some((key, value))
  }

  def insert(key: K, value: V): Unit = {
    if (size > table.size * 0.75) resize()

    insertIntoTable(table, key, value)
    size += 1
  }

  def get(key: K): Option[V] = {
    var index = hash(key)
    while (table(index).isDefined) {
      table(index) match {
        case Some((k, v)) if k == key => return Some(v)
        case _                        => index = (index + 1) % table.size
      }
    }
    None
  }

  def remove(key: K): Unit = {
    var index = hash(key)
    while (table(index).isDefined) {
      table(index) match {
        case Some((k, _)) if k == key =>
          table(index) = None
          size -= 1
          return
        case _ => index = (index + 1) % table.size
      }
    }
  }

  def contains(key: K): Boolean = get(key).isDefined

  def getSize: Int = size
}
