
package ua.epam.kitchen;

/**
 * Component for Using on Kitchen, holds and operates with the
 * amount of a certain Vegetable
 * 
 * @author Yuriy Miedviediev
 * @version 1.0 Build 17.04.2014
 */
public class Component {
    
    private final Vegetable ingredient;
    private double amount;
    
    //Creating new Component with a certain amount of Vegetable
    public Component(Vegetable veggie, double amount) {
        this.ingredient = veggie;
        if(amount < 0) {
            throw new IllegalArgumentException("Amount must be nonnegative");
        }
        this.amount = amount;
    }
    
    //If amount not given, just set it to zero
    public Component(Vegetable veggie) {
        this.ingredient = veggie;
        this.amount = 0;
    }
    
    /**
     * Getter for the amount
     * @return double, amount of the vegetable in the current supply 
     */
    public double getAmount() {
        return this.amount;
    }
    
    /**
     * Getter for the Ingredient
     * @return vegetable
     */
    public Vegetable getIngredient() {
        return this.ingredient;
    }
    
    /**
     * Add some amount of the vegetable to the current supply
     * @param amt float, amount in kilos to add to the supply
     */
    public void addAmount(double amt) {
        if(amt < 0) {
            throw new IllegalArgumentException("Amount must be nonnegative");
        }
        this.amount += amt;
    }
    
    /**
     * Use some amount of the vegetable for cooking
     * @param amt float, amount of the vegetable required
     */
    public void use(double amt) {
        if(amt < 0) {
            throw new IllegalArgumentException("Amount must be nonnegative");
        }
        else if(this.amount - amt >= 0) {
            this.amount -= amt;
        }
        else {
            throw new ArithmeticException("Not enough " + this.getIngredient());
        }
    }
}
