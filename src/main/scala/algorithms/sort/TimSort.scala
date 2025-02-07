package algorithms.sort

/// Hybrid sorting algorithm: Timsort
/// Combines Insertion Sort for small partitions and Merge Sort for merging
/// Example:
/// val Arr = Array(5, 2, 9, 1, 5, 6)
/// timSort(Arr, 64)
/// println(Arr) // Returns (1, 2, 5, 5, 6, 9)
/// Timsort runs in O(n log n) worst-case.
def timSort(arr: Array[Int], k: Int = 64): Unit = {
  require(arr != null, "Input array must not be null") // Ensure valid input

  val n = arr.length

  // Sort small chunks with Insertion Sort
  for (i <- 0 until n by k) {
    val end = Math.min(i + k, n)
    subArrayInsertionSort(arr, i, end)
  }

  // Merge sorted chunks with Merge Sort
  var size = k
  while (size < n) {
    for (left <- 0 until n by 2 * size) {
      val mid = Math.min(left + size, n)
      val right = Math.min(left + 2 * size, n)

      if (mid < right) merge(arr, left, mid, right)
    }
    size *= 2
  }
}

// Helper function: Insertion Sort for a subarray
private def subArrayInsertionSort(arr: Array[Int], start: Int, end: Int): Unit = {
  for (i <- start + 1 until end) {
    val key = arr(i)
    var j = i - 1

    while (j >= start && arr(j) > key) {
      arr(j + 1) = arr(j)
      j -= 1
    }
    arr(j + 1) = key
  }
}

// Helper function: Merge two sorted subarrays
private def merge(arr: Array[Int], left: Int, mid: Int, right: Int): Unit = {
  val leftArr = arr.slice(left, mid)
  val rightArr = arr.slice(mid, right)

  var i = 0
  var j = 0
  var k = left

  while (i < leftArr.length && j < rightArr.length) {
    if (leftArr(i) <= rightArr(j)) {
      arr(k) = leftArr(i)
      i += 1
    } else {
      arr(k) = rightArr(j)
      j += 1
    }
    k += 1
  }

  while (i < leftArr.length) {
    arr(k) = leftArr(i)
    i += 1
    k += 1
  }

  while (j < rightArr.length) {
    arr(k) = rightArr(j)
    j += 1
    k += 1
  }
}

// Return version of Timsort
def returnTimSort(arr: Array[Int], k: Int = 64): Array[Int] = {
  timSort(arr, k)
  arr
}
