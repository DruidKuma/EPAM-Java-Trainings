
package ua.epam.coffeemachine.repository.user;

import java.util.List;
import ua.epam.coffeemachine.domain.User;

/**
 * Interface for User Data Access Object
 * @author Yuriy Miedviediev
 * @version 1.0 Build 14.05.2014
 */
public interface UserRepository {
    public User findUser(String login);
    public void addUser(User user);
    public void setPassword(String login, String password);
    public void removeUser(String login);
    public List<User> findAll();
    public void updateUser(User user);
    public String getPassword(String login);
    public String getPasswordByID(int id);
}
