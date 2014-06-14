
package ua.epam.coffeemachine.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for User input validation
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class Validator {
    
    /**
     * Check if user entered valid email while registration
     * @param email user input to check
     * @return true if entered text is valid email, false otherwise
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Check if user entered passwords match while registration
     * @param pass entered password
     * @param verification entered password verification
     * @return true, if passwords match, false otherwise
     */
    public static boolean isValidPassword(String pass, String verification) {
        return pass.equals(verification);
    }
    
    /**
     * Check if entered amount is a valid number (while adding ingredients to the Coffee Machine)
     * @param amount entered amount
     * @return true, if amount is valid number, false otherwise
     */
    public static boolean isValidAmount(String amount) {
        try {
            Integer.parseInt(amount);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
