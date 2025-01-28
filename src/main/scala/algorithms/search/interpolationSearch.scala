package algorithms.search

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
