package datastructures.tree

case class avlTree[T](
  key: T,
  val left: Option[avlTree[T]] = None,
  val right: Option[avlTree[T]] = None,
  val parent: Option[avlTree[T]] = None
)

def isSearchTree[T](avl: avlTree[T]): Boolean = {
  val key = avl.key

  def helper(avl: avlTree[T], v: T) = {}

  false

}
