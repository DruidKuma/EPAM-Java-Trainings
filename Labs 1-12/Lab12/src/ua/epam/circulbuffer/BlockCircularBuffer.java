
package ua.epam.circulbuffer;

/**
 * Start point of working with circular buffer
 * @author Yuriy Miediediev
 * @version 1.0 Build 15.05.2014
 */
public class BlockCircularBuffer {
    
    public static void main(String[] args) {
        CircularBuffer cb = new CircularBuffer();
        new Thread(new Producer(cb)).start();
        new Thread(new Consumer(cb)).start();
    }
}
