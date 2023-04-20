package com.knoldus

object Driver extends App {
private val bstObject = new BinarySearchTree
  bstObject.addNode(12)
  bstObject.addNode(6)
  bstObject.addNode(15)
  bstObject.addNode(4)
  bstObject.addNode(9)

  bstObject.traversal()
  bstObject.deleteNode(1)
  println("BST after deletion")
  bstObject.traversal()
  println(bstObject.search(194))
}
