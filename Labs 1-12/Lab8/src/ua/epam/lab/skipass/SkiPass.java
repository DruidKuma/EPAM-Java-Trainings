
package ua.epam.lab.skipass;

import ua.epam.lab.skipass.types.SkiPassType;
import ua.epam.lab.skipass.types.Duration;
import java.util.Date;
import static ua.epam.lab.skipass.SkiPassSystem.BEGINSEASON;
import static ua.epam.lab.skipass.SkiPassSystem.ENDSEASON;
 

/**
 * Standard SkiPass (for both SEASON and LIMITED Ski Passes)
 * @author DruidKuma
 */
public class SkiPass {
    
    //unique ID
    protected final int ID;
    
    //Duration Dates
    protected Date dateOfActivation;
    protected Date dateOfExpiration;
    
    //Type of Ski Pass
    protected final SkiPassType type;
    
    //Duration in milliseconds
    protected Duration duration;
    
    //Activated
    protected boolean active = false;
    
    //Blocked
    protected boolean blocked = false;
    
    //Constructor for SkiPass
    public SkiPass(int id, SkiPassType type, Duration duration) {
        this.ID = id;
        this.type = type;
        this.duration = duration;
    }
    
    /**
     * ID getter
     * @return ID of the SkiPass
     */
    public int getID() {
        return this.ID;
    }
    
    /**
     * Type getter
     * @return SkiPassType of the SkiPass (LIMITED, AMOUNT, SEASON)
     */
    public SkiPassType getType() {
        return this.type;
    }
    
    /**
     * DOA getter
     * @return Date Of Activation of the SkiPass of null if not activated
     */
    public Date getDateOfActivation() {
        return this.dateOfActivation;
    }
    
    /**
     * DOE getter
     * @return Date Of Expiration of the SkiPass of null if not activated
     */
    public Date getDateOfExpiration() {
        return this.dateOfExpiration;
    }
    
    /**
     * Duration getter
     * @return Duration of the Ski Pass
     */
    public Duration getDuration() {
        return this.duration;
    }
    
    /**
     * Active getter
     * @return true if SkiPass is activated, false otherwise
     */
    public boolean isActive() {
        return this.active;
    }
    
    /**
     * Blocked getter
     * @return true is SkiPass is blocked, false otherwise
     */
    public boolean isBlocked() {
        return this.blocked;
    }
    
    /**
     * Blocked setter <br>
     * block the SkiPass
     */
    public void blockSkiPass() {
        this.blocked = true;
    }
    
    /**
     * Activate the SkiPass <br>
     * Called only once with first usage of the SkiPass
     */
    public void activate() {
        
        //Set proper Date of Activation
        if(new Date().getTime() <= BEGINSEASON) {
            this.dateOfActivation = new Date(BEGINSEASON);
        }
        else {
            this.dateOfActivation = new Date();
        }
        
        //Set proper Date of Expiration
        if(this.dateOfActivation.getTime() + this.duration.duration >= ENDSEASON) {
            this.dateOfExpiration = new Date(ENDSEASON);
        }
        else {
            this.dateOfExpiration = new Date(this.dateOfActivation.getTime()+this.duration.duration);
        }
        this.active = true;   
    }
    
    /**
     * Verification of the SkiPass, activate SkiPass if not activated,
     * block SkiPass if it is expired
     * @return true, if SkiPass is verified, false otherwise
     */
    public boolean makeTrip() {
        
        //if SkiPass is not yet activated, this is its first time usage, activate it
        if(!this.isActive()) {
            this.activate();
        }
        
        //today's date
        Date today = new Date();
        
        //if the Day of Activation has not yet come, permission denied
        if(today.before(this.dateOfActivation)) {
            return false;
        }
        
        //if SkiPass is expired, block it and deny the permission
        else if(today.after(this.dateOfExpiration)) {
            this.blockSkiPass();
            return false;
        }
        
        //permission accepted
        return true;
    }
}
