package datastructures.tree
import datastructures.tree.{TreeNode, TreeUtils}

import scala.annotation.tailrec

// Define the TreeNode class
class TreeNode[T](val value: T, var parent: Option[TreeNode[T]] = None) {
  var children: List[TreeNode[T]] = List()

  // Add a child node
  def addChild(childValue: T): TreeNode[T] = {
    val child = new TreeNode(childValue, Some(this))
    children = children :+ child
    child
  }

  // Remove a child by value
  def removeChild(childValue: T): Unit = {
    children = children.filterNot(_.value == childValue)
  }

  // Get depth of the node
  def depth: Int = parent.map(_.depth + 1).getOrElse(0)

  // Get height of the subtree
  def height: Int = if (children.isEmpty) 0 else children.map(_.height).max + 1

  // Find a node by value (Depth-First Search)
  def find(value: T): Option[TreeNode[T]] = {
    if (this.value == value) Some(this)
    else children.view.flatMap(_.find(value)).headOption
  }

  // Print the tree (Indented)
  def printTree(level: Int = 0): Unit = {
    println(" " * level + value)
    children.foreach(_.printTree(level + 2))
  }
}

// Tree utilities object
object TreeUtils {
  // Find the root of the tree
  @tailrec
  def findRoot[T](node: TreeNode[T]): TreeNode[T] = {
    node.parent match {
      case Some(parent) => findRoot(parent)
      case None         => node
    }
  }

  // Count the total number of nodes in the tree
  def countNodes[T](node: TreeNode[T]): Int = {
    1 + node.children.map(countNodes).sum
  }
}

// Example usage
object TreeExample {
  def main(args: Array[String]): Unit = {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")
    val grandchild1 = child1.addChild("Grandchild 1")

    root.printTree()

    println(s"Tree Height: ${root.height}")
    println(s"Total Nodes: ${TreeUtils.countNodes(root)}")
    println(s"Find 'Grandchild 1': ${root.find("Grandchild 1").map(_.value)}")
  }
}
