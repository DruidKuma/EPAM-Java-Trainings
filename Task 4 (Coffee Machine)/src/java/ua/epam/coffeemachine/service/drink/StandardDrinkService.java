
package ua.epam.coffeemachine.service.drink;

import java.util.List;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.Ingredient;
import ua.epam.coffeemachine.repository.drink.DrinkRepository;
import ua.epam.coffeemachine.repository.ingredient.IngredientRepository;

/**
 * Standard Drink Service for the Coffee Machine
 * @author Yuriy Miedviediev
 * @version 1.0 Build 30.05.2014
 */
public class StandardDrinkService implements DrinkService {
    
    private final DrinkRepository drinkRep;
    private final IngredientRepository ingrRep;
    
    public StandardDrinkService(DrinkRepository drinkRep, IngredientRepository ingrRep) {
        this.drinkRep = drinkRep;
        this.ingrRep = ingrRep;
    }

    /**
     * Add some amount of the ingredient to the coffee machine
     * @param ingr ingredient to update the coffee machine
     */
    @Override
    public void loadIngredient(Ingredient ingr) {
        ingrRep.loadIngredient(ingr.getTitle(), ingr.getAmount());
    }

    /**
     * Get Drink from the coffee machine
     * @param title title of the drink
     * @return Drink with given title
     */
    @Override
    public Drink getDrink(String title) {
        return drinkRep.findDrink(title);
    }

    /**
     * Get Ingredient from the coffee machine
     * @param title title of the ingredient
     * @return ingredient with given title
     */
    @Override
    public Ingredient getIngredient(String title) {
        return ingrRep.getIngredient(title);
    }

    /**
     * Add new drink to the coffee machine
     * @param drink drink to add to the machine
     */
    @Override
    public void addDrink(Drink drink) {
        drinkRep.addDrink(drink);
    }

    /**
     * Check the amount of ingredient available in the coffee machine
     * @param ingr ingredient to check
     * @return true, if amount of the ingredient is enough, false otherwise
     */
    @Override
    public boolean checkAmount(Ingredient ingr) {
        return ingrRep.getAmount(ingr.getTitle()) >= ingr.getAmount();
    }

    /**
     * Check the amount of all ingredients in the given list
     * @param ingrs list of ingredients to check
     * @return true, if there is enough amount of every ingredient in the list, false otherwise
     */
    @Override
    public boolean checkAll(List<Ingredient> ingrs) {
        for(Ingredient ingr : ingrs) {
            if(!this.checkAmount(ingr)) return false;
        }
        return true;
    }

    /**
     * Update the amount of ingredients in the coffee machine
     * @param ingrs list of ingredients
     */
    @Override
    public void withdraw(List<Ingredient> ingrs) {
        for(Ingredient ingr : ingrs) {
            ingrRep.withdraw(ingr.getTitle(), ingr.getAmount());
        }
    }

    /**
     * Get all possible drinks from the coffee machine
     * @return list with all available drinks
     */
    @Override
    public List<Drink> findAllDrinks() {
        return drinkRep.findAll();
    }

    /**
     * Get all possible ingredients from the coffee machine
     * @return list with all available ingredients
     */
    @Override
    public List<Ingredient> findAllIngredients() {
        return ingrRep.findAll();
    }

    /**
     * Check the amount of ingredients required for given drink
     * @param drink drink to check
     * @return true, if there is enough ingredients, false otherwise
     */
    @Override
    public boolean checkDrink(Drink drink) {
        return drinkRep.checkAmount(drink.getTitle());
    }

    /**
     * Update the amount of ingredients required for the drink in the coffee machine
     * @param drink drink to withdraw
     */
    @Override
    public void withdraw(Drink drink) {
        ingrRep.withdraw(drink.getTitle());
    }

    /**
     * Get the total amount of given ingredient in the Coffee Machine
     * @param ingr ingredient to look in the Coffee Machine
     * @return Integer, total amount of the ingredient in the Coffee Machine
     */
    @Override
    public Integer getAmount(Ingredient ingr) {
        return ingrRep.getAmount(ingr.getTitle());
    }
    
}
