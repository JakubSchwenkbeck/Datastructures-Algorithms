package datastructures.tree

// Node class to represent each element of the tree
case class Node[T](value: T, left: Option[Node[T]] = None, right: Option[Node[T]] = None)

// A simple implementation of a Binary Search Tree (BST)

// Time Complexity of Basic Operations:
//   - Insert: O(log n) on average, O(n) in the worst case (unbalanced tree).
//   - Search: O(log n) on average, O(n) in the worst case (unbalanced tree).
//   - Get Min/Max: O(log n) on average, O(n) in the worst case (unbalanced tree).

class SearchTree[T: Ordering](var root: Option[Node[T]] = None) {

  // Insert a new element into the tree
  def insert(value: T): Unit = {
    root match {
      case Some(r) => insertRecursive(r, value)
      case None    => root = Some(Node(value))
    }
  }

  // Recursive helper function to insert a new value
  private def insertRecursive(node: Node[T], value: T): Node[T] = {
    if (implicitly[Ordering[T]].lt(value, node.value)) {
      node.copy(left = Some(node.left match {
        case Some(leftNode) => insertRecursive(leftNode, value)
        case None           => Node(value)
      }))
    } else {
      node.copy(right = Some(node.right match {
        case Some(rightNode) => insertRecursive(rightNode, value)
        case None            => Node(value)
      }))
    }
  }

  // Search for a value in the tree
  def search(value: T): Option[T] = searchRecursive(root, value)

  // Recursive helper function to search for a value
  private def searchRecursive(node: Option[Node[T]], value: T): Option[T] = {
    node match {
      case Some(n) if n.value == value                           => Some(n.value)
      case Some(n) if implicitly[Ordering[T]].lt(value, n.value) => searchRecursive(n.left, value)
      case Some(n)                                               => searchRecursive(n.right, value)
      case None                                                  => None
    }
  }

  // Get the minimum value in the tree
  def getMin: Option[T] = findMin(root)

  // Helper function to find the minimum value
  private def findMin(node: Option[Node[T]]): Option[T] = {
    node match {
      case Some(n) if n.left.isEmpty => Some(n.value)
      case Some(n)                   => findMin(n.left)
      case None                      => None
    }
  }

  // Get the maximum value in the tree
  def getMax: Option[T] = findMax(root)

  // Helper function to find the maximum value
  private def findMax(node: Option[Node[T]]): Option[T] = {
    node match {
      case Some(n) if n.right.isEmpty => Some(n.value)
      case Some(n)                    => findMax(n.right)
      case None                       => None
    }
  }
}
