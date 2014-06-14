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
import ua.epam.lab.MyHashMap.SimpleEntry;
import ua.epam.lab.MyMap.Entry;

/**
 *
 * @author DruidKuma
 */
public class MyHashMapTest {
    
    private MyHashMap hashMap;
    
    @Before
    public void setUp() {
        hashMap = new MyHashMap();
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.put("c", 3);
    }

    @Test
    public void ClearDeletesAllEntriesInTheMap() {
        hashMap.clear();
        assertEquals("Clear method does not properly delete MyHashMap's entries", 0, hashMap.size());
    }

    @Test
    public void ContainsKeyCorrectlyFindsKey() {
        assertTrue("containsKey does not find key in the MyHashMap", hashMap.containsKey("b"));
    }
    
    @Test
    public void ContainsKeyCorrectlyRejectsKeyWhichIsNotOnTheMap() {
        assertFalse("containsKey incorrectly finds key in the Map", hashMap.containsKey("d"));
    }

    @Test
    public void ContainsValueCorrectlyFindsValue() {
        assertTrue("containsValue does not find value in the MyHashMap", hashMap.containsValue(2));
    }
    
    @Test
    public void ContainsValueCorrectlyRejectsValueWhichIsNotOnTheMap() {
        assertFalse("containsValue incorrectly finds value in the Map", hashMap.containsValue(4));
    }

    @Test
    public void GetCorrectlyReturnsValueLocatedOnTheMap() {
        assertEquals("Get Method does not find value located on the map", 1, hashMap.get("a"));
    }
    
    @Test
    public void GetCorrectlyReturnsNullOnKeyWhichIsNotOnTheMap() {
        assertNull("Get Method incorrectly finds value which is not on the map", hashMap.get("d"));
    }

    @Test
    public void IsEmptyReturnsTrueOnAnEmptyMap() {
        MyHashMap testMap = new MyHashMap();
        assertTrue("isEmpty method does not return true on an empty map", testMap.isEmpty());
    }
    
    @Test
    public void isEmptyReturnsFalseOnAMapWithElements() {
        assertFalse("isEmpty does not correctly recognize MyHashMap with elements", hashMap.isEmpty());
    }

    @Test
    public void PutCorrectlyPutsANewElementToTheMapWithElements() {
        hashMap.put("d", 4);
        assertEquals("put method does not correctly put a new entry to the map with elements", 4, hashMap.size());
    }
    
    @Test
    public void PutCorrectlyPutsNewElementToAnEmptyMap() {
        MyHashMap testMap = new MyHashMap();
        testMap.put("q", 123);
        assertEquals("put method does not correctly put new elements to an empty map", 1, testMap.size());
    }
    
    @Test
    public void PutCorrectlyReplaceValueOfExistingKey() {
        hashMap.put("a", 10);
        assertEquals("put method does not correctly replace value of the existing key in the map", 3, hashMap.size());
        assertEquals("put method does not correctly replace value of the existing key in the map", 10, hashMap.get("a"));
    }
    
    @Test
    public void PutDoesNotThrowExceptionsAndEnlargeStorageDueToLoadFactor() {
        MyHashMap testMap = new MyHashMap(2, 0.5f);
        testMap.put("q", 1);
        testMap.put("w", 2);
        testMap.put("e", 3);
        testMap.put("r", 4);
        testMap.put("t", 5);
        assertEquals("put method does not enlarge the entry storage properly", 5, testMap.size());
    }

    @Test
    public void RemoveReturnsNullOnAnEmptyMap() {
        MyHashMap testMap = new MyHashMap();
        assertNull("remove method does not return null on an empty map", testMap.remove("a"));
    }
    
    @Test
    public void RemoveReturnsNullOnANonExistingEntry() {
        assertNull("remove method does not return null on a nonexisting entry of the map", hashMap.remove("d"));
    }
    
    @Test
    public void RemoveCorrectlyRemovesAndReturnsExistingEntry() {
        assertEquals("remove method does not correctly remove existing entries", 1, ((SimpleEntry)hashMap.remove("a")).getValue());
        assertEquals("remove method does not correctly remove existing entries", 2, hashMap.size());
    }

    @Test
    public void SizeCorrectlyWorksForEmptyMap() {
        MyHashMap testMap = new MyHashMap();
        assertEquals("Size does not return zero on an empty map", 0, testMap.size());
    }
    
    @Test
    public void SizeCorrectlyWorksForSingleElementMaps() {
        MyHashMap testMap = new MyHashMap();
        testMap.put("a", 1);
        assertEquals("Size does not return 1 on a single element map", 1, testMap.size());
    }
    
    @Test
    public void SizeCorrectlyWorksForMapWithManyElements() {
        assertEquals("Size does not correctly work for map with many elements", 3, hashMap.size());
    }

    @Test
    public void EntryIteratorWorksProperlyForEmptyMap() {
        MyHashMap testMap = new MyHashMap();
        Iterator testIter = testMap.entryIterator();
        assertFalse("Entry Iterator does not works properly for empty map", testIter.hasNext());
        assertNull("Entry Iterator does not works properly for empty map", testIter.next());
    }
    
    @Test
    public void EntryIteratorWorksProperlyForSingleElementMap() {
        MyHashMap testMap = new MyHashMap();
        testMap.put("a", 1);
        Iterator testIter = testMap.entryIterator();
        assertEquals("EntryIterator does not works properly for single element map", 1, ((Entry)testIter.next()).getValue());
        assertFalse("Entry Iterator does not works properly for single element map", testIter.hasNext());
        assertNull("Entry Iterator does not works properly for single element map", testIter.next());
    }
    
    @Test
    public void EntryIteratorWorksProperlyForMapWithManyElements() {
        Iterator testIter = hashMap.entryIterator();
        testIter.next();
        testIter.next();
        testIter.next();
        assertFalse("Entry Iterator does not works properly for map with many elements", testIter.hasNext());
        assertNull("Entry Iterator does not works properly for map with many elements", testIter.next());
    }
}
