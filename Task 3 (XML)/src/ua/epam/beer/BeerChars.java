
package ua.epam.beer;

import java.util.Objects;

/**
 * Beer Characteristics
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class BeerChars {
    
    //All fields
    private double abv;
    private double transparency;
    private boolean isFiltered;
    private double nutrition;
    private double volume;
    private String pack;
    
    /**
     * Empty Constructor for parsing
     */
    public BeerChars() {}
    
    /**
     * Full Constructor for Testing and Production
     * @param abv Alcohol By Volume
     * @param transparency transparency of the beer
     * @param isFiltered is current beer filtered
     * @param nutrition calories
     * @param volume Either 0.33, 0.5, 1, 1.5
     * @param pack plastic, glass or can
     */
    public BeerChars(double abv, double transparency, boolean isFiltered, double nutrition, double volume, String pack) {
        this.abv = abv;
        this.transparency = transparency;
        this.isFiltered = isFiltered;
        this.nutrition = nutrition;
        this.volume = volume;
        this.pack = pack;
    }
    
    /**
     * Constructor without optional ABV argument
     * @param transparency transparency of the beer
     * @param isFiltered is current beer filtered
     * @param nutrition calories
     * @param volume Either 0.33, 0.5, 1, 1.5
     * @param pack plastic, glass or can 
     */
    public BeerChars(double transparency, boolean isFiltered, double nutrition, double volume, String pack) {
        this(0.0, transparency, isFiltered, nutrition, volume, pack);
    }
    
    /**
     * ABV getter
     * @return Alcohol By Volume of the Beer
     */
    public double getABV() {
        return this.abv;
    }
    
    /**
     * ABV setter
     * @param abv new alcohol by volume 
     */
    public void setABV(String abv) {
        this.abv = Double.valueOf(abv);
    }
    
    /**
     * Transparency getter
     * @return transparency of the current beer
     */
    public double getTransparency() {
        return this.transparency;
    }
    
    /**
     * Transparency setter
     * @param transparency new transparency of the beer
     */
    public void setTransparency(String transparency) {
        this.transparency = Double.valueOf(transparency);
    }
    
    /**
     * Is current Beer filtered
     * @return true if beer is filtered, false otherwise
     */
    public boolean isFiltered() {
        return this.isFiltered;
    }
    
    /**
     * IsFiltered Setter
     * @param isFiltered new value for filtered beer
     */
    public void setIsFiltered(String isFiltered) {
        this.isFiltered = Boolean.valueOf(isFiltered);
    }
    
    /**
     * Calories getter
     * @return calories of the current beer
     */
    public double getNutrition() {
        return this.nutrition;
    }
    
    /**
     * Nutrition setter
     * @param nutrition new amount of calories 
     */
    public void setNutrition(String nutrition) {
        this.nutrition = Double.valueOf(nutrition);
    }
    
    /**
     * Volume getter
     * @return draught amount of the current beer
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * Volume setter
     * @param vol new volume for the beer
     */
    public void setVolume(String vol) {
        this.volume = Double.valueOf(vol);
    }
    
    /**
     * Pack Getter
     * @return plastic, glass or can
     */
    public String getPack() {
        return this.pack;
    }
    
    /**
     * Pack Setter
     * @param pack new pack for the beer 
     */
    public void setPack(String pack) {
        this.pack = pack;
    }
    
    /**
     * Check the equality with other BeerChars
     * @param o other Object to check
     * @return true if both are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof BeerChars)) return false;
        BeerChars other = (BeerChars) o;
        return this.abv == other.getABV() &&
                this.isFiltered == other.isFiltered() &&
                this.nutrition == other.getNutrition() &&
                this.transparency == other.getTransparency() &&
                this.pack.equals(other.getPack()) &&
                this.volume == other.getVolume();
    }

    /**
     * Automatically generated Hash Code method
     * @return Hash Code of the current BeerChars
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.abv) ^ (Double.doubleToLongBits(this.abv) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.transparency) ^ (Double.doubleToLongBits(this.transparency) >>> 32));
        hash = 43 * hash + (this.isFiltered ? 1 : 0);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.nutrition) ^ (Double.doubleToLongBits(this.nutrition) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.volume) ^ (Double.doubleToLongBits(this.volume) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.pack);
        return hash;
    }
}
