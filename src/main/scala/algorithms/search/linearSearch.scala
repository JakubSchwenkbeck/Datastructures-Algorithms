package algorithms.search

import scala.annotation.tailrec

def linearSearch[A](lst: List[A], elem: A): Option[Int] = {
  @tailrec
  def helper(xs: List[A], target: A, index: Int): Option[Int] = xs match {
    case Nil => None
    case hd :: tail =>
      if (hd == target) Some(index)
      else helper(tail, target, index + 1)
  }

  helper(lst, elem, 0)
}
