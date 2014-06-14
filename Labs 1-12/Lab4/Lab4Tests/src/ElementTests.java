import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ua.epam.lab.Element;

public class ElementTests {
	
	private Element element;
	
	@Before
	public void setUp() {
		element = new Element(1);
	}

	@Test
	public void NewElementConstructorWithoutArgumentsHasAllFieldsNull() {
		element = new Element();
		assertEquals("Element no argument constructor's value is not null", null, element.getValue());
		assertEquals("Element no argument constructor's pointer is not null", null, element.getNext());
	}
	
	@Test
	public void NewElementArgumentConstructorCreatesProperElement() {
		assertEquals("Element's value is not 1", (Integer)1, element.getValue());
	}
	
	@Test
	public void ElementProperlySetPointerToTheNextElement() {
		element.setNext(new Element(2));
		assertEquals("Element's next value is not 2", (Integer)2, element.getNext().getValue());
		assertEquals("Element's next pointer is not null", null, element.getNext().getNext());
	}
	
	@Test
	public void RemoveValueFromElementMakeItNull() {
		element.remove();
		assertEquals("Remove method does not properly remove the value", null, element.getValue());
	}
	
	@Test
	public void HasNextMethodWorksProperly() {
		assertFalse("Just created Element has next Element", element.hasNext());
		element.setNext(new Element(2));
		assertTrue("After adding new pointer, Element does not have next element", element.hasNext());
	}
}
