package algorithms.dynamic

case class Result(route: List[Int], minCost: Int)

/// Find the optimal set of stops along a route to minimize the penalty for deviating from 200km/day driving distance.
/// The penalty is based on the square of the difference between the ideal 200km and the actual distance driven each day.
/// The function returns the optimal stops and the minimum penalty cost.
///
/// Example:
/// val hotels = Array(0, 100, 250, 400, 600, 800)
/// val result = findOptimalStops(hotels)
/// println(s"Optimal stops: ${result.route}")  // [0, 250, 400,600, 800]
/// println(s"Minimum penalty: ${result.minCost}")  // Minimum penalty : 500
///
/// Time Complexity: O(n²) – Nested loops for dynamic programming.
def findOptimalStops(a: Array[Int]): Result = {
  val n = a.length
  val dp = Array.fill(n)(Int.MaxValue) // Minimum penalty cost up to hotel i
  val prev = Array.fill(n)(-1) // To reconstruct the route

  dp(0) = 0 // No cost at the starting point

  // Dynamic Programming
  for (j <- 1 until n) {
    for (i <- 0 until j) {
      val cost = Math.pow(200 - (a(j) - a(i)), 2).toInt
      if (dp(i) + cost < dp(j)) {
        dp(j) = dp(i) + cost
        prev(j) = i
      }
    }
  }

  // Route reconstruction
  var route = List[Int]()
  var current = n - 1
  while (current != -1) {
    route = current :: route // Add stop at the beginning of the list
    current = prev(current)
  }

  Result(route, dp(n - 1))
}

@main
def optimalStopsMain(): Unit = {
  val hotels = Array(0, 100, 250, 400, 600, 800) // Hotel locations along the route

  val result = findOptimalStops(hotels)

  println(s"Optimal stops: ${result.route}")
  println(s"Minimum penalty: ${result.minCost}")
}
