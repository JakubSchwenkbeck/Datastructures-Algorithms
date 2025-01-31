package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph
import algorithms.graph.floydWarshall

class FloydWarshallTest extends FunSuite {

  test("Floyd-Warshall should compute all-pairs shortest paths correctly") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 3.0)
    graph.addEdge("A", "C", 1.0)
    graph.addEdge("B", "C", 7.0)
    graph.addEdge("B", "D", 5.0)
    graph.addEdge("C", "D", 2.0)

    val distances = floydWarshall(graph)

    val vertices = graph.getVertices.toList
    val index = vertices.zipWithIndex.toMap

    assertEquals(distances(index("A"))(index("A")), 0.0)
    assertEquals(distances(index("A"))(index("B")), 3.0)
    assertEquals(distances(index("A"))(index("C")), 1.0)
    assertEquals(distances(index("A"))(index("D")), 3.0) // A -> C -> D (1 + 2)

    assertEquals(distances(index("B"))(index("D")), 5.0)
    assertEquals(distances(index("C"))(index("D")), 2.0)
  }

  test("Floyd-Warshall should handle disconnected graphs") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 2.0)
    graph.addEdge("C", "D", 3.0) // Disconnected components

    val distances = floydWarshall(graph)
    val vertices = graph.getVertices.toList
    val index = vertices.zipWithIndex.toMap

    assertEquals(distances(index("A"))(index("C")), Double.PositiveInfinity)
    assertEquals(distances(index("B"))(index("D")), Double.PositiveInfinity)
  }
}
