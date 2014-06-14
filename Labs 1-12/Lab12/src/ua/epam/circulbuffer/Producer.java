
package ua.epam.circulbuffer;
/**
 * Producer (Writer) for Circular Buffer
 * @author DruidKuma
 * @version 1.0 Build 15.05.2014
 */
public class Producer implements Runnable {
    
    private int i = 1;
    private final CircularBuffer cb;
    
    public Producer(CircularBuffer cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        while(true) {
            while(!cb.isFull()) {
                cb.add(i);
                i++;
            }
        }
    }
    
}
