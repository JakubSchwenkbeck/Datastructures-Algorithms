package algorithms.sort

/// Merge Sort an immutable list of integers
/// Example
/// val Arr = [ 1 , 5 , 4 , 2 , 3 ]
/// val result = mergeSort(Arr)
/// println(result) // Returns [ 1 , 2 , 3 , 4 , 5 ]
/// Selection sort runs in O(log n)
def mergeSort(lst: List[Int]): List[Int] = {
  def merge(ls1: List[Int], ls2: List[Int]): List[Int] = {
    val k = ls2.length
    val l = ls1.length

    if k == 0 then return ls1
    if l == 0 then return ls2

    if (ls1.head <= ls2.head) {
      ls1.head :: merge(ls1.tail, ls2)
    } else {
      ls2.head :: merge(ls1, ls2.tail)
    }
  }

  val (left, right) = lst.splitAt(lst.length / 2)
  if (lst.length > 1) {
    merge(mergeSort(left), mergeSort(right))
  } else {
    lst
  }
}

/// Merge Sort an immutable list of integers
/// generic with abstraction over type and comparison
/// Example
/// val Arr = [ 1 , 5 , 4 , 2 , 3 ]
/// val result = mergeSort(Arr, (x : Int, y : Int) => x > y )
/// println(result) // Returns [ 5 , 4 , 3 , 2 ,1]
/// Selection sort runs in O(log n)
def mergeSort[A](lst: List[A], cmp: (A, A) => Boolean): List[A] = {
  def merge(ls1: List[A], ls2: List[A]): List[A] = {
    val k = ls2.length
    val l = ls1.length

    if k == 0 then return ls1
    if l == 0 then return ls2

    if (cmp(ls1.head, ls2.head)) {
      ls1.head :: merge(ls1.tail, ls2)
    } else {
      ls2.head :: merge(ls1, ls2.tail)
    }
  }

  val (left, right) = lst.splitAt(lst.length / 2)
  if (lst.length > 1) {
    merge(mergeSort(left, cmp), mergeSort(right, cmp))
  } else {
    lst
  }
}
