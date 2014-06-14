
package ua.epam.coffeemachine.repository.ingredient;

import java.util.List;
import ua.epam.coffeemachine.domain.Ingredient;

/**
 * DAO interface for the Coffee Machine
 * @author Yuriy Miedviediev
 * @version 1.0 Build 19.05.2014
 */
public interface IngredientRepository {
    public void loadIngredient(String title, int amount);
    public Ingredient getIngredient(int id);
    public Ingredient getIngredient(String title);
    public List<Ingredient> findAll();
    public void withdraw(String title, int amount);
    public void updatePrice(String title, Double newPrice);
    public Integer getAmount(String title);
    public void withdraw(String drink);
}
