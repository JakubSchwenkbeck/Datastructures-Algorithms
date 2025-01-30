package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.PriorityQueue
import scala.collection.mutable

/// Dijkstra's Algorithm
/// Finds the shortest paths from a starting node to all other nodes in a weighted graph
/// Uses a priority queue-based approach
/// Runs in O((V + E) log V) time complexity

def dijkstra[T](graph: Graph[T], start: T): Unit = {
  val distances = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  val visited = mutable.Set[T]()
  val priorityQueue = PriorityQueue[(T, Int)]()(Ordering.by(-_._2))

  distances(start) = 0
  priorityQueue.enqueue((start, 0))
  while (!priorityQueue.isEmpty) {
    val (currentNode: T, currentDistance: Int) = priorityQueue.dequeue().get // unsafe getter for now
    if (!visited.contains(currentNode)) {
      visited.add(currentNode)

      for ((neighbor, weight) <- graph.getNeighbors(currentNode)) {
        if (!visited.contains(neighbor)) {
          val newDistance = currentDistance + weight
          if (newDistance < distances(neighbor)) {
            distances(neighbor) = newDistance.toInt
            priorityQueue.enqueue((neighbor, newDistance.toInt))
          }
        }
      }
    }
  }

  distances.toMap

}
