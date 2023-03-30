import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Chapter 13 page 385-388 begins at Listing 13-4
 */

public class ArrayListWithIterator<T> implements 
    ListWithIteratorInterface<T>
{
    private T[] list;
    private int numberOfEntries;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 1000;

    public ArrayListWithIterator()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public ArrayListWithIterator(int initialCapacity)
    {
        integrityOK = false;
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        integrityOK = true;
    } // end constructor

    /**Implementations of the methods of the ADT list go here; 
     * they can be found in chapter 11 beginning with segment 11.5 */

    public void add(T newEntry)
    {
        checkIntegrity();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    } // end add

    public void add(int givenPosition, T newEntry)
    {
        checkIntegrity();
        if ((givenPosition >= 1 ) && 
            (givenPosition <= numberOfEntries + 1))
        {
            if (givenPosition <= numberOfEntries)
            {
                makeRoom(givenPosition);
            }
            list[givenPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity();
        }
        else
        {
            throw new IndexOutOfBoundsException("Given position "
                + "of add's new entry is out of bounds");
        }
    } // end add

    private void makeRoom(int givenPosition)
    {
        for (int i = numberOfEntries; i >= givenPosition; i--)
        {
            list[i+1] = list[i];
        }
    }

    public T remove(int givenPosition)
    {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            T result = list[givenPosition];
            if (givenPosition < numberOfEntries)
            {
                removeGap(givenPosition);
            }
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result;
        }
        else
        {
            throw new IndexOutOfBoundsException("Illegal positon "
                + "given to remove operation");
        }
    } // end remove

    private void removeGap(int givenPosition)
    {
        for (int i = givenPosition; i < numberOfEntries; i++)
        {
            list[i] = list[i+1];
        }
    }

    public void clear()
    {
        for (int i = 1; i <= numberOfEntries; i++)
        {
            list[i] = null;
        }
        integrityOK = false;
        numberOfEntries = 0;
    }

    public T replace(int givenPosition, T newEntry)
    {
        if (givenPosition >= 1 && (givenPosition <= numberOfEntries))
        {
            T replaced = list[givenPosition];
            list[givenPosition] = newEntry;
            return replaced;
        }
        else
        {
            throw new IndexOutOfBoundsException("Illegal postion "
            + "given to remove operation");
        }
    }

    public T getEntry(int givenPosition)
    {
        if (givenPosition >= 1 && (givenPosition <= numberOfEntries))
        {
            return list[givenPosition];
        }
        else
        {
            throw new IndexOutOfBoundsException("Illegal postion "
            + "given to remove operation");
        }
    }

    public T[] toArray()
    {
        checkIntegrity();
        
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = list[index + 1];
        }
        return result;
    } // end toArray

    public boolean contains(T anEntry)
    {
        checkIntegrity();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries))
        {
            if (anEntry.equals(list[index]))
                found = true;
            index++;
        } //end while
        return found;
    } // end contains

    public int getLength()
    {
        return numberOfEntries;
    } // end getLength

    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    private void ensureCapacity()
    {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity)
        {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    } // end ensureCapacity


    public Iterator<T> iterator()
    {
        return new IteratorForArrayList();
    } // end iterator

    public Iterator<T> getIterator()
    {
        return iterator();
    }

    private class IteratorForArrayList implements Iterator<T>
    {
        private int nextIndex;
        private boolean wasNextCalled;

        private IteratorForArrayList()
        {
            nextIndex = 1;
            wasNextCalled = false;
        } // end default constructor

        public boolean hasNext()
        {
            return nextIndex <= numberOfEntries;
        }

        public T next()
        {
            checkIntegrity();
            if (hasNext())
            {
                wasNextCalled = true;
                T nextEntry = list[nextIndex];
                nextIndex++;
                return nextEntry;
            }
            else
                throw new NoSuchElementException("Illegal call to"
                + " next();" + "iterator is after the end of list");
        }// end next

        public void remove()
        {
            if (wasNextCalled)
            {
               ArrayListWithIterator.this.remove(nextIndex - 1);
               nextIndex--;
               wasNextCalled = false;
            }
            else
            {
                throw new IllegalStateException("Illegal call" 
                + "to remove(); "  + "next() was not called.");
            }
        }// end remove
        
    } // end IteratorForArrayList
    
    public void checkIntegrity()
    {
        if (!integrityOK)
        {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    } // end checkIntegrity

    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create an list whose "
                + "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
    } //end checkCapacity
} // end ArrayListWitIterator