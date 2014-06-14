
package ua.epam.coffeemachine.web.control.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Factory of Actions For the Coffee Machine System
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class ActionFactory {
    
    //map all the possible actions with appropriate handlers
    private static final Map<String, Action> actions;
    static {
        actions = new HashMap();
        actions.put("/login", new LoginPage());
        actions.put("/registration", new RegistrationPage());
        actions.put("/order", new OrderPage());
        actions.put("/confirm", new ConfirmAction());
        actions.put("/buy", new BuyAction());
        actions.put("/load", new LoadIngredientsPage());
    }
    
    /**
     * Get appropriate handler by the requested path
     * @param request http request
     * @return handler for the request
     */
    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getPathInfo());
    }
}
