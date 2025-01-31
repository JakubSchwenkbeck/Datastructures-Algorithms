package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph
import algorithms.graph.jarnikPrim

class JarnikPrimTest extends FunSuite {

  test("Jarník-Prim should compute MST correctly") {
    val graph = new Graph[String](isDirected = false)
    graph.addEdge("A", "B", 1.0)
    graph.addEdge("A", "C", 3.0)
    graph.addEdge("B", "C", 1.0)
    graph.addEdge("B", "D", 4.0)
    graph.addEdge("C", "D", 1.0)
    graph.addEdge("C", "E", 5.0)
    graph.addEdge("D", "E", 2.0)

    val mst = jarnikPrim(graph)

    val expectedMST = Set(
      ("A", "B", 1.0),
      ("B", "C", 1.0),
      ("C", "D", 1.0),
      ("D", "E", 2.0)
    )

    assertEquals(mst.size, expectedMST.size)
    assertEquals(mst.map(e => (e._1, e._2, e._3)), expectedMST)
  }

  test("Jarník-Prim should handle a single-node graph") {
    val graph = new Graph[String](isDirected = false)
    graph.addVertex("A")

    val mst = jarnikPrim(graph)

    assertEquals(mst, Set.empty)
  }

}
