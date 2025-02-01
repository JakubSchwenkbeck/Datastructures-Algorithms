package algorithms.sort

/// Sorting an integer array using Radix Sort.
/// Radix Sort runs in O(d * (n + k)), where d is the number of digits.
/// It uses Counting Sort as a stable subroutine.
/// Example:
/// val Arr = Array(170, 45, 75, 90, 802, 24, 2, 66)
/// val result = radixSort(Arr, 3)
/// println(result) // Returns Array(2, 24, 45, 66, 75, 90, 170, 802)

def radixSort(A: Array[Int], d: Int): Array[Int] = {
  var output = A.clone()
  var placeValue = 1

  // Apply Counting Sort for each digit (from least significant to most significant)
  for (_ <- 0 until d) {
    output = countingSortByDigit(output, placeValue)
    placeValue *= 10 // Move to the next digit place
  }

  output
}

// Counting Sort that sorts based on a specific digit place (1s, 10s, 100s, etc.)
def countingSortByDigit(A: Array[Int], placeValue: Int): Array[Int] = {
  val n = A.length
  val output: Array[Int] = new Array(n)
  val count: Array[Int] = Array.fill(10)(0) // Base 10 numbers (0-9)

  // Count occurrences of digits at the given place value
  for (i <- 0 until n) {
    val digit = (A(i) / placeValue) % 10
    count(digit) += 1
  }

  // Compute cumulative sum for stable sorting
  for (i <- 1 until 10) {
    count(i) += count(i - 1)
  }

  // Place elements in sorted order, iterating backwards for stability
  for (i <- (n - 1) to 0 by -1) {
    val digit = (A(i) / placeValue) % 10
    output(count(digit) - 1) = A(i)
    count(digit) -= 1
  }

  output
}
