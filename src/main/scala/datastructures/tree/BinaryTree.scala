package datastructures.tree

// Define the BinaryNode case class
case class BinaryNode[T](value: T, left: Option[BinaryNode[T]] = None, right: Option[BinaryNode[T]] = None) {

  // In-order traversal (Left, Root, Right)
  def inOrderTraversal: List[T] = {
    left.map(_.inOrderTraversal).getOrElse(Nil) ::: List(value) ::: right.map(_.inOrderTraversal).getOrElse(Nil)
  }

  // Pre-order traversal (Root, Left, Right)
  def preOrderTraversal: List[T] = {
    List(value) ::: left.map(_.preOrderTraversal).getOrElse(Nil) ::: right.map(_.preOrderTraversal).getOrElse(Nil)
  }

  // Post-order traversal (Left, Right, Root)
  def postOrderTraversal: List[T] = {
    left.map(_.postOrderTraversal).getOrElse(Nil) ::: right.map(_.postOrderTraversal).getOrElse(Nil) ::: List(value)
  }
}
