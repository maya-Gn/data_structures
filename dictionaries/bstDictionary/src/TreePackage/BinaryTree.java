package TreePackage;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* import StackAndQueuePackage.*; // Needed by tree iterators */
import java.util.Queue;

/**
   A class that implements the ADT binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
   private BinaryNode<T> root;

   public BinaryTree()
   {
      root = null;
   } // end default constructor

   public BinaryTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end constructor

   public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
   {
      initializeTree(rootData, leftTree, rightTree);
   } // end constructor

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
   BinaryTreeInterface<T> rightTree)
   {
      initializeTree(rootData, (BinaryTree<T>)leftTree,
      (BinaryTree<T>)rightTree);
   } // end setTree

   private void initializeTree(T rootData, BinaryTree<T> leftTree, 
   BinaryTree<T> rightTree)
   { // See segment 25.5 in the book for "notes" on this method
      root = new BinaryNode<>(rootData);
   
      if ((leftTree != null) && !leftTree.isEmpty())
         root.setLeftChild(leftTree.root);
   
      if ((rightTree != null) && !rightTree.isEmpty())
      {
         if (rightTree != leftTree)
            root.setRightChild(rightTree.root);
         else
            root.setRightChild(rightTree.root.copy());
      } // end if
   
      if ((leftTree != null) && (leftTree != this))
         leftTree.clear();
   
      if ((rightTree != null) && (rightTree != this))
         rightTree.clear();
   } // end initializeTree

   public void setRootData(T rootData)
   {
      root.setData(rootData);
   } // end setRootData
    
   public T getRootData()
   {
      if (isEmpty())
         throw new EmptyTreeException();
      else
         return root.getData();
   } // end getRootData

   public boolean isEmpty()
   {
      return root == null;
   } // end isEmpty

   public void clear()
   {
      root = null;
   } // end clear

   protected void setRootNode(BinaryNode<T> rootNode)
   {
      root = rootNode;
   } // end setRootNode

   protected BinaryNode<T> getRootNode()
   {
      return root;
   } // end getRootNode

   public int getHeight()
   {
      int height = 0;
      if (root != null)
         height = root.getHeight();
      return height;
   } // end getHeight

   public int getNumberOfNodes()
   {
      int numberOfNodes = 0;
      if (root != null)
         numberOfNodes = root.getNumberOfNodes();
      return numberOfNodes;
   } // end getNumberOfNodes

   public Iterator<T> getInorderIterator()
   {
      return new InorderIterator();
   } // end getInorderIterator

   private class InorderIterator implements Iterator<T>
   {
      private ArrayDeque<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;

      public InorderIterator()
      {
         nodeStack = new ArrayDeque<>();
         currentNode = root;
      } // end default constructor

      public boolean hasNext() 
      {
         return !nodeStack.isEmpty() || (currentNode != null);
      } // end hasNext

      public T next()
      {
         BinaryNode<T> nextNode = null;

         // Find leftmost node with no left child
         while (currentNode != null)
         {
            nodeStack.addFirst(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while

         // Get leftmost node, then move to its right subtree
         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            // Assertion: nextNode != null, since nodeStack was not empty
            // before the pop
            currentNode = nextNode.getRightChild();
         }
         else
            throw new NoSuchElementException();

         return nextNode.getData(); 
      } // end next

      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
   } // end InorderIterator

   private class PreorderIterator implements Iterator<T>
   {
      private ArrayDeque<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;

      public PreorderIterator()
      {
         nodeStack = new ArrayDeque<>();
         currentNode = root;
      }

      public boolean hasNext()
      {
         return !nodeStack.isEmpty() || (currentNode != null);
      }

      // Implimentation not correct. Remove once it is.
      public T next()
      {
         BinaryNode<T> nextNode = null;

         while(currentNode != null)
         {
            nodeStack.addFirst(currentNode);
            currentNode = currentNode.getRightChild();
         }

         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            currentNode = nextNode.getLeftChild();
         }

         return nextNode.getData();
      }

      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }

} // end BinaryTree
