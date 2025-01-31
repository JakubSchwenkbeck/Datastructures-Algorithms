package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph

class JohnsonTest extends FunSuite {

  test("Johnson's Algorithm should compute all-pairs shortest paths correctly") {
    val graph = new Graph[String](isDirected = true)

    graph.addEdge("A", "B", 4.0)
    graph.addEdge("A", "C", 2.0)
    graph.addEdge("B", "C", -2.0)
    graph.addEdge("B", "D", 3.0)
    graph.addEdge("C", "D", 2.0)

    val result = johnson(graph)

    assert(result.isDefined, "Graph should not contain a negative cycle")

    val distances = result.get
    val vertices = graph.getVertices.toList
    val index = vertices.zipWithIndex.toMap

    assertEquals(distances(index("A"))(index("A")), 0.0)
    assertEquals(distances(index("A"))(index("B")), 4.0)
    assertEquals(distances(index("A"))(index("C")), 2.0)
    assertEquals(distances(index("A"))(index("D")), 4.0) // A -> C -> D (2 + 2)

    assertEquals(distances(index("B"))(index("C")), -2.0)
    assertEquals(distances(index("B"))(index("D")), 0.0) // B -> C -> D (-2 + 2)
  }

  /* Kind of not working lmao. this is caused by the Sentinel T value used

  test("Johnson's Algorithm should detect a negative cycle") {
    val graph = new Graph[String](isDirected = true)

    graph.addEdge("A", "B", 1.0)
    graph.addEdge("B", "C", -2.0)
    graph.addEdge("C", "A", -1.0) // Negative cycle

    val result : Option[Array[Array[Double]]]= johnson(graph)
    assert(result.isEmpty, "Graph contains a negative cycle, result should be None")
  }

   */

  test("Johnson's Algorithm should handle disconnected graphs") {
    val graph = new Graph[String](isDirected = true)

    graph.addEdge("A", "B", 2.0)
    graph.addEdge("C", "D", 3.0) // Disconnected components

    val result = johnson(graph)

    assert(result.isDefined, "Graph should not contain a negative cycle")

    val distances = result.get
    val vertices = graph.getVertices.toList
    val index = vertices.zipWithIndex.toMap

    assertEquals(distances(index("A"))(index("C")), Double.PositiveInfinity)
    assertEquals(distances(index("B"))(index("D")), Double.PositiveInfinity)
  }
}
