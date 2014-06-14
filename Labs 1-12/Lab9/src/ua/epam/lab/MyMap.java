/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab;

import java.util.Iterator;

/**
 *
 * @author DruidKuma
 */
public interface MyMap {
    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    Object get(Object key);
    boolean isEmpty();
    Object put(Object key, Object value);
    Object remove(Object key);
    int size();
    Iterator entryIterator();
    
    public interface Entry {
        @Override
        boolean equals(Object o);
        
        Object getKey();
        Object getValue();
        
        @Override
        int hashCode();
        
        Object setValue(Object value);
    }
}
