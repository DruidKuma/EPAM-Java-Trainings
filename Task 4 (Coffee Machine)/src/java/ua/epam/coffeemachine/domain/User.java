
package ua.epam.coffeemachine.domain;

import java.util.Objects;

/**
 * User of the Coffee Machine
 * @author Yuriy Miedviediev
 * @version 1.0 Build 19.05.2014
 */
public class User {
    
    //data fields
    private final String login;
    private final String name;
    private final String surname;
    private final String email;
    private Double account;
    private final Role role;
    
    /**
     * Enumeration of User's access (simple user, or administrator)
     */
    public enum Role {
        USER(1), ADMIN(2);
        
        private final int role;      
        
        Role(int r) {
            role = r;
        }
    }
    
    public User(String login, String name, String surname, String email, Double account, int role) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.account = account;
        switch(role) {
                case 2:
                    this.role = Role.ADMIN;
                    break;
                default:
                    this.role = Role.USER;
        }
    }
    
    /**
     * Login getter
     * @return Login of the current user
     */
    public String getLogin() {
        return this.login;
    }
    
    /**
     * Name getter
     * @return name of the user
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Surname getter
     * @return surname of the current user
     */
    public String getSurname() {
        return this.surname;
    }
    
    /**
     * Email getter
     * @return email of the current user
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Account getter
     * @return account of the current User
     */
    public Double getAccount() {
        return this.account;
    }
    
    /**
     * Make a payment
     * @param amount bill to pay
     */
    public void withdraw(Double amount) {
        this.account -= amount;
    }
    
    /**
     * Get role of the current user
     * @return role of the current user
     */
    public Role getRole() {
        return this.role;
    }
    
    /**
     * Check the equality with other user
     * @param otherUser other user to check
     * @return true, if users are equal, false otherwise
     */
    @Override
    public boolean equals(Object otherUser) {
        if(otherUser instanceof User) {
            User other = (User) otherUser;
            return this.login.equals(other.getLogin());
        }
        return false;
    }

    /**
     * Generate hash code for current user
     * @return integer, hash code for the current user
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.login);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.surname);
        hash = 29 * hash + Objects.hashCode(this.email);
        return hash;
    }
}

