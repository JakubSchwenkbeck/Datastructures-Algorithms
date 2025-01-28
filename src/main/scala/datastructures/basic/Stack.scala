package datastructures.basic

import datastructures.basic.StackTrait
import datastructures.basic.LinkedNode

// Stack trait: defines basic operations for a stack
trait StackTrait[T] {
  def push(item: T): Unit
  def pop(): Option[T]
  def peek(): Option[T]
  def isEmpty: Boolean
}

/// Array-based stack implementation with a fixed capacity.
/// Allows pushing and popping elements in constant time, with a stack overflow if full.
/// Example usage:
/// val stack = new ArrayStack[Int](5)
/// stack.push(1)   // Stack: [1]
/// stack.push(2)   // Stack: [1, 2]
/// stack.pop()     // Returns Some(2), Stack: [1]
/// stack.peek()    // Returns Some(1), Stack remains unchanged
/// stack.isEmpty   // Returns false
/// All operations (push, pop, peek, isEmpty) are O(1) time complexity.
class ArrayStack[T](capacity: Int) extends StackTrait[T] {
  private val array = new Array[Any](capacity)
  private var top = -1

  // Push an item onto the stack
  def push(item: T): Unit = {
    if (top >= capacity - 1) {
      throw new StackOverflowError("Stack is full")
    }
    top += 1
    array(top) = item
  }

  // Pop an item from the stack
  def pop(): Option[T] = {
    if (isEmpty) None
    else {
      val item = array(top).asInstanceOf[T]
      top -= 1
      Some(item)
    }
  }

  // Peek at the top item without removing it
  def peek(): Option[T] = {
    if (isEmpty) None
    else Some(array(top).asInstanceOf[T])
  }

  // Check if the stack is empty
  def isEmpty: Boolean = top == -1
}

/// Linked list-based stack implementation with dynamic sizing (no capacity limits).
/// Supports pushing and popping elements in constant time.
/// Example usage:
/// val stack = new Stack[Int]
/// stack.push(1)   // Stack: [1]
/// stack.push(2)   // Stack: [2, 1]
/// stack.pop()     // Returns Some(2), Stack: [1]
/// stack.peek()    // Returns Some(1), Stack remains unchanged
/// stack.isEmpty   // Returns false
/// All operations (push, pop, peek, isEmpty) are O(1) time complexity.
class Stack[T] extends StackTrait[T] {
  private var top: Option[LinkedNode[T]] = None

  // Push an item onto the stack
  def push(item: T): Unit = {
    val newNode = new LinkedNode(item, top)
    top = Some(newNode)
  }

  // Pop an item from the stack
  def pop(): Option[T] = {
    top match {
      case Some(node) =>
        top = node.next
        Some(node.value)
      case None => None
    }
  }

  // Peek at the top item without removing it
  def peek(): Option[T] = top.map(_.value)

  // Check if the stack is empty
  def isEmpty: Boolean = top.isEmpty
}
