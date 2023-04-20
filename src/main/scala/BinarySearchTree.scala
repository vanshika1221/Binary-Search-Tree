package com.knoldus

import com.typesafe.scalalogging.Logger

// Node class representing each node of the binary search tree
case class Node(var data: Int, var leftNode: Node = null, var rightNode: Node = null)

class BinarySearchTree {
  private var rootNode: Node = null
  private val loggerObject = Logger(getClass)

  // Method to add a node to the binary search tree
  def addNode(data: Int): Node = {
    rootNode = insertNode(rootNode, data)
    loggerObject.info(s"Data $data Added Successfully")
    rootNode
  }
  // Private helper method to insert a node in the binary search tree
  private def insertNode(currentNode: Node, data: Int): Node = {
    // If the current node is null, creates a new node with the given data and returns it
    if (currentNode == null) Node(data)
    // If the given data is less than the current node's data, recursively inserts the node in the left subtree
    else if (data < currentNode.data) {
      currentNode.leftNode = insertNode(currentNode.leftNode, data)
      currentNode
    }
    // If the given data is greater than or equal to the current node's data, recursively inserts the node in the right subtree
    else {
      currentNode.rightNode = insertNode(currentNode.rightNode, data)
      currentNode
    }
  }

  def search(data: Int): String = {
    def searchHelper(currentNode: Node): String = {
      if (currentNode == null) "Binary search tree is empty"
      else if (currentNode.data == data) s"$data is found"
      else if (currentNode.data != data && currentNode.leftNode == null && currentNode.rightNode == null) s"$data not found"
      else if (data < currentNode.data) searchHelper(currentNode.leftNode)
      else searchHelper(currentNode.rightNode)
    }

    searchHelper(rootNode)
  }

  // Method to delete a node from the binary search tree
  def deleteNode(data: Int): Node = {
    deleteHelper(rootNode, data)
    rootNode
  }

  private def deleteHelper(currentNode: Node, data: Int): Node = {
    if (currentNode != null) {
      if (currentNode.data == data) {
        if (currentNode.rightNode == null) currentNode.leftNode
        else if (currentNode.leftNode == null) currentNode.rightNode
        else {
          val minNode = findMinNode(currentNode.rightNode)
          currentNode.data = minNode
          currentNode.rightNode = deleteHelper(currentNode.rightNode, currentNode.data)
          currentNode
        }
      }
      else if (data < currentNode.data) {
        currentNode.leftNode = deleteHelper(currentNode.leftNode, data)
        currentNode
      }
      else {
        currentNode.rightNode = deleteHelper(currentNode.rightNode, data)
        currentNode
      }
    }
    else {
      loggerObject.info("Element not found to delete")
      null
    }
  }

  private def findMinNode(node: Node): Int = {
    if (node.leftNode == null) {
      node.data
    } else {
      findMinNode(node.leftNode)
    }
  }

  def traversal(): Unit = {
    def traversalHelper(currentNode: Node): Unit = {
      if (currentNode != null) {
        traversalHelper(currentNode.leftNode)
        println(currentNode.data)
        traversalHelper(currentNode.rightNode)
      }
    }
    traversalHelper(rootNode)
  }
}

