package datastructures.graph

import scala.collection.mutable

case class Edge[T](src: T, dest: T, weight: Double)

class Graph[T](val isDirected: Boolean) {
  private val adjacencyList: mutable.Map[T, mutable.ListBuffer[(T, Double)]] = mutable.Map()
  private val edges: mutable.ListBuffer[Edge[T]] = mutable.ListBuffer()

  def addVertex(vertex: T): Unit = {
    adjacencyList.getOrElseUpdate(vertex, mutable.ListBuffer())
  }

  def addEdge(src: T, dest: T, weight: Double = 1.0): Unit = {
    addVertex(src)
    addVertex(dest)
    adjacencyList(src).append((dest, weight))
    edges.append(Edge(src, dest, weight))
    if (!isDirected) {
      adjacencyList(dest).append((src, weight))
    }
  }

  def getVertices: Iterable[T] = adjacencyList.keys

  def getEdges: Iterable[Edge[T]] = edges

  def getNeighbors(vertex: T): Iterable[(T, Double)] = adjacencyList.getOrElse(vertex, Seq())
}