
package ua.epam.kitchen;

import static ua.epam.kitchen.Vegetable.*;

/**
 * <b>A Few Vegetarian Salad Recipes</b><br>
 * All recipes were taken from
 * <em>http://www.allrecipes.com</em>
 * 
 * @author Yuriy Miedviediev
 * @version 1.1 Build 15.04.2014
 */
public enum Salads {
    
    TOMATOCUCUMBERSALAD(new Component[] {new Component(CUCUMBER, 0.3), new Component(TOMATO, 0.3), 
                                         new Component(ONION, 0.2)}),
    
    SUMMERCORNSALAD(new Component[] {new Component(CORN, 0.4), new Component(TOMATO, 0.4), 
                                     new Component(ONION, 0.2)}),
    
    BABYZUCCHINISALAD(new Component[] {new Component(ZUCCHINI, 0.4), new Component(CUCUMBER, 0.3), 
                                       new Component(ONION, 0.2)}),
    
    FRESHBROCCOLISALAD(new Component[] {new Component(BROCCOLI, 0.4), new Component(TOMATO, 0.2), 
                                        new Component(POTATO, 0.3), new Component(PARSLEY, 0.1)}),
    
    SWEETCARROTSALAD(new Component[] {new Component(CARROT, 0.4), new Component(CORN, 0.3), 
                                      new Component(CUCUMBER, 0.2)}),
    
    CRUNCHYPEASALAD(new Component[] {new Component(PEAS, 0.3), new Component(CELERY, 0.3), 
                                     new Component(ONION, 0.2), new Component(TOMATO, 0.2)});
    
    public Component[] ingredients;
    
    Salads(Component[] veggies) {
        ingredients = veggies;
    }
}
