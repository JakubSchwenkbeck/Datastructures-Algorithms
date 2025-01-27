package algorithms.sort

/// Sorting an integer array by selection, in place of the argument Array
/// The Array needs to be mutable in order to be in place
/// Example
/// val Arr = Array( 1 , 5 , 4 , 2 , 3 )
/// selectionSortInPlace(Arr)
/// println(Arr) // Returns ( 1 , 2 , 3 , 4 , 5 )
/// Selection sort runs in O(n²)
def selectionSortInPlace(arr: Array[Int]): Unit = {
  require(arr != null, "Input array must not be null") // Ensure valid input
  val n = arr.length

  for (i <- 0 until n - 1) { // Iterate through array
    var minIndex = i

    for (j <- i + 1 until n) { // Find the smallest element in the unsorted portion
      if (arr(j) < arr(minIndex)) {
        minIndex = j
      }
    }

    if (minIndex != i) { // Swap only if needed
      val temp = arr(i)
      arr(i) = arr(minIndex)
      arr(minIndex) = temp
    }
  }
}

/// Sorting an integer array by selection, returning a new List
/// Input is immutable (lists are immutable in scala)
/// Example
/// var input = [ 2 , 5 , 3 , 4 , 1 ]
/// var result = selectionSortInPlace(input)
/// println(result) // Returns [ 1 , 2 , 3 , 4 , 5 ]
/// Selection sort runs in O(n²)
def selectionSortNotInPlace(lst: List[Int]): List[Int] = {
  require(lst != null, "Input list must not be null") // Ensure valid input

  def selectMin(xs: List[Int]): (Int, List[Int]) = {
    val min = xs.min
    val (beforeMin, afterMin) = xs.span(_ != min) // Splits at first occurrence of min
    (min, beforeMin ::: afterMin.tail) // Reconstructs the list without removing duplicates
  }

  def sort(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case _ =>
      val (min, rest) = selectMin(xs)
      min :: sort(rest) // Recursively append min to sorted list
  }

  sort(lst)
}
