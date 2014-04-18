/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.kitchen;

import java.util.ArrayList;

/**
 * All the magic of cooking is placed here
 * @author Yuriy Miedviediev
 * @version 1.0 Build 15.04.2014
 */
public class Kitchen {
    
    private final StockRoom stockRoom;
    
    //Constructor connects the kitchen to the certain Stock Room
    public Kitchen(StockRoom storage) {
        this.stockRoom = storage;
    }
    
    /**
     * Update Vegetable supplies according to the salad recipe
     * In case of being not enough ingredients, print message to the console
     * @param salad recipe of the salad
     */
    public void makeSalad(Salads salad) {
        if(this.stockRoom.verifyAmount(salad)) {
            for(Component component : salad.ingredients) {
                this.stockRoom.use(component.getIngredient(), component.getAmount());
            }
        }
    }
    
    /**
     * Compute all the nutrition information for the given salad
     * 
     * @param salad Salad to compute nutrition info
     * @return array of doubles with weight/calories/proteins/carbohydrates/fats
     */
    public double[] saladNutrition(Salads salad) {
        
        //variables for computing nutrition facts
        double calories = 0;
        double fats = 0;
        double carbs = 0;
        double proteins = 0;
        double weight = 0;
        
        //summing up of nutriments
        for(Component component : salad.ingredients) {
            
            //amount of Vegetable in 100g proportion for computing nutrition
            double convertedAmount = component.getAmount() * 10;
            
            calories += convertedAmount * component.getIngredient().getCalories();
            fats += convertedAmount * component.getIngredient().getFats();
            proteins += convertedAmount * component.getIngredient().getProteins();
            carbs += convertedAmount * component.getIngredient().getCarbs();
            weight += component.getAmount();
        }
        return new double[] {weight, calories, proteins, carbs, fats}; 
    }
    
    /**
     * Print all the Nutrition Information
     * e.g total weight of the salad, proteins, carbohydrates, fats and calories
     * 
     * @param salad salad recipe to get the nutrition
     */
    public void printNutrition(Salads salad) {
        
        //get nutrition info
        double[] info = this.saladNutrition(salad);
        
        //printing out all the nutrition information
        System.out.println("Nutrition Facts for " + salad);
        System.out.println("Weight Netto: " + (float) info[0] + " kg");
        System.out.println("Total Calories: " + (float) info[1]);
        System.out.println("Proteins: " + (float) info[2]);
        System.out.println("Carbohydrates: " + (float) info[3]);
        System.out.println("Fats: " + (float) info[4]);
    }
    
    /**
     * Find all vegetables in the salad within given range
     * @param salad salad recipe to seek for the vegetables
     * @param minCal minimal amount of calories
     * @param maxCal maximal amount of calories
     * @return ArrayList with all vegetables from the recipe which fit given range
     */
    public ArrayList findVeggiesInRange(Salads salad, double minCal, double maxCal) {
        
        //ArrayList for wanted vegetables
        ArrayList<Component> result = new ArrayList();
        
        //filtering vegetables in the recipe
        for(Component component : salad.ingredients) {
            double current = component.getIngredient().getCalories();
            if(current >= minCal && current <= maxCal) {
                result.add(component);
            }
        }
        return result;
    }
}
