/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.kitchen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author DruidKuma
 */
public class ComponentTest {
    
    private Component component;
    
    @Before
    public void setUp() {
        component = new Component(Vegetable.CARROT, 0.2);
    }
    
    @Test
    public void ConstructorWithOneArgumentCorrectlySetsAmountToZero() {
        Component testComponent = new Component(Vegetable.BROCCOLI);
        assertEquals("Constructor with one argument does not set amount to zero", 0, testComponent.getAmount(), 0);
    }
    
    @Test
    public void ConstructorWithOneArgumentCorrectlySetsIngredient() {
        Component testComponent = new Component(Vegetable.BROCCOLI);
        assertEquals("Constructor with one argument does not correctly set the ingredient", Vegetable.BROCCOLI, testComponent.getIngredient());
    }

    @Test
    public void ConstructorCorrectlySetsAmount() {
        assertEquals("Constructor with two arguments does not correctly set the amount", 0.2, component.getAmount(), 0);
    }

    @Test
    public void ConstructorCorrectlySetsIngredient() {
        assertSame("Constructor with two arguments does not correctly set the ingredient", Vegetable.CARROT, component.getIngredient());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorThrowsExceptionGivenNegativeAmount() {
        Component testComponent = new Component(Vegetable.BROCCOLI, -1);
    }

    @Test
    public void AddAmountMethodCorrectlyAddsAmount() {
        component.addAmount(0.2);
        assertEquals("addAmount method does not correctly set amount", 0.4, component.getAmount(), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void AddAmountThrowsExceptionGivenNegativeAmount() {
        component.addAmount(-1);
    }
    
    @Test
    public void UseMethodCorrectlySubtractsAmount() {
        component.use(0.1);
        assertEquals("Use method does not correctly subtracts amount", 0.1, component.getAmount(), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void UseMethodThrowsExceptionGivenNegativeAmount() {
        component.use(-1);
    }
    
    @Test(expected = ArithmeticException.class)
    public void UseMethodThrowsExceptionWhenSubtractingFollowsIntoNegativeAmount() {
        component.use(0.3);
    }
    
}
