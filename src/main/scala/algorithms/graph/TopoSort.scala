package algorithms.graph

import datastructures.graph._
import scala.collection.mutable

//Kahns Algorithm
def topologicalSort[T](graph : Graph[T]) : Option[List[T]] =  {

  val inDegree = mutable.Map[T, Int]().withDefaultValue(0)

    for ((src, neighbors) <- graph.adjacencyList) {
      for ((dest, _) <- neighbors) {
        inDegree.update(dest, inDegree(dest) + 1)
      }
    }

    //  Initialize queue with nodes having in-degree 0
    val queue = mutable.Queue[T]()
    for (vertex <- graph.adjacencyList.keys if inDegree(vertex) == 0) {
      queue.enqueue(vertex)
    }

    val sortedList = mutable.ListBuffer[T]()

    //  Process queue
    while (queue.nonEmpty) {
      val vertex = queue.dequeue()
      sortedList.append(vertex)

      for ((neighbor, _) <- graph.adjacencyList(vertex)) {
        inDegree.update(neighbor, inDegree(neighbor) - 1)
        if (inDegree(neighbor) == 0) {
          queue.enqueue(neighbor)
        }
      }
    }

    //  Cycle check
    if (sortedList.size == graph.adjacencyList.size) Some(sortedList.toList) else None
}


