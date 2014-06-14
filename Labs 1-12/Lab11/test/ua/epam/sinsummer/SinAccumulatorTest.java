
package ua.epam.sinsummer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the multi-threaded Sin Summer 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 8.05.2014
 */
public class SinAccumulatorTest {

    @Test
    public void MultiThreadedSinSummerWorksCorrectlyForNumber100() {
        double expected = 0.0;
        double actual = SinAccumulator.countSin(100, 3);
        assertEquals("SinSummer does not correctly sum sins within [-100; 100]", actual, expected, 0);
    }
    
    @Test
    public void MultiThreadedSinSummerWorksCorrectlyForNumber1000() {
        double expected = 0.0;
        double actual = SinAccumulator.countSin(1000, 5);
        assertEquals("SinSummer does not correctly sum sins within [-1000; 1000]", actual, expected, 0);
    }
    
    @Test
    public void MultiThreadedSinSummerWorksCorrectlyForNumber10000() {
        double expected = 0.0;
        double actual = SinAccumulator.countSin(10000, 10);
        assertEquals("SinSummer does not correctly sum sins within [-10000; 10000]", actual, expected, 0);
    }
    
    @Test
    public void MultiThreadedSinSummerWorksCorrectlyForNumber100000() {
        double expected = 0.0;
        double actual = SinAccumulator.countSin(100000, 15);
        assertEquals("SinSummer does not correctly sum sins within [-100000; 100000]", actual, expected, 0);
    }
}
