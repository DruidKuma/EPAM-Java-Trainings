
package ua.epam.coffeemachine.repository.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;

/**
 * MySQL implementation of DAO for User
 * @author Yuriy Miedviediev
 * @version 1.0 Build 14.05.2014
 */
public class MySQLUserRep implements UserRepository {
    
    //DAO factory
    private final ConnectionFactory factory;
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(MySQLUserRep.class);
    
    public MySQLUserRep(ConnectionFactory factory) {
        this.factory = factory;
    }

    /**
     * Get User from DB based on id
     * @param login User login
     * @return User, if such exists, null otherwise
     */
    @Override
    public User findUser(String login) { 
        String selection = "SELECT * FROM users where login = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(selection);) {
            
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return new User(resultSet.getString("login"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("email"), resultSet.getDouble("account"), resultSet.getInt("role"));
            }            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }

    /**
     * Add new User to the DB
     * @param user User to add to the DB
     */
    @Override
    public void addUser(User user) {
        String insert = "INSERT INTO users (login, name, surname, email, account, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(insert);) {
            
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getEmail());
            stmt.setDouble(5, user.getAccount());
            stmt.setInt(6, 1);
            stmt.executeUpdate();
            
            log.info("New User (" + user.getLogin() + ") was added to the database");
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Delete User from the DB based on id
     * @param login User login
     */
    @Override
    public void removeUser(String login) {
        String deletion = "DELETE FROM users WHERE login= ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(deletion);) {

            stmt.setString(1, login);
            stmt.executeUpdate();
            
            log.info("User " + login + " was successfully deleted");
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Get all Users from the database
     * @return List with all users currently exist in the DB
     */
    @Override
    public List<User> findAll() {
        
        String select = "SELECT * FROM users";
        
        //list with results of query
        List<User> result = new ArrayList();
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(select);) {
            
            ResultSet users = stmt.executeQuery();
            
            while(users.next()) {
                result.add(new User(users.getString("login"), users.getString("name"), users.getString("surname"), users.getString("email"), users.getDouble("account"), users.getInt("role")));
            }
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return result;
    }

    /**
     * Update the account of given User
     * @param user user to update
     */
    @Override
    public void updateUser(User user) {
        String update = "UPDATE users SET account = ? WHERE login = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(update);) {
            
            stmt.setDouble(1, user.getAccount());
            stmt.setString(2, user.getLogin());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Tie given login to the given password for authorization
     * @param login user's login
     * @param password desired password
     */
    @Override
    public void setPassword(String login, String password) {
        String findID = "SELECT * FROM users WHERE login = ?";
        String insertPass = "INSERT INTO passwords VALUES(?, ?)";
        String updatePass = "UPDATE password SET password = ? WHERE id = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stFindID = connection.prepareStatement(findID);
            PreparedStatement stInsertPass = connection.prepareStatement(insertPass);
            PreparedStatement stUpdatePass = connection.prepareStatement(updatePass);) {
            
            //Find id of the user with given login
            stFindID.setString(1, login);
            ResultSet rs = stFindID.executeQuery();
            int id = 0;
            if(rs.next()) id = rs.getInt("id");
            
            //if there is some data, change password
            if(this.getPasswordByID(id) != null) {
               stUpdatePass.setString(1, password);
               stUpdatePass.setInt(2, id);
               stUpdatePass.executeUpdate();
            }
            //else set new id-password pair
            else {
               stInsertPass.setInt(1, id);
               stInsertPass.setString(2, password);
               stInsertPass.executeUpdate(); 
            }
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
    }

    /**
     * Get User's password by login
     * @param login
     * @return password of the user with given login or null if not found
     */
    @Override
    public String getPassword(String login) {
        String findPass = "SELECT * FROM passwords WHERE userID in (SELECT id FROM users WHERE login = ?)";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(findPass);) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getString("password");
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }

    /**
     * Find password of the user with given ID
     * @param id User's ID
     * @return password of the User with given ID or null if not found
     */
    @Override
    public String getPasswordByID(int id) {
        String findPass = "SELECT * FROM passwords WHERE id = ?";
        
        try (Connection connection = factory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(findPass);) {
            
            //find data in the passwords table with given id
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getString("password");
            
        } catch (SQLException ex) {
            log.error(ex, ex);
        }
        return null;
    }
}
