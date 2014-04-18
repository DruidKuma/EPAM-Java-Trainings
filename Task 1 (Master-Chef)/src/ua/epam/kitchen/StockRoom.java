
package ua.epam.kitchen;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Storage for all vegetables present on Vegetarian Salad Kitchen
 * @author Yuriy Miedviediev
 * @version 1.0 Build 15.04.2014
 */
public class StockRoom {
    
    private ArrayList<Component> storage;
    
    /**
     * Lazy Singleton implementation for the storage of the Stock Room
     */
    public void activate() {
        if(this.storage == null) {
            this.storage = new ArrayList();
        }
    }
    
    /**
     * Purchase one vegetable (add vegetable to the storage or enlarge the amount
     * of vegetable in the storage if it is already present
     * 
     * @param veggie vegetable to purchase
     */
    public void purchase(Component veggie) {
        if(veggie == null) {
            throw new IllegalArgumentException("Component is not allowed to be null");
        }
        int index = this.indexOf(veggie.getIngredient());
        
        //if this vegetable is not present in the storage, add it
        if(index < 0) {
            this.storage.add(veggie);
        }
        
        //if it is present, enlarge its amount
        else {
            this.storage.get(index).addAmount(veggie.getAmount());
        }
    }
    
    /**
     * Make a big supply of vegetables (add an array of vegetables to the storage)
     * @param veggies array of vegetables to purchase
     */
    public void purchaseSupply(Component[] veggies) {
        for(Component veggie : veggies) {
            this.purchase(veggie);
        }
    }
    
    /**
     * Find the index of vegetable in the storage
     * @param veggie vegetable to seek in the storage
     * @return index of vegetable in the storage, or -1 if is not present 
     */
    public int indexOf(Vegetable veggie) {
        
        //find in the storage vegetable with the same type
        for(int pos=0, length=this.storage.size(); pos<length; pos++) {
            if(this.storage.get(pos).getIngredient() == veggie) {
                return pos;
            }
        }
        //if not found
        return -1;
    }
    
    /**
     * Sort the storage by a certain characteristic
     * Using Bubble Sort
     * @param c Comparator with logic for comparing vegetables
     */
    public void sort(Comparator c) {
        if(this.storage.size() <= 1) {
            return;
        }
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for(int pos=0,length=this.storage.size(); pos<length-1; pos++) {
                if(c.compare(this.storage.get(pos), this.storage.get(pos+1)) > 0) {
                    sorted = false;
                    Component temp = this.storage.get(pos);
                    this.storage.set(pos, this.storage.get(pos+1));
                    this.storage.set(pos+1, temp);
                }
            }
        }
    }
    
    /**
     * Update the storage on the amount of used vegetable
     * If there is not enough amount of vegetable, print message to the console
     * @param veggie vegetable to use
     * @param amt amount of vegetable to use
     */
    public void use(Vegetable veggie, double amt) {
        int index = this.indexOf(veggie);
        if(index < 0) {
            System.out.println("Not enough " + veggie);
        }
        else {
            this.storage.get(this.indexOf(veggie)).use(amt);
        }
    }
    
    /**
     * Verify storage to have enough products for the salad
     * @param salad Salad to verify
     * @return true, if enough products in the storage, false otherwise
     */
    public boolean verifyAmount(Salads salad) {
        for(Component c : salad.ingredients) {
            if(c.getAmount() > this.getAmount(c.getIngredient())) {
                System.out.println("Not enough " + c.getIngredient());
                return false;
            }
        }
        return true;
    } 
    
    /**
     * Returns the number of the components in the storage
     * @return integer, size of the storage
     */
    public int size() {
        return this.storage.size();
    }
    
    /**
     * Get the Amount of given Vegetable in the storage
     * @param veggie Vegetable to find
     * @return double, amount of given vegetable in the storage
     */
    public double getAmount(Vegetable veggie) {
        int index = this.indexOf(veggie);
        
        if(index < 0) { return 0; }
        else { return this.storage.get(index).getAmount(); }
    }
    
    /**
     * Get the Component at given index
     * @param index position in the storage
     * @return Component at given position
     */
    public Component get(int index) {
        return this.storage.get(index);
    }
}
