
package ua.epam.primefinder;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the Multi-threaded prime finder 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class PrimeAccumulatorTest {
    
    private PrimeAccumulator primeFinder;
    
    @Before
    public void setUp() {
        primeFinder = new PrimeAccumulator();
    }
    
    @Test
    public void IsPrimeCorrectlyReturnsTrueForThePrimeNumber() {
        assertTrue("isPrime does not recognize primes", PrimeFinder.isPrime(11));
    }
    
    @Test
    public void IsPrimeCorrectlyReturnsFalseForNonPrimeNumber() {
        assertFalse("isPrime doe not return false for non-prime number", PrimeFinder.isPrime(10));
    }
    
    @Test
    public void IsPrimeCorrectlyReturnsFalseForTheOne() {
        assertFalse("isPrime returns true for 1", PrimeFinder.isPrime(1));
    }

    @Test
    public void MultithreadedPrimeFinderFoundsAllPrimesUpTo1000FasterThanStraightImplementation() {
        long beginTime = System.currentTimeMillis();
        for(int i = 2; i <= 1000; i++) {
            if(PrimeFinder.isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        long finishTime = System.currentTimeMillis();
        long straightTime = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        primeFinder.findPrimes(5, 1000);
        finishTime = System.currentTimeMillis();
        long threadTime = finishTime - beginTime;
        
        assertTrue("Multi-threaded version is slower than regular for finding primes up to 1000", threadTime <= straightTime);
    }
    
    @Test
    public void MultithreadedPrimeFinderFoundsAllPrimesUpTo10000FasterThanStraightImplementation() {
        long beginTime = System.currentTimeMillis();
        for(int i = 2; i <= 10000; i++) {
            if(PrimeFinder.isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        long finishTime = System.currentTimeMillis();
        long straightTime = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        primeFinder.findPrimes(13, 10000);
        finishTime = System.currentTimeMillis();
        long threadTime = finishTime - beginTime;
        
        assertTrue("Multi-threaded version is slower than regular for finding primes up to 10000", threadTime <= straightTime);
    }
    
    @Test
    public void MultithreadedPrimeFinderFoundsAllPrimesUpTo100000FasterThanStraightImplementation() {
        long beginTime = System.currentTimeMillis();
        for(int i = 2; i <= 100000; i++) {
            if(PrimeFinder.isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        long finishTime = System.currentTimeMillis();
        long straightTime = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        primeFinder.findPrimes(3, 100000);
        finishTime = System.currentTimeMillis();
        long threadTime = finishTime - beginTime;
        
        assertTrue("Multi-threaded version is slower than regular for finding primes up to 100000", threadTime <= straightTime);
    }
    
    @Test
    public void MultithreadedPrimeFinderFoundsAllPrimesUpTo1000000FasterThanStraightImplementation() {
        long beginTime = System.currentTimeMillis();
        for(int i = 2; i <= 1000000; i++) {
            if(PrimeFinder.isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        long finishTime = System.currentTimeMillis();
        long straightTime = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        primeFinder.findPrimes(10, 1000000);
        finishTime = System.currentTimeMillis();
        long threadTime = finishTime - beginTime;
        
        assertTrue("Multi-threaded version is slower than regular for finding primes up to 1000000", threadTime <= straightTime);
    }
    
    /**
     * Bonus Test, proves once more benefits of multi-threading
     * Shows that multi-threaded prime finder finds all primes up to million
     * faster than regular linear version up to 100000
     */
    @Test
    public void MultithreadedPrimeFinderFoundsAllPrimesUpTo1000000FasterThanStraightImplementationUpTo100000() {
        long beginTime = System.currentTimeMillis();
        for(int i = 2; i <= 100000; i++) {
            if(PrimeFinder.isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        long finishTime = System.currentTimeMillis();
        long straightTime = finishTime - beginTime;
        
        beginTime = System.currentTimeMillis();
        primeFinder.findPrimes(5, 1000000);
        finishTime = System.currentTimeMillis();
        long threadTime = finishTime - beginTime;
        
        assertTrue("Multi-threaded version is not as cool, as possible", threadTime <= straightTime);
    }
}
