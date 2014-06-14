
package ua.epam.mergesort;

import java.util.List;

/**
 * Runnable merger for the multi-threaded Merge Sort 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class MergeThread implements Runnable {
    
    //holds whole array and its left and right halves
    public List<Integer> array;
    
    public MergeThread(List<Integer> array) {
        this.array = array;
    }
    
    //when called, sorts own array
    @Override
    public void run() {
        ThreadedMergeSort.mergeSort(this.array);
    }
    
}
