package algorithms.sort

/// Sorting an integer array by insertion, in place of the argument Array
/// The Array needs to be mutable in order to be in place
/// Example
/// val Arr = Array( 1 , 5 , 4 , 2 , 3 )
/// insertionSortInPlace(Arr)
/// println(Arr) // Returns ( 1 , 2 , 3 , 4 , 5 )
/// Insertion sort runs in O(n²)
def insertionSortInPlace(arr: Array[Int]): Unit = {
  for (i <- 1 until arr.length) {
    val key = arr(i)
    var j = i - 1

    while (j >= 0 && arr(j) > key) {
      arr(j + 1) = arr(j)
      j -= 1
    }
    arr(j + 1) = key
  }
}

/// Sorting an integer array by insertion, returning a new List
/// The List be immutable
/// Example
/// var input = [ 2 , 5 , 3 , 4 , 1 ]
/// var result = insertionSortInPlace(input)
/// println(result) // Returns [ 1 , 2 , 3 , 4 , 5 ]
/// Insertion sort runs in O(n²)
def insertionSortNotInPlace(lst: List[Int]): List[Int] = {
  def insert(x: Int, sorted: List[Int]): List[Int] = {
    sorted match {
      case Nil              => List(x)
      case h :: t if x <= h => x :: sorted
      case h :: t           => h :: insert(x, t)
    }
  }

  lst.foldLeft(List.empty[Int])((sorted, x) => insert(x, sorted))
}
