package ua.epam.lab;

/**
* Element of the Single Linked List
*
* @author Yuriy Miedviediev
* @version 1.0 Build 31.03.2014
*
*/
public class Element {
	// Structure for the Element
	private Integer value;
	private Element next;
	
	//constructor without arguments
	public Element() {}
	
	//constructor for Element
	public Element(Integer newValue) {
		value = newValue;
	}
	// value getter/setter
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer newValue) {
		value = newValue;
	}
	
	// next element getter/setter
	public Element getNext() {
		return next;
	}
	public void setNext(Element newNext) {
		next = newNext;
	}
	
	// value deleter
	public void remove() {
		value = null;
	}
	
	// check for the next element
	public boolean hasNext() {
		return !(next==null);
	}
}
