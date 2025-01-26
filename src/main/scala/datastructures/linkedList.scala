package datastructures

// Define the Node class for the linked list
class Node[T](val value: T, var next: Option[Node[T]] = None)
