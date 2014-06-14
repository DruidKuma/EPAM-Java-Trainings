
package ua.epam.coffeemachine.repository.drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;

/**
 * MySQL implementation of the Drink DAO
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.05.2014
 */
public class MySQLDrinkRep implements DrinkRepository {
    
    //DAO factory
    private final ConnectionFactory factory;
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(MySQLDrinkRep.class);
    
    public MySQLDrinkRep(ConnectionFactory factory) {
        this.factory = factory;
    }

    /**
     * Find Drink in the DB by ID
     * @param id id of the drink
     * @return Drink from the DB or null if not found
     */
    @Override
    public Drink findDrinkByID(int id) {
        String select = "SELECT id, title, price FROM drinks JOIN drinkPrices ON id = drinkID WHERE drinks.id = ?";
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {

            stmt.setInt(1, id);
            ResultSet rSet = stmt.executeQuery();
            
            if (rSet.next()){
                return new Drink(rSet.getString("title"), rSet.getDouble("price"));
            }
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }

    /**
     * Find drink by title
     * @param title title of the wanted drink
     * @return Drink from the DB or null if not found
     */
    @Override
    public Drink findDrink(String title) {
        String select = "SELECT id, title, price FROM drinks JOIN drinkPrices ON id = drinkID WHERE drinks.title = ?";
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setString(1, title);
            ResultSet result = stmt.executeQuery();
            
            if (result.next()){
                return new Drink(result.getString("title"), result.getDouble("price"));
            }
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }
    
    /**
     * Add new Drink to the DB
     * @param drink drink to add to the DB
     */
    @Override
    public void addDrink(Drink drink) {
        String insertDrink = "INSERT INTO drinks (title) VALUES (?)";
        String findID = "SELECT * FROM drinks WHERE title = ?";
        String insertPrice = "INSERT INTO drinkPrices VALUES (?, ?)";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stInsertDrink = connection.prepareStatement(insertDrink);
            PreparedStatement stFindID = connection.prepareStatement(findID);
            PreparedStatement stInsertPrice = connection.prepareStatement(insertPrice);){
            
            //insert drink title
            stInsertDrink.setString(1, drink.getTitle());
            stInsertDrink.executeUpdate();
            
            //find drink id
            stFindID.setString(1, drink.getTitle());
            ResultSet rs = stFindID.executeQuery();
            int id = 0;
            if(rs.next()) id = rs.getInt("id");
            
            //insert drink price
            stInsertPrice.setInt(1, id);
            stInsertPrice.setDouble(2, drink.getPrice());
            stInsertPrice.executeUpdate();
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Update price of the drink
     * @param title drink title
     * @param newPrice new Price for the drink
     */
    @Override
    public void updatePrice(String title, int newPrice) {
        String update = "UPDATE drinkPrices SET PRICE = ? WHERE id in (SELECT id FROM drinks WHERE title = ?)";
        
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
     * Delete drink from the database
     * @param title title of the Drink to remove
     */
    @Override
    public void removeDrink(String title) {
        String deleteDrink = "DELETE * FROM drinks where title = ?";
        String deletePrice = "DELETE * FROM drinkPrices where id in (SELECT id from drinks WHERE title = ?)";
        String deleteRecipe = "DELETE * FROM recipe where drinkID in (SELECT id from drinks WHERE title = ?)";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stDelDrink = connection.prepareStatement(deleteDrink);
            PreparedStatement stDelPrice = connection.prepareStatement(deletePrice);
            PreparedStatement stDelRecipe = connection.prepareStatement(deleteRecipe);) {
            
            
            //Delete drink title
            stDelDrink.setString(1, title);
            stDelDrink.executeUpdate();
            
            //Delete drink price
            stDelPrice.setString(1, title);
            stDelPrice.executeUpdate();
            
            //Delete drink recipe
            stDelRecipe.setString(1, title);
            stDelRecipe.executeUpdate();
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Find all Drinks in the Database
     * @return List of all the drinks from the DB
     */
    @Override
    public List<Drink> findAll() {
        String select = "SELECT title, price FROM drinks JOIN drinkPrices where drinks.id = drinkPrices.drinkID";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            ArrayList<Drink> drinks = new ArrayList();
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) drinks.add(new Drink(rs.getString("title"), rs.getDouble("price")));
            return drinks;
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }
    
    /**
     * Check the availability of ingredients for given drink
     * @param title title of the drink
     * @return true, if there is enough ingredients for the given drink
     */
    @Override
    public boolean checkAmount(String title) {
        String select = "SELECT recipe.amount, ingredients.amount FROM drinks JOIN recipe ON id=drinkID JOIN ingredients ON ingredients.id=ingrID WHERE drinks.title = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            
            if(!rs.next()) return false;
            rs.previous();
            while(rs.next()) {
                if(rs.getInt("recipe.amount") > rs.getInt("ingredients.amount")) return false;
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return true;
    }
}
