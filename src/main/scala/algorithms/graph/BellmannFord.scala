package algorithms.graph

import datastructures.graph.Graph
import scala.collection.mutable
/// Bellman-Ford Algorithm

/// Computes the shortest paths from a single source vertex to all other vertices in a weighted graph
/// Supports graphs with negative weight edges and detects negative cycles
/// Uses edge relaxation over |V| - 1 iterations

/// Returns a map of shortest distances from the source vertex and a boolean indicating cycle detection

/// The algorithm runs in O(V * E) time complexity

def bellmanFord[T](graph: Graph[T], start: T): (mutable.Map[T, Int], Boolean) = {
  val distances = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  val pi = mutable.Map[T, T]()

  distances(start) = 0

  for (vertex <- graph.vertices) {
    if vertex != start then distances(vertex) = Int.MaxValue
  }
  for (_ <- 1 until graph.vertices.length - 1) {
    for (edge <- graph.edges) {
      if (distances(edge.dest) > distances(edge.src) + edge.weight.toInt) {
        distances(edge.dest) = distances(edge.src) + edge.weight.toInt
        pi(edge.dest) = edge.src
      }
    }
  }
  var hasNegativeCycle = false
  for (edge <- graph.edges) {
    if (distances(edge.dest) > distances(edge.src) + edge.weight.toInt) {
      hasNegativeCycle = true
    }
  }

  (distances, !hasNegativeCycle) // Return distances and whether there is NO negative cycle
}
