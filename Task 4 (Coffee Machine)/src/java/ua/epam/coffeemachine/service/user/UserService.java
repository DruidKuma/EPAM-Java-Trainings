
package ua.epam.coffeemachine.service.user;

import ua.epam.coffeemachine.domain.Drink;
import ua.epam.coffeemachine.domain.User;

/**
 * Interface for business logic with User
 * @author Yuriy Miedviediev
 * @version 1.0 Build 29.05.2014
 */
public interface UserService {
    public boolean existUser(String login);
    public void addUser(User user);
    public User getUser(String login);
    public void pay(User user, Drink drink);
    public boolean verifyUser(String login, String password);
    public void setPassword(String login, String password);
}
