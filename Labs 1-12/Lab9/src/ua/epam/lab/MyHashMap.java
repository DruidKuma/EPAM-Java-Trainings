
package ua.epam.lab;

import java.util.Iterator;

/**
 * My Personal implementation of HashMap data structure
 * 
 * @version 1.0 Build 26.04.2014
 * @author Yuriy Miedviediev
 */
public class MyHashMap implements MyMap {
    
    /**
     * SimpleEntry of MyHashMap
     * In this implementation method setValue return old value of the entry 
     */
    public class SimpleEntry implements MyMap.Entry {
        
        //Key->Value Pair 
        private final Object key;
        private Object value;
        
        //Single constructor with key and value
        public SimpleEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Key Getter
         * @return Key of the entry, Object
         */
        @Override
        public Object getKey() {
            return this.key;
        }
        
        /**
         * Value Getter
         * @return Value of the Entry, Object
         */
        @Override
        public Object getValue() {
            return this.value;
        }

        /**
         * Set new Value for current Entry
         * @param value new desired value
         * @return Object, old value of the entry
         */
        @Override
        public Object setValue(Object value) {
            Object oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        
        /**
         * Hash Code of the Entry (hash code of the key)
         * @return integer, result of hashing the key of the entry
         */
        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
        
        /**
         * Check the equality with other Entry
         * @param other Entry to check
         * @return true, if current entry equals the other, false otherwise
         */
        @Override
        public boolean equals(Object other) {
            if(!(other instanceof SimpleEntry)) return false;
            SimpleEntry otherEntry = (SimpleEntry) other;
            return (this.key.equals(otherEntry.getKey()) && this.value.equals(otherEntry.getValue()));
        }
    }
    
    //capacity of the MyHashMap
    private int capacity;

    //percentage of occupancy of the storage to enlarge its capacity when needed
    private final float loadFactor;

    //array with all the entries of the MyHashMap
    private SimpleEntry[] storage;
    
    //number of Entries in the MyHashMap
    private int length = 0;
    
    /* Constructors for MyHashMap */
    public MyHashMap(int initCapacity, float loadFactor) {
        if(initCapacity < 0 || loadFactor <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = initCapacity;
        this.loadFactor = loadFactor;
        this.storage = new SimpleEntry[this.capacity];
    }
    
    public MyHashMap(int initCapacity) {
        this(initCapacity, 0.75f);
    }
    
    public MyHashMap() {
        this(16, 0.75f);
    }
    /* End of Constructors */

    /**
     * Removes all of the mappings from this map
     */
    @Override
    public void clear() {
        this.storage = new SimpleEntry[this.capacity];
        this.length = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key
     * @param key key to search for
     * @return true if key is in storage, false otherwise
     */
    @Override
    public boolean containsKey(Object key) {
        for(int pos = 0; pos < this.length; pos++) {
            if(this.storage[pos].getKey().equals(key)) return true;
        }
        return false;
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     * @param value value to search for
     * @return true if value is in the map, false otherwise
     */
    @Override
    public boolean containsValue(Object value) {
        for(int pos = 0; pos < this.length; pos++) {
            if(this.storage[pos].getValue().equals(value)) return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, 
     * or null if this map contains no mapping for the key
     * 
     * @param key key to find in the map
     * @return value of the key in the map or null if there is no such key
     */
    @Override
    public Object get(Object key) {
        for(int pos = 0; pos < this.length; pos++) {
            if(this.storage[pos].getKey().equals(key)) return this.storage[pos].getValue();
        }
        return null;
    }

    /**
     * Returns true if this map contains no key-value mappings
     * @return true, if length of the MyHashMap is zero, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * Also ensures the capacity of the storage according to the load factor
     * 
     * @param key Key of the Entry
     * @param value Value of the Entry
     * @return Object, newly created Entry
     */
    @Override
    public Object put(Object key, Object value) {
        SimpleEntry newEntry = new SimpleEntry(key, value);
        this.ensureCapacity();
        
        if(this.containsKey(key)) {
            int index = this.indexOf(key);
            this.storage[index].setValue(value);
        }
        else {
            this.storage[this.length] = newEntry;
            this.length++;
        }
        
        return newEntry;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key key to remove
     * @return removed Entry
     */
    @Override
    public Object remove(Object key) {
        int index = this.indexOf(key);
        if(index < 0) return null;
        
        Entry removed = this.storage[index];
        SimpleEntry[] newStorage = new SimpleEntry[this.capacity - 1];
        Iterator iter = this.entryIterator();
        int position = 0;
        while(iter.hasNext()) {
            Entry next = (Entry)iter.next();
            if(!next.getKey().equals(key)) {
                newStorage[position] = (SimpleEntry) next;
                position++;
            }
        }
        this.storage = newStorage;
        this.length--;
        return removed;
    }

    /**
     * Returns the number of key-value mappings in this map
     * @return integer, size of the MyHashMap
     */
    @Override
    public int size() {
        return this.length;
    }

    /**
     * Returns an iterator over the elements (MyMap.Entry) in proper sequence
     * @return self-made Iterator over Entries
     */
    @Override
    public Iterator entryIterator() {
        return new Iterator() {
            
            SimpleEntry[] container = storage;
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length;
            }

            @Override
            public Object next() {
                return container[currentIndex++];
            }
        };
    }
    
    /* Helper Methods */
    
    //Return index of the entry or -1 if it is not in the MyHashMap
    private int indexOf(Object key) {
        for(int pos = 0; pos < this.length; pos++) {
            if(this.storage[pos].getKey().equals(key)) return pos;
        }
        return -1;
    }
    
    //Ensure the capacity of the storage according to the load factor
    private void ensureCapacity() {
        if(this.storage.length < this.length / this.loadFactor) {
            SimpleEntry[] newStorage = new SimpleEntry[this.storage.length * 2];
            Iterator iter = this.entryIterator();
            int position = 0;
            while(iter.hasNext()) {
                newStorage[position] = (SimpleEntry) iter.next();
                position++;
            }
            this.storage = newStorage;
        }
    }
    
    /* End of Helper Methods */
}
