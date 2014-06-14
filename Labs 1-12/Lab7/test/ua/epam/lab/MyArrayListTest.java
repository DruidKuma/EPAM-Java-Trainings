package ua.epam.lab;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {
	
	private MyArrayList list;
	
	@Before
	public void setUp() {
		list = new MyArrayList();
		list.addAll(new Integer[] {1,2,3});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void UsingArgumentConstructorWithNegativeCapacityThrowsAnException() {
		list = new MyArrayList(-10);
	}
	
	@Test
	public void SizeCorrectlyComputedonEmptyList() {
		list = new MyArrayList();
		assertEquals("Size is not correctly computed in an empty list", 0, list.size());
	}
	
	@Test
	public void SizeCorrectlyComputedonListWithElements() {
		assertEquals("Size is not correctly computed in a list with elements", 3, list.size());
	}
	
	@Test
	public void GetCorrectlyGetsElementsFromDifferentPositions() {
		assertEquals("Get does not correctly get elements at first position", 1, list.get(0));
		assertEquals("Get does not correctly get elements at middle position", 2, list.get(1));
		assertEquals("Get does not correctly get elements at last position", 3, list.get(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void GetThrowsAnExceptionWhenUsedWithIncorrectIndex() {
		list.get(4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void GetThrowsAnExceptionWhenUsedWithNegativeIndex() {
		list.get(-1);
	}
	
	@Test
	public void SetWorksProperlyAtDifferentIndexes() {
		list.set(0, 10);
		assertEquals("Set does not correctly set new values at first position", 10, list.get(0));
		list.set(1, 20);
		assertEquals("Set does not correctly set new values at middle position", 20, list.get(1));
		list.set(2, 30);
		assertEquals("Set does not correctly set new values at last position", 30, list.get(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void SetThrowsAnExceptionWhenUsedWithIncorrectIndex() {
		list.set(4, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void SetThrowsAnExceptionWhenUsedWithNegativeIndex() {
		list.set(-1, 10);
	}
	
	@Test
	public void AddCorrectlyAddsElementsToTheEmptyList() {
		list = new MyArrayList();
		list.add(1);
		assertEquals("Add does not corectly add elements into empty ArrayList", 1, list.get(0));
	}
	
	@Test
	public void AddCorrectlyAddsElementsToTheList() {
		list.add(4);
		assertEquals("Add does not corectly add elements into ArrayList with elements", 4, list.get(3));
	}
	
	@Test
	public void IndexedAddCorrectlyAddsElementsToFirstPosition() {
		list.add(0, 5);
		assertEquals("Indexed Add does not corectly add elements at first position", 5, list.get(0));
		assertEquals("Indexed Add does not corectly add elements at first position", 1, list.get(1));
		assertEquals("Indexed Add does not corectly add elements at first position", 3, list.get(3));
	}
	
	@Test
	public void IndexedAddCorrectlyAddsElementsToMiddlePosition() {
		list.add(1, 5);
		assertEquals("Indexed Add does not corectly add elements at middle position", 1, list.get(0));
		assertEquals("Indexed Add does not corectly add elements at middle position", 5, list.get(1));
		assertEquals("Indexed Add does not corectly add elements at middle position", 3, list.get(3));
	}
	
	@Test
	public void IndexedAddCorrectlyAddsElementsToLastPosition() {
		list.add(3, 5);
		assertEquals("Indexed Add does not corectly add elements at last position", 1, list.get(0));
		assertEquals("Indexed Add does not corectly add elements at last position", 3, list.get(2));
		assertEquals("Indexed Add does not corectly add elements at last position", 5, list.get(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void IndexedAddThrowsAnExceptionGivenNegativeIndex() {
		list.add(-1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void IndexedAddThrowsAnExceptionGivenIncorrectIndex() {
		list.add(4, 5);
	}
	
	@Test
	public void AddAllWorksProperlyOnListWithElements() {
		list.addAll(new Integer[] {6, 7, 8});
		assertEquals("addAll does not corectly add elements into ArrayList with Elements", 6, list.get(3));
		assertEquals("addAll does not corectly add elements into ArrayList with Elements", 8, list.get(5));
	}
	
	@Test
	public void AddAllWorksProperlyOnEmptyList() {
		list = new MyArrayList();
		list.addAll(new Integer[] {6, 7, 8});
		assertEquals("addAll does not corectly add elements into empty ArrayList", 6, list.get(0));
		assertEquals("addAll does not corectly add elements into empty ArrayList", 8, list.get(2));
	}
	
	@Test
	public void IndexedAddAllCorrectlyInsertElementsToFirstPosition() {
		list.addAll(0, new Integer[] {5, 6, 7});
		assertEquals("Indexed addAll does not corectly add elements to the start of the ArrayList", 5, list.get(0));
		assertEquals("Indexed addAll does not corectly add elements to the start of the ArrayList", 1, list.get(3));
	}
	
	@Test
	public void IndexedAddAllCorrectlyInsertElementsToMiddlePosition() {
		list.addAll(1, new Integer[] {5, 6, 7});
		assertEquals("Indexed addAll does not corectly add elements to the middle of the ArrayList", 1, list.get(0));
		assertEquals("Indexed addAll does not corectly add elements to the middle of the ArrayList", 5, list.get(1));
		assertEquals("Indexed addAll does not corectly add elements to the middle of the ArrayList", 7, list.get(3));
		assertEquals("Indexed addAll does not corectly add elements to the middle of the ArrayList", 2, list.get(4));
	}
	
	@Test
	public void IndexedAddAllCorrectlyAppendsElements() {
		list.addAll(3, new Integer[] {5, 6, 7});
		assertEquals("Indexed addAll does not corectly append elements to the ArrayList", 3, list.get(2));
		assertEquals("Indexed addAll does not corectly append elements to the ArrayList", 5, list.get(3));
		assertEquals("Indexed addAll does not corectly append elements to the ArrayList", 7, list.get(5));
	}
	
	@Test
	public void AddMethodsCorrectlyChangeTheSize() {
		list.add(10);
		assertEquals("add does not corectly change the size of the ArrayList", 4, list.size());
		list.add(1, 20);
		assertEquals("Indexed add does not corectly change the size of the ArrayList", 5, list.size());
		list.addAll(new Integer[] {30, 40});
		assertEquals("addAll does not corectly change the size of the ArrayList", 7, list.size());
		list.addAll(5, new Integer[] {50, 60});
		assertEquals("Indexed addAll does not corectly change the size of the ArrayList", 9, list.size());
	}
	
	@Test
	public void RemoveCorrectlyRemovesFirstElement() {
		list.remove(0);
		assertEquals("Remove does not correctly delete first element", 2, list.get(0));
		assertEquals("Remove does not correctly delete first element", 3, list.get(1));
	}
	
	@Test
	public void RemoveCorrectlyRemovesMiddleElement() {
		list.remove(1);
		assertEquals("Remove does not correctly delete first element", 1, list.get(0));
		assertEquals("Remove does not correctly delete first element", 3, list.get(1));
	}
	
	@Test
	public void RemoveCorrectlyRemovesLastElement() {
		list.remove(2);
		assertEquals("Remove does not correctly delete first element", 1, list.get(0));
		assertEquals("Remove does not correctly delete first element", 2, list.get(1));
	}
	
	@Test
	public void RemoveCorrectlyChangesTheSize() {
		list.remove(1);
		list.remove(1);
		assertEquals("Remove does not correctly change the size of the ArrayList", 1, list.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void RemoveThrowsAnExceptionGivenNegativeIndex() {
		list.remove(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void RemoveThrowsAnExceptionGivenIncorrectIndex() {
		list.remove(4);
	}	
}
