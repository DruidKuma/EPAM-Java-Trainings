
package ua.epam.coffeemachine.service.user;

import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.repository.user.UserRepository;

/**
 * Standard implementation for User Service
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.05.2014
 */
public class StandardUserService implements UserService {
    
    //DAO for the service
    private final UserRepository repository;
    
    public StandardUserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Check the existence of the user in the DB
     * @param login
     * @return true if user with such login exists, false otherwise
     */
    @Override
    public boolean existUser(String login) {
        return repository.findUser(login) != null;
    }

    /**
     * Add new User to the DB
     * @param user user to add
     */
    @Override
    public void addUser(User user) {
        repository.addUser(user);
    }

    /**
     * Get user from the DB
     * @param login login of the wanted user
     * @return User with requested login or null if not found
     */
    @Override
    public User getUser(String login) {
        return repository.findUser(login);
    }

    /**
     * Verify user's login and password
     * @param login entered login
     * @param password entered password
     * @return true if login and password are verified, false otherwise
     */
    @Override
    public boolean verifyUser(String login, String password) {
        User user = this.getUser(login);
        if(user != null) {
            return password.equals(repository.getPassword(login));
        }
        return false;
    }

    /**
     * Pay for the ordered drink
     * @param user User who pays for the order
     * @param drink ordered drink
     */
    @Override
    public void pay(User user, Drink drink) {
        user.withdraw(drink.getTotalPrice());
        repository.updateUser(user);
    }
    
    /**
     * Set password for the user
     * @param login login of the user
     * @param password new password for the user
     */
    @Override
    public void setPassword(String login, String password) {
        repository.setPassword(login, password);
    }
}
