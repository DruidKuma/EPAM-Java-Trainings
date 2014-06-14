
package ua.epam.primefinder;

/**
 * Multi-threaded implementation of Prime Finder 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 6.05.2014
 */
public class PrimeAccumulator {
    
    /**
     * Launch multi-threaded version of Prime Finder 
     * @param threads number of desirable threads
     * @param maxPrime max number to check for being prime
     */
    public void findPrimes(int threads, int maxPrime) {
        int maxForThread = maxPrime / threads;
        int minPrime = 2;
        for(int thread = 0; thread < threads; thread++) {
            Thread t = new Thread(new PrimeFinder(minPrime, maxForThread));
            t.start();
            minPrime = maxForThread + 1;
            maxForThread += maxPrime / threads;
        }
    }
}
