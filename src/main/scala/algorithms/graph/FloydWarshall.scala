package algorithms.graph

import datastructures.graph.Graph

/// Floyd-Warshall Algorithm
/// Solves the all-pairs shortest paths problem for a weighted graph
/// Uses dynamic programming to compute shortest paths between all pairs of vertices

/// Returns a matrix where each entry (i, j) represents the shortest distance from vertex i to vertex j

/// The algorithm runs in O(V³) time complexity

def floydWarshall[T](graph: Graph[T]): Array[Array[Double]] = {
  val vertices = graph.getVertices.toList
  val n = vertices.length

  val D: Array[Array[Double]] = Array.fill(n, n)(Double.PositiveInfinity)

  // Fill initial distances from adjacency matrix
  for (i <- vertices.indices; j <- vertices.indices) {
    if (i == j) D(i)(j) = 0 // Distance to itself is 0
    else
      graph.getNeighbors(vertices(i)).foreach { case (neighbor, weight) =>
        if (vertices(j) == neighbor) D(i)(j) = weight
      }
  }

  // Floyd-Warshall main algorithm
  for (k <- 0 until n) {
    for (s <- 0 until n) {
      for (t <- 0 until n) {
        D(s)(t) = math.min(D(s)(t), D(s)(k) + D(k)(t))
      }
    }
  }

  D
}
