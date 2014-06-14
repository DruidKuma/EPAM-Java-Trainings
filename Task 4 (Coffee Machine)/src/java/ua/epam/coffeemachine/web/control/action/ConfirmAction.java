
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.Ingredient;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.drink.DrinkService;
import ua.epam.coffeemachine.service.drink.StandardDrinkService;

/**
 * Confirm the Order of the Coffee Drink
 * @author Yuriy Miedviediev
 * @version 1.0 Build 02.06.2014
 */
class ConfirmAction implements Action {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) throws ServletException, IOException {
        
        DrinkService ds = new StandardDrinkService(dao.getDrinkRep(), dao.getIngredientRep());
        Drink drink = ds.getDrink(request.getParameter("drink"));
        
        //get all available ingredients and remove Coffee and Water from available toppings
        List<Ingredient> ingrs = ds.findAllIngredients();
        ingrs.remove(0);
        ingrs.remove(0);
        
        //Add ingredients with chosen amount to the ordered drink
        for(Ingredient ingr : ingrs) {
            ingr.setAmount(Integer.parseInt(request.getParameter(ingr.getTitle())));
            drink.addIngr(ingr);
        }
        
        //set initial value for permission of the transaction
        request.setAttribute("accept", true);
        
        //set order to the request and to the current session
        request.setAttribute("drink", drink);
        request.getSession().setAttribute("order", drink);
        
        //if user don't have enough money to buy the drink
        if(((User)request.getSession().getAttribute("user")).getAccount() < drink.getTotalPrice()) {
            request.setAttribute("errorMoney", "errorMoney");
            request.removeAttribute("accept");
        }
        
        //if there is not enough ingredients in the Coffee Machine to make the drink
        if(!ds.checkDrink(drink) || !ds.checkAll(drink.getIngrs())) {
            request.setAttribute("errorSupply", "errorSupply");
            request.removeAttribute("accept");
        }
        return "confirm";
    }
    
}
