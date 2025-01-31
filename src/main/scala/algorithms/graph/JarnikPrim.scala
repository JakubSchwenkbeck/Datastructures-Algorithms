package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.PriorityQueue
import scala.collection.mutable
import scala.util.control.Breaks._

/// Jarník-Prim’s Algorithm
/// Computes the Minimum Spanning Tree (MST) of a weighted graph
/// Uses a greedy approach with a priority queue to select the lightest edge
/// Works for connected, undirected graphs

/// Returns a set of edges forming the MST

/// The algorithm runs in O(E log V) time complexity

def jarnikPrim[T](graph: Graph[T]): Set[(T, T, Double)] = {
  val vertices = graph.getVertices.toList
  if (vertices.isEmpty) return Set.empty // Handle empty graph

  val mstEdges = mutable.Set[(T, T, Double)]()
  val visited = mutable.Set[T]()
  val priorityQueue = PriorityQueue[(T, T, Double)]()(Ordering.by(-_._3)) // Min-Heap

  //  Start from an arbitrary vertex
  val start = vertices.head
  visited.add(start)

  // Add all edges from the start vertex to the priority queue
  for ((neighbor, weight) <- graph.getNeighbors(start)) {
    priorityQueue.enqueue((start, neighbor, weight))
  }

  //  Main loop
  while (visited.size < vertices.size && !priorityQueue.isEmpty) {
    breakable {
      val (u: T, v: T, weight: Double) = priorityQueue.dequeue().get

      if (visited.contains(v)) break // Ignore edges leading to already visited nodes

      // Add the edge to MST
      mstEdges.add((u, v, weight))
      visited.add(v)

      // Add new edges from v to priority queue
      for ((neighbor, w) <- graph.getNeighbors(v)) {
        if (!visited.contains(neighbor)) {
          priorityQueue.enqueue((v, neighbor, w))
        }
      }
    }
  }

  mstEdges.toSet
}
