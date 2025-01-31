package algorithms.graph

import datastructures.graph.Graph
import datastructures.union.UnionFind
import scala.collection.mutable

/// Kruskal’s Algorithm
/// Computes the Minimum Spanning Tree (MST) of a weighted graph using a greedy approach
/// It sorts all the edges and adds them to the MST, ensuring no cycles are formed by using Union-Find.
///
/// The algorithm runs in O(E log E) time complexity due to sorting the edges

def kruskal[T](graph: Graph[T]): Set[(T, T, Double)] = {
  val edges = graph.getEdges.toList
  if (edges.isEmpty) return Set.empty // Handle empty graph

  // Initialize UnionFind for vertices
  val uf = new UnionFind[Int]()
  graph.getVertices.foreach(v => uf.addElement(graph.getVertices.toList.indexOf(v)))

  // Sort edges by their weight
  val sortedEdges = edges.sortBy(_.weight)

  val mstEdges = mutable.Set[(T, T, Double)]()

  // Iterate through sorted edges and add to MST if they don't form a cycle
  for (edge <- sortedEdges) {
    val srcIndex = graph.getVertices.toList.indexOf(edge.src)
    val destIndex = graph.getVertices.toList.indexOf(edge.dest)

    if (!uf.isConnected(srcIndex, destIndex)) {
      uf.union(srcIndex, destIndex)
      mstEdges.add((edge.src, edge.dest, edge.weight))
    }
  }

  mstEdges.toSet
}
