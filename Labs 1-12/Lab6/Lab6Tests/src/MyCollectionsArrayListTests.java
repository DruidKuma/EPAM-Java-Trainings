import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ua.epam.lab.MyArrayList;
import ua.epam.lab.collections.MyCollections;


public class MyCollectionsArrayListTests {
	
	private MyArrayList list;
	private MyArrayList expected;
	
	@Before
	public void setUp() {
		list = new MyArrayList();
		list.addAll(new Integer[] {5, 2, 1, 3});
		expected = new MyArrayList();
	}

	@Test
	public void SortMethodCorrectlySortsEmptyList() {
		MyArrayList list = new MyArrayList();
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort empty MyArrayList",list.equals(expected));
	}
	
	@Test
	public void SortMethodCorrectlySortsListWithOneElement() {
		MyArrayList list = new MyArrayList();
		list.add(1);
		expected.add(1);
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort MyArrayList with one element",list.equals(expected));
	}
	
	@Test
	public void SortMethodCorrectlySortsListWithManyElements() {
		MyArrayList expected = new MyArrayList();
		expected.addAll(new Integer[] {1, 2, 3, 5});
		MyCollections.sort(list);
		assertTrue("Sort method does not correctly sort MyArrayList with many elements",list.equals(expected));
	}
	
	@Test
	public void SwapCorrectlySwapsElements() {
		MyCollections.swap(list, 0, 2);
		assertEquals("Swap method does not correctly swap elements in the MyArrayList", (Integer)1, list.get(0));
		assertEquals("Swap method does not correctly swap elements in the MyArrayList", (Integer)5, list.get(2));
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
		expected.addAll(new Integer[] {1, 6, 9});
		MyCollections.copy(expected, list);
		assertEquals("Copy does not work properly on list with elements", 7, expected.size());
	}
	
	@Test
	public void CloneMethodWorksProperly() {
		expected.addAll(new Integer[] {1, 6, 9, 10});
		MyCollections.clone(expected, list);
		assertTrue("Clone does not work properly", list.equals(expected));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CloneMethodThrowsExceptionWhenGivenListsWithDifferentCapacity() {
		MyCollections.clone(expected, list);
	}
	
	@Test
	public void ReverseMethodReverseArrayListProperly() {
		expected.addAll(new Integer[] {3, 1, 2, 5});
		MyCollections.reverse(list);
		assertTrue("Reverse method does not reverse MyLinkedList properly", list.equals(expected));
	}
	
	@Test
	public void BinarySearchCorrectlyFindsElementsThatAreInTheList() {
		MyCollections.sort(list);
		assertEquals("Binary search does not find element on the first place", 0, MyCollections.binarySearch(list, 1));
		assertEquals("Binary search does not find element on the middle place", 2, MyCollections.binarySearch(list, 3));
		assertEquals("Binary search does not find element on the last place", 3, MyCollections.binarySearch(list, 5));
	}
	
	@Test
	public void BinarySearchReturnsCorrectlyComputedIndexOfElementsThatAreNotInTheList() {
		MyCollections.sort(list);
		assertEquals("Binary search does not return correctly computed index of element less than the first", -1, MyCollections.binarySearch(list, 0));
		assertEquals("Binary search does not return correctly computed index of element greater than the last", -5, MyCollections.binarySearch(list, 6));
		assertEquals("Binary search does not return correctly computed index of element that should be in the middle", -4, MyCollections.binarySearch(list, 4));
	}
}
