package ua.epam.lab.collections;

import java.util.Comparator;
import java.util.RandomAccess;
import ua.epam.lab.MyArrayList;
import ua.epam.lab.MyLinkedList;
import ua.epam.lab.interfaces.MyList;

/**
* My Personal Collection
*
* @author Yuriy Miedviediev
* @version 1.0 Build 06.04.2014
*
*/
public class MyCollections {
	
        //Helper, recursive implementation of the Quick Sort with Comparator
	private static MyList quickSortComparator(MyList list, Comparator c) {
		
		//base case
		if(list.size() <= 1) {
			return list;
		}
		
		else {
			//holders for elements less and bigger (or equal) than the pivot
			MyList less = new MyLinkedList();
			MyList bigger = new MyLinkedList();
			Integer pivot = (Integer)list.get(0);
			
			//sorting the elements
			for(int pos=1, length = list.size(); pos<length; pos++) {
				Integer current = (Integer)list.get(pos);
				if(c.compare(current,pivot) < 0) {
					less.add(current);
				}
				else {
					bigger.add(current);
				}
			}
			//recursively sort less and bigger elements
			less = quickSortComparator(less, c);
			bigger = quickSortComparator(bigger, c);
			
			//merge all the results and return result
			less.add(pivot);
			copy(less,bigger);
			return less;
		}
	}
        
        //Helper, recursive implementation of the Quick Sort with Comparable
        private static MyList quickSort(MyList list) {
		
		//base case
		if(list.size() <= 1) {
			return list;
		}
		
		else {
			//holders for elements less and bigger (or equal) than the pivot
			MyList less = new MyLinkedList();
			MyList bigger = new MyLinkedList();
			Integer pivot = (Integer)list.get(0);
			
			//sorting the elements
			for(int pos=1, length = list.size(); pos<length; pos++) {
				Integer current = (Integer)list.get(pos);
				if(current.compareTo(pivot) < 0) {
					less.add(current);
				}
				else {
					bigger.add(current);
				}
			}
			//recursively sort less and bigger elements
			less = quickSort(less);
			bigger = quickSort(bigger);
			
			//merge all the results and return result
			less.add(pivot);
			copy(less,bigger);
			return less;
		}
	}
        
        //Helper method, implementation of BubbleSort with Comparator
        private static void bubbleSortComparator(MyList list, Comparator c) {
            if(list.size() <= 1) {
                return;
            }
            boolean sorted = false;
            while(!sorted) {
                sorted = true;
                for(int pos=0,length=list.size(); pos<length-1; pos++) {
                    if(c.compare(list.get(pos), list.get(pos+1)) > 0) {
                        sorted = false;
                        swap(list, pos, pos+1);
                    }
                }
            }
        }
        
        //Helper method, implementation of BubbleSort with Comparable
        private static void bubbleSort(MyList list) {
            if(list.size() <= 1) {
                return;
            }
            boolean sorted = false;
            while(!sorted) {
                sorted = true;
                for(int pos=0,length=list.size(); pos<length-1; pos++) {
                    if(((Integer)list.get(pos)).compareTo((Integer)list.get(pos+1)) > 0) {
                        sorted = false;
                        swap(list, pos, pos+1);
                    }
                }
            }
        }
	
	/**
	 * Sort MyList
         * Use either Recursive QuickSort algorithm (for MyArrayList) or
         * BubbleSort for MyLinkedList
         * 
	 * @param list	MyList to be sorted 
	 */
	public static void sort(MyList list) {
            if(list instanceof RandomAccess) {
                MyList newList = quickSort(list);
                clone(list,newList);
            }
            else {
                bubbleSort(list);
            }
	}
        
        /**
         * Sort MyList, using Comparator
         * Use either Recursive QuickSort algorithm (for MyArrayList) or
         * BubbleSort for MyLinkedList
         * 
         * @param list      MyList to be sorted
         * @param c         Object with Comparator interface realization
         */
        public static void sort(MyList list, Comparator c) {
            if(list instanceof RandomAccess) {
                MyList newList = quickSortComparator(list, c);
                clone(list,newList);
            }
            else {
                bubbleSortComparator(list, c);
            }
        }
	
	/**
	 * Swaps the elements at the specified positions in the specified list
	 * @param list	Source MyList
	 * @param i		Position of the first element to swap
	 * @param j		Position of the second element to swap
	 */
	public static void swap(MyList list, int i, int j) {
		
		//restrict using negative or too large indexes
		if(i > list.size()-1 || i < 0 || j > list.size()-1 || j < 0) {
			throw new IllegalArgumentException("Incorrect index for MyList");
		}
		//remember element at position i
		Integer temp = (Integer)list.get(i);
		
		//swap elements
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Copy (Append) all of the elements from one MyList into another
	 * @param dest	Destination MyList
	 * @param src	Source MyList
	 */
	public static void copy(MyList dest, MyList src) {
		for(int pos=0, length=src.size(); pos<length; pos++) {
			dest.add(src.get(pos));
		}
	}
	
	/**
	 * Clone (Replace) all of the elements from one MyList into another
	 * @param dest	Destination MyList
	 * @param src	Source MyList
	 */
	public static void clone(MyList dest, MyList src) {
		if(dest.size() != src.size()) {
			throw new IllegalArgumentException("Given lists have different capacity");
		}
		else {
			for(int pos=0, length=src.size(); pos<length; pos++) {
				dest.set(pos, src.get(pos));
			}
		}
	}
	
	/**
	 * Reverses the order of the elements in the specified list
	 * @param list 		MyList to be reversed
	 */
	public static void reverse(MyList list) {
		
		//holder for the new reversed MyLinkedList
		MyList reversed;
                
                if(list instanceof RandomAccess) {
                    reversed = new MyArrayList();
                }
                else {
                    reversed = new MyLinkedList();
                }
		
		//Go from the back of the List and add elements to the holder
		for(int pos=list.size()-1; pos>=0; pos--) {
			reversed.add(list.get(pos));
		}
		clone(list,reversed);
	}
	
	/**
	 * Binary search on MyList
	 * 
	 * @param list		MyList, field to search 
	 * @param key		element to find
	 * @return		index of desirable element or (-(insertion point) - 1), if element is not in the List
	 */
	public static int binarySearch(MyList list, Integer key) {
		return bisectionSearch(list, key, 0, list.size());
	}
	
	//Helper for binarySearch
	private static int bisectionSearch(MyList list, Integer key, int minIndex, int maxIndex) {
		
		//possible position of desirable element 
		int midIndex = (minIndex + maxIndex) / 2;
		
		//if element was not found
		if(minIndex >= maxIndex) {
			return (-(midIndex) - 1);
		}
		
		//if element is possibly in the left part of the List, drop the right part
		if((Integer) list.get(midIndex) > key) {
			return bisectionSearch(list, key, minIndex, midIndex);
		}
		
		//if element is possibly in the right part of the List, drop the left part
		else if((Integer) list.get(midIndex) < key) {
			return bisectionSearch(list, key, midIndex+1, maxIndex);		
		}
		
		//if none of the above, than element is found
		else {
			return midIndex;
		}
	}
	
}
