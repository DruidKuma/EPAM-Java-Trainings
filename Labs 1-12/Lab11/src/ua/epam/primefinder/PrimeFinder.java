
package ua.epam.primefinder;

/**
 * Finder of the Prime Numbers 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 6.05.2014
 */
public class PrimeFinder implements Runnable {
    
    //bounds for searching numbers
    private int begin;
    private final int end;

    public PrimeFinder(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    
    /**
     * Prints all found prime numbers to the console
     */
    @Override
    public void run() {
        while(begin <= end) {
            if(isPrime(begin)) {
                System.out.print(begin + " ");
            }
            begin++;
        }
    }
    
    /**
     * Check if given number is prime
     * @param num number to check
     * @return true if number is prime, false otherwise
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }  
}
