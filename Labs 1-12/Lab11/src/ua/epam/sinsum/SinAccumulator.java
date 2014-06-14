
package ua.epam.sinsummer;

/**
 * Multi-threaded Sin Summer 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class SinAccumulator {
    
    //variable to accumulate all sins
    public static volatile Double result = 0.0;

    /**
     * Sum up all the sins within the range [-N, N]
     * @param N indicator of the range
     * @param threads number of desirable threads
     * @return sum of all the sins within given range
     */
    public static double countSin(int N, int threads) {
        
        //set to zero accumulator if there was previous operations
        result = 0.0;
        
        //lower bound of the range
        int begin = - N;
        //number of sins per thread
        int numForThread = N*2+1 / threads;
        
        for(int thread = 0; thread < threads; thread++) {
            
            //upper bound for the thread
            int maxForThread = begin + numForThread;
            
            //for the last thread upper bound is the upper bound of the whole range
            if(thread == threads - 1 || maxForThread >= N) maxForThread = N;
            
            Thread t = new Thread(new SinFinder(begin, maxForThread));
            t.start();
            
            //wait for the first thread to finish
            if(thread == 0) {
                try {
                    t.join();
                }
                catch(InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            //correct lower bound for the next thread
            begin = maxForThread + 1;
        }
        return result;
    }
}
