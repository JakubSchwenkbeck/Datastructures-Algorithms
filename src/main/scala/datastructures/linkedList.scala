package datastructures

// Define the Node class for the linked list
class LinkedNode[T](val value: T, var next: Option[LinkedNode[T]] = None)
