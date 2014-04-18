/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.kitchen;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static ua.epam.kitchen.Vegetable.CORN;
import static ua.epam.kitchen.Vegetable.ONION;
import static ua.epam.kitchen.Vegetable.TOMATO;

/**
 *
 * @author DruidKuma
 */
public class KitchenTest {
    
    private Kitchen kitchen;
    private StockRoom stockRoom;
    
    @Before
    public void setUp() {
        stockRoom = new StockRoom();
        kitchen = new Kitchen(stockRoom);
        stockRoom.activate();
        stockRoom.purchaseSupply(new Component[] {new Component(CORN, 0.4), new Component(TOMATO, 0.4), new Component(ONION, 0.2)});
    }
            
    @Test
    public void MakeSaladCorrectlyUseAllIngredients() {
        kitchen.makeSalad(Salads.SUMMERCORNSALAD);
        assertEquals("makeSalad does not correctly use all ingredients", 0, stockRoom.getAmount(CORN), 0);
        assertEquals("makeSalad does not correctly use all ingredients", 0, stockRoom.getAmount(TOMATO), 0);
        assertEquals("makeSalad does not correctly use all ingredients", 0, stockRoom.getAmount(ONION), 0);
    }
    
    @Test
    public void MakeSaladDoesNotModifyIngredientsIfNotEnoughOneOrMoreIngredients() {
        kitchen.makeSalad(Salads.TOMATOCUCUMBERSALAD);
        assertEquals("makeSalad modifies storage if not enough ingredients", 0.2, stockRoom.getAmount(ONION), 0);
        assertEquals("makeSalad modifies storage if not enough ingredients", 0.4, stockRoom.getAmount(TOMATO), 0);
    }

    @Test
    public void SaladNutritionIsComputedProperly() {
        double[] actual = kitchen.saladNutrition(Salads.SUMMERCORNSALAD);
        double[] expected = new double[] {1.0, 488, 17.8, 107.2, 5.8};
        assertArrayEquals("saladNutrition does not properly compute nutrition information", expected, actual, 0.0001);
    }
    
    @Test
    public void FindVeggiesInRangeWorksProperlyFilteringManyComponents() {
        ArrayList<Vegetable> actual = kitchen.findVeggiesInRange(Salads.SUMMERCORNSALAD, 15, 87);
        assertSame("findVeggiesInRange does not properly works when nothing need to filter", 3, actual.size());
    }
    
    @Test
    public void FindVeggiesInRangeWorksProperlyFilteringFewComponents() {
        ArrayList<Vegetable> actual = kitchen.findVeggiesInRange(Salads.SUMMERCORNSALAD, 20, 87);
        assertSame("findVeggiesInRange does not properly works filtering few components", 2, actual.size());
    }
    
    @Test
    public void FindVeggiesInRangeWorksProperlyFilteringNoComponents() {
        ArrayList<Vegetable> actual = kitchen.findVeggiesInRange(Salads.SUMMERCORNSALAD, 10, 15);
        assertSame("findVeggiesInRange does not properly works filtering out no components", 0, actual.size());
    }
    
}
