package algorithms.graph

import datastructures.graph.Graph
import scala.collection.mutable

def bellmanford[T](graph: Graph[T], start: T): (mutable.Map[T, Int], Boolean) = {
  val distances = mutable.Map[T, Int]().withDefaultValue(Int.MaxValue)
  val pi = mutable.Map[T, T]()

  distances(start) = 0

  for (vertex <- graph.adjacencyList.keys.toList) {
    if vertex != start then distances(vertex) = Int.MaxValue
  }
  for (i <- 1 until graph.adjacencyList.keys.toList.length) {
    for (edge <- graph.edges) {
      if (distances(edge.dest) > distances(edge.src) + edge.weight.toInt) {
        distances(edge.dest) = distances(edge.src) + edge.weight.toInt
        pi(edge.dest) = edge.src
      }
    }
  }
  for (edge <- graph.edges) {
    if (distances(edge.dest) > distances(edge.src) + edge.weight.toInt) {
      return (distances, false)
    }
  }

  (distances, true)
}
