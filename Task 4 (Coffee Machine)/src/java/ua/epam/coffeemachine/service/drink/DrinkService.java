
package ua.epam.coffeemachine.service.drink;

import java.util.List;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.Ingredient;

/**
 * Interface for the Drink Service
 * @author Yuriy Miedviediev
 * @version 1.0 Build 30.05.2014
 */
public interface DrinkService {
    public void loadIngredient(Ingredient ingr);
    public Drink getDrink(String title);
    public Ingredient getIngredient(String title);
    public void addDrink(Drink drink);
    public boolean checkAmount(Ingredient ingr);
    public Integer getAmount(Ingredient ingr);
    public boolean checkAll(List<Ingredient> ingrs);
    public void withdraw(List<Ingredient> ingrs);
    public List<Drink> findAllDrinks();
    public List<Ingredient> findAllIngredients();
    public boolean checkDrink(Drink drink);
    public void withdraw(Drink drink);
}
