
package ua.epam.sinsummer;

/**
 * Runnable sin finder for the multi-threaded Sin Summer 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class SinFinder implements Runnable {
    
    //bounds to find sin within
    private final int begin;
    private final int end;
    
    public SinFinder(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    
    @Override
    public void run() {
        for(int num = this.begin; num <= this.end; num++) {
            SinAccumulator.result += (float) Math.sin(num);
        }
    }  
}
