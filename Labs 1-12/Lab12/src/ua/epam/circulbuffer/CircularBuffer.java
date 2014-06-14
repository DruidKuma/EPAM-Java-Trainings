
package ua.epam.circulbuffer;

/**
 * Standard Circular Buffer
 * @author Yuriy Miedviediev
 * @version 1.0 Build 15.05.2014
 */
public class CircularBuffer {
    
    //capacity of the buffer
    private final int capacity;
    
    //data holder
    private int[] contents;
    
    //current indexes for adding and deleting elements
    private int addIndex = 0;
    private int takeIndex = 0;
    
    //number of elements in the buffer
    private int length = 0;
    
    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        contents = new int[capacity];
    }
    
    public CircularBuffer() {
        this(10);
    }
    
    /**
     * Add new element to the buffer
     * @param element integer to add to the buffer
     */
    public synchronized void add(int element) {
        contents[this.addIndex % this.capacity] = element;
        if(this.addIndex == this.takeIndex) this.takeIndex++;
        this.addIndex++;
        this.length++;
        if(this.length > this.capacity) this.length = this.capacity;
    }
    
    /**
     * Take an element from Circular Buffer without deleting
     * @return next element in the queue
     */
    public int peek() {
        if(this.length > 0) {
            return this.contents[takeIndex % this.capacity];
        }
        throw new RuntimeException("Error: Circular Buffer is empty");
    }
    
    /**
     * Take and delete (move deletion cursor) element from the buffer
     * @return next integer in the queue
     */
    public synchronized int poll() {
        if(this.length > 0) {
            int result = this.contents[takeIndex % this.capacity];
            this.takeIndex++;
            this.length--;
            if(this.length < 0) this.length = 0;
            return result;
        }
        throw new RuntimeException("Error: Circular Buffer is empty");
    }
    
    /**
     * Size of the Circular Buffer
     * @return number of elements in the buffer
     */
    public int size() {
        return this.length;
    }
    
    /**
     * Check if the buffer is full
     * @return true, if buffer is full, false otherwise
     */
    public boolean isFull() {
        return this.length == this.capacity;
    }
    
    /**
     * String representation of the buffer
     * @return String representation of the current circular buffer
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < this.size(); i++) {
            result.append(this.contents[(takeIndex+i) % this.capacity]).append(" ");
        }
        return result.toString();
    }
}
