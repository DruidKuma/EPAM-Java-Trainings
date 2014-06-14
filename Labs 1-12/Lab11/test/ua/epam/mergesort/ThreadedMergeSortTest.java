
package ua.epam.mergesort;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Multi-threaded implementation of Merge Sort 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class ThreadedMergeSortTest {

    private List<Integer> test;
    
    @Before
    public void setUp() {
        test = new ArrayList();
    }
    
    @Test
    public void ThreadedMergeSortCorrectlySortsEmptyList() {
        ThreadedMergeSort.mergeSort(test);
        assertEquals("Multi-threaded merge sort does not correctly sort empty list", new ArrayList(), test);
    }
    
    @Test
    public void ThreadedMergeSortCorrectlySortsListWithOneElement() {
        test.add(1);
        ThreadedMergeSort.mergeSort(test);
        List<Integer> expected = new ArrayList();
        expected.add(1);
        assertEquals("Multi-threaded merge sort does not correctly sort list with one element", expected, test);
    }
    
    @Test
    public void ThreadedMergeSortCorrectlySortsListWithManyElements() {
        List<Integer> expected = new ArrayList();
        for(int i = 0; i < 100; i++) {
            expected.add(i);
        }
        for(int i = 99; i >= 0; i--) {
            test.add(i);
        }
        ThreadedMergeSort.mergeSort(test);
        assertEquals("Multi-threaded merge sort does not correctly sort list with many elements", expected, test);        
    }
    
    @Test
    public void ThreadedMergeSortWorksFasterThanSimpleOne() {
        List<Integer> testSimple = new ArrayList();
        for(int i = 4105000; i >= 0; i--) {
            testSimple.add(i);
            test.add(i);
        }
        
        long beginTime = System.currentTimeMillis();
        SimpleMergeSort.mergeSort(testSimple);
        long finishTime = System.currentTimeMillis();
        long timeSimple = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        ThreadedMergeSort.mergeSort(test);
        finishTime = System.currentTimeMillis();
        long timeThreaded = finishTime - beginTime;
        
        System.out.println("Time for single-threaded merge sort: " + timeSimple);
        System.out.println("Time for multi-threaded merge sort: " + timeThreaded);
                
        assertTrue(timeThreaded <= timeSimple);
    }
}
