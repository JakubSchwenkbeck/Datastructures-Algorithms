package datastructures.union

import scala.collection.mutable.ArrayBuffer

/// A Union-Find (Disjoint Set) implementation with path compression and union by rank.
///
/// Time Complexity:
/// - Find: O(α(n)), where α is the inverse Ackermann function (amortized time)
/// - Union: O(α(n))
/// - Size: O(1)
/// - IsConnected: O(α(n))
/// - AddElement: O(1)
class UnionFind[T] {
  private val parent: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
  private val rank: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
  private val elements: ArrayBuffer[T] = ArrayBuffer.empty[T]

  def addElement(element: T): Unit = {
    elements.append(element)
    parent.append(parent.size)
    rank.append(0)
  }

  private def find(index: Int): Int = {
    if (parent(index) != index) {
      parent(index) = find(parent(index)) // Path compression
    }
    parent(index)
  }

  def union(index1: Int, index2: Int): Unit = {
    val root1 = find(index1)
    val root2 = find(index2)

    if (root1 != root2) {
      // Union by rank
      if (rank(root1) > rank(root2)) {
        parent(root2) = root1
      } else if (rank(root1) < rank(root2)) {
        parent(root1) = root2
      } else {
        parent(root2) = root1
        rank(root1) += 1
      }
    }
  }

  def isConnected(index1: Int, index2: Int): Boolean = {
    find(index1) == find(index2)
  }

  def getParent(index: Int): Int = find(index)

  def size: Int = parent.size

  def getElement(index: Int): T = elements(index)

}
