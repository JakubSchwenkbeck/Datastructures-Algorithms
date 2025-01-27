package algorithms.sort

///
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
