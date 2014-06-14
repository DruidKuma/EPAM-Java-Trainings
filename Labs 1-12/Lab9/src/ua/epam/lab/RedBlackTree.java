

package ua.epam.lab;

import java.util.Comparator;
import ua.epam.lab.MyMap.Entry;
import java.util.Iterator;

/**
 * My Implementation of Red-Black Tree (Original structure for java TreeMap)
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.04.2014
 * @param <K> any type extending Comparable
 * @param <V> any type extending Comparable
 */
public class RedBlackTree<K extends Comparable, V extends Comparable> implements MyMap {
    
    /**
     * Color of Red-Black Tree Entries
     */
    public enum RBTColor {
        RED, BLACK;
    }
    
    /**
     * Element of the Red-Black Tree
     */
    public class RBEntry implements MyMap.Entry {
        
        //color of the current entry (can be either RED or BLACK)
        RBTColor color = RBTColor.BLACK;
        
        //parent of the current entry
        private RBEntry parent;
        
        //left part (smaller than current element)
        private RBEntry left;
        
        //right part (bigger than current element)
        private RBEntry right;
        
        //key-value pair
        private K key;
        private V value;
        
        public RBEntry(K key, V value) {
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
        public RBEntry getLeft() {
            return left;
        }
        
        /**
         * Right part getter
         * @return SimpleEntry, right part of current entry
         */
        public RBEntry getRight() {
            return right;
        }
        
        /**
         * Left part setter
         * @param leaf SimpleEntry, smaller than current entry
         */
        public void setLeft(RBEntry leaf) {
            this.left = leaf;
        }
        
        /**
         * Right part setter
         * @param leaf Simple Entry, bigger than current entry
         */
        public void setRight(RBEntry leaf) {
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
        
        /**
         * Parent getter
         * @return SimpleEntry, parent of the current element
         */
        public RBEntry getParent() {
            return this.parent;
        }
        
        /**
         * Parent setter
         * @param p new Parent for current Entry
         */
        public void setParent(RBEntry p) {
            this.parent = p;
        }
        
        /**
         * General method for setting new child for current Entry
         * Automatically sets relations in both directions 
         * (from parent to child and vice versa)
         * 
         * @param child new child for current Entry
         */
        public void setChild(RBEntry child) {
            if(compare(child, this) < 0) this.setLeft(child);
            else this.setRight(child);
        }
        
        /**
         * Color getter
         * @return color of the current Entry
         */
        public RBTColor getColor() {
            return this.color;
        }
        
        /**
         * Color setter
         * @param c new RBTColor (RED or BLACK) for current Entry
         */
        public void setColor(RBTColor c) {
            this.color = c;
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof Entry) return false;
            Entry entry = (Entry) o;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
    }
    
    //constant fake Entry 
    public final RBEntry nullEntry = new RBEntry(null, null);
    
    //creation stuff
    private int length = 0; 
    private RBEntry root;
    private Comparator cmp;
    
    public RedBlackTree() {
        this.root = nullEntry;
        this.root.setParent(nullEntry);
    }
    public RedBlackTree(Comparator comparator) {
        this();
        this.cmp = comparator;
    }
    
    /**
     * Put new Entry to the Red-Black Tree
     * @param key key of the new entry
     * @param value value of the new entry
     * @return new entry
     */
    @Override
    public Object put(Object key, Object value) {
        RBEntry newEntry = new RBEntry((K)key, (V)value);
        RBEntry current = nullEntry;
        RBEntry next = this.root;
        while(next != nullEntry) {
            current = next;
            if(compare(newEntry, next) < 0) next = next.getLeft();
            else if(compare(newEntry, next) == 0) {
                next.setValue(value);
                return next;
            }
            else next = next.getRight();
        }
        newEntry.setParent(current);
        if(current == nullEntry) {
            this.root = newEntry;
        }
        else current.setChild(newEntry);
        newEntry.setColor(RBTColor.RED);
        newEntry.setLeft(nullEntry);
        newEntry.setRight(nullEntry);
        putFix(newEntry);
        this.length++;
        return newEntry;
    }
    
    /**
     * Remove given entry from the tree
     * @param key key of the entry to remove
     * @return removed entry
     */
    @Override
    public Entry remove(Object key) {
        RBEntry entry = this.getEntry((K)key);
        RBEntry toRemove;
        if(entry.getLeft() == nullEntry || entry.getRight() == nullEntry) toRemove = entry;
        else toRemove = treeSuccessor(entry);
        
        if(toRemove == nullEntry) return nullEntry;
        
        RBEntry child;
        if(toRemove.getLeft() != nullEntry) child = toRemove.getLeft();
        else child = toRemove.getRight();
        
        child.setParent(toRemove.getParent());
        
        if(toRemove.getParent() == nullEntry) this.root = child;
        else if(toRemove == toRemove.getParent().getLeft()) toRemove.getParent().setLeft(child);
        else toRemove.getParent().setRight(child);
        
        if(toRemove != entry) {
            entry.setKey(toRemove.getKey());
            entry.setValue(toRemove.getValue());
        }
        
        if(toRemove.getColor() == RBTColor.BLACK) {
            removeFix(child);
        }
        
        return toRemove;
    }
    
    /**
     * Clear all the entries from the current tree
     */
    @Override
    public void clear() {
        this.root = nullEntry;
        this.root.setParent(nullEntry);
        this.length = 0;
    }
    
    /**
     * Check whether current tree contains given key
     * @param key key to check
     * @return true, if key is in the tree, false otherwise
     */
    @Override
    public boolean containsKey(Object key) {
        return this.getEntry((K)key) != nullEntry;
    }
    
    /**
     * Get value of the given key in the tree
     * @param key key to find entry
     * @return value of the key in the tree, or null if not found
     */
    @Override
    public V get(Object key) {
        RBEntry found = this.getEntry((K)key);
        if(found == null) return null;
        return found.getValue();
    }
    
    /**
     * Check if there exists entry with given value in current tree
     * @param value value to check
     * @return true if value exists, false otherwise
     */
    @Override
    public boolean containsValue(Object value) {
        return this.depthFirstSearch(this.root, (V)value);
    }
    
    /**
     * Check if current tree is empty
     * @return true if tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Get the size of the current tree
     * @return number of entries in the current tree
     */
    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Iterator entryIterator() {
        return new Iterator() {
            
            RBEntry current = treeMinimum();

            @Override
            public boolean hasNext() {
                return current != nullEntry;
            }

            @Override
            public Object next() {
                RBEntry next = current;
                current = treeSuccessor(current);
                return next;                
            }
        };
    }
    
    /* Helper methods */
    
    /**
     * Compare two SimpleEntries either by natural comparison
     * or with comparator if such exists in current RedBlackTree
     * 
     * @param e1 first SimpleEntry
     * @param e2 second SimpleEntry
     * @return -1 if e1 less than e2, 0 if equals, 1 if bigger
     */
    private int compare(RBEntry e1, RBEntry e2) {
        if(this.cmp == null) return e1.getKey().compareTo(e2.getKey());
        else return this.cmp.compare(e1.getKey(), e2.getKey());
    }
    
    /**
     * Rotate given node to the left with correct relation changes
     * Assumes given entry has right child that is not null
     * 
     * @param entry node to rotate
     */
    private void leftRotate(RBEntry entry) {
        
        if(entry == nullEntry) return;
        
        //take right child of given entry
        RBEntry rightChild = entry.getRight();
        
        //link entry to the left of the right child
        entry.setRight(rightChild.getLeft());
        
        //if left of the right child is existing element, set given entry as its parent
        if(rightChild.getLeft() != nullEntry) {
            rightChild.getLeft().setParent(entry);
        }
        
        //parent of given entry become parent of its right child
        rightChild.setParent(entry.getParent());
        
        //if parent of given entry is null, right child becomes root of the tree
        if(entry.getParent() == nullEntry) {
            this.root = rightChild;
        }
        
        //else set properly right child as child of the parent of the given entry
        else entry.getParent().setChild(rightChild);
        
        //reestablish relation between given entry and right child
        rightChild.setLeft(entry);
        entry.setParent(rightChild);
    }
    
    /**
     * Rotate given node to the right with correct relation changes
     * Assumes given entry has left child that is not null
     * 
     * @param entry node to rotate
     */
    private void rightRotate(RBEntry entry) {
        
        if(entry == nullEntry) return;
        
        //take left child of given entry
        RBEntry leftChild = entry.getLeft();
        
        //link entry to the right of the left child
        entry.setLeft(leftChild.getRight());
        
        //if right of the left child is existing element, set given entry as its parent
        if(leftChild.getRight() != nullEntry) {
            leftChild.getRight().setParent(entry);
        }
        
        //parent of given entry become parent of its left child
        leftChild.setParent(entry.getParent());
        
        //if parent of given entry is null, right child becomes root of the tree
        if(entry.getParent() == nullEntry) {
            this.root = leftChild;
        }
        
        //else set properly left child as child of the parent of the given entry
        else entry.getParent().setChild(leftChild);
        
        //reestablish relation between given entry and left child
        leftChild.setRight(entry);
        entry.setParent(leftChild);
    }
    
    /**
     * Fix Colors of the Entries and restructure the tree after insertion
     * @param entry newly created entry in the tree
     */
    private void putFix(RBEntry entry) {
        while(entry != nullEntry && entry != this.root && entry.getParent().getColor() == RBTColor.RED) {
            RBEntry grandParent = entry.getParent().getParent();
            
            //Parent is left child of grandparent
            if(entry.getParent() == grandParent.getLeft()) {
                RBEntry uncle = grandParent.getRight();
                
                //Uncle node has same color with parent (change their family colors)
                //Case 1
                if(uncle.getColor() == RBTColor.RED) {
                    entry.getParent().setColor(RBTColor.BLACK);
                    uncle.setColor(RBTColor.BLACK);
                    grandParent.setColor(RBTColor.RED);
                    entry = grandParent;
                }
                
                //Uncle and parent are different colors
                else {
                    
                    //Case 2
                    if(entry == entry.getParent().getRight()) {
                        entry = entry.getParent();
                        leftRotate(entry);
                    }
                    
                    //Case 3
                    entry.getParent().setColor(RBTColor.BLACK);
                    entry.getParent().getParent().setColor(RBTColor.RED);
                    rightRotate(entry.getParent().getParent());
                }
            }
            
            //Parent is right child of grandparent
            else {
                RBEntry uncle = grandParent.getLeft();
                
                //Uncle node has same color with parent (change their family colors)
                //Case 1
                if(uncle.getColor() == RBTColor.RED) {
                    entry.getParent().setColor(RBTColor.BLACK);
                    uncle.setColor(RBTColor.BLACK);
                    grandParent.setColor(RBTColor.RED);
                    entry = grandParent;
                }
                
                //Uncle and parent are different colors
                else {
                    
                    //Case 2
                    if(entry == entry.getParent().getLeft()) {
                        entry = entry.getParent();
                        rightRotate(entry);
                    }
                    
                    //Case 3
                    entry.getParent().setColor(RBTColor.BLACK);
                    entry.getParent().getParent().setColor(RBTColor.RED);
                    leftRotate(entry.getParent().getParent());
                }
            }
        }
        //Root has to be black
        this.root.setColor(RBTColor.BLACK);
    }
    
    /**
     * Restore all Red-Black Tree Properties after removal
     * @param x the deepest entry that was engaged
     */
    private void removeFix(RBEntry x) {
        while(x != this.root && x.getColor() == RBTColor.BLACK) {
            if(x == x.getParent().getLeft()) {
                RBEntry w = x.getParent().getRight();
                if(w.getColor() == RBTColor.RED) {
                    w.setColor(RBTColor.BLACK);
                    x.getParent().setColor(RBTColor.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                
                if(w.getLeft().getColor() == RBTColor.BLACK && w.getRight().getColor() == RBTColor.BLACK) {
                    w.setColor(RBTColor.RED);
                    x = x.getParent();
                }
                else if(w.getRight().getColor() == RBTColor.BLACK) {
                    w.getLeft().setColor(RBTColor.BLACK);
                    w.setColor(RBTColor.RED);
                    rightRotate(w);
                    w = x.getParent().getRight();
                }
                w.setColor(x.getParent().getColor());
                x.getParent().setColor(RBTColor.BLACK);
                w.getRight().setColor(RBTColor.BLACK);
                leftRotate(x.getParent());
                x = this.root;                
            }
            else {
                RBEntry w = x.getParent().getLeft();
                if(w.getColor() == RBTColor.RED) {
                    w.setColor(RBTColor.BLACK);
                    x.getParent().setColor(RBTColor.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                
                if(w.getRight().getColor() == RBTColor.BLACK && w.getLeft().getColor() == RBTColor.BLACK) {
                    w.setColor(RBTColor.RED);
                    x = x.getParent();
                }
                else if(w.getLeft().getColor() == RBTColor.BLACK) {
                    w.getRight().setColor(RBTColor.BLACK);
                    w.setColor(RBTColor.RED);
                    leftRotate(w);
                    w = x.getParent().getLeft();
                }
                w.setColor(x.getParent().getColor());
                x.getParent().setColor(RBTColor.BLACK);
                w.getLeft().setColor(RBTColor.BLACK);
                rightRotate(x.getParent());
                x = this.root;
            }
        }
        x.setColor(RBTColor.BLACK);
    }
    
    /**
     * Get Entry by given key
     * @param key key to find the entry
     * @return entry with found key, or null if no such key in the tree
     */
    private RBEntry getEntry(K key) {
        RBEntry current = this.root;
        RBEntry fake = new RBEntry(key, null);
        while(current != nullEntry) {
            if(compare(current, fake) < 0) current = current.getRight();
            else if(compare(current, fake) > 0) current = current.getLeft();
            else return current;
        }
        return nullEntry;
    }
    
    /**
     * Depth-First Search through the tree for finding given value
     * @param root current node to check
     * @param value value to find
     * @return true, if value found, false otherwise
     */
    private boolean depthFirstSearch(RBEntry root, V value) {
        if(root == nullEntry) return false;
        else if(root.getValue().equals(value)) return true;
        else return depthFirstSearch(root.getLeft(), value) || depthFirstSearch(root.getRight(), value);
    }
    
    /**
     * Return next biggest element after given element in the current tree
     * @param entry start entry
     * @return next biggest element in the tree
     */
    private RBEntry treeSuccessor(RBEntry entry) {
        if(entry == nullEntry) return nullEntry;
        RBEntry successor = entry.getRight();
        if(successor != nullEntry) {
            while(successor.getLeft() != nullEntry) {
                successor = successor.getLeft();
            }
            return successor;
        }
        successor = entry.getParent();
        while(successor != nullEntry && entry == successor.getRight()) {
            entry = successor;
            successor = successor.getParent();
        }
        return successor;
    }
    
    /**
     * Find the minimal element of the tree
     * @return minimal entry
     */
    private RBEntry treeMinimum() {
        RBEntry result = this.root;
        if(result == nullEntry) return nullEntry;
        while(result.getLeft() != nullEntry) {
            result = result.getLeft();
        }
        return result;
    }
    
    /**
     * Helper method to return root for testing
     * @return root of the tree
     */
    public RBEntry getRoot() {
        return this.root;
    }
    /* End of Helper Methods*/
}
