
package ua.epam.lab;

import java.util.Comparator;
import java.util.Iterator;

/**
 * My Personal implementation of TreeMap data structure
 * This realization of TreeMap is a simple BST tree
 * Single LinkedList logic was used
 * 
 * @version 1.0 Build 26.04.2014
 * @author Yuriy Miedviediev
 * @param <K> Key of the Entry, must implement Comparable interface
 * @param <V> Value of the Entry
 */
public class MyTreeMap<K extends Comparable, V extends Comparable> implements MyMap {
    
    /**
     * Entry of MyTreeMap
     */
    public class SimpleEntry implements MyMap.Entry {
        
        //left part (smaller than current element)
        private SimpleEntry left;
        
        //right part (bigger than current element)
        private SimpleEntry right;
        
        //key-value pair
        private K key;
        private V value;
        
        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Key getter
         * @return Object, key of the entry
         */
        @Override
        public K getKey() {
            return this.key;
        }
        
        /**
         * Key setter
         * @param k new key
         */
        public void setKey(K k) {
            this.key = k;
        }
        
        /**
         * Value getter
         * @return Object, value of the entry
         */
        @Override
        public V getValue() {
            return this.value;
        }
        
        /**
         * Left part getter
         * @return SimpleEntry, left part of current entry
         */
        public SimpleEntry getLeft() {
            return left;
        }
        
        /**
         * Right part getter
         * @return SimpleEntry, right part of current entry
         */
        public SimpleEntry getRight() {
            return right;
        }
        
        /**
         * Left part setter
         * @param leaf SimpleEntry, smaller than current entry
         */
        public void setLeft(SimpleEntry leaf) {
            this.left = leaf;
        }
        
        /**
         * Right part setter
         * @param leaf Simple Entry, bigger than current entry
         */
        public void setRight(SimpleEntry leaf) {
            this.right = leaf;
        }

        /**
         * Value setter
         * @param value new value for the current entry
         * @return Object, old value of the entry
         */
        @Override
        public V setValue(Object value) {
            V oldValue = this.value;
            this.value = (V) value;
            return oldValue;
        }
    }
    
    private int length;
    private SimpleEntry root;
    private Comparator cmp;
    
    public MyTreeMap() {}
    public MyTreeMap(Comparator comparator) {
        this.cmp = comparator;
    }
    
    public SimpleEntry getRoot() {
        return this.root;
    }

    @Override
    public void clear() {
        this.length = 0;
        this.root = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.getEntryByKey((K)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return this.getEntryByValue((V)value) != null;
    }

    @Override
    public Object get(Object key) {
        SimpleEntry entry = this.getEntryByKey((K)key);
        if(entry == null) return null;
        else return entry.getValue();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Object put(Object key, Object value) {
        SimpleEntry newEntry = new SimpleEntry((K)key, (V)value);
        SimpleEntry found = this.getEntryByKey((K)key);
        if(this.isEmpty()) {
            root = newEntry;
            this.length++;
        }
        else if(found != null) {
            found.setValue((V) value);
        }
        else {
            SimpleEntry current = root;
            SimpleEntry next;
            
            if(this.compare(newEntry.getKey(), current.getKey()) < 0) next = current.getLeft();
            else next = current.getRight();
        
            while(next != null) {
                if(this.compare(newEntry.getKey(), current.getKey()) < 0) {
                    current = next;
                    next = current.getLeft();
                }
                else {
                    current = next;
                    next = current.getRight();
                }
            }
            
            if(this.compare(newEntry.getKey(), current.getKey()) < 0) current.setLeft(newEntry);
            else current.setRight(newEntry);
            this.length++;
        }
        return newEntry;
    }

    @Override
    public Entry remove(Object key) {
        SimpleEntry entry = this.getEntryByKey((K)key);
        if(entry == null) return null;
        
        SimpleEntry parent = getParent(entry, this.root, null);
        if(parent==null) parent = this.root;
        
        //finding relation between parent and its child
        int relation = this.compare(parent.getKey(), entry.getKey());
        
        //node has two children
        if((entry.getLeft() != null) && (entry.getRight() != null)) {
            SimpleEntry maxFromLeft = findMax(entry.getLeft());
            entry.setKey(maxFromLeft.getKey());
            remove(entry.getLeft().getKey());
        }
        
        //node has one child to the left
        else if(entry.getLeft() != null) {
            if(relation < 0) parent.setRight(entry.getLeft());
            else if(relation == 0) this.root = entry.getLeft();
            else parent.setLeft(entry.getLeft());
        }
        
        //node has one child to the right
        else if(entry.getRight() != null) {
            if(relation < 0) parent.setRight(entry.getRight());
            else if(relation == 0) this.root = entry.getRight();
            else parent.setLeft(entry.getRight());
        }
        
        //leaf node
        else {
            if(relation < 0) parent.setRight(null);
            else parent.setLeft(null);
        }
        return entry;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Iterator entryIterator() {
        return new Iterator() {

            SimpleEntry current = treeMinimum();

            @Override
            public boolean hasNext() {
                return current != null && !isEmpty();
            }

            @Override
            public Object next() {
                SimpleEntry next = current;
                current = treeSuccessor(current);
                return next;                
            }
        };
    }
    
    /* Helper methods */
    
    //method for finding entry in the tree by key
    private SimpleEntry getEntryByKey(K key) {
        return getEntryByKey(this.root, key);
    }
    
    //recursive helper for finding by key
    private SimpleEntry getEntryByKey(SimpleEntry root, K key) {
        if(root == null) {
            return null;
        }
        
        int compareResult = this.compare(root.getKey(), key);
        if(compareResult == 0) {
            return root;
        }
        else if(compareResult < 0) {
            return getEntryByKey(root.getRight(), key);
        }
        else {
            return getEntryByKey(root.getLeft(), key);
        }
    }
    
    //method for finding entry in the tree by value
    private SimpleEntry getEntryByValue(V value) {
        return getEntryByValue(this.root, value);
    }
    
    //recursive helper for finding by value
    private SimpleEntry getEntryByValue(SimpleEntry root, V value) {
        if(root == null) return null;
        
        int compareResult = this.compare(root.getValue(), value);
        if(compareResult == 0) return root;
        else if(compareResult < 0) return getEntryByValue(root.getLeft(), value);
        else return getEntryByValue(root.getRight(), value);
    }
    
    /**
     * Get parent of the given entry
     * @param entry entry to find parent
     * @param root root of the current tree
     * @param parent found parent
     * @return parent of the given node or null if node is root
     */
    private SimpleEntry getParent(SimpleEntry entry, SimpleEntry root, SimpleEntry parent) {
        if(root == null) return null;
        else if(this.compare(entry.getKey(), root.getKey()) != 0) {
            parent = getParent(entry, root.getLeft(), root);
            if(parent == null) {
                parent = getParent(entry, root.getRight(), root);
            }
        }
        return parent;
    }
    
    /**
     * Find max element comparing with given element in the tree
     * @param entry element to compare
     * @return max entry
     */
    private SimpleEntry findMax(SimpleEntry entry) {
        while(entry.getRight() != null) {
            entry = entry.getRight();
        }
        return entry;
    }
    
    /**
     * Compare two entries depending on tree structure
     * @param <T> type, extending Comparable
     * @param val1 first entry
     * @param val2 second entry
     * @return 
     */
    private <T extends Comparable> int compare(T val1, T val2) {
        if(this.cmp == null) return val1.compareTo(val2);
        else return this.cmp.compare(val1, val2);
    }
    
    /**
     * Return next biggest element after given element in the current tree
     * @param entry start entry
     * @return next biggest element in the tree
     */
    private SimpleEntry treeSuccessor(SimpleEntry entry) {
        SimpleEntry successor = entry.getRight();
        if(successor != null) {
            while(successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            return successor;
        }
        successor = this.getParent(entry, root, null);
        while(successor != null && entry == successor.getRight()) {
            entry = successor;
            successor = this.getParent(successor, root, null);
        }
        return successor;
    }
    
    /**
     * Find the minimal element of the tree
     * @return minimal entry
     */
    private SimpleEntry treeMinimum() {
        SimpleEntry result = this.root;
        if(this.isEmpty()) return result;
        while(result.getLeft() != null) {
            result = result.getLeft();
        }
        return result;
    }
    /* End of Helper methods */
}