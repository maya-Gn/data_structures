package bstdictionary;

import TreePackage.SearchTreeInterface;

public class BstDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
    private SearchTreeInterface<Entry<K, V>> bst;
    
    public BstDictionary()
    {
        bst = new BinarySearchTree<>;
    }


    private class Entry<S extends Comparable<? super S>, T> 
           implements Comparable<Entry<S, T>>
   {
      private S key;
      private T value;

      private Entry(S searchKey, T dataValue)
      {
         key = searchKey;
         value = dataValue;
      } // end constructor

      public int compareTo(Entry<S, T> other)
      {
         return key.compareTo(other.key);
      } // end compareTo

/*   The class Entry also defines the methods equals, toString, getKey, getValue,
     and setValue; no setKey method is provided. 
   . . . */
   } // end Entry
}
