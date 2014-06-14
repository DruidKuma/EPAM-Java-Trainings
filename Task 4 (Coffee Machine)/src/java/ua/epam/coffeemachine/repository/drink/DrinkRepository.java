
package ua.epam.coffeemachine.repository.drink;

import java.util.List;
import ua.epam.coffeemachine.domain.Drink;

/**
 * Interface For the Drink DAO
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.05.2014
 */
public interface DrinkRepository {
    public Drink findDrinkByID(int id);
    public Drink findDrink(String title);
    public void addDrink(Drink drink);
    public void removeDrink(String title);
    public void updatePrice(String title, int newPrice);
    public List<Drink> findAll();
    public boolean checkAmount(String drink);
}
