package algorithms.dynamic

import algorithms.sort.mergeSort


/// you plan a car drive from city A to city B with an E-car
/// you have a max reach of n kilometres, and want to minimize stops at loading stations
///
/// greedy : always take the loading station closest below n from your current position
///
/// Time complexity:  sorting the List dominates, which results in : O(m * log m)
/**
* @param n  Maximum reach of the E-car in kilometers.
* @param d  Total road distance between city A and city B.
* @param ls List of charging station distances from the starting point.
* @return   A list of selected charging stations minimizing the number of stops.
  */
def greedyCarDrive(n: Int, d: Int, ls: List[Int]): List[Int] = {
  var currentPos: Int = 0
  var result: List[Int] = List.empty[Int]
  val sortedLS: List[Int] = mergeSort(ls) :+ d // Add destination to the list
  var lastStop = 0

  while (currentPos < d) {
    val reachable = sortedLS.filter(_ <= currentPos + n) // Stations within reach
    if (reachable.isEmpty || reachable.last == lastStop) return List() // No valid stop, return failure case

    lastStop = reachable.last
    if (lastStop < d) result = result :+ lastStop
    currentPos = lastStop
  }

  result
}

object Main extends App {
  private val maxReach = 10
  private val distance = 25
  private val stations = List(3, 7, 12, 18, 22)

  private val stops = greedyCarDrive(maxReach, distance, stations)
  println(s"Charging stops: $stops")
}

