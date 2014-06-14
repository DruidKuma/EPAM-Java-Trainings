
package ua.epam.mergesort;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-threaded implementation of Merge Sort 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class ThreadedMergeSort {
    
    //Upper bound of possible threads
    public static final int MAXTHREADS = 2048;
    
    //Number of currently running threads
    public static int currentThreads = 0;
    
    /**
     * Standard merge operation to concatenate two lists
     * @param left left part of the array
     * @param right right part of the array
     * @return merged array
     */
    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        
        //result of merging
        List<Integer> merged = new ArrayList();
        
        //indexes of the left and right parts
        int leftIndx = 0;
        int rightIndx = 0;
        
        //while both parts have unseen elements
        while(leftIndx < left.size() && rightIndx < right.size()) {
            if(left.get(leftIndx) <= right.get(rightIndx)) {
                merged.add(left.get(leftIndx));
                leftIndx++;
            }
            else {
                merged.add(right.get(rightIndx));
                rightIndx++;
            }
        }
        
        //while left part have unseen elements
        while(leftIndx < left.size()) {
            merged.add(left.get(leftIndx));
            leftIndx++;
        }
        
        //while right part have unseen elements
        while(rightIndx < right.size()) {
            merged.add(right.get(rightIndx));
            rightIndx++;
        }
        
        return merged;
    }
    
    /**
     * Main Logic with threading and sorting the array
     * @param array
     * @return sorted array
     */
    public static List<Integer> sort(List<Integer> array) {
        if(array.size() > 1) {
            
            //divide array into left and right parts
            List<Integer> left = array.subList(0, array.size()/2);
            List<Integer> right = array.subList(array.size()/2, array.size());
            
            List<Integer> sorted;
            
            //if not exceed threshold of possible threads
            if(currentThreads < MAXTHREADS) {
                
                //create mergers for every part
                MergeThread leftMerge = new MergeThread(left);
                MergeThread rightMerge = new MergeThread(right);

                //create threads for every part
                Thread leftThread = new Thread(leftMerge);
                Thread rightThread = new Thread(rightMerge);

                //start both threads and wait for them to finish sorting their contents
                try {
                    leftThread.start();
                    currentThreads++;
                    leftThread.join();

                    rightThread.start();
                    currentThreads++;
                    rightThread.join();
                } 
                catch(InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            
                //merge results
                sorted = merge(leftMerge.array, rightMerge.array);
                return sorted;
            }
            
            //else run regular single-threaded merge sort
            else {
                sorted = merge(SimpleMergeSort.mergeSort(left), SimpleMergeSort.mergeSort(right));
                currentThreads--;
                return sorted;
            }
        }
        
        //if length of the array is <= 1, it is already sorted
        currentThreads--;
        return array;
    }
    
    /**
     * Decorative method to set the contents of the given array to the sorted version
     * @param array list to be sorted
     */
    public static void mergeSort(List<Integer> array) {
        List<Integer> c = sort(array);
        for(int pos = 0; pos < c.size(); pos++) {
            array.set(pos, c.get(pos));
        }
    }
}
