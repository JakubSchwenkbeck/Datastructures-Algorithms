package algorithms.sort

/// Quick Sort an immutable list of integers
/// generic with abstraction over type and comparison
/// Example
/// val Arr = [ 1 , 5 , 4 , 2 , 3 ]
/// val result = quickSort(Arr, (x : Int, y : Int) => x > y )
/// println(result) // Returns [ 5 , 4 , 3 , 2 ,1]
/// Selection sort runs in O(nlog n)
def quickSort[A](lst: List[A], cmp: (A, A) => Boolean): List[A] = {
  if (lst.length <= 1) lst // Base case: return if one or zero elements
  else {
    val pivot = lst.head // Choose first element as pivot
    val (less, equal, greater) = lst.foldLeft((List.empty[A], List.empty[A], List.empty[A])) {
      case ((lt, eq, gt), elem) =>
        if (cmp(elem, pivot)) (elem :: lt, eq, gt) // A− (less than pivot)
        else if (cmp(pivot, elem)) (lt, eq, elem :: gt) // A+ (greater than pivot)
        else (lt, elem :: eq, gt) // A= (equal to pivot)
    }

    quickSort(less, cmp) ::: equal ::: quickSort(greater, cmp) // Recursively sort & concatenate
  }
}

def quickSort(lst: List[Int]): List[Int] = {
  quickSort(lst, (x: Int, y: Int) => x < y)
}
