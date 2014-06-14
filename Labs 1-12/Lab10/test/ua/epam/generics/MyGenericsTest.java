/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.generics;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ua.epam.fruits.*;

/**
 *
 * @author DruidKuma
 */
public class MyGenericsTest {
    
    @Before
    public void setUp() {
        List<Fruit> fr = null;
        List<Apple> app = null;
        List<Orange> or = null;
        List<GreenApple> rapp = null; 
        List<Melon> mel = null;
    }
    
    @Test
    public void FromArrayToCollectionCopiesElementsProperly() {
        Apple[] appleArray = {new Apple(), new Apple(), new Apple()};
        ArrayList<Apple> appleList = new ArrayList<>();
        MyGenerics.fromArrayToCollection(appleArray, appleList);
        assertEquals("fromArrayToCollection does not properly copy elements to Collection", 3, appleList.size());
    }

    @Test
    public void CopyAllAllowsToCopyCollectionsWithEqualsTypes() {
        LinkedList<Melon> source = new LinkedList<>();
        source.add(new Melon());
        LinkedList<Melon> dest = new LinkedList<>();
        MyGenerics.copyAll(source, dest);
        assertEquals("copyAll does not allow to copy collections with equal types", 1, dest.size());
    }
    
    @Test
    public void CopyAllAllowsToCopyCollectionsWithSubClasses() {
        LinkedList<GreenApple> source = new LinkedList<>();
        source.add(new GreenApple());
        LinkedList<Apple> dest = new LinkedList<>();
        MyGenerics.copyAll(source, dest);
        assertEquals("copyAll does not allow to copy collections with subclasses", 1, dest.size());
    }

    @Test
    public void FilterCollectionWorksProperly() {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        for(int i = 1; i < 6; i++) {
            fruitList.add(new Melon());
            fruitList.get(i-1).setAmount(i*10);
        }
        Collection<Fruit> actual = MyGenerics.filterCollection(fruitList, 30);
        assertEquals("filterCollection does not filter source collection properly", 2, actual.size());
    }
    
}
