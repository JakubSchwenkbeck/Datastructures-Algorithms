package algorithms.dynamic

// Knapsack Problem (Bottom-Up Dynamic Programming):
// Given a budget and arrays for item prices and values, find the maximum value
// of items that can be selected without exceeding the budget.
// The solution is built using dynamic programming, where we compute the
// optimal value for each subproblem (considering each item and budget).
//
// Example:
// Given prices: [2, 3, 4], values: [3, 4, 5], budget = 5
// The optimal selection: item 1 (price 2, value 3) and item 2 (price 3, value 4)
// for a total value of 7, without exceeding the budget of 5.

def knapsack(budget: Int, prices: Array[Int], values: Array[Int]): Int = {
  val n = prices.length
  if (prices.length != values.length) throw new RuntimeException("Mismatching Arrays!")

  // Create DP table (E) to store optimal values for subproblems
  val E: Array[Array[Int]] = Array.ofDim[Int](budget + 1, n + 1)

  // Initialize base case
  for (j <- 0 to n) { E(0)(j) = 0 }
  for (b <- 0 to budget) { E(b)(0) = 0 }

  // Fill DP table using bottom-up
  for (i <- 1 to n) { // Loop over items
    for (b <- 1 to budget) { // Loop over possible budgets
      if (prices(i - 1) <= b) { // If the current item can be included
        E(b)(i) = Math.max(E(b)(i - 1), E(b - prices(i - 1))(i - 1) + values(i - 1))
      } else {
        E(b)(i) = E(b)(i - 1) // If the item can't be included, take the value from the previous row
      }
    }
  }

  // Return the maximum value with the given budget
  E(budget)(n)
}

@main
def knapSackMain(): Unit = {
  val prices: Array[Int] = Array(2, 3, 4)
  val values: Array[Int] = Array(3, 4, 5)
  val budget = 5
  val result = knapsack(budget, prices, values)
  println(s"The knapsack solution for prices [2,3,4] and values [3,4,5] with budget 5 is $result")
}
