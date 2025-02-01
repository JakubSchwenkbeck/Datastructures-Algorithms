package algorithms.graph

import datastructures.graph.Graph
import datastructures.basic.PriorityQueue
import scala.collection.mutable

/// A* Algorithm
/// Finds the shortest path from a starting node to a target node in a weighted graph.
/// Uses a heuristic function to guide the search towards the target node more efficiently.
/// Is proven to be the best algorithm
/// https://github.com/bb4/bb4-A-star/blob/master/scala-source/com/barrybecker4/search/AStarSearch.scala
/// Runs in O((V + E) log V) in the worst case, depending on the quality of the heuristic.

def aStar[T](graph: Graph[T], start: T, target: T, heuristic: T => Int): Option[Int] = {
  // Check if start and target are the same, return 0 if true
  if (start == target) return Some(0)

  // Map to store the shortest known distance from the start node to each node
  val gScores = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  gScores(start) = 0

  // Map to store the estimated distance from each node to the target
  val fScores = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  fScores(start) = heuristic(start)

  // Set to keep track of visited nodes
  val visited = mutable.Set[T]()

  // Priority queue (min-heap) to select the node with the lowest f-score
  val priorityQueue = PriorityQueue[(T, Int)]()(Ordering.by(-_._2))
  priorityQueue.enqueue((start, fScores(start)))

  // Process the queue until it is empty or the target is reached
  while (!priorityQueue.isEmpty) {
    val (currentNode, currentFScore) = priorityQueue.dequeue().get

    // If we reach the target, return the cost to get here
    if (currentNode == target) return Some(gScores(currentNode))

    // If node has been visited, skip it
    if (!visited.contains(currentNode)) {
      visited.add(currentNode)

      // For each neighbor of the current node, calculate the potential new g-score and f-score
      for ((neighbor, weight) <- graph.getNeighbors(currentNode)) {
        val tentativeGScore: Int = gScores(currentNode) + weight.toInt
        if (tentativeGScore < gScores(neighbor)) {
          gScores(neighbor) = tentativeGScore
          fScores(neighbor) = tentativeGScore + heuristic(neighbor)
          priorityQueue.enqueue((neighbor, fScores(neighbor)))
        }
      }
    }
  }

  // Return None if the target is unreachable
  None
}
