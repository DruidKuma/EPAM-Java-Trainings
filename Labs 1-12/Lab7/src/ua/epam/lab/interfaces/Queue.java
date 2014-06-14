package ua.epam.lab.interfaces;

/**
* My Personal interface for Queue
*
* @author Yuriy Miedviediev
* @version 1.0 Build 08.04.2014
*
*/
public interface Queue {
    public void offer(Object e);
    public Object peek();
    public Object poll();
}