package algorithms.graph

import munit.FunSuite
import datastructures.graph.Graph

class AStarTest extends FunSuite {

  // Example heuristic function (Manhattan Distance for grid-based problems)
  def heuristic(node: String): Int = node match {
    case "A" => 6
    case "B" => 5
    case "C" => 2
    case "D" => 0 // Target node
    case _   => Int.MaxValue
  }

  test("A* should find the shortest path in a small graph") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 1)
    graph.addEdge("A", "C", 4)
    graph.addEdge("B", "C", 2)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 1)

    val result = aStar(graph, "A", "D", heuristic)

    assertEquals(result, Some(4)) // The shortest path from A to D is via B and C: A -> B -> C -> D (1 + 2 + 1)
  }

  test("A* should handle the case where the start is the same as the target") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 1)

    val result = aStar(graph, "A", "A", heuristic)

    assertEquals(result, Some(0)) // If start and target are the same, the cost is 0
  }

  test("A* should return None if the target is unreachable") {
    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 1)
    graph.addEdge("B", "C", 1)

    val result = aStar(graph, "A", "D", heuristic)

    assertEquals(result, None) // "D" is unreachable from "A"
  }

  test("A* should work with different heuristics") {
    // Using a different heuristic function for this case
    def alternativeHeuristic(node: String): Int = node match {
      case "A" => 7
      case "B" => 4
      case "C" => 3
      case "D" => 0
      case _   => Int.MaxValue
    }

    val graph = new Graph[String](isDirected = true)
    graph.addEdge("A", "B", 3)
    graph.addEdge("A", "C")
    graph.addEdge("B", "C", 7)
    graph.addEdge("B", "D", 5)
    graph.addEdge("C", "D", 2)

    val result = aStar(graph, "A", "D", alternativeHeuristic)

    assertEquals(result, Some(3)) // The shortest path from A to D is via C: A -> C -> D (1 + 2)
  }
}
