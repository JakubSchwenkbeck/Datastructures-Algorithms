package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.PriorityQueue
import scala.collection.mutable

/// Bidirectional Dijkstra's Algorithm
/// Finds the shortest path between two nodes by running Dijkstra's algorithm from both the source and target simultaneously
/// This approach can be faster than standard Dijkstra when the target is known
/// Runs in O((V + E) log V) in the worst case, but may terminate earlier

def bidirectionalDijkstra[T](graph: Graph[T], start: T, target: T): Map[T, Int] = {
  if (start == target) return Map(start -> 0)

  val forwardDistances = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  val backwardDistances = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  val forwardVisited = mutable.Set[T]()
  val backwardVisited = mutable.Set[T]()
  val forwardQueue = PriorityQueue[(T, Int)]()(Ordering.by(-_._2))
  val backwardQueue = PriorityQueue[(T, Int)]()(Ordering.by(-_._2))

  forwardDistances(start) = 0
  backwardDistances(target) = 0
  forwardQueue.enqueue((start, 0))
  backwardQueue.enqueue((target, 0))

  def processQueue(
    queue: PriorityQueue[(T, Int)],
    distances: mutable.Map[T, Int],
    visited: mutable.Set[T],
    otherDistances: mutable.Map[T, Int]
  ): Option[Int] = {
    queue.dequeue() match {
      case Some((currentNode: T, currentDistance: Int)) =>
        if (otherDistances.contains(currentNode)) return Some(currentDistance + otherDistances(currentNode))

        if (!visited.contains(currentNode)) {
          visited.add(currentNode)

          for ((neighbor, weight) <- graph.getNeighbors(currentNode)) {
            if (!visited.contains(neighbor)) {
              val newDistance = currentDistance + weight
              if (newDistance < distances(neighbor)) {
                distances(neighbor) = newDistance.toInt
                queue.enqueue((neighbor, newDistance.toInt))
              }
            }
          }
        }
      case None =>
    }
    None
  }

  // While both queues are non-empty, continue processing
  while (!forwardQueue.isEmpty && !backwardQueue.isEmpty) {
    processQueue(forwardQueue, forwardDistances, forwardVisited, backwardDistances) match {
      case Some(_) => // Found the shortest path, continue
      case None    =>
    }
    processQueue(backwardQueue, backwardDistances, backwardVisited, forwardDistances) match {
      case Some(_) => // Found the shortest path, continue
      case None    =>
    }
  }

  // Return the merged distances from both directions
  // The final result will be the combination of forward and backward distances
  forwardDistances.toMap ++ backwardDistances.toMap
}
