package algorithms.dynamic

import scala.annotation.tailrec

/**
 * Finds all optimal combinations of coins to make up the given sum.
 *
 * @param sum   The target sum.
 * @param coins The available denominations.
 * @return      A list of all optimal combinations.
 */
def dynamicAlgorithm(sum: Int, coins: List[Int]): List[List[Int]] = {
  val sortedCoins = coins.sorted(Ordering[Int].reverse) // Sort in descending order
  val solutions = sortedCoins.indices.map(i => greedy(sum, sortedCoins.drop(i))).toList

  val optimalSize = solutions.map(_.size).min // Get the minimum number of coins used
  solutions.filter(_.size == optimalSize) // Keep only optimal solutions
}

/**
 * Greedy approach: Always selects the largest possible coin.
 *
 * @param sum   The target sum.
 * @param coins The available denominations.
 * @return      A list of selected coins.
 */
def greedy(sum: Int, coins: List[Int]): List[Int] = {
  @tailrec
  def helper(remaining: Int, availableCoins: List[Int], result: List[Int]): List[Int] = {
    availableCoins match {
      case Nil => result
      case coin :: tail =>
        if (remaining >= coin) helper(remaining - coin, availableCoins, result :+ coin)
        else helper(remaining, tail, result)
    }
  }
  helper(sum, coins, List.empty)
}

/**
 * Main function to test the algorithm.
 */
@main def coinMain(): Unit = {
  val sum1 = 110
  val money1 = List(50, 2, 1)
  // Expected optimal solutions: [[50, 50, 2, 2, 2, 2, 2]]
  println(s"Optimal solutions for sum=$sum1 with coins=$money1: ${dynamicAlgorithm(sum1, money1)}")

  val sum2 = 22
  val money2 = List(18, 12, 5, 3, 1)
  // Expected optimal solutions: [[18, 3, 1], [12, 5, 5]]
  println(s"Optimal solutions for sum=$sum2 with coins=$money2: ${dynamicAlgorithm(sum2, money2)}")

  val sum3 = 30
  val money3 = List(25, 23, 7, 1)
  // Expected optimal solutions: [[23 , 7 ]]
  println(s"Optimal solutions for sum=$sum3 with coins=$money3: ${dynamicAlgorithm(sum3, money3)}")
}
