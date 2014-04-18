
package ua.epam.kitchen;

/**
 * All nutrition facts were taken from
 * <em>United States Department of Agriculture</em> (USDA)
 * http://ndb.nal.usda.gov/
 * 
 * @author Yuriy Miedviediev
 * @version 1.1 Build 17.04.2014
 */
public enum Vegetable {
    
    //Alliums
    GARLIC(6.0, 30, 0.5, 149),
    ONION(1.1, 9, 0.1, 40),
    
    //Cabbages
    CABBAGE(1.3, 6, 0.1, 25),
    BROCCOLI(2.8, 7, 0.4, 34),
    CAULIFLOWER(1.8, 5, 0.3, 25),
    
    //Cucurbi
    CUCUMBER(0.6, 3.6, 0.1, 16),
    PUMPKIN(1, 4.2, 0.1, 25),
    ZUCCHINI(1.2, 3.1, 0.3, 17),
    
    //Roots
    CARROT(0.9, 10, 0.2, 41),
    CELERY(0.7, 3, 0.2, 16),
    RADISH(0.7, 3.4, 0.1, 16),
    
    //Tubers
    POTATO(2, 17, 0.1, 77),
    SWEETPOTATO(1.6, 20, 0, 86),
    
    //Other
    CORN(3.3, 18.7, 1.3, 86),
    PARSLEY(3.7, 8.1, 0, 45),
    PEAS(5.4, 14.4, 0.4, 81),
    TOMATO(0.6, 3.6, 0.1, 16);
    
    
    //Nutrition facts (for 100g)
    private final double proteins;
    private final double carbohydrates;
    private final double fats;
    private final double calories;
    
    Vegetable(double proteins, double carbs, double fats, double cal) {
        
        this.proteins = proteins;
        this.carbohydrates = carbs;
        this.fats = fats;
        this.calories = cal;
    }

    /**
     * Getter for the proteins
     * @return float, proteins of the vegetable
     */
    public double getProteins() {
        return this.proteins;
    }
    
    /**
     * Getter for the carbohydrates 
     * @return float, carbohydrates of the vegetable
     */
    public double getCarbs() {
        return this.carbohydrates;
    }
    
    /**
     * Getter for the fats
     * @return float, fats of the vegetable
     */
    public double getFats() {
        return this.fats;
    }
    
    /**
     * Getter for the calories
     * @return float, calories of the vegetable
     */
    public double getCalories() {
        return this.calories;
    }
}
