
package ua.epam.coffeemachine.repository.mysql;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.repository.drink.DrinkRepository;
import ua.epam.coffeemachine.repository.drink.MySQLDrinkRep;
import ua.epam.coffeemachine.repository.ingredient.IngredientRepository;
import ua.epam.coffeemachine.repository.ingredient.MySQLIngredientRep;
import ua.epam.coffeemachine.repository.user.MySQLUserRep;
import ua.epam.coffeemachine.repository.user.UserRepository;

/**
 * DAO Factory for MySQL RDBMS
 * @author Yuriy Miedviediev
 * @version 1.0 Build 14.05.2014
 */
public class MySQLRepFactory extends ConnectionFactory {

    //Connection Pool
    private static BoneCP connectionPool;
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(MySQLRepFactory.class);
    
    /**
     * Initialize Connection Pool before starting application
     */
    public static void startConPool() {
        
        ResourceBundle info = ResourceBundle.getBundle("mysqldb");
        
        try {
            Class.forName(info.getString("driver"));

            //Creating configurations for connection pool
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(info.getString("jdbcUrl") + "?useUnicode=true&characterEncoding=UTF-8");
            config.setUsername(info.getString("username"));
            config.setPassword(info.getString("password"));
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            
            connectionPool = new BoneCP(config);
            log.info("Connection Pool successfully created");
        } catch (SQLException | ClassNotFoundException ex) {
            log.error(ex, ex);
        }
    }
    
    /**
     * Close Connection Pool before shutting the application
     */
    public static void destroyConPool() {
        if(connectionPool != null) {
            connectionPool.shutdown();
            log.info("Connection Pool was successfully shut down");
        }
    }
    
    /**
     * Connect to the MySQL Coffee Machine database
     * @return connection to the above mentioned database
     */
    @Override
    public Connection getConnection() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }
    
    /**
     * Get MySQL User DAO
     * @return MySQL DAO for the User
     */
    @Override
    public UserRepository getUserRep() {
        return new MySQLUserRep(this);
    }
    
    /**
     * Get MySQL Drink DAO
     * @return MySQL DAO for the Drink
     */
    @Override
    public DrinkRepository getDrinkRep() {
        return new MySQLDrinkRep(this);
    }

    /**
     * Get MySQL Ingredient DAO
     * @return MySQL DAO for the Ingredient
     */
    @Override
    public IngredientRepository getIngredientRep() {
        return new MySQLIngredientRep(this);
    }
}
