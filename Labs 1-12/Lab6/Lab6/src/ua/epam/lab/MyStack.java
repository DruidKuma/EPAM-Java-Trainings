package ua.epam.lab;

/**
* My Personal version of Stack based on Linked List
*
* @author Yuriy Miedviediev
* @version 1.0 Build 03.04.2014
*
*/
public class MyStack {
	
	//instance of Linked List (use of composition)
	private MyLinkedList stack = new MyLinkedList();
	
	/**
	 * Push a new Element on top of the Stack
	 * 
	 * @param e		Element to push
	 */
	public void push(Element e) {
		//method 'add' of the Linked List needs Integer as argument
		Integer value = e.getValue();
		stack.addFirst(value);
	}
	
	/**
	 * Take element from the top of the Stack (and remove it)
	 * 
	 * @return Element, identical to the one on top of the Stack
	 */
	public Element pop() {
		//return new Element not to avoid back-door using links in the Element
		return new Element(stack.removeFirst());
	}
	
	/**
	 * Prints the Stack, helper for debugging
	 */
	public void printStack() {
		stack.printList();
	}
}
