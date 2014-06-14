
package ua.epam.circulbuffer;

/**
 * Consumer (Reader) for Circular Buffer
 * @author Yuriy Miedviediev
 * @version 1.0 Build 15.05.2014
 */
public class Consumer implements Runnable {
    
    private final CircularBuffer cb;
    
    public Consumer(CircularBuffer cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        while(true) {
            while(cb.size() > 0) {
                cb.poll();
                System.out.println(cb);
            }
        }
    }
}
