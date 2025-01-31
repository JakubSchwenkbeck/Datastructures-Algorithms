package datastructures.graph

import scala.collection.mutable

case class Edge[T](src: T, dest: T, weight: Double)

class Graph[T](val isDirected: Boolean) {
  val adjacencyList: mutable.Map[T, mutable.ListBuffer[(T, Double)]] = mutable.Map()
  val edges: mutable.ListBuffer[Edge[T]] = mutable.ListBuffer()
  val vertices: mutable.ListBuffer[T] = mutable.ListBuffer()
  var adjacencyMatrix: Array[Array[Double]] = Array()

  def addVertex(vertex: T): Unit = {
    if (!vertices.contains(vertex)) {
      vertices.append(vertex)
      resizeMatrix()
      adjacencyList.getOrElseUpdate(vertex, mutable.ListBuffer())
    }
  }

  def addEdge(src: T, dest: T, weight: Double = 1.0): Unit = {
    addVertex(src)
    addVertex(dest)

    adjacencyList(src).append((dest, weight))
    edges.append(Edge(src, dest, weight))

    updateMatrix(src, dest, weight)
    if (!isDirected) {
      adjacencyList(dest).append((src, weight))
      updateMatrix(dest, src, weight)
    }
  }

  def getVertices: Iterable[T] = vertices
  def getEdges: Iterable[Edge[T]] = edges

  def getNeighbors(vertex: T): Iterable[(T, Double)] = adjacencyList.getOrElse(vertex, Seq())

  private def resizeMatrix(): Unit = {
    val size = vertices.length
    val newMatrix = Array.fill(size, size)(Double.PositiveInfinity)

    for (i <- vertices.indices; j <- vertices.indices) {
      if (i < adjacencyMatrix.length && j < adjacencyMatrix.length) {
        newMatrix(i)(j) = adjacencyMatrix(i)(j)
      }
    }
    adjacencyMatrix = newMatrix
  }

  private def updateMatrix(src: T, dest: T, weight: Double): Unit = {
    val srcIndex = vertices.indexOf(src)
    val destIndex = vertices.indexOf(dest)
    if (srcIndex != -1 && destIndex != -1) {
      adjacencyMatrix(srcIndex)(destIndex) = weight
    }
  }

}
