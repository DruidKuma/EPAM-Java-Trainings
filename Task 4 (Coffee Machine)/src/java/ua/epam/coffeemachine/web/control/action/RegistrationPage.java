
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.service.user.StandardUserService;
import ua.epam.coffeemachine.service.user.UserService;
import ua.epam.coffeemachine.web.validator.Validator;

/**
 * Register Action for the Coffee Machine System
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class RegistrationPage implements Action {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao) throws ServletException, IOException {
        
        UserService us = new StandardUserService(dao.getUserRep());
        
        //Get user entered info
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String verifyPassword = request.getParameter("verification");
        
        //if at least one of the fields is empty, render same page again
        if(name==null || surname==null || email==null || login==null) {
            request.setAttribute("infoMessage", "initialRegInfo");
            return "registration";
        }
        
        //Validate all entered data
        boolean validMail = Validator.isValidEmail(email);
        boolean validPassword = Validator.isValidPassword(password, verifyPassword);
        boolean newUser = !us.existUser(login);
        
        //if everything is all right, register new user
        if(validMail && validPassword && newUser) {
            us.addUser(new User(login, name, surname, email, 1000.0, 1));
            us.setPassword(login, password);
            request.setAttribute("error", "errorAfterRegister");
            return "login";
        }
        
        //if something is wrong, render the same page with appropriate message
        if(!validMail) request.setAttribute("emailErr", "emailError");
        if(!validPassword) request.setAttribute("passErr", "passError");
        if(!newUser) request.setAttribute("loginErr", "loginError");
        return "registration";
    }
    
}
