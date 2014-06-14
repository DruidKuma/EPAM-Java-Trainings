
package ua.epam.lab.skipass;

/**
 * Turnstile for Ski Resort System
 * @author DruidKuma
 */
public class Wicket {
    
    //Ski Pass System for wicket to be connected to
    public SkiPassSystem system;
    
    //Wicket must connect to a certain system in the moment of creation
    public Wicket(SkiPassSystem s) {
        //connect system to the wicket
        this.system = s;
        
        //activate the system if not already activated
        this.system.getDatabase();
    }
    
    /**
     * Verify existing Ski Pass
     * @param sp Ski Pass for verification
     * @return true, if SkiPass is verified, false otherwise
     */
    public boolean verifySkiPass(SkiPass sp) {
        return system.verify(sp);
    }
}
