
package ua.epam.lab.skipass.types;

/**
 * Enumeration for Half Day Ski Passes
 * @author DruidKuma
 */
public enum HalfDay {
    first(9), second(13);
    public int beginTime;
    
    HalfDay(int time) {
        beginTime = time;
    }
}
