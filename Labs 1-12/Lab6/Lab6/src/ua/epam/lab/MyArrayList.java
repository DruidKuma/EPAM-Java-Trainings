package ua.epam.lab;

/**
* My Personal version of ArrayList
*
* @author Yuriy Miedviediev
* @version 1.0 Build 05.04.2014
*
*/
public class MyArrayList {
	//container of all the data
	private Object[] contents;
	//number of valuable elements in the ArrayList
	private int numElements = 0;
	
	/**
	 * Using no-argument constructor creates holder for data with initial capacity of 10
	 */
	public MyArrayList() {
		this.contents = new Object[10];
	}
	
	/**
	 * Using this constructor creates holder for data with given initial capacity
	 * 
	 * @param initialCapacity	nonnegative integer, initial capacity for ArrayList
	 * @throws IllegalArgumentException		In case of negative number, throws an exception
	 */
	public MyArrayList(int initialCapacity) {
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("ArrayList cannot have negative capacity");
		}
		this.contents = new Object[initialCapacity];
	}
	
	// Helper method to verify and correct the size of ArrayList to be big enough to contain all elements
	private void ensureCapacity(int minCapacity) {	
		
		//while size of ArrayList isn't big enough
		while(minCapacity > this.contents.length) {		
			
			//new container with twice bigger size
			Object[] newContents = new Object[this.contents.length * 2];		
			
			//copy all elements from old container to the new one
			for(int pos=0; pos<this.contents.length; pos++) {
				newContents[pos] = this.contents[pos];
			}
			this.contents = newContents;
		}
	}
	
	/**
	 * Add new Object element to the end of the ArrayList
	 *  
	 * @param e 	new element of type Object
	 */
	public void add(Object e) {	
		
		//first verify if current container can accommodate the element
		ensureCapacity(numElements+1);		
		
		//place new element after last existing element
		this.contents[numElements] = e;	
		
		//track the number of elements in the ArrayList
		this.numElements++;
	}
	
	/**
	 * Add new element to a specified index of the ArrayList and shift all elements to the right
	 * @param index		position to insert in
	 * @param e		new element to add
	 * @throws IllegalArgumentException		In case of incorrect index, throws an exception
	 */
	public void add(int index, Object e) {
		
		//restrict using wrong indexes
		if(index > this.numElements || index < 0) {
			throw new IllegalArgumentException("Such index doesn't exist in the ArrayList");
		}
		else if(index == this.numElements) {
			this.add(e);
			return;
		}
		else {
			//temporary variables for shifting elements
			Object tempCurrent;
			Object tempNext;			
			
			//verify the size of the container
			ensureCapacity(numElements+1);
			
			//remember old element and place the new one on its place
			tempCurrent = this.contents[index];
			this.contents[index] = e;			
			
			//shift all elements to the right by one position
			for(int pos = index+1; pos<=this.numElements; pos++) {
				tempNext = this.contents[pos];
				this.contents[pos] = tempCurrent;
				tempCurrent = tempNext;
			}
			//track the number of elements
			this.numElements++;
		}
	}
	
	/**
	 * Append all elements in the array to the end of the ArayList
	 * @param c		array of Objects to append
	 */
	public void addAll(Object[] c) {
		for(Object element : c) {
			this.add(element);
		}
	}
	
	/**
	 * Insert all elements of the array to the ArrayList starting at given index
	 * @param index		index to start from
	 * @param c		array of Objects to insert
	 */
	public void addAll(int index, Object[] c) {
		for(Object element : c) {
			this.add(index, element);
			index++;
		}
	}
	
	/**
	 * Get element of the ArrayList by index
	 * @param index		position of the element
	 * @return 		element of the ArrayList, Object type
	 * @throws IllegalArgumentException		In case of incorrect index, throws an exception
	 */
	public Object get(int index) {
		if(index >= this.numElements || index < 0) {
			throw new IllegalArgumentException("Such index doesn't exist in the ArrayList");
		}
		else {
			return this.contents[index];
		}
		
	}
	
	/**
	 * Set new value for the given position of the ArrayList
	 * @param index
	 * @param element
	 * @throws IllegalArgumentException		In case of incorrect index, throws an exception
	 */
	public void set(int index, Object element) {
		if(index >= this.numElements || index < 0) {
			throw new IllegalArgumentException("Such index doesn't exist in the ArrayList");
		}
		else {
			this.contents[index] = element;
		}
	}
	
	/**
	 * Return the number of elements in the ArrayList
	 * @return	integer, size of the ArrayList
	 */
	public int size() {
		return this.numElements;
	}
	
	//Helper for adjusting the size of the container, reduce the number of empty cells if any
	private void reduceSize() {
		
		//if there exist empty cells
		if(this.numElements < this.contents.length) {
			
			//new container
			Object[] newContents = new Object[(this.contents.length * 3)/2 + 1];
			
			//copy all elements to the new container
			for(int pos=0; pos<this.numElements; pos++) {
				newContents[pos] = this.contents[pos];
			}
			this.contents = newContents;
		}
	}
	
	/**
	 * Remove the element at given index, shift all elements to the left and adjust the size of the container
	 * @param index		position in the ArrayList
	 * @return	removed element of type Object
	 */
	public Object remove(int index) {
		
		if(index >= this.numElements || index < 0) {
			throw new IllegalArgumentException("Such index doesn't exist in the ArrayList");
		}
		//remember removed element
		Object removed = this.contents[index];
		
		//temporary variable for shifting to the left 
		Object tempCurrent = this.contents[numElements-1];
		
		//shift every element to the left from the last element to the removed element
		for(int pos=this.numElements-2; pos>=index; pos--) {
			Object tempPrevious = this.contents[pos];
			this.contents[pos] = tempCurrent;
			tempCurrent = tempPrevious;
		}
		
		//track the number of elements
		this.numElements--;
		
		//adjust the size of the container
		this.reduceSize();
		
		return removed;
	}
	
	public boolean equals(MyArrayList list) {
		//if lists have different sizes, they are not equal
		if(this.size() != list.size()) {
			return false;
		}
		else {

			//check the equality of every element in the lists
			for(int pos=0, length=this.size(); pos<length; pos++) {
				if(!(this.get(pos).equals(list.get(pos)))) {
					return false;
				}
			}

			//if up to this moment none of the elements returned false, lists are equal
			return true;
		}
	}
	
	/**
	 * Print all valuable contents of the ArrayList to the console 
	 */
	public void print() {
		System.out.print("[");
		for(int i=0; i<this.numElements; i++) {
			System.out.print(this.contents[i] + ", ");
		}
		System.out.print("]");
		System.out.println("");
	}
}