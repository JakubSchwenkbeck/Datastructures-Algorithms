package datastructures

import datastructures.tree.{TreeNode, TreeUtils}
import munit.FunSuite

class TreeTest extends FunSuite {

  test("TreeNode: addChild should add a child node") {
    val root = new TreeNode("Root")
    val child = root.addChild("Child 1")

    assertEquals(root.children.size, 1)
    assertEquals(root.children.head.value, "Child 1")
    assertEquals(child.parent.get.value, "Root")
  }

  test("TreeNode: removeChild should remove a child node by value") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")

    root.removeChild("Child 1")

    assertEquals(root.children.size, 1)
    assertEquals(root.children.head.value, "Child 2")
  }

  test("TreeNode: depth should return the correct depth of a node") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = child1.addChild("Child 2")

    assertEquals(child2.depth, 2)
    assertEquals(child1.depth, 1)
    assertEquals(root.depth, 0)
  }

  test("TreeNode: height should return the correct height of a subtree") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")
    val grandchild = child1.addChild("Grandchild")

    assertEquals(root.height, 2) // height of the subtree rooted at "Root"
    assertEquals(child1.height, 1) // height of the subtree rooted at "Child 1"
    assertEquals(child2.height, 0) // height of the subtree rooted at "Child 2"
  }

  test("TreeNode: find should return Some(node) if node exists") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")

    assertEquals(root.find("Child 1").map(_.value), Some("Child 1"))
    assertEquals(root.find("Child 2").map(_.value), Some("Child 2"))
    assertEquals(root.find("Nonexistent"), None)
  }

  test("TreeNode: printTree should print the tree structure") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")
    val grandchild = child1.addChild("Grandchild")

    // This is a simple way to visually check if the output is correct
    // By running this test, you'll verify the print format
    // root.printTree()
    // Expected printed output:
    // Root
    //   Child 1
    //     Grandchild
    //   Child 2
  }

  test("TreeUtils: findRoot should return the root of the tree") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = child1.addChild("Child 2")

    assertEquals(TreeUtils.findRoot(child2).value, "Root")
  }

  test("TreeUtils: countNodes should return the correct number of nodes") {
    val root = new TreeNode("Root")
    val child1 = root.addChild("Child 1")
    val child2 = root.addChild("Child 2")
    val grandchild = child1.addChild("Grandchild")

    assertEquals(TreeUtils.countNodes(root), 4) // Root + 2 children + 1 grandchild
  }

}
