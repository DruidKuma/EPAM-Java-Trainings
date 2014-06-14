/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.fruits;

/**
 *
 * @author DruidKuma
 */
public class Apple extends Fruit {
    Taste taste;
    
    public Taste getTaste() {
        return this.taste;
    }
    
    public void setTaste(Taste t) {
        this.taste = t;
    }
}
