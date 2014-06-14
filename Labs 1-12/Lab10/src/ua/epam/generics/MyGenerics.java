/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.generics;

import java.util.ArrayList;
import java.util.Collection;
import ua.epam.fruits.Fruit;

/**
 *
 * @author DruidKuma
 */
public class MyGenerics {
    
    /**
     * Copy from array to Collection
     * @param <T> any type
     * @param a array of type T
     * @param c Collection of type T
     */
    public static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for(T element : a) {
            c.add(element);
        }
    }
    
    /**
     * Copy all elements from one collection into the other
     * @param <T> Any object of class E or its subclass
     * @param <E> Any type
     * @param collection source collection
     * @param out destination collection
     */
    public static <T extends E, E> void copyAll(Collection<T> collection, Collection<E> out) {
        for(T element : collection) {
            out.add(element);
        }
    }
    
    /**
     * Filter collection from all elements, less then given criteria
     * @param <T> Any Type that implements Comparable interface
     * @param source source collection
     * @param criteria criteria to filter source collection
     * @return Collection with filtered results
     */
    public static <T extends Fruit> Collection filterCollection(Collection<T> source, int criteria) {
        Collection<T> result = new ArrayList<>();
        for(T element : source) {
            if(element.getAmount() - criteria > 0) {
                result.add(element);
            }
        }
        return result;
    }
}
