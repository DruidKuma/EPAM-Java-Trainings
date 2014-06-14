package ua.epam.lab;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

	private MyLinkedList list;
	
	@Before
	public void setUp() {
		list = new MyLinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
	}
	
	@Test
	public void SizeIsProperlyComputedInAListWithElements() {
		assertEquals("Size is not properly computed in a List with elements", 3, list.size());
	}
	
	@Test
	public void SizeIsProperlyComputedInAnEmptyList() {
		list = new MyLinkedList();
		assertEquals("Size is not properly computed in an empty List", 0, list.size());
	}
	
	@Test
	public void AddMethodCorrectlyAddsElementToAnEmptyList() {
		list = new MyLinkedList();
		list.add(1);
		assertEquals("Add methos does not correctly add element in an empty List", (Integer)1, list.getLast());
	}
	
	@Test
	public void AddMethodCorrectlyAddsElementToAListWithElements() {
		list.add(5);
		assertEquals("Add methos does not correctly add element in a List with elements", (Integer)5, list.getLast());
	}
	
	@Test
	public void AddWithIndexMethodCorrectlyAddsElementsToTheFirstPosition() {
		list.add(0, 10);
		assertEquals("Add with index method does not properly add element to the first position", (Integer)10, list.getFirst());
	}
	
	@Test
	public void AddWithIndexMethodCorrectlyAddsElementsToTheLastPosition() {
		list.add(list.size(), 10);
		assertEquals("Add with index method does not properly add element to the last position", (Integer)10, list.getLast());
	}
	
	@Test
	public void AddWithIndexMethodCorrectlyAddsElementsToTheMiddlePosition() {
		list.add(2, 10);
		assertEquals("Add with index method does not properly add element to the middle position", (Integer)10, list.get(2));
	}
	
	@Test
	public void GetMethodCorrectlyGetsFirstElement() {
		assertEquals("Get method does not correctly get first element", (Integer)1, list.get(0));
	}
	
	@Test
	public void GetMethodCorrectlyGetsLastElement() {
		assertEquals("Get method does not correctly get last element", (Integer)3, list.get(list.size()-1));
	}
	
	@Test
	public void GetMethodCorrectlyGetsMiddleElement() {
		assertEquals("Get method does not correctly get middle element", (Integer)2, list.get(1));
	}
	
	@Test
	public void GetMethodReturnsNullWhenUsedWithAnEmptyList() {
		list = new MyLinkedList();
		assertEquals("Get method does not return null when used with an empty List", null, list.get(0));
	}
	
	@Test
	public void GetMethodReturnsNullWhenUsedWithIncorrectIndex() {
		assertEquals("Get method does not return null when used with an empty List", null, list.get(10));
	}
	
	@Test
	public void RemoveMethodCorrectlyRemovesFirstElement() {
		assertEquals("Remove method does not remove first element correctly", (Integer)1, list.remove(0));
	}
	
	@Test
	public void RemoveMethodCorrectlyRemovesLastElement() {
		assertEquals("Remove method does not remove last element correctly", (Integer)3, list.remove(list.size()-1));
	}
	
	@Test
	public void RemoveMethodCorrectlyRemovesMiddleElement() {
		assertEquals("Remove method does not remove middle element correctly", (Integer)2, list.remove(1));
	}
	
	@Test
	public void RemoveMethodReturnsNullWhenUsedWithIncorrectIndex() {
		assertEquals("Remove method does not remove middle element correctly", null, list.remove(10));
	}
	
	@Test
	public void SetMethodCorrectlySetsValueOfTheFirstElement() {
		list.set(0, 10);
		assertEquals("Set method does not correctly set value of the first element", (Integer)10, list.getFirst());
	}
	
	@Test
	public void SetMethodCorrectlySetsValueOfTheLastElement() {
		list.set(list.size()-1, 10);
		assertEquals("Set method does not correctly set value of the last element", (Integer)10, list.getLast());
	}
	
	@Test
	public void SetMethodCorrectlySetsValueOfTheMiddleElement() {
		list.set(1, 10);
		assertEquals("Set method does not correctly set value of the middle element", (Integer)10, list.get(1));
	}
	
	@Test
	public void IndexOfMethodReturnsNegativeOneInCaseOfNotFoundValue() {
		assertEquals("IndexOf method does not return -1 when used with not found value", -1, list.indexOf(10));
	}
	
	@Test
	public void IndexOfMethodCorrectlyFindsIndexOfTheFirstElement() {
		assertEquals("IndexOf method does not correctly find index of the first element", 0, list.indexOf(1));
	}
	
	@Test
	public void IndexOfMethodCorrectlyFindsIndexOfTheLastElement() {
		assertEquals("IndexOf method does not correctly find index of the last element", 2, list.indexOf(3));
	}
	
	@Test
	public void IndexOfMethodCorrectlyFindsIndexOfTheMiddleElement() {
		assertEquals("IndexOf method does not correctly find index of the first element", 1, list.indexOf(2));
	}
	
	@Test
	public void IndexOfMethodCorrectlyFindsIndexOfTheDuplicateElement() {
		list.add(2);
		assertEquals("IndexOf method does not correctly find index of the first element", 1, list.indexOf(2));
	}
}
