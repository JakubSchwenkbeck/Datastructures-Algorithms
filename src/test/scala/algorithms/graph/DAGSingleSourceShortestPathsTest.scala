package algorithms.graph

import datastructures.graph._
import munit.FunSuite

class DAGSingleSourceShortestPathsTest extends FunSuite {

  test("DAG SSSP should return correct shortest paths and predecessors") {
    val graph = new Graph[Char](true) // Directed Graph
    graph.addEdge('A', 'B', 1)
    graph.addEdge('A', 'C', 4)
    graph.addEdge('B', 'C', 2)
    graph.addEdge('B', 'D', 5)
    graph.addEdge('C', 'D', 1)

    val (distances, predecessors) = DAGSingleSourceShortestPaths(graph, 'A')

    // Expected distances from 'A'
    val expectedDistances = Map('A' -> 0.0, 'B' -> 1.0, 'C' -> 3.0, 'D' -> 4.0)
    assertEquals(distances, expectedDistances)

    // Expected predecessors
    val expectedPredecessors = Map('B' -> 'A', 'C' -> 'B', 'D' -> 'C')
    assertEquals(predecessors, expectedPredecessors)
  }

  test("DAG SSSP should work with a single-node graph") {
    val graph = new Graph[Char](true)
    graph.addVertex('A')

    val (distances, predecessors) = DAGSingleSourceShortestPaths(graph, 'A')

    assertEquals(distances, Map('A' -> 0.0))
  }
}
