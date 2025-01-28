package algorithms.search

/// Interpolation Search Algorithm
/// This search technique is an improvement over binary search for uniformly distributed data.
/// It estimates the position of the target element using a linear interpolation formula.
/// Runs in O(log log n) for uniformly distributed data and O(n) in the worst case.
/// Example:
/// val Arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
/// val index = interpolationSearch(Arr, 7)
/// println(index) // Returns 6 (0-based index)
def interpolationSearch(Arr: Array[Int], a: Int): Int = {
  val n = Arr.length
  if (n == 0) return -1 // Handle empty array case

  var l = 0
  var r = n - 1

  while (l <= r && a >= Arr(l) && a <= Arr(r)) {
    if (Arr(l) == Arr(r)) {
      if (Arr(l) == a) return l
      else return -1
    }

    val m = l + ((r - l).toDouble * (a - Arr(l)) / (Arr(r) - Arr(l))).toInt

    if (m < l || m > r) return -1 // Prevent out-of-bounds access

    if (Arr(m) == a) return m
    if (Arr(m) < a) l = m + 1
    else r = m - 1
  }

  -1
}
