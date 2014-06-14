import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ua.epam.lab.Element;
import ua.epam.lab.MyStack;


public class MyStackTests {
	
	private MyStack stack;
	
	@Before
	public void setUp() {
		stack = new MyStack();
		stack.push(new Element(1));
	}
	
	@Test
	public void PushCorrectlyPushesElementOnTheTop() {
		assertEquals("Push does not place new Elements", (Integer)1, stack.pop().getValue());
	}
	
	@Test
	public void PopMethodPopsElementsFromTheTop() {
		assertEquals("Pop does not return Element from the top", (Integer)1, stack.pop().getValue());
	}
	
	@Test
	public void PopMethodReturnsNullFromEmptyStack() {
		stack = new MyStack();
		assertEquals("Pop does not return Element from the top", null, stack.pop().getValue());
	}

}
