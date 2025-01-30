package algorithms.graph

import datastructures.graph.Graph
import algorithms.graph.topologicalSort

/// Single Source Shortest Paths (SSSP) in a Directed Acyclic Graph (DAG)
/// Uses topological sorting and edge relaxation for shortest path calculation
/// Returns a map of distances from the start vertex to all other vertices

/// The algorithm runs in O(V + E) time complexity
def DAGSingleSourceShortestPaths[T](graph: Graph[T], s: T): (Map[T, Double], Map[T, T]) = {
  val maybeSortedList = topologicalSort(graph)
  val sortedList = maybeSortedList.getOrElse(List.empty[T])

  var dist: Map[T, Double] = sortedList.map(v => v -> Double.PositiveInfinity).toMap
  var pred: Map[T, T] = Map()

  dist = dist.updated(s, 0)

  for (v <- sortedList) {
    for ((u, weight) <- graph.getNeighbors(v)) {
      if (dist(v) != Double.PositiveInfinity && dist(u) > dist(v) + weight) {
        dist = dist.updated(u, dist(v) + weight)
        pred = pred.updated(u, v)
      }
    }
  }

  (dist, pred)
}
