package TreePackage;

import java.util.Iterator;
/**
   A class that implements the ADT binary search tree by extending BinaryTree.
   Recursive version.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BinarySearchTree<T extends Comparable<? super T>>
             extends BinaryTree<T> implements SearchTreeInterface<T>
{
   public BinarySearchTree()
   {
      super();
   } // end default constructor
   
   public BinarySearchTree(T rootEntry)
   {
      super();
      setRootNode(new BinaryNode<T>(rootEntry));
   } // end constructor
   
   // Disable setTree (see Segment 26.6)
   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      throw new UnsupportedOperationException();
   } // end setTree

   public boolean contains(T anEntry)
   {
      return getEntry(anEntry) != null;
   } // end contains

   public T add(T anEntry)
   {
      T result = null;

      if (isEmpty())
         setRootNode(new BinaryNode<>(anEntry));
      else
         result = addEntry(getRootNode(), anEntry);
      
      return result;
   } // end add

   private T addEntry(BinaryNode<T> rootNode, T anEntry)
   {
      // Assertion: rootNode != null
      T result = null;
      int comparison = anEntry.compareTo(rootNode.getData());
   
      if (comparison == 0)
      {
         result = rootNode.getData();
         rootNode.setData(anEntry);
      }
      else if (comparison < 0)
      {
         if (rootNode.hasLeftChild())
            result = addEntry(rootNode.getLeftChild(), anEntry);
         else
            rootNode.setLeftChild(new BinaryNode<>(anEntry));
      }
      else
      {
         // Assertion: comparison > 0
   
         if (rootNode.hasRightChild())
            result = addEntry(rootNode.getRightChild(), anEntry);
         else
            rootNode.setRightChild(new BinaryNode<>(anEntry));
      } // end if
   
      return result;
   } // end addEntry   

   public T getEntry(T anEntry)
   {
      return findEntry(getRootNode(), anEntry);
   } // end getEntry

   private T findEntry(BinaryNode<T> rootNode, T anEntry)
   {
      T result = null;

      if (rootNode != null)
      {
         T rootEntry = rootNode.getData();

         if (anEntry.equals(rootEntry))
            result = rootEntry;
         else if (anEntry.compareTo(rootEntry) < 0)
            result = findEntry(rootNode.getLeftChild(), anEntry);
         else
            result = findEntry(rootNode.getRightChild(), anEntry);
      } // end if

      return result;
   } // end findEntry

   public T remove(T anEntry) 
   {
      ReturnObject oldEntry = new ReturnObject(null);
      BinaryNode<T> newRoot = removeEntry(getRootNode(), anEntry, oldEntry);
      setRootNode(newRoot);

      return oldEntry.get();
   } // end remove

   private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T anEntry,
                                  ReturnObject oldEntry)
   {
      if (rootNode != null)
      {
         T rootData = rootNode.getData();
         int comparison = anEntry.compareTo(rootData);

         if (comparison == 0)       // anEntry == root entry
         {
            oldEntry.set(rootData);
            rootNode = removeFromRoot(rootNode);
         }
         else if (comparison < 0)   // anEntry < root entry
         {
            BinaryNode<T> leftChild = rootNode.getLeftChild();
            BinaryNode<T> subtreeRoot = removeEntry(leftChild, anEntry, oldEntry);
            rootNode.setLeftChild(subtreeRoot);
         }
         else                       // anEntry > root entry
         {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            // A different way of coding than for left child:
            rootNode.setRightChild(removeEntry(rightChild, anEntry, oldEntry));
         } // end if
      } // end if

      return rootNode;
   } // end removeEntry

   private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
   {
      // Case 1: rootNode has two children
      if (rootNode.hasLeftChild() && rootNode.hasRightChild())
      {
         // Find node with largest entry in left subtree
         BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
         BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);

         // Replace entry in root
         rootNode.setData(largestNode.getData());

         // Remove node with largest entry in left subtree
         rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
      } // end if

      // Case 2: rootNode has at most one child
      else if (rootNode.hasRightChild())
         rootNode = rootNode.getRightChild();
      else
         rootNode = rootNode.getLeftChild();

      // Assertion: If rootNode was a leaf, it is now null

      return rootNode;
   } // end removeFromRoot

   private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
   {
      if (rootNode.hasRightChild())
         rootNode = findLargest(rootNode.getRightChild());

      return rootNode;
   } // end findLargest

   private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
   {
      if (rootNode.hasRightChild())
      {
         BinaryNode<T> rightChild = rootNode.getRightChild();
         rightChild = removeLargest(rightChild);
         rootNode.setRightChild(rightChild);
      }
      else
         rootNode = rootNode.getLeftChild();

      return rootNode;
   } // end removeLargest


/* Implementations of contains, getEntry, add, and remove are here.
   . . .
   Other methods in SearchTreeInterface are inherited from BinaryTree.  */
} // end BinarySearchTree

