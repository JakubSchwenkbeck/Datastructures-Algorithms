package datastructures.tree

case class avlTree[T](
  key: T,
  left: Option[avlTree[T]] = None,
  right: Option[avlTree[T]] = None,
  parent: Option[avlTree[T]] = None
)

def isSearchTree[T](avl: avlTree[T]): Boolean = {
  val key = avl.key

  def helper(avl: avlTree[T], v: T) = {}

  false

}
