
package ua.epam.lab.skipass;

import java.util.GregorianCalendar;
import ua.epam.lab.MyArrayList;
import ua.epam.lab.skipass.types.*;

/**
 * Ski Pass System, database for the Ski Resort System
 * @author DruidKuma
 */
public class SkiPassSystem {
    
    //database for SkiPasses
    private MyArrayList skiPassDatabase;
    
    //number of published SkiPasses; use for ID
    public int numSkiPasses = 0;
    
    //counters for entry permissions
    int numAllowed = 0;
    int numDenied = 0;
    
    //Season begins on April 1-st 2014
    public static final long BEGINSEASON = new GregorianCalendar(2014,3,1).getTimeInMillis();
    //Season ends on June 1-st 2014
    public static final long ENDSEASON = new GregorianCalendar(2014,5,1).getTimeInMillis();
    
    /**
     * Lazy implementation of Singleton template for SkiPass Database
     * Instantiates the SkiPass database
     */
    public synchronized void getDatabase() {
        if (skiPassDatabase == null) {
            skiPassDatabase = new MyArrayList();
        }
    }
    
    /**
     * Create new Numerical Ski Pass and add it to the database
     * @param num NumTrips, amount of trips for the Ski Pass
     * @return New CountSkiPass
     */
    public SkiPass createSkiPass(NumTrips num) {
        SkiPass newSkiPass = new CountSkiPass(this.numSkiPasses, SkiPassType.AMOUNT, num);
        this.numSkiPasses++;
        skiPassDatabase.add(newSkiPass);
        return newSkiPass;        
    }
    
    /**
     * Create new Ski Pass and add it to the database
     * @param type  type of Ski Pass
     * @param duration  duration of Ski Pass
     * @return new SkiPass
     */
    public SkiPass createSkiPass(SkiPassType type, Duration duration) {
        SkiPass newSkiPass = new SkiPass(this.numSkiPasses, type, duration);
        this.numSkiPasses++;
        skiPassDatabase.add(newSkiPass);
        return newSkiPass;
    }
    
    /**
     * Create new Half Day Ski Pass
     * @param type
     * @param partOfDay
     * @return 
     */
    public SkiPass createSkiPass(SkiPassType type, HalfDay partOfDay) {
        SkiPass newSkiPass = new HalfDaySkiPass(this.numSkiPasses, type, partOfDay);
        this.numSkiPasses++;
        skiPassDatabase.add(newSkiPass);
        return newSkiPass;
    }
    
    /**
     * Verify SkiPass, update permission counters and SkiPass data
     * @param sp SkiPass to verify
     * @return true, if SkiPass is verified, false otherwise
     */
    public boolean verify(SkiPass sp) {
        
        //if Ski Pass is not registered in the database
        if(!this.registered(sp)) {
            numDenied++;
            return false;
        }
        
        //if Ski Pass is blocked, remove it from the database (if it is registered)
        else if(sp.isBlocked()) {
            if(this.registered(sp)) {
                this.removeFromDatabase(sp);
            }
            numDenied++;
            return false;
        }
        
        //try to make a trip with verified Ski Pass, update its data
        else if(!sp.makeTrip()) {
            numDenied++;
            return false;
        }
        
        //else SkiPass is verified and permission accepted
        else {
            numAllowed++;
            return true;
        }
    }
    
    /**
     * Remove Ski Pass from the database
     * @param sp SkiPass to remove
     */
    public void removeFromDatabase(SkiPass sp) {
        int position = this.bisectionSearch(sp.getID(), 0, this.skiPassDatabase.size());
        if(position<0) {
            throw new RuntimeException("Ski Pass is not in the database");
        }
        else {
            this.skiPassDatabase.remove(position);
        }
    }
    
    /**
     * Check if Ski Pass is registered in the database
     * @param sp    SkiPass to check
     * @return      True, if SkiPass is in the database, False otherwise
     */
    public boolean registered(SkiPass sp) {
        return bisectionSearch(sp.getID(), 0, this.skiPassDatabase.size()) >= 0;
    }
    
    //Helper for finding SkiPass in the database
    private int bisectionSearch(int ID, int minIndex, int maxIndex) {

        //possible position of desirable element 
        int midIndex = (minIndex + maxIndex) / 2;

        //if element was not found
        if(minIndex >= maxIndex) {
                return -1;
        }

        //if element is possibly in the left part of the List, drop the right part
        if(((SkiPass) this.skiPassDatabase.get(midIndex)).getID() > ID) {
                return bisectionSearch(ID, minIndex, midIndex);
        }

        //if element is possibly in the right part of the List, drop the left part
        else if(((SkiPass) this.skiPassDatabase.get(midIndex)).getID() < ID) {
                return bisectionSearch(ID, midIndex+1, maxIndex);		
        }

        //if none of the above, than element is found
        else {
                return midIndex;
        }
    }
    
    /**
     * Get info about trips from the database
     */
    public void getTripsInfo() {
        System.out.println("Number of allowed Trips: " + this.numAllowed);
        System.out.println("Number of denied Trips: " + this.numDenied);
    }
    
    /**
     * Get info about SkiPasses of certain type
     * @param type SkiPassType to search in the Database
     */
    public void getSkiPassInfo(SkiPassType type) {
        int numType = 0;
        for(int pos=0, length=this.skiPassDatabase.size(); pos<length; pos++) {
            SkiPass sp = (SkiPass)this.skiPassDatabase.get(pos);
            if(sp.getType() == type) {
                numType++;
                System.out.println("Ski Pass ID: " + sp.getID());
                System.out.println("Ski Pass Type: " + sp.getType());
                System.out.println("Ski Pass Duration: " + sp.getDuration());
                if(sp.getType() == SkiPassType.AMOUNT) {
                    System.out.println("Number of Trips: " + ((CountSkiPass)sp).getNumTrips());
                }
                if(sp.isActive()) {
                    System.out.println("Ski Pass DOA: " + sp.getDateOfActivation());
                    System.out.println("Ski Pass DOE: " + sp.getDateOfExpiration());
                }
            }
        }
        System.out.println("Total " + numType + " Ski Passes of type " + type);
    }
    
    /**
     * Get info about all Ski Passes in the Database
     */
    public void getSkiPassInfo() {
        for(SkiPassType type : SkiPassType.values()) {
            this.getSkiPassInfo(type);
        }
    }
}