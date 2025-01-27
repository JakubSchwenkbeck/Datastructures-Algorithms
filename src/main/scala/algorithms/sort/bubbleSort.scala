package algorithms.sort

/// Sorting an integer array by bubbleSort, in place of the argument Array
/// The Array needs to be mutable in order to be in place
/// Example
/// val Arr = Array( 1 , 5 , 4 , 2 , 3 )
/// bubbleSort(Arr)
/// println(Arr) // Returns ( 1 , 2 , 3 , 4 , 5 )
/// BubbleSort sort runs in O(n²)
def bubbleSort(arr: Array[Int]): Unit = {
  require(arr != null, "Input array must not be null") // Ensure valid input
  val n = arr.length

  for (i <- 0 until n - 1) { // Iterate through array passes
    for (j <- 1 until (n - i)) { // BubbleSort up the largest element in each pass
      if (arr(j) < arr(j - 1)) { // Swap if elements are out of order
        val temp = arr(j)
        arr(j) = arr(j - 1)
        arr(j - 1) = temp
      }
    }
  }
}
