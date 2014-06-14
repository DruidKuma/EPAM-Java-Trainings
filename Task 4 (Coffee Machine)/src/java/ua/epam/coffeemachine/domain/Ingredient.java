
package ua.epam.coffeemachine.domain;

/**
 * Ingredient of the Coffee Model
 * @author Yuriy Miedviediev
 * @version 1.0 Build 19.05.2014
 */
public class Ingredient {
    
    //ingredient fields
    private final String title;
    private Integer amount;
    private final Double price;
    
    public Ingredient(String title, Integer amount, Double price) {
        this.title = title;
        this.amount = amount;
        this.price = price;
    }
    
    public Ingredient(String title, Double price) {
        this(title, 0, price);
    }
    
    /**
     * Title getter
     * @return title of the ingredient
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Amount getter
     * @return amount of ingredient left in the coffee machine
     */
    public Integer getAmount() {
        return this.amount;
    }
    
    /**
     * Amount setter
     * @param newAmount new amount of the current ingredient 
     */
    public void setAmount(Integer newAmount) {
        this.amount = newAmount;
    }
    
    /**
     * Price getter
     * @return price of the current Ingredient
     */
    public Double getPrice() {
        return this.price * this.amount;
    }
}
