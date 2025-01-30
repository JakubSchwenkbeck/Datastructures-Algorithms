package algorithms.graph

import datastructures.graph._
import munit.FunSuite

class TopologicalSortTest extends FunSuite {

  test("Topological sort returns valid order for DAG") {
    val graph = new Graph[Char](true) // Directed Graph
    graph.addEdge('A', 'B')
    graph.addEdge('A', 'C')
    graph.addEdge('B', 'D')
    graph.addEdge('C', 'D')
    graph.addEdge('D', 'E')

    val result = topologicalSort(graph)

    assert(result.isDefined, "Topological sort should return Some(List) for a DAG")

    val sortedOrder = result.get
    val indexMap = sortedOrder.zipWithIndex.toMap

    // Ensure each edge (u, v) respects u before v
    for (Edge(src, dest, _) <- graph.getEdges) {
      assert(indexMap(src) < indexMap(dest), s"Edge $src -> $dest violated topological order")
    }
  }

  test("Topological sort returns None for cyclic graph") {
    val cyclicGraph = new Graph[Char](true) // Directed Graph
    cyclicGraph.addEdge('A', 'B')
    cyclicGraph.addEdge('B', 'C')
    cyclicGraph.addEdge('C', 'A') // Creates a cycle

    val result = topologicalSort(cyclicGraph)

    assert(result.isEmpty, "Topological sort should return None for cyclic graphs")
  }
}
