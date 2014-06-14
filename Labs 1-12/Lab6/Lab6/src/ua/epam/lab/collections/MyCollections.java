package ua.epam.lab.collections;

import ua.epam.lab.*;

/**
* My Personal Collection
*
* @author Yuriy Miedviediev
* @version 1.0 Build 06.04.2014
*
*/
public class MyCollections {
	
	/* Collection of methods for MyLinkedList
	 * ===================================================================
	 */
	
	//Helper, recursive implementation of the Quick Sort
	private static MyLinkedList quickSort(MyLinkedList list) {
		
		//base case
		if(list.size() <= 1) {
			return list;
		}
		
		else {
			//holders for elements less and bigger (or equal) than the pivot
			MyLinkedList less = new MyLinkedList();
			MyLinkedList bigger = new MyLinkedList();
			Integer pivot = list.get(0);
			
			//sorting the elements
			for(int pos=1, length = list.size(); pos<length; pos++) {
				Integer current = list.get(pos);
				if(current < pivot) {
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
	
	/**
	 * Sort MyLinkedList (using Recursive QuickSort algorithm)
	 * @param list	MyLinkedList to be sorted 
	 */
	public static void sort(MyLinkedList list) {
		MyLinkedList newList = quickSort(list);
		clone(list,newList);
	}
	
	/**
	 * Swaps the elements at the specified positions in the specified list
	 * @param list	Source MyLinkedList
	 * @param i		Position of the first element to swap
	 * @param j		Position of the second element to swap
	 */
	public static void swap(MyLinkedList list, int i, int j) {
		
		//restrict using negative or too large indexes
		if(i > list.size()-1 || i < 0 || j > list.size()-1 || j < 0) {
			throw new IllegalArgumentException("Incorrect index for MyLinkedList");
		}
		//remember element at position i
		Integer temp = list.get(i);
		
		//swap elements
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Copy (Append) all of the elements from one MyLinkedList into another
	 * @param dest	Destination MyLinkedList
	 * @param src	Source MyLinkedList
	 */
	public static void copy(MyLinkedList dest, MyLinkedList src) {
		for(int pos=0, length=src.size(); pos<length; pos++) {
			dest.add(src.get(pos));
		}
	}
	
	/**
	 * Clone (Replace) all of the elements from one MyLinkedList into another
	 * @param dest	Destination MyLinkedList
	 * @param src	Source MyLinkedList
	 */
	public static void clone(MyLinkedList dest, MyLinkedList src) {
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
	 * @param list 		MyLinkedList to be reversed
	 */
	public static void reverse(MyLinkedList list) {
		
		//holder for the new reversed MyLinkedList
		MyLinkedList reversed = new MyLinkedList();
		
		//Go from the back of the List and add elements to the holder
		for(int pos=list.size()-1; pos>=0; pos--) {
			reversed.add(list.get(pos));
		}
		clone(list,reversed);
	}
	/* ===================================================================
	 * End of methods for MyLinkedList
	 */
	
	/* Collection of methods for MyArrayList
	 * ===================================================================
	 */
	
	//Helper, recursive implementation of the Quick Sort
	private static MyArrayList quickSort(MyArrayList list) {

		//base case
		if(list.size() <= 1) {
			return list;
		}

		else {
			//holders for elements less and bigger (or equal) than the pivot
			MyArrayList less = new MyArrayList();
			MyArrayList bigger = new MyArrayList();
			Integer pivot = (Integer) list.get(0);

			//sorting the elements
			for(int pos=1, length = list.size(); pos<length; pos++) {
				Integer current = (Integer) list.get(pos);
				if(current < pivot) {
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

	/**
	 * Sort MyLinkedList (using Recursive QuickSort algorithm)
	 * @param list	MyLinkedList to be sorted 
	 */
	public static void sort(MyArrayList list) {
		MyArrayList newList = quickSort(list);
		clone(list,newList);
	}
	
	/**
	 * Swaps the elements at the specified positions in the specified list
	 * @param list	Source MyArrayList
	 * @param i		Position of the first element to swap
	 * @param j		Position of the second element to swap
	 */
	public static void swap(MyArrayList list, int i, int j) {
		
		//restrict using negative or too large indexes
		if(i > list.size()-1 || i < 0 || j > list.size()-1 || j < 0) {
			throw new IllegalArgumentException("Incorrect index for MyLinkedList");
		}
		//remember element at position i
		Integer temp = (Integer) list.get(i);
		
		//swap elements
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Copy (Append) all of the elements from one MyArrayList into another
	 * @param dest	Destination MyArrayList
	 * @param src	Source MyArrayList
	 */
	public static void copy(MyArrayList dest, MyArrayList src) {
		for(int pos=0, length=src.size(); pos<length; pos++) {
			dest.add(src.get(pos));
		}
	}
	
	/**
	 * Clone (Replace) all of the elements from one MyArrayList into another
	 * @param dest	Destination MyArrayList
	 * @param src	Source MyArrayList
	 */
	public static void clone(MyArrayList dest, MyArrayList src) {
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
	 * @param list 		MyLinkedList to be reversed
	 */
	public static void reverse(MyArrayList list) {
		
		//holder for the new reversed MyLinkedList
		MyArrayList reversed = new MyArrayList();
		
		//Go from the back of the List and add elements to the holder
		for(int pos=list.size()-1; pos>=0; pos--) {
			reversed.add(list.get(pos));
		}
		clone(list,reversed);
	}
	
	/* ===================================================================
	 * End of methods for MyLinkedList
	 */
	
	/**
	 * Binary search on MyArrayList
	 * 
	 * @param list		MyArrayList, field to search 
	 * @param key		element to find
	 * @return		index of desirable element or (-(insertion point) - 1), if element is not in the List
	 */
	public static int binarySearch(MyArrayList list, Integer key) {
		return bisectionSearch(list, key, 0, list.size());
	}
	
	//Helper for binarySearch
	private static int bisectionSearch(MyArrayList list, Integer key, int minIndex, int maxIndex) {
		
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
