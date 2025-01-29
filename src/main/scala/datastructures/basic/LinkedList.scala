package datastructures.basic

/// Define a node for a singly linked list. Each node stores a value and a reference to the next node.
///
/// Time Complexity:
/// - Accessing the value: O(1)
/// - Insertion/Deletion: O(1) at the head (or known position)

class LinkedNode[T](val value: T, var next: Option[LinkedNode[T]] = None)
