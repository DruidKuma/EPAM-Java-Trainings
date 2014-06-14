
package ua.epam.coffeemachine.web.control.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.domain.User.Role;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.user.StandardUserService;
import ua.epam.coffeemachine.service.user.UserService;

/**
 * Action for User authorization to the Coffee Machine System
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class LoginPage implements Action {
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(LoginPage.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) {
        UserService us = new StandardUserService(dao.getUserRep());
        
        //if user is already authorized, push him to the coffee page
        if(request.getSession().getAttribute("user") != null) {
            return "coffee";
        }
        
        //get entered authorization info
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        //if nothing is entered (user has just entered the page)
        if(login==null || password==null) {
            request.setAttribute("error", "errorLoginEnter");
            return "login";
        }
        
        //if info is verified, add user to the current session 
        else if(us.verifyUser(login, password)){
            User user = us.getUser(login);
            request.getSession().setAttribute("user", user);
            
            //if user is admin
            if(user.getRole() == Role.ADMIN) {
                request.getSession().setAttribute("admin", true);
            }
            log.info("User " + user.getLogin() + " has logged in");
            return "coffee";
        }
        
        //else (user entered wrong info)
        else {
            request.setAttribute("error", "errorLoginIncorrect");
            return "login";
        }
    }
    
}
