package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.Queue

/// Breadth-First Search (BFS)
/// Traverses the graph level by level from a given start node
/// Uses a queue-based approach

/// Runs in O(V + E) time complexity

def BFS[T](graph: Graph[T], start: T): List[T] = {
  val visited = scala.collection.mutable.Set[T]()
  val queue = new Queue[T]()
  val result = scala.collection.mutable.ListBuffer[T]()

  queue.enqueue(start)
  visited.add(start)

  while (!queue.isEmpty) {
    val node = queue.dequeue().get
    result.append(node)

    for ((neighbor, _) <- graph.getNeighbors(node)) {
      if (!visited.contains(neighbor)) {
        visited.add(neighbor)
        queue.enqueue(neighbor)
      }
    }
  }

  result.toList
}
