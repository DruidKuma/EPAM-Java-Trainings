
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.domain.Ingredient;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.drink.DrinkService;
import ua.epam.coffeemachine.service.drink.StandardDrinkService;
import ua.epam.coffeemachine.web.validator.Validator;

/**
 * Page for loading the Coffee Machine
 * @author Yuriy Miedviediev
 * @version 1.0 Build 03.06.2014
 */
class LoadIngredientsPage implements Action {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) throws ServletException, IOException {
        DrinkService ds = new StandardDrinkService(dao.getDrinkRep(), dao.getIngredientRep());
        
        //get entered data
        String choice = request.getParameter("ingr");
        String amount = request.getParameter("amount");
        
        //if fields are not empty
        if(choice != null && amount != null) {
            
            //if entered amount is a valid number
            if(Validator.isValidAmount(amount)) {
                ds.loadIngredient(new Ingredient(choice, Integer.parseInt(amount), null));
                request.setAttribute("infoMessage", "successIngrLoad");
            }
            else {
                request.setAttribute("infoMessage", "wrongInput");
            }
        }
        
        else if(choice != null) {
            request.setAttribute("amountInput", choice);
        }
        
        //List all the available ingredients
        List<Ingredient> ingrs = ds.findAllIngredients();
        for(Ingredient ingr : ingrs) {
            ingr.setAmount(ds.getAmount(ingr));
        }
        request.setAttribute("ingrs", ingrs);
        return "load";
    }

}
