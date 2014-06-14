
package ua.epam.coffeemachine.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Coffee Drink for the Coffee Machine 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.05.2014
 */
public class Drink {
    
    //data fields
    private final String title;
    private Double price;
    private final List<Ingredient> ingrs;
    
    public Drink(String title, Double price) {
        this.title = title;
        this.price = price;
        ingrs = new ArrayList();
    }
    
    /**
     * Title getter
     * @return title of the current drink
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Price getter
     * @return price of the current drink
     */
    public Double getPrice() {
        return this.price;
    }
    
    /**
     * Set new price for the drink
     * @param newPrice new price for the current drink
     */
    public void setPrice(Double newPrice) {
        this.price = newPrice;
    }
    
    /**
     * Add another additional ingredient
     * @param ingr additional ingredient
     */
    public void addIngr(Ingredient ingr) {
        this.ingrs.add(ingr);
    }
    
    /**
     * Get total price for the drink with all additional ingredients
     * @return total price for the current drink
     */
    public Double getTotalPrice() {
        double total = this.price;
        for(Ingredient ingr : this.ingrs) {
            total += ingr.getPrice();
        }
        return total;
    }
    
    /**
     * Get List of additional ingredients
     * @return additional ingredients to current coffee drink
     */
    public List<Ingredient> getIngrs() {
        return this.ingrs;
    }
}
