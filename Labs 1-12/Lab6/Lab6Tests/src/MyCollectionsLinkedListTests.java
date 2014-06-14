import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ua.epam.lab.MyLinkedList;
import ua.epam.lab.collections.MyCollections;


public class MyCollectionsLinkedListTests {
	
	private MyLinkedList list;
	private MyLinkedList expected;
	
	@Before
	public void setUp() {
		list = new MyLinkedList();
		list.add(new Integer(5));
		list.add(new Integer(2));
		list.add(new Integer(1));
		list.add(new Integer(3));
		expected = new MyLinkedList();
	}

	@Test
	public void SortMethodCorrectlySortsEmptyList() {
		MyLinkedList list = new MyLinkedList();
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort empty MyLinkedList",list.equals(expected));
	}
	
	@Test
	public void SortMethodCorrectlySortsListWithOneElement() {
		MyLinkedList list = new MyLinkedList();
		list.add(1);
		expected.add(1);
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort MyLinkedList with one element",list.equals(expected));
	}
	
	@Test
	public void SortMethodCorrectlySortsListWithManyElements() {
		MyLinkedList expected = new MyLinkedList();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(5);
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort MyLinkedList with many elements",list.equals(expected));
	}
	
	@Test
	public void SwapCorrectlySwapsElements() {
		MyCollections.swap(list, 0, 2);
		assertEquals("Swap method does not correctly swap elements in the MyLinkedList", (Integer)1, list.get(0));
		assertEquals("Swap method does not correctly swap elements in the MyLinkedList", (Integer)5, list.get(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void SwapRestrictsIncorrectIndexes() {
		MyCollections.swap(list, 10, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void SwapRestrictsNegativeIndexes() {
		MyCollections.swap(list, 0, -2);
	}
	
	@Test
	public void CopyWorksProperlyOnEmptyList() {
		MyCollections.copy(expected, list);
		assertEquals("Copy does not work properly on empty list", 4, expected.size());
	}
	
	@Test
	public void CopyWorksProperlyOnListWithElements() {
		expected.add(1);
		expected.add(6);
		expected.add(9);
		MyCollections.copy(expected, list);
		assertEquals("Copy does not work properly on list with elements", 7, expected.size());
	}
	
	@Test
	public void CloneMethodWorksProperly() {
		expected.add(1);
		expected.add(6);
		expected.add(9);
		expected.add(10);
		MyCollections.clone(expected, list);
		assertTrue("Clone does not work properly", list.equals(expected));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CloneMethodThrowsExceptionWhenGivenListsWithDifferentCapacity() {
		MyCollections.clone(expected, list);
	}
	
	@Test
	public void ReverseMethodReverseLinkedListProperly() {
		expected.add(3);
		expected.add(1);
		expected.add(2);
		expected.add(5);
		MyCollections.reverse(list);
		assertTrue("Reverse method does not reverse MyLinkedList properly", list.equals(expected));
	}

}
