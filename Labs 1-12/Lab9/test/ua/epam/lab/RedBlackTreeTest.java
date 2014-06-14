/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab;

import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ua.epam.lab.MyMap.Entry;

/**
 *
 * @author DruidKuma
 */
public class RedBlackTreeTest {
    
    private RedBlackTree<Integer, String> treeMap;
    
    @Before
    public void setUp() {
        treeMap = new RedBlackTree<>();
        treeMap.put(1, "a");
        treeMap.put(2, "b");
        treeMap.put(3, "c");
    }
    
    
    @Test
    public void ClearRemovesAllEntriesFromTheTreeMap() {
        treeMap.clear();
        assertEquals("Clear does not remove all entries from TreeMap", 0, treeMap.size());
    }

    @Test
    public void ContainsKeyCorrectlyFindsEntryWithGivenKey() {
        assertTrue("containsKey does not find existing entries", treeMap.containsKey(1));
    }
    
    @Test
    public void ContainsKeyCorrectlyReturnsFalseForNonExistingKeys() {
        assertFalse("containsKey returns true for non-existing keys", treeMap.containsKey(4));
    }

    @Test
    public void ContainsValueCorrectlyFindsEntryWithGivenValue() {
        assertTrue("containsValue does not find existing entries", treeMap.containsValue("a"));
    }
    
    @Test
    public void ContainsValueCorrectlyReturnsFalseForNonExistingEntries() {
        assertFalse("containsValue returns true for non-existing values", treeMap.containsValue("d"));
    }

    @Test
    public void GetCorrectlyReturnsValueOfExistingKey() {
        assertEquals("Get does not return value of existing key", "a", treeMap.get(1));
    }
    
    @Test
    public void GetReturnsNullForNonExistingKeys() {
        assertNull("Get does not return null for non-existing keys", treeMap.get(4));
    }

    @Test
    public void IsEmptyReturnsFalseForTreeMapWithElements() {
        assertFalse("isEmpty does not return false for TreeMap with elements", treeMap.isEmpty());
    }
    
    @Test
    public void IsEmptyReturnsTrueForEmptyTreeMap() {
        RedBlackTree<Integer, Integer> testMap = new RedBlackTree<>();
        assertTrue("isEmpty does not return true for empty TreeMap", testMap.isEmpty());
    }
    
    
    @Test
    public void PutCorrectlyAddsNewEntries() {
        treeMap.put(4, "d");
        assertEquals("Put does not correctly put new entries to the TreeMap", 4, treeMap.size());
    }
    
    @Test
    public void PutCorrectlyReplaceValuesOfTheExistingEntries() {
        treeMap.put(3, "d");
        assertEquals("Put does not correctly replace values of the existing entries", 3, treeMap.size());
    }

    @Test
    public void PutCorrectlyPlacesNewEntries() {
        treeMap.put(5, "e");
        treeMap.put(4, "d");
        Integer actualFor5 = treeMap.getRoot().getRight().getRight().getKey();
        Integer actualFor4 = treeMap.getRoot().getRight().getKey();
        assertEquals("Put places new Entries to the wrong place", (Integer) 5, actualFor5);
        assertEquals("Put places new Entries to the wrong place", (Integer) 4, actualFor4);
    }
    @Test
    public void RemoveReturnsNullForNonExistingElements() {
        assertSame("Remove does not return null for non-existing entries", treeMap.nullEntry, treeMap.remove(4));
        assertEquals("Remove deletes entries using non-existing key", 3, treeMap.size());
    }
    
    @Test
    public void RemoveCorrectlyRemovesMiddleEntries() {
        assertEquals("Remove does not correctly remove middle entries", (Integer) 3, treeMap.remove(2).getKey());
        assertEquals("Remove does not correctly remove middle entries", (Integer) 3, treeMap.getRoot().getKey());        
    }
    
    @Test
    public void RemoveCorrectlyRemovesFirstEntries() {
        assertEquals("Remove does not correctly remove first entries", (Integer) 1, treeMap.remove(1).getKey());
        assertEquals("Remove does not correctly remove first entries", (Integer) 2, treeMap.getRoot().getKey());
    }
    
    @Test
    public void RemoveCorrectlyRemovesLastEntries() {
        assertEquals("Remove does not correctly remove last entries", (Integer) 3, treeMap.remove(3).getKey());
        assertEquals("Remove does not correctly remove last entries", (Integer) 2, treeMap.getRoot().getKey());
    }
    
    @Test
    public void SizeCorrectlyComputedForNonEmptyTreeMap() {
        assertEquals("Size is not correctly computed for non-empty TreeMap", 3, treeMap.size());
    }
    
    @Test
    public void SizeCorrectlyComputedForEmptyTreeMap() {
        RedBlackTree<?,?> testMap = new RedBlackTree<>();
        assertEquals("Size is not correctly computed for empty TreeMap", 0, testMap.size());
    }

    @Test
    public void EntryIteratorWorksProperlyForTreeMapWithElements() {
        Iterator iter = treeMap.entryIterator();
        assertEquals("Entry Iterator does not properly sorts the entries", 1, ((Entry)iter.next()).getKey());
        assertEquals("Entry Iterator does not properly sorts the entries", 2, ((Entry)iter.next()).getKey());
        assertEquals("Entry Iterator does not properly sorts the entries", 3, ((Entry)iter.next()).getKey());
        assertFalse("Entry Iterator does not work properly", iter.hasNext());
    }
    
    @Test
    public void EntryIteratorWorksProperlyForEmptyTreeMap() {
        RedBlackTree<?,?> testMap = new RedBlackTree<>();
        Iterator iter = testMap.entryIterator();
        assertFalse("Entry Iterator does not work properly for empty TreeMap", iter.hasNext());
    }
    
}
