package ua.epam.fruits;


import java.awt.Color;

/**
 * Simple Fruit Hierarchy
 * 
 * @version 1.0 Build 28.04.2014
 * @author Yuriy Miedviediev
 */
public abstract class Fruit implements Comparable<Fruit> {
    Color color;
    Integer amount;
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color c) {
        this.color = c;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amt) {
        this.amount = amt;
    }
    
    @Override
    public int compareTo(Fruit o) {
        return this.amount.compareTo(o.getAmount());
    }
}
