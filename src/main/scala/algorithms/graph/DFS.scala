package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.Stack

/// Depth-First Search (DFS)
/// Explores as deep as possible before backtracking
/// Uses a stack-based approach

/// Runs in O(V + E) time complexity

def DFS[T](graph: Graph[T], start: T): List[T] = {
  val visited = scala.collection.mutable.Set[T]()
  val stack = new Stack[T]()
  val result = scala.collection.mutable.ListBuffer[T]()

  stack.push(start)

  while (!stack.isEmpty) {
    val node = stack.pop().get

    if (!visited.contains(node)) {
      visited.add(node)
      result.append(node)

      for ((neighbor, _) <- graph.getNeighbors(node).toList.reverse) {
        if (!visited.contains(neighbor)) {
          stack.push(neighbor)
        }
      }
    }
  }

  result.toList
}
