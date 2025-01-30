package algorithms.graph

import datastructures.graph._
import algorithms.graph.BFS
import algorithms.graph.DFS
import munit.FunSuite

class GraphTraversalTest extends FunSuite {

  test("BFS should return correct level-order traversal") {
    val graph = new Graph[Char](false) // Undirected Graph
    graph.addEdge('A', 'B')
    graph.addEdge('A', 'C')
    graph.addEdge('B', 'D')
    graph.addEdge('C', 'E')

    val result = BFS(graph, 'A')
    val expected = List('A', 'B', 'C', 'D', 'E')

    assertEquals(result, expected)
  }

  test("DFS should return correct depth-first traversal") {
    val graph = new Graph[Char](false) // Undirected Graph
    graph.addEdge('A', 'B')
    graph.addEdge('A', 'C')
    graph.addEdge('B', 'D')
    graph.addEdge('C', 'E')

    val result = DFS(graph, 'A')

    // Possible valid DFS orders:
    val expected1 = List('A', 'C', 'E', 'B', 'D')
    val expected2 = List('A', 'B', 'D', 'C', 'E')

    assert(expected1 == result || expected2 == result)
  }

  test("BFS should return a single node if no edges exist") {
    val graph = new Graph[Int](false)
    graph.addVertex(1)

    val result = BFS(graph, 1)
    assertEquals(result, List(1))
  }

  test("DFS should return a single node if no edges exist") {
    val graph = new Graph[Int](false)
    graph.addVertex(1)

    val result = DFS(graph, 1)
    assertEquals(result, List(1))
  }

  test("BFS should correctly traverse a directed graph") {
    val graph = new Graph[Char](true) // Directed Graph
    graph.addEdge('A', 'B')
    graph.addEdge('A', 'C')
    graph.addEdge('B', 'D')
    graph.addEdge('C', 'E')

    val result = BFS(graph, 'A')
    val expected = List('A', 'B', 'C', 'D', 'E')

    assertEquals(result, expected)
  }

  test("DFS should correctly traverse a directed graph") {
    val graph = new Graph[Char](true) // Directed Graph
    graph.addEdge('A', 'B')
    graph.addEdge('A', 'C')
    graph.addEdge('B', 'D')
    graph.addEdge('C', 'E')

    val result = DFS(graph, 'A')

    val expected1 = List('A', 'C', 'E', 'B', 'D')
    val expected2 = List('A', 'B', 'D', 'C', 'E')

    assert(expected1 == result || expected2 == result)
  }
}
