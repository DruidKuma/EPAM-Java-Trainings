import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ua.epam.lab.Element;
import ua.epam.lab.MyQueue;


public class MyQueueTests {
	
	private MyQueue queue;
	
	@Before
	public void setUp() {
		queue = new MyQueue();
		queue.offer(new Element(1));
	}
	
	@Test
	public void OfferMethodProperlyAddsElementInAnEmptyQueue() {
		assertEquals("Offer method does not properly adds elements in an empty queue", (Integer)1, queue.peek().getValue());
	}
	
	@Test
	public void OfferMethodProperlyAddsElementInAQueueWithElements() {
		queue.offer(new Element(2));
		assertEquals("Offer method does not properly add elements in an queue with elements", (Integer)1, queue.peek().getValue());
		queue.poll();
		assertEquals("Offer method does not properly add elements in an queue with elements", (Integer)2, queue.peek().getValue());
	}
	
	@Test
	public void PeekMethodCorrectlyReturnsNullFromTopOfEmptyQueue() {
		queue = new MyQueue();
		assertEquals("Peek method does not properly returns null from empty queue", null, queue.peek().getValue());
	}
	
	@Test
	public void PeekMethodCorrectlyGetsElementFromTopOfQueue() {
		assertEquals("Peek method does not properly get elements from the queue", (Integer)1, queue.peek().getValue());
	}
	
	@Test
	public void PollMethodWorksCorrectly() {
		assertEquals("Peek method does not correctly return element from the queue", (Integer)1, queue.poll().getValue());
		assertEquals("Peek method does not correctly remove element from the queue", null, queue.poll().getValue());
		assertEquals("Peek method does not correctly return null from empty queue", null, queue.poll().getValue());
	}
}
