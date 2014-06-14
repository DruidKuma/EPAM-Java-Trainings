package ua.epam.lab;

/**
* My Personal Single Linked List
*
* @author Yuriy Miedviediev
* @version 1.0 Build 02.03.2014
*
*/

import java.util.Objects;
import ua.epam.lab.interfaces.*;

public class MyLinkedList implements MyList, Queue, Stack {
	
	//Head of the list, fake Element
	private final Element head = new Element(null);
	
	//helpers, pointers to current element and the element next to it
	Element current;
	Element next;
	
	/**
	 * Method for debugging to print the Linked List to the console
	 */
        @Override
	public void print() {
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
	 * @return integer, size of the Linked List
	 */
        @Override
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
	 * @param e		Object, value of the Element
	 */
        @Override
	public void add(Object e) {
		// if Linked List is empty, place new Element at start of it
		if(head.getNext()==null) {
			head.setNext(new Element((Integer)e));
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
			current.setNext(new Element((Integer)e));
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
        @Override
	public void add(int index, Object element) {
		//in List is empty, or index is the last one in the List, just add to the end
		if(this.size() == 0 || index == this.size()) {
			this.add(element);
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
				current.setNext(new Element((Integer)element));
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
        @Override
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
        @Override
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
        @Override
	public void set(int index, Object element) {
		int counter = 0;
		current = head.getNext();
		try {
			//find necessary element
			while (counter!=index) {
			current = current.getNext();
			counter++;
			}
			//update its value
			current.setValue((Integer)element);
		} catch (NullPointerException e) { }
	}
	
	/**
	 * Get the index of an Element with certain value
	 * In case of not finding element with necessary value, returns -1
	 *  
	 * @param o		Integer, value to find
	 * @return		index of Element with matching value
	 */
        @Override
	public int indexOf(Object o) {
		int index = 0;
		current = head.getNext();
		try {
			//find first element with necessary value
			while (!Objects.equals(current.getValue(), o)) {
			current = current.getNext();
			index++;
			}
			return index;
		} catch (NullPointerException e) {
			//in case of incorrect or not found value, return -1
			return -1;
		}
		
	}
	
	/**
	 * Check the equality of current MyLinkedList with the other one
	 * @param list	Other MyLinkedList to check whether it is equal to the current one
	 * @return	true, if lists are equal, false otherwise
	 */
        @Override
	public boolean equals(MyList list) {
            //if lists have different sizes, they are not equal
            if(this.size() != list.size()) {
                    return false;
            }
            else {

                    //check the equality of every element in the lists
                    for(int pos=0, length=this.size(); pos<length; pos++) {
                            if(this.get(pos).compareTo((Integer) list.get(pos)) != 0) {
                                return false;
                            }
                    }		
                    //if up to this moment none of the elements returned false, lists are equal
                    return true;
            }
	}
        
        /**
         * Convert MyLinkedList to an array of Objects
         * @return array of Objects
         */
        @Override
        public Object[] toArray() {
            //result array of length identical to size of MyLinkedList
            Object[] converted = new Object[this.size()];
            
            //assign values one by one
            for(int pos=0,length=this.size(); pos<length; pos++) {
                converted[pos] = this.get(pos);
            }
            return converted;
        }
        
        /**
         * Add an array of Objects to MyLinkedList
         * @param c array of Objects
         */
        @Override
        public void addAll(Object[] c) {
            for(Object element : c) {
                this.add(element);
            }
        }
        
        /**
         * Add an array of Objects to MyLinkedList starting at specified index
         * @param index     position where to insert first element
         * @param c         array of Objects
         */
        @Override
        public void addAll(int index, Object[] c) {
            for(Object element : c) {
                this.add(index, element);
                index++;
            }
        }

        /*
        Queue methods
        ================================================================
        */
        /**
	 * Add new element to the end of the Queue
	 * 
	 * @param e		Element to add, type Object
	 */
        @Override
        public void offer(Object e) {
            //method 'add' of the Linked List needs Integer as argument
            this.add(((Element) e).getValue());
        }
        
        /**
	 * Get the first element of the Queue or null if the Queue is empty
	 * 
	 * @return	new Element, identical to the first of the Queue, type Object
	 */
        @Override
        public Object peek() {
            //return new Element not to avoid back-door using links in the Element
            return new Element(this.getFirst());
        }
        
        /**
	 * Get the first element of the Queue, and remove it
	 * Return null, if Queue is empty
	 * 
	 * @return new Element, identical to the first of the Queue, type Object
	 */
        @Override
        public Object poll() {
            //return new Element not to avoid back-door using links in the Element
            return new Element(this.removeFirst());
        }
        
        /*
        End of Queue methods
        ================================================================
        */
        
        /*
        Stack methods
        ================================================================
        */
        
        /**
	 * Push a new Element on top of the Stack
	 * 
	 * @param e		Element to push
	 */
        @Override
        public void push(Object e) {
            //method 'add' of the Linked List needs Integer as argument
            Integer value = ((Element) e).getValue();
            this.addFirst(value);
        }

        /**
	 * Take element from the top of the Stack (and remove it)
	 * 
	 * @return Element, identical to the one on top of the Stack
	 */
        @Override
        public Object pop() {
            //return new Element not to avoid back-door using links in the Element
            return new Element(this.removeFirst());
        }
        
        /*
        End of Stack methods
        ================================================================
        */
}