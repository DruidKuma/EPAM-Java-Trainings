package ua.epam.lab;

/**
* My Personal Single Linked List
*
* @author Yuriy Miedviediev
* @version 1.0 Build 02.03.2014
*
*/
public class MyLinkedList {
	
	//Head of the list, fake Element
	private Element head = new Element(null);
	
	//helpers, pointers to current element and the element next to it
	Element current;
	Element next;
	
	/**
	 * Method for debugging to print the Linked List to the console
	 */
	public void printList() {
		System.out.print("head -> ");
		current = head.getNext();
		while(!(current==null)) {
			System.out.print(current.getValue() + " -> ");
			current = current.getNext();
		}
		System.out.println("null");
	}
	
	/**
	 * Compute the size of the Linked List (the number of its valuable elements)
	 *
	 * @return int, size of the Linked List
	 */
	public int size() {
		int length = 0;
		current = head.getNext();
		//counts elements one by one up to the end
		while(!(current==null)) {
			length++;
			current = current.getNext();
		}
		return length;
	}
	
	/**
	 * This method adds new Element to the end of the Linked List
	 * 
	 * @param e		Integer, value of the Element
	 */
	public void add(Integer e) {
		// if Linked List is empty, place new Element at start of it
		if(head.getNext()==null) {
			head.setNext(new Element(e));
		}
		else {
			//pointers to current element and the next element to it
			current = head.getNext();
			next = current.getNext();
			
			//move up to the end of the Linked List
			while(!(next==null)) {
				current = current.getNext();
				next = current.getNext();
			}
			//current is now pointing to the last element of the Linked List
			current.setNext(new Element(e));
		}
	}
	
	/**
	 * Add a new Element to a certain place in the Linked List <br>
	 * In case of negative index or index bigger than size of the Linked List,
	 * element would just be added to the end of the List
	 * 
	 * @param index		desirable position of the element
	 * @param element	new Element to add to the Linked List
	 */
	public void add(int index, Integer element) {
		//in List is empty, or index is the last one in the List, just add to the end
		if(this.size() == 0 || index == this.size()) {
			this.add(element);
			return;
		}
		else {
			int counter = 0;
			current = head;
			next = current.getNext();
			try {
				//find necessary position in the List
				while (counter!=index) {
				current = current.getNext();	
				next = current.getNext();
				counter++;
				}
				//insert element to appropriate position and point to the rest of the List
				current.setNext(new Element(element));
				current = current.getNext();
				current.setNext(next);
			} catch (NullPointerException e) {
				//in case of incorrect index, add element to the end of the List
				this.add(element);
			}
			
		}
		
	}
	
	/**
	 * Add element to the start of the Linked List
	 * 
	 * @param e		new Element to be added
	 */
	public void addFirst(Integer e) {
		this.add(0, e);
	}
	
	/**
	 * Add element to the end of the Linked List
	 * Duplicate of the standard add(Integer e) method
	 * 
	 * @param e		new Element to be added
	 */
	public void addLast(Integer e) {
		this.add(e);
	}
	
	/**
	 * Get value of the Element at certain position
	 * In case of negative index or index bigger than size of the Linked List,
	 * method would return null
	 *  
	 * @param index		natural, position in the Linked List
	 * @return		Integer, value of the Element
	 */
	public Integer get(int index) {
		int counter = 0;
		current = head.getNext();
		try {
			//path through the List and find necessary position
			while (counter!=index) {
			current = current.getNext();
			counter++;
			}
			//take the value of the Element and return
			return current.getValue();
		} catch (NullPointerException e) {
			//in case of incorrect index, recurse on the last element of the List
			return null;
		}
	}
	
	/**
	 * Get value of the first Element
	 * @return	Integer, value of the first Element
	 */
	public Integer getFirst() {
		return this.get(0);
	}
	
	/**
	 * Get value of the last Element
	 * @return	Integer, value of the last Element
	 */
	public Integer getLast() {
		return this.get(this.size()-1);
	}
	
	/**
	 * Remove an Element at a certain index and return it
	 * In case of empty Linked List or incorrect index, returns null
	 * 
	 * @param index		position in the Linked List
	 * @return		Integer, removed element
	 */
	public Integer remove(int index) {
		//if List is empty, nothing to remove
		if (this.size() == 0) {
			return null;
		}
		else {
			int counter = 0;
			current = head;
			next = current.getNext();
			try {
				//find appropriate element
				while (counter!=index) {
				current = current.getNext();	
				next = current.getNext();
				counter++;
				}
				//remember removed element to return it
				Element removed = next;
				//tie current element to the element next to removed
				next = next.getNext();
				current.setNext(next);
				return removed.getValue();
			} catch (NullPointerException e) {
				//in case in incorrect index, return null
				return null;
			}
		}
	}
	
	/**
	 * Removes and returns the first element of the Linked List
	 * 
	 * @return		Integer, removed element
	 */
	public Integer removeFirst() {
		return this.remove(0);
	}
	
	/**
	 * Removes and returns the last element of the Linked List
	 * 
	 * @return		Integer, removed element
	 */
	public Integer removeLast() {
		return this.remove(this.size()-1);
	}
	
	/**
	 * Set the value of Element at certain position
	 * Ignores the operation in case of incorrect index
	 * 
	 * @param index position of the Element
	 * @param element	new value of the Element
	 */
	public void set(int index, Integer element) {
		int counter = 0;
		current = head.getNext();
		try {
			//find necessary element
			while (counter!=index) {
			current = current.getNext();
			counter++;
			}
			//update its value
			current.setValue(element);
		} catch (NullPointerException e) {
			//in case of incorrect index, immediately return
			return;
		}
	}
	
	/**
	 * Get the index of an Element with certain value
	 * In case of not finding element with necessary value, returns -1
	 *  
	 * @param o		Integer, value to find
	 * @return		index of Element with matching value
	 */
	public int indexOf(Integer o) {
		int index = 0;
		current = head.getNext();
		try {
			//find first element with necessary value
			while (current.getValue() != o) {
			current = current.getNext();
			index++;
			}
			return index;
		} catch (NullPointerException e) {
			//in case of incorrect or not found value, return -1
			return -1;
		}
		
	}
}