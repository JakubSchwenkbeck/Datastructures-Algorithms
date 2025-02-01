package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph
import algorithms.graph.dijkstra
import algorithms.graph.bidirectionalDijkstra

class DijkstraTest extends FunSuite {

  test("Dijkstra should compute the shortest paths correctly for all nodes") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 3)
    graph.addEdge("A", "C", 1)
    graph.addEdge("B", "C", 7)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 2)

    val distances = dijkstra(graph, "A")

    // Directly compare map values using vertex names as keys
    assertEquals(distances("A"), 0)
    assertEquals(distances("B"), 3)
    assertEquals(distances("C"), 1)
    assertEquals(distances("D"), 3) // A -> C -> D (1 + 2)
  }

  test("Dijkstra should compute the shortest path to a target node correctly") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 3)
    graph.addEdge("A", "C", 1)
    graph.addEdge("B", "C", 7)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 2)

    val distances = dijkstra(graph, "A", "D")

    // Directly compare map values using vertex names as keys
    assertEquals(distances("A"), 0)
    assertEquals(distances("B"), 3)
    assertEquals(distances("C"), 1)
    assertEquals(distances("D"), 3) // A -> C -> D (1 + 2)
  }

  test("Dijkstra should compute shortest path correctly when there are multiple paths") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 1)
    graph.addEdge("A", "C", 4)
    graph.addEdge("B", "C", 2)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 1)

    val distances = dijkstra(graph, "A")

    // Directly compare map values using vertex names as keys
    assertEquals(distances("A"), 0)
    assertEquals(distances("B"), 1)
    assertEquals(distances("C"), 3) // A -> B -> C (1 + 2)
    assertEquals(distances("D"), 4) // A -> B -> C -> D (1 + 2 + 1)
  }

  test("Bi-directional Dijkstra should compute the shortest path to a target node correctly") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 3)
    graph.addEdge("A", "C", 1)
    graph.addEdge("B", "C", 7)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 2)

    val distances = bidirectionalDijkstra(graph, "A", "D")

    // Directly compare map values using vertex names as keys
    assertEquals(distances("A"), 0)
    assertEquals(distances("B"), 3)
    assertEquals(distances("C"), 1)
  }
}
