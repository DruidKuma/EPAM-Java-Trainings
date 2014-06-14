
package ua.epam.beer;

import java.util.Arrays;
import java.util.Objects;

/**
 * Beer Class for XML Beer File
 * @author Yuriy Miedviediev
 * @version 1.0 Build 13.05.2014
 */
public class Beer {
    
    //All fields
    private String ID;
    private String name;
    private String type;
    private boolean isAlcoholic;
    private String manufacturer;
    private String[] ingredients;
    private BeerChars chars;
    
    /**
     * Empty Constructor for parsing
     */
    public Beer() {}
    
    /**
     * Full Constructor For Testing and Production
     * @param ID id of current beer
     * @param name name of the beer
     * @param type type (lager, live, dark or light)
     * @param al is current beer alcoholic
     * @param manufacturer producer of the beer
     * @param ingredients ingredients of the beer
     * @param chars beer characteristics
     */
    public Beer(String ID, String name, String type, boolean al, String manufacturer, String[] ingredients, BeerChars chars) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.isAlcoholic = al;
        this.manufacturer = manufacturer;
        this.ingredients = ingredients;
        this.chars = chars;
    }
    
    /**
     * ID getter
     * @return id of the beer
     */
    public String getID() {
        return this.ID;
    }
    
    /**
     * ID setter
     * @param ID new ID for the beer 
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     * Name getter
     * @return Name of the current beer
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Name setter
     * @param name new name for the beer 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Type getter
     * @return type of the current beer
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Type setter
     * @param type new type for the beer 
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Is current beer alcoholic
     * @return true if it is, false otherwise
     */
    public boolean isAlcoholic() {
        return this.isAlcoholic;
    }
    
    /**
     * Alcohol setter
     * @param isAlcoholic new value for beer alcohol 
     */
    public void setIsAlcoholic(String isAlcoholic) {
        this.isAlcoholic = Boolean.valueOf(isAlcoholic);
    }
    
    /**
     * Manufacturer getter
     * @return producer of the beer
     */
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    /**
     * Manufacturer setter
     * @param manufacturer new producer for the beer 
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    /**
     * Ingredients getter
     * @return ingredients of the current beer
     */
    public String[] getIngredients() {
        return this.ingredients;
    }
    
    /**
     * Ingredients setter
     * @param ingredients new ingredients for the beer 
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients.split(" ");
    }
    
    /**
     * Characteristics getter
     * @return current beer characteristics
     */
    public BeerChars getChars() {
        return this.chars;
    }
    
    /**
     * Characteristics setter
     * @param chars new characteristics for the beer
     */
    public void setChars(BeerChars chars) {
        this.chars = chars;
    }
    
    /**
     * String representation of the Beer
     * @return Beer string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getName()).append("-->").append(this.getType()).append("\n");
        result.append("ID: ").append(this.getID()).append("\n");
        result.append("Created by: ").append(this.getManufacturer()).append("\n");
        result.append("Ingredients: ").append(Arrays.toString(this.getIngredients())).append("\n");        
        result.append("ABV: ").append(chars.getABV()).append(", Calories: ").append(chars.getNutrition()).append(", Transparency: ").append(chars.getTransparency()).append("\n");
        result.append("Volume: ").append(chars.getVolume()).append(", Pack: ").append(chars.getPack()).append("\n");
        return result.toString();
    }
    
    /**
     * Check the equality with other Beer
     * @param o other Beer to check
     * @return true if both are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Beer)) return false;
        Beer other = (Beer) o;
        return this.name.equals(other.getName()) &&
                this.ID.equals(other.getID()) &&
                this.isAlcoholic == other.isAlcoholic() &&
                this.manufacturer.equals(other.getManufacturer()) &&
                Arrays.equals(this.ingredients, other.getIngredients()) &&
                this.chars.equals(other.getChars());
    }

    /**
     * Automatically generated method for hashing Beer
     * @return Hash Code of the current Beer
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.ID);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + (this.isAlcoholic ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.manufacturer);
        hash = 29 * hash + Arrays.deepHashCode(this.ingredients);
        hash = 29 * hash + Objects.hashCode(this.chars);
        return hash;
    }
}

