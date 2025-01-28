package datastructures.hash

import scala.collection.mutable.ArrayBuffer

/// A simple hash table implementation using separate chaining for collision handling
/// In this implementation, each bucket is a linked list (ArrayBuffer) that stores key-value pairs.
/// This provides O(1) average time complexity for insertions, deletions, and lookups.

class HashTable[K, V](size: Int = 16) {
  // ArrayBuffer to store the hash table buckets
  private val table: ArrayBuffer[ArrayBuffer[(K, V)]] = ArrayBuffer.fill(size)(ArrayBuffer.empty[(K, V)])

  // Simple hash function to determine the index for a given key
  private def hash(key: K): Int = {
    key.hashCode % size
  }

  // Insert a key-value pair into the hash table
  def insert(key: K, value: V): Unit = {
    val index = hash(key)
    val bucket = table(index)

    // Check if the key already exists in the bucket, if so, update the value
    val existing = bucket.indexWhere(_._1 == key)
    if (existing != -1) {
      bucket(existing) = (key, value) // Update value
    } else {
      bucket.append((key, value)) // Insert new key-value pair
    }
  }

  // Retrieve the value associated with a given key
  def get(key: K): Option[V] = {
    val index = hash(key)
    val bucket = table(index)

    // Look for the key in the corresponding bucket
    bucket.find(_._1 == key).map(_._2) // If found, return the value; otherwise None
  }

  // Remove a key-value pair from the hash table
  def remove(key: K): Unit = {
    val index = hash(key)
    val bucket = table(index)

    // Find the index of the key-value pair and remove it
    val existing = bucket.indexWhere(_._1 == key)
    if (existing != -1) {
      bucket.remove(existing)
    }
  }

  // Check if the hash table contains a key
  def contains(key: K): Boolean = {
    val index = hash(key)
    val bucket = table(index)
    bucket.exists(_._1 == key) // Check if the key exists in the bucket
  }

  /// Return the size (number of key-value pairs) in the hash table
  def getSize: Int = table.flatten.size

}
