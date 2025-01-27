package datastructures

import datastructures.*
import munit.*

class ArrayStackTest extends FunSuite {

  test("ArrayStack[Int]: push and pop") {
    val stack = new ArrayStack[Int](3)
    stack.push(1)
    stack.push(2)
    stack.push(3)

    assertEquals(stack.pop(), Some(3))
    assertEquals(stack.pop(), Some(2))
    assertEquals(stack.pop(), Some(1))
  }

  test("ArrayStack[Int]: peek should return top item without removing it") {
    val stack = new ArrayStack[Int](3)
    stack.push(1)
    stack.push(2)

    assertEquals(stack.peek(), Some(2))
    assertEquals(stack.pop(), Some(2))
    assertEquals(stack.peek(), Some(1))
  }

  test("ArrayStack[Int]: isEmpty should return true for empty stack") {
    val stack = new ArrayStack[Int](0)
    assert(stack.isEmpty)

  }

  test("ArrayStack[Int]: isEmpty should return false after pushing items") {
    val stack = new ArrayStack[Int](1)
    stack.push(1)
    assert(!stack.isEmpty)
  }

  test("ArrayStack[Int]: push beyond capacity should throw StackOverflowError") {
    val stack = new ArrayStack[Int](2)
    stack.push(1)
    stack.push(2)
    try {
      stack.push(3)
      assert(false) // this should never be reached
    } catch {
      case e: StackOverflowError => assert(true)
    }
  }

  test("ArrayStack[Int]: pop from empty stack should return None") {
    val stack = new ArrayStack[Int](2)
    assertEquals(stack.pop(), None)
  }
}

class StackTest extends FunSuite {

  test("Stack: push and pop") {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.push(3)

    assertEquals(stack.pop(), Some(3))
    assertEquals(stack.pop(), Some(2))
    assertEquals(stack.pop(), Some(1))
  }

  test("Stack: peek should return top item without removing it") {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)

    assertEquals(stack.peek(), Some(2))
    assertEquals(stack.pop(), Some(2))
    assertEquals(stack.peek(), Some(1))
  }

  test("Stack: isEmpty should return true for empty stack") {
    val stack = new Stack[Int]
    assert(stack.isEmpty)
  }

  test("Stack: isEmpty should return false after pushing items") {
    val stack = new Stack[Int]
    stack.push(1)
    assert(!stack.isEmpty)
  }

  test("Stack: pop from empty stack should return None") {
    val stack = new Stack[Int]
    assertEquals(stack.pop(), None)
  }

  test("Stack: push and pop with different types of data") {
    val stack = new Stack[String]
    stack.push("Hello")
    stack.push("World")

    assertEquals(stack.pop(), Some("World"))
    assertEquals(stack.pop(), Some("Hello"))
  }
}
