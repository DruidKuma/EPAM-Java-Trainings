
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.Ingredient;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.drink.DrinkService;
import ua.epam.coffeemachine.service.drink.StandardDrinkService;

/**
 * Handler for the Drink Order Page
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class OrderPage implements Action {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) throws ServletException, IOException {
        DrinkService ds = new StandardDrinkService(dao.getDrinkRep(), dao.getIngredientRep());
        
        //Get all possible drinks and ingredients, remove coffee and water from the toppings
        List<Drink> drinks = ds.findAllDrinks();
        List<Ingredient> ingrs = ds.findAllIngredients();
        ingrs.remove(0);
        ingrs.remove(0);
        
        request.setAttribute("drinks", drinks);
        request.setAttribute("ingrs", ingrs);
        return "order";
    }
    
}
