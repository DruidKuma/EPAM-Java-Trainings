
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.drink.DrinkService;
import ua.epam.coffeemachine.service.drink.StandardDrinkService;
import ua.epam.coffeemachine.service.user.StandardUserService;
import ua.epam.coffeemachine.service.user.UserService;

/**
 * Action for making a purchase of Coffee Drink
 * @author Yuriy Miedviediev
 * @version 1.0 Build 02.06.2014
 */
class BuyAction implements Action {

    //Log4j logger
    public static final Logger log = Logger.getLogger(BuyAction.class);
    
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) throws ServletException, IOException {
        
        DrinkService ds = new StandardDrinkService(dao.getDrinkRep(), dao.getIngredientRep());
        UserService us = new StandardUserService(dao.getUserRep());
        
        Drink drink = (Drink)request.getSession().getAttribute("order");
        User user = (User)request.getSession().getAttribute("user");
        
        //if user has enough money and there is enough ingredients for making purchase, make the deal
        if(user.getAccount() >= drink.getTotalPrice() && ds.checkAll(drink.getIngrs()) && ds.checkDrink(drink)) {
            ds.withdraw(drink);
            ds.withdraw(drink.getIngrs());
            us.pay(user, drink);
        }
        
        log.info("User "+user.getLogin()+" purchased " + drink.getTitle() + " for "+drink.getTotalPrice()+"$");
        return "coffee";
    }

}
