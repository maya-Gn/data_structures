public class implement<T> {
    ArrayList<BinaryNode<T>> prevArlist = new ArrayList<>();
    int numOfrtns = 0; // keeps count of how many times prevArlist is accessed
    int curHeight = 0
    public T next()
    {
        while (curHeight < Tree.size() && cur != null)
        {
            if (cur.getLeftChild() != null)
            {
                if (!prevArlist.contains(cur.getLeftChild()) )
                {
                    while (cur != null)
                    {
                        nodeStack.push(cur);
                        if (!cur.isLeaf())
                        {
                            prevArlist.add(cur);
                            numOfrtns++;
                        }
                        cur = cur.getLeftChild();
                        curHeight++;
                        
                    }
                    prev = prevArlist.getLast()
                    cur = prev.getRightChild();
                    curHeight++;


                    while (cur != null)
                    {
                        nodeStack.push(cur);
                        if (!cur.isLeaf())
                        {
                            prevArlist.add(cur);
                            numOfrtns++;
                        }
                        cur = cur.getLeftChild();
                        curHeight++;
                    
                    }
                }
            }
            else if (cur.getRightChild() != null)
            {
                if (!prevArlist.contains(cur.getRightChild()))
                {
                    while (cur != null)
                    {
                        nodeStack.push(cur);
                        if (!cur.isLeaf())
                        {
                            prevArlist.add(cur);
                            numOfrtns++;
                        }
                        cur = cur.getLeftChild();
                        curHeight++;
                    
                    }
                    prev = prevArlist.getLast();
                    cur = prev.getRightChild();
                    curHeight++;
    
    
                    while (cur != null)
                    {
                        nodeStack.push(cur);
                        if (!cur.isLeaf())
                        {
                            prevArlist.add(cur);
                            numOfrtns++;
                        }
                        cur = cur.getLeftChild();
                        curHeight++
                    
                }
                }
            }
        }
        if (nodeStack.isEmpty())
            next = root;
        else
            next = nodeStack.pop();
        if (next.getData().equals(root.getDatat()))
            curHeight = 0;
        assert(!prevArlist.isEmpty())
        cur = prevArlist.get(numOfrtns);
        numOfrtns--;
    }
 
}
