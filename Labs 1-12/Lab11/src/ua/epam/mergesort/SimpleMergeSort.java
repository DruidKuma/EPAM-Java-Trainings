
package ua.epam.mergesort;

import java.util.List;

/**
 * Simple straight implementation of Merge Sort for testing
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class SimpleMergeSort {
    
    public static List<Integer> mergeSort(List<Integer> array) {
        if(array.size() > 1) {
            List<Integer> left = mergeSort(array.subList(0, array.size()/2));
            List<Integer> right = mergeSort(array.subList(array.size()/2, array.size()));
            return ThreadedMergeSort.merge(left, right);
        }
        return array;
    }
}
