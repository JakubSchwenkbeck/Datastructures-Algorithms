package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph
import algorithms.graph.bellmanFord

class BellmanFordTest extends FunSuite {

  test("Bellman-Ford should compute shortest paths correctly") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 4.0)
    graph.addEdge("A", "C", 1.0)
    graph.addEdge("C", "B", 2.0)
    graph.addEdge("B", "D", 1.0)
    graph.addEdge("C", "D", 5.0)

    val (distances, hasNoNegativeCycle) = bellmanFord(graph, "A")

    assertEquals(hasNoNegativeCycle, true)
    assertEquals(distances("A"), 0)
    assertEquals(distances("B"), 3) // A -> C -> B (1 + 2)
    assertEquals(distances("C"), 1) // A -> C
    assertEquals(distances("D"), 4) // A -> C -> B -> D (1 + 2 + 1)
  }

  test("Bellman-Ford should detect a negative cycle") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 1.0)
    graph.addEdge("B", "C", -2.0)
    graph.addEdge("C", "A", -1.0) // Negative cycle

    val (_, hasNoNegativeCycle) = bellmanFord(graph, "A")

    assertEquals(hasNoNegativeCycle, false)
  }
}
