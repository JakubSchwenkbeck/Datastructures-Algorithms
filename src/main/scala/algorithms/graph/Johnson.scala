package algorithms.graph

import algorithms.graph.bellmanFord
import datastructures.graph.Graph

/// Johnson's Algorithm
/// Computes all-pairs shortest paths in a weighted graph
/// Uses Bellman-Ford to reweight edges, then runs Dijkstra for each vertex
/// Efficient for sparse graphs compared to Floyd-Warshall

/// Returns a matrix where entry (i, j) represents the shortest path from vertex i to vertex j

/// The algorithm runs in O(V * E + V * (V log V)) time complexity

def johnson[T](graph: Graph[T]): Option[Array[Array[Double]]] = {
  val vertices = graph.getVertices.toList
  val n = vertices.length

  //  Create a new vertex 'Q' and connect it to all others with weight 0
  val extendedGraph = new Graph[T](isDirected = true)
  vertices.foreach(v => extendedGraph.addEdge(null.asInstanceOf[T], v, 0.0)) // Q → all vertices

  // Run Bellman-Ford from 'Q' to detect negative cycles and get vertex potentials
  val (h, noNegativeCycle) = bellmanFord(extendedGraph, null.asInstanceOf[T])
  if (!noNegativeCycle) return None // Negative cycle detected

  println(noNegativeCycle)

  //  Reweight the graph to ensure no negative edges
  val reweightedGraph = new Graph[T](isDirected = true)
  for (edge <- graph.getEdges) {
    val newWeight = edge.weight + h(edge.src) - h(edge.dest)
    reweightedGraph.addEdge(edge.src, edge.dest, newWeight)
  }

  // Run Dijkstra from each vertex
  val shortestPaths = Array.fill(n, n)(Double.PositiveInfinity)
  for (i <- vertices.indices) {
    val source = vertices(i)
    val distances = dijkstra(reweightedGraph, source) // Run Dijkstra from this vertex

    for (j <- vertices.indices) {
      val target = vertices(j)
      if (distances.contains(target)) {
        // Reverse the reweighting transformation
        shortestPaths(i)(j) = distances(target) - h(source) + h(target)
      }
    }
  }

  Some(shortestPaths)
}
