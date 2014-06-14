
package ua.epam.coffeemachine.repository.mysql;

import java.sql.Connection;
import ua.epam.coffeemachine.repository.drink.DrinkRepository;
import ua.epam.coffeemachine.repository.ingredient.IngredientRepository;
import ua.epam.coffeemachine.repository.user.UserRepository;

/**
 * Abstract Data Access Object Factory
 * @author Yuriy Miedviediev
 * @version 1.0 Build 14.05.2014
 */
public abstract class ConnectionFactory {
    
    //Available types of data management
    public enum ConnectionType {
        MySQL
    }
    
    //abstract methods for concrete DAO factories
    public abstract Connection getConnection();
    public abstract UserRepository getUserRep();
    public abstract DrinkRepository getDrinkRep();
    public abstract IngredientRepository getIngredientRep();
    
    //Create concrete DAO Factory
    public static ConnectionFactory getDAOFactory(ConnectionType type) {
        switch(type) {
            case MySQL: return new MySQLRepFactory();
            default: return null;
        }
    }
}
