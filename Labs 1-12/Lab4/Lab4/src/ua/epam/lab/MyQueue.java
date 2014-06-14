package ua.epam.lab;

/**
* My Personal version of Queue based on Linked List
*
* @author Yuriy Miedviediev
* @version 1.0 Build 03.04.2014
*
*/
public class MyQueue {
	
	//instance of Linked List (use of composition)
	private MyLinkedList queue = new MyLinkedList();
	
	/**
	 * Add new element to the end of the Queue
	 * 
	 * @param e		Element to add
	 */
	public void offer(Element e) {
		//method 'add' of the Linked List needs Integer as argument
		Integer value = e.getValue();
		queue.add(value);
	}
	
	/**
	 * Get the first element of the Queue or null if the Queue is empty
	 * 
	 * @return	new Element, identical to the first of the Queue
	 */
	public Element peek() {
		//return new Element not to avoid back-door using links in the Element
		return new Element(queue.getFirst());
	}
	
	/**
	 * Get the first element of the Queue, and remove it
	 * Return null, if Queue is empty
	 * 
	 * @return new Element, identical to the first of the Queue
	 */
	public Element poll() {
		//return new Element not to avoid back-door using links in the Element
		return new Element(queue.removeFirst());
	}
	
	/**
	 * Prints the Queue, helper for debugging
	 */
	public void printQueue() {
		queue.printList();
	}
}
