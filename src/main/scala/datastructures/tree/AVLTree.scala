case class AVLTree[T](key: T, left: Option[AVLTree[T]] = None, right: Option[AVLTree[T]] = None, height: Int = 1) {

  // Get the height of the tree or subtree
  def getHeight: Int = height

  // Get balance factor of the node
  def balanceFactor: Int = getHeightOfLeft - getHeightOfRight

  // Get the height of left subtree
  def getHeightOfLeft: Int = left.map(_.height).getOrElse(0)

  // Get the height of right subtree
  def getHeightOfRight: Int = right.map(_.height).getOrElse(0)

  // Search for a value in the AVL tree
  def search(value: T)(implicit ord: Ordering[T]): Option[AVLTree[T]] = {
    if (ord.equiv(value, key)) {
      Some(this)
    } else if (ord.lt(value, key)) {
      left.flatMap(_.search(value))
    } else {
      right.flatMap(_.search(value))
    }
  }

  def insert(newKey: T)(implicit ord: Ordering[T]): AVLTree[T] = {
    if (ord.lt(newKey, key)) {
      val newLeft = left.map(_.insert(newKey)).orElse(Some(AVLTree(newKey)))
      createUpdatedNode(newLeft, right).balance()
    } else if (ord.gt(newKey, key)) {
      val newRight = right.map(_.insert(newKey)).orElse(Some(AVLTree(newKey)))
      createUpdatedNode(left, newRight).balance()
    } else {
      this
    }
  }

  private def createUpdatedNode(
                                 newLeft: Option[AVLTree[T]],
                                 newRight: Option[AVLTree[T]]
                               ): AVLTree[T] = {
    val newHeight = 1 + Math.max(
      newLeft.map(_.height).getOrElse(0),
      newRight.map(_.height).getOrElse(0)
    )
    AVLTree(key, newLeft, newRight, newHeight)
  }

  private def balance()(implicit ord: Ordering[T]): AVLTree[T] = {
    val bf = balanceFactor
    if (bf > 1) {
      if (left.get.balanceFactor >= 0) rightRotate()
      else {
        val rotatedLeft = left.get.leftRotate()
        this.copy(left = Some(rotatedLeft)).rightRotate()
      }
    } else if (bf < -1) {
      if (right.get.balanceFactor <= 0) leftRotate()
      else {
        val rotatedRight = right.get.rightRotate()
        this.copy(right = Some(rotatedRight)).leftRotate()
      }
    } else {
      this
    }
  }

  private def rightRotate()(implicit ord: Ordering[T]): AVLTree[T] = {
    val pivot = left.get
    val newRight =  AVLTree(
      key = key,
      left = pivot.right,
      right = right,
      height = 1 + Math.max(pivot.getHeightOfRight, getHeightOfRight)
    )
     AVLTree(
      key = pivot.key,
      left = pivot.left,
      right = Some(newRight),
      height = 1 + Math.max(pivot.getHeightOfLeft, newRight.height)
    )
  }

  private def leftRotate()(implicit ord: Ordering[T]): AVLTree[T] = {
    val pivot = right.get
    val newLeft =  AVLTree(
      key = key,
      left = left,
      right = pivot.left,
      height = 1 + Math.max(getHeightOfLeft, pivot.getHeightOfLeft)
    )
     AVLTree(
      key = pivot.key,
      left = Some(newLeft),
      right = pivot.right,
      height = 1 + Math.max(newLeft.height, pivot.getHeightOfRight)
    )
  }

}
