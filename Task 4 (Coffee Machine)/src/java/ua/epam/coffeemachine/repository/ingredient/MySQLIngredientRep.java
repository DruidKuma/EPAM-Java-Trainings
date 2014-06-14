
package ua.epam.coffeemachine.repository.ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.Ingredient;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.repository.mysql.MySQLRepFactory;

/**
 * MySQL DAO for the Coffee Machine
 * @author Yuriy Miedviediev
 * @version 1.0 Build 19.05.2014
 */
public class MySQLIngredientRep implements IngredientRepository {
    
    //DAO factory
    private final ConnectionFactory factory;
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(MySQLIngredientRep.class);

    public MySQLIngredientRep(MySQLRepFactory factory) {
        this.factory = factory;
    }

    /**
     * Load amount of the certain ingredient to the Coffee Machine
     * @param title title of the ingredient
     * @param amount amount to load
     */
    @Override
    public void loadIngredient(String title, int amount) {
        String update = "UPDATE ingredients SET amount = amount + ? WHERE title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(update);) {
            
            stmt.setInt(1, amount);
            stmt.setString(2, title);
            stmt.executeUpdate();
            log.info(amount + "gr. of " + title + " were added to the Coffee Machine");
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Get Ingredient from the database
     * @param id id of the ingredient
     * @return Ingredient, model of the coffee ingredient or null of ingredient is not found
     */
    @Override
    public Ingredient getIngredient(int id) {
        String select = "SELECT id, title, price FROM ingredients JOIN ingrPrices ON id = ingrID WHERE id = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()) {
                return new Ingredient(result.getString("title"), result.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }
    
    /**
     * Get Ingredient from the DB by title
     * @param title title of the Ingredient 
     * @return Ingredient, model of the coffee ingredient, or null if ingredient is not found
     */
    @Override
    public Ingredient getIngredient(String title) {
        String select = "SELECT id, title, price FROM ingredients JOIN ingrPrices ON id = ingrID WHERE title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setString(1, title);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()) {
                return new Ingredient(result.getString("title"), result.getDouble("price"));
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }

    /**
     * Withdraw a certain amount of the ingredient from the DB
     * @param title title of the ingredient
     * @param amount amount of ingredient to withdraw
     */
    @Override
    public void withdraw(String title, int amount) {
        String update = "UPDATE ingredients SET amount = amount - ? WHERE title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(update);) {
            
            stmt.setInt(1, amount);
            stmt.setString(2, title);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Set new Price per gram for the ingredient
     * @param title title of the ingredient
     * @param newPrice new price for for the ingredient
     */
    @Override
    public void updatePrice(String title, Double newPrice) {
        String update = "UPDATE ingrPrices SET price = ? WHERE ingrID in (SELECT id FROM ingredients WHERE title = ?)";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(update);) {
            
            stmt.setDouble(1, newPrice);
            stmt.setString(2, title);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }
    
    /**
     * Get total amount of ingredient available in the Coffee Machine 
     * @param title title of the ingredient
     * @return integer, amount of the ingredient in the coffee-machine
     */
    @Override
    public Integer getAmount(String title) {
        String select = "SELECT * FROM ingredients WHERE title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) return rs.getInt("amount");
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }

    /**
     * Get all ingredients from the DB
     * @return list of all available ingredients
     */
    @Override
    public List<Ingredient> findAll() {
        String select = "SELECT id, title, price FROM ingredients JOIN ingrPrices ON id = ingrID";
        List<Ingredient> result = new ArrayList();
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                result.add(new Ingredient(rs.getString("title"), rs.getDouble("price")));
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return result;
    }

    @Override
    public void withdraw(String drink) {
        String select = "SELECT ingredients.title, recipe.amount FROM drinks JOIN recipe ON id=drinkID JOIN ingredients ON ingredients.id=ingrID WHERE drinks.title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setString(1, drink);
            ResultSet rs = stmt.executeQuery();
            
            if(!rs.next()) return;
            rs.previous();
            while(rs.next()) {
                this.withdraw(rs.getString("title"), rs.getInt("amount"));
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }
}
