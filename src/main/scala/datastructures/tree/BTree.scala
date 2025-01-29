case class BTree[T: Ordering](keys: List[T] = List(), children: List[BTree[T]] = List(), maxKeys: Int = 2) {

  def isLeaf: Boolean = children.isEmpty

  def search(value: T): Boolean = {
    keys match {
      case Nil                      => false
      case ks if ks.contains(value) => true
      case ks if isLeaf             => false
      case ks =>
        val (left, right) = ks.span(implicitly[Ordering[T]].lt(_, value))
        children(left.length).search(value)
    }
  }

  def insert(newKey: T): BTree[T] = {
    val (newRoot, maybeSplit) = insertInternal(newKey)
    maybeSplit match {
      case Some((median, rightNode)) => BTree(List(median), List(newRoot, rightNode), maxKeys)
      case None                      => newRoot
    }
  }

  private def insertInternal(newKey: T): (BTree[T], Option[(T, BTree[T])]) = {
    val (left, right) = keys.span(implicitly[Ordering[T]].lt(_, newKey))

    if (isLeaf) {
      val newKeys = (left :+ newKey) ++ right
      splitIfNeeded(BTree(newKeys, children, maxKeys))
    } else {
      val (updatedChild, maybeSplit) = children(left.length).insertInternal(newKey)
      val newChildren = children.updated(left.length, updatedChild)

      maybeSplit match {
        case Some((median, rightNode)) =>
          val newKeys = (left :+ median) ++ right
          val newChildrenWithSplit =
            (newChildren.take(left.length + 1) :+ rightNode) ++ newChildren.drop(left.length + 1)
          splitIfNeeded(BTree(newKeys, newChildrenWithSplit, maxKeys))
        case None => (BTree(keys, newChildren, maxKeys), None)
      }
    }
  }

  private def splitIfNeeded(tree: BTree[T]): (BTree[T], Option[(T, BTree[T])]) = {
    if (tree.keys.length <= maxKeys) {
      (tree, None)
    } else {
      val mid = tree.keys.length / 2
      val median = tree.keys(mid)
      val leftNode = BTree(tree.keys.take(mid), tree.children.take(mid + 1), maxKeys)
      val rightNode = BTree(tree.keys.drop(mid + 1), tree.children.drop(mid + 1), maxKeys)
      (leftNode, Some((median, rightNode)))
    }
  }
}
