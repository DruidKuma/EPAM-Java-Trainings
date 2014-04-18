/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.kitchen;

import java.util.Comparator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Cases for the StockRoom class
 * @author Yuriy Miedviediev
 * @version 1.0 Build 17.04.2014
 */
public class StockRoomTest {
    
    private StockRoom stockRoom;
    
    //Standard Comparator for sorting
    private final Comparator stdComparator = new Comparator() {

        @Override
        public int compare(Object o1, Object o2) {
            double caloriesFirst = ((Component) o1).getIngredient().getCalories();
            double caloriesSecond = ((Component) o2).getIngredient().getCalories();
            
            if(caloriesFirst > caloriesSecond) { return 1; }
            else if(caloriesFirst == caloriesSecond) {return 0; }
            else { return -1; }
        }
    };
    
    @Before
    public void setUp() {
        stockRoom = new StockRoom();
        stockRoom.activate();
    }

    @Test(expected = NullPointerException.class)
    public void WithoutActivationAnyMethodThrowsException() {
        StockRoom testStockRoom = new StockRoom();
        testStockRoom.purchase(new Component(Vegetable.BROCCOLI));
    }

    @Test(expected = IllegalArgumentException.class)
    public void PurchaseThrowsExceptionGivenNull() {
        stockRoom.purchase(null);
    }
    
    @Test
    public void PurchaseCorrectlyAddsNewComponent() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI));
        assertEquals("Purchase does not correctly add new Component", 1, stockRoom.size());
        assertEquals("Purchase does not correctly add new Component", 0, stockRoom.indexOf(Vegetable.BROCCOLI));
    }
    
    @Test
    public void PurchaseCorrectlyAddAmountToTheExistingComponent() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.1));
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.5));
        assertEquals("Purchase does not correctly add amount to the existing component", 1, stockRoom.size());
        assertEquals("Purchase does not correctly add amount to the existing component", 0.6, stockRoom.getAmount(Vegetable.BROCCOLI), 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void PurchaseSupplyThrowsExceptionGivenNull() {
        stockRoom.purchaseSupply(null);
    }
    
    @Test
    public void PurchaseSupplyDontModifyStorageGivenEmptyArray() {
        stockRoom.purchaseSupply(new Component[] {});
        assertEquals("purchaseSupply modifies sorage given empty array of Components", 0, stockRoom.size());
    }
    
    @Test
    public void PurchaseSupplyCorrectlyPurchasesSupplyWithOneComponent() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI)});
        assertEquals("purchaseSupply does not correctly purchase list with one element", 1, stockRoom.size());
    }
    
    @Test
    public void PurchaseSupplyCorrectlyPurchasesSupplyWithManyComponents() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE)});
        assertEquals("purchaseSupply does not correctly purchase supply with many Components", 2, stockRoom.size());
    }
    
    @Test
    public void PurchaseSupplyCorrectlyPurchasesSupplyWithManyEqualComponents() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.BROCCOLI, 0.4)});
        assertEquals("purchaseSupply does not correctly purchase supply with many equal Components", 1, stockRoom.size());
        assertEquals("purchaseSupply does not correctly purchase supply with many equal Components", 0.4, stockRoom.getAmount(Vegetable.BROCCOLI), 0);
    }

    @Test
    public void IndexOfCorrectlyReturnsNegativeIndexInEmptyStorage() {
        int actual = stockRoom.indexOf(Vegetable.BROCCOLI);
        assertSame("indexOf does not return -1 in an empty storage", -1, actual);
    }
    
    @Test
    public void IndexOfCorrectlyFindsIndexInStorageWithOneElement() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI));
        int actual = stockRoom.indexOf(Vegetable.BROCCOLI);
        assertSame("indexOf does not find correct index in a storage with one element", 0, actual);
    }
    
    @Test
    public void IndexOfCorrectlyFindsFirstIndexInStorageWithManyElements() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE), new Component(Vegetable.CORN)});
        int actual = stockRoom.indexOf(Vegetable.BROCCOLI);
        assertSame("indexOf does not find correct index of the first component in a storage with many elements", 0, actual);
    }
    
    @Test
    public void IndexOfCorrectlyFindsMiddleIndexInStorageWithManyElements() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE), new Component(Vegetable.CORN)});
        int actual = stockRoom.indexOf(Vegetable.CABBAGE);
        assertSame("indexOf does not find correct index of the middle component in a storage with many elements", 1, actual);
    }
    
    @Test
    public void IndexOfCorrectlyFindsLastIndexInStorageWithManyElements() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE), new Component(Vegetable.CORN)});
        int actual = stockRoom.indexOf(Vegetable.CORN);
        assertSame("indexOf does not find correct index of the last component in a storage with many elements", 2, actual);
    }
    
    @Test
    public void IndexOfCorrectlyReturnNegativeIndexToComponentNotFoundInTheStorage() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE), new Component(Vegetable.CORN)});
        int actual = stockRoom.indexOf(Vegetable.CAULIFLOWER);
        assertSame("indexOf does not return -1 to the component not found in the storage", -1, actual);
    }

    @Test
    public void SortCorrectlySortsEmptyStorage() {
        stockRoom.sort(stdComparator);
        assertEquals("Sort does not correctly sort empty storage", 0, stockRoom.size());
    }
    
    @Test
    public void SortCorrectlySortsStorageWithOneElement() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI));
        stockRoom.sort(stdComparator);
        assertSame("Sort does not correctly sort storage with one element", 0, stockRoom.indexOf(Vegetable.BROCCOLI));
    }
    
    @Test
    public void SortCorrectlySortsStorageWithManyElements() {
        stockRoom.purchaseSupply(new Component[] {new Component(Vegetable.BROCCOLI), new Component(Vegetable.CABBAGE), new Component(Vegetable.CORN)});
        stockRoom.sort(stdComparator);
        assertEquals("Sort does not correctly sort storage with many elements", Vegetable.CABBAGE, stockRoom.get(0).getIngredient());
        assertEquals("Sort does not correctly sort storage with many elements", Vegetable.BROCCOLI, stockRoom.get(1).getIngredient());
        assertEquals("Sort does not correctly sort storage with many elements", Vegetable.CORN, stockRoom.get(2).getIngredient());
    }

    @Test
    public void UseCorrectlySubtractsAmountOfComponent() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.5));
        stockRoom.use(Vegetable.BROCCOLI, 0.3);
        assertEquals("Use does not correctly subtracts amount from the component", 0.2, stockRoom.getAmount(Vegetable.BROCCOLI), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void UseThrowsExceptionWithNegativeAmountArgument() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.5));
        stockRoom.use(Vegetable.BROCCOLI, -0.3);
    }
    
    @Test(expected = ArithmeticException.class)
    public void UseThrowsExceptionIfNotEnoughAmount() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.2));
        stockRoom.use(Vegetable.BROCCOLI, 0.3);
    }
    
    @Test
    public void GetAmountCorrectlyGetsAmountOfPresentVegetable() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.2));
        assertEquals("getAmount does not get correct amount of present vegetable", 0.2, stockRoom.getAmount(Vegetable.BROCCOLI), 0);
    }
    
    @Test
    public void GetAmountCorrectlyGetsAmountOfNotPresentVegetable() {
        stockRoom.purchase(new Component(Vegetable.BROCCOLI, 0.2));
        assertEquals("getAmount does not get correct amount of not present vegetable", 0, stockRoom.getAmount(Vegetable.CORN), 0);
    }
    
}
