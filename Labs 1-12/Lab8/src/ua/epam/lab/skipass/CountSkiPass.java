/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab.skipass;

import java.util.Date;
import ua.epam.lab.skipass.types.Duration;
import ua.epam.lab.skipass.types.NumTrips;
import ua.epam.lab.skipass.types.SkiPassType;

/**
 * SkiPass for a certain amount of Trips
 * @author DruidKuma
 */
public class CountSkiPass extends SkiPass {
    
    //Amount of available Trips for the SkiPass
    private int numTrips;
    
    //Constructor
    public CountSkiPass(int id, SkiPassType type, NumTrips num) {
        super(id, type, Duration.FORSEASON);
        numTrips = num.numTrips;
    }
    
    
    /**
     * numTrips getter
     * @return integer, number of trips per this Ski Pass
     */
    public int getNumTrips() {
        return this.numTrips;
    }
    
    /**
     * Verification of the SkiPass, activate SkiPass if not activated,
     * block SkiPass if it is expired, or ran out of trips, update the numTrips 
     * @return true, if SkiPass is verified, false otherwise
     */
    @Override
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
        else if(today.after(this.dateOfExpiration) || this.numTrips <= 0) {
            this.blockSkiPass();
            return false;
        }
        
        //permission accessed, update numTrips
        else {
            this.numTrips--;
            return true;
        }
    }
}
