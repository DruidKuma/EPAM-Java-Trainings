package ua.epam.lab;

import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import ua.epam.lab.collections.MyCollections;
import ua.epam.lab.interfaces.MyList;

public class MyCollectionsTest {
	
    private MyList list;
    private MyList expected;
    private MyList linkedList;
    private MyList expectedLinkedList;

    @Before
    public void setUp() {
        list = new MyArrayList();
        list.addAll(new Integer[] {5, 2, 1, 3});
        expected = new MyArrayList();
        linkedList = new MyLinkedList();
        linkedList.addAll(new Integer[] {5, 2, 1, 3});
        expectedLinkedList = new MyLinkedList();
    }

    @Test
    public void SortMethodCorrectlySortsEmptyList() {
        MyList testList = new MyLinkedList();
        MyCollections.sort(testList);
        assertTrue("Sort method does not correctly sort empty MyLinkedList",testList.equals(expectedLinkedList));
    }

    @Test
    public void SortMethodCorrectlySortsListWithOneElement() {
        MyList testList = new MyLinkedList();
        testList.add(1);
        expectedLinkedList.add(1);
        MyCollections.sort(testList);
        assertTrue("Sort method does not correctly sort MyLinkedList with one element",testList.equals(expectedLinkedList));
    }

    @Test
    public void SortMethodCorrectlySortsListWithManyElements() {
        MyList testExpected = new MyLinkedList();
        testExpected.addAll(new Integer[] {1, 2, 3, 5});
        MyCollections.sort(linkedList);
        assertTrue("Sort method does not correctly sort MyLinkedList with many elements",linkedList.equals(testExpected));
    }

    @Test
    public void SwapCorrectlySwapsElements() {
        MyCollections.swap(linkedList, 0, 2);
        assertEquals("Swap method does not correctly swap elements in the MyLinkedList", (Integer)1, linkedList.get(0));
        assertEquals("Swap method does not correctly swap elements in the MyLinkedList", (Integer)5, linkedList.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void SwapRestrictsIncorrectIndexes() {
        MyCollections.swap(linkedList, 10, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SwapRestrictsNegativeIndexes() {
        MyCollections.swap(linkedList, 0, -2);
    }

    @Test
    public void CopyWorksProperlyOnEmptyList() {
        MyCollections.copy(expectedLinkedList, linkedList);
        assertEquals("Copy does not work properly on empty list", 4, expectedLinkedList.size());
    }

    @Test
    public void CopyWorksProperlyOnListWithElements() {
        expectedLinkedList.add(1);
        expectedLinkedList.add(6);
        expectedLinkedList.add(9);
        MyCollections.copy(expectedLinkedList, linkedList);
        assertEquals("Copy does not work properly on list with elements", 7, expectedLinkedList.size());
    }

    @Test
    public void CloneMethodWorksProperly() {
        expectedLinkedList.add(1);
        expectedLinkedList.add(6);
        expectedLinkedList.add(9);
        expectedLinkedList.add(10);
        MyCollections.clone(expectedLinkedList, linkedList);
        assertTrue("Clone does not work properly", linkedList.equals(expectedLinkedList));
    }

    @Test(expected = IllegalArgumentException.class)
    public void CloneMethodThrowsExceptionWhenGivenListsWithDifferentCapacity() {
        MyCollections.clone(expectedLinkedList, linkedList);
    }

    @Test
    public void ReverseMethodReverseLinkedListProperly() {
        expectedLinkedList.add(3);
        expectedLinkedList.add(1);
        expectedLinkedList.add(2);
        expectedLinkedList.add(5);
        MyCollections.reverse(linkedList);
        assertTrue("Reverse method does not reverse MyLinkedList properly", linkedList.equals(expectedLinkedList));
    }

    @Test
    public void SortMethodCorrectlySortsEmptyArrayList() {
        MyList testList = new MyArrayList();
        MyCollections.sort(testList);
        assertTrue("Sort method does not correctly sort empty MyArrayList",testList.equals(expected));
    }

    @Test
    public void SortMethodCorrectlySortsArrayListWithOneElement() {
        MyList testList = new MyArrayList();
        testList.add(1);
        expected.add(1);
        MyCollections.sort(testList);
        assertTrue("Sort method does not correctly sort MyArrayList with one element",testList.equals(expected));
    }

    @Test
    public void SortMethodCorrectlySortsArrayListWithManyElements() {
        MyList testExpected = new MyArrayList();
        testExpected.addAll(new Integer[] {1, 2, 3, 5});
        MyCollections.sort(list);
        assertTrue("Sort method does not correctly sort MyArrayList with many elements",list.equals(testExpected));
    }

    @Test
    public void SwapCorrectlySwapsElementsOfArrayList() {
        MyCollections.swap(list, 0, 2);
        assertEquals("Swap method does not correctly swap elements in the MyArrayList", (Integer)1, list.get(0));
        assertEquals("Swap method does not correctly swap elements in the MyArrayList", (Integer)5, list.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void SwapRestrictsIncorrectIndexesArrayList() {
        MyCollections.swap(list, 10, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SwapRestrictsNegativeIndexesArrayList() {
        MyCollections.swap(list, 0, -2);
    }

    @Test
    public void CopyWorksProperlyOnEmptyArrayList() {
        MyCollections.copy(expected, list);
        assertEquals("Copy does not work properly on empty list", 4, expected.size());
    }

    @Test
    public void CopyWorksProperlyOnArrayListWithElements() {
        expected.addAll(new Integer[] {1, 6, 9});
        MyCollections.copy(expected, list);
        assertEquals("Copy does not work properly on list with elements", 7, expected.size());
    }

    @Test
    public void CloneMethodWorksProperlyArrayList() {
        expected.addAll(new Integer[] {1, 6, 9, 10});
        MyCollections.clone(expected, list);
        assertTrue("Clone does not work properly", list.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void CloneMethodThrowsExceptionWhenGivenArrayListsWithDifferentCapacity() {
        MyCollections.clone(expected, list);
    }

    @Test
    public void ReverseMethodReverseArrayListProperly() {
        expected.addAll(new Integer[] {3, 1, 2, 5});
        MyCollections.reverse(list);
        assertTrue("Reverse method does not reverse MyArrayList properly", list.equals(expected));
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

    @Test
    public void SortComparatorMethodCorrectlySortsNoElementArrayList() {
        MyList testList = new MyArrayList();
        MyCollections.sort(testList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort Empty MyArrayList", testList.equals(new MyLinkedList()));
    }
    @Test
    public void SortComparatorMethodCorrectlySortsOneElementArrayList() {
        MyList testList = new MyArrayList();
        MyList expectedList = new MyArrayList();
        expectedList.add(1);
        testList.add(1);
        MyCollections.sort(testList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort One Element MyArrayList", testList.equals(expectedList));
    }
    
    @Test
    public void SortComparatorMethodCorrectlySortsManyElementsArrayList() {
        expected.addAll(new Integer[] {1, 2, 3, 5});
        MyCollections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort MyArrayList with many elements", list.equals(expected));
    }
    
    @Test
    public void SortComparatorMethodCorrectlySortsNoElementLinkedList() {
        MyList testList = new MyLinkedList();
        MyCollections.sort(testList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort Empty MyArrayList", testList.equals(expectedLinkedList));
    }
    @Test
    public void SortComparatorMethodCorrectlySortsOneElementLinkedList() {
        MyList testList = new MyLinkedList();
        MyList expectedList = new MyLinkedList();
        expectedList.add(1);
        testList.add(1);
        MyCollections.sort(testList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort One Element MyArrayList", testList.equals(expectedList));
    }
    
    @Test
    public void SortComparatorMethodCorrectlySortsManyElementsLinkedList() {
        MyList expectedList = new MyLinkedList();
        expectedList.addAll(new Integer[] {1, 2, 3, 5});
        MyCollections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        assertTrue("Sort with Comparator does not sort MyArrayList with many elements", list.equals(expectedList));
    }
}
