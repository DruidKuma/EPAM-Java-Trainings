
package ua.epam.lab.skipass;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.epam.lab.skipass.types.Duration;
import ua.epam.lab.skipass.types.HalfDay;
import ua.epam.lab.skipass.types.NumTrips;
import ua.epam.lab.skipass.types.SkiPassType;

/**
 * Test Suit for Ski Resort System
 * @author DruidKuma
 */
public class SkiPassSystemTest {
    
    SkiPassSystem database = new SkiPassSystem();
    Wicket wicket = new Wicket(database);

    @Test
    public void CreateSkiPassProperlyCreatesAndRegisterSkiPasses() {
        SkiPass testSP = database.createSkiPass(NumTrips.TEN);
        assertEquals("Ski Pass System does not properly sets ID of SkiPass", 0, testSP.getID());
        assertTrue("Ski Pass System does not register SkiPass in the database", database.registered(testSP));
        assertFalse("Ski Pass is activated before having used", testSP.isActive());   
    }

    @Test
    public void SkiPassSystemProperlyEnlargeIDs() {
        SkiPass fakeSP = database.createSkiPass(NumTrips.FIFTY);
        SkiPass testSP = database.createSkiPass(NumTrips.FIFTY);
        assertEquals("Ski Pass System does not properly enlarge ID", 1, testSP.getID());
    }

    @Test
    public void SkiPassSystemCorrectlyChecksRegistrationOfTheSkiPasses() {
        SkiPass testSP = new HalfDaySkiPass(0, SkiPassType.WORK, HalfDay.first);
        assertFalse("Ski Pass System incorrectly verify registration in the database", database.registered(testSP));
        testSP = database.createSkiPass(SkiPassType.WORK, HalfDay.second);
        assertTrue("Ski Pass System does not register new Ski Passes in the database", database.registered(testSP));
    }

    @Test
    public void SkiPassForDayMakesTripCorrectlyInfo() {
        SkiPass testSP = database.createSkiPass(SkiPassType.WORK, Duration.DAY);
        assertTrue("Wicket does not correctly verify Ski Pass", wicket.verifySkiPass(testSP));
        assertTrue("Ski Pass is not activated after having used", testSP.makeTrip());
    }
    
    @Test
    public void SkiPassesForDayActivatesCorrectly() {
        SkiPass testSP = new SkiPass(0,SkiPassType.WORK, Duration.DAY);
        assertFalse("Ski Pass is activated before using", testSP.isActive());
        assertFalse("Ski Pass is blocked before using", testSP.isBlocked());
        testSP.activate();
        assertTrue("SkiPass is not activated after calling activate method", testSP.isActive());
        long today = new Date().getTime();
        assertTrue("Activate does not correctly set date of activation", today==testSP.getDateOfActivation().getTime());
        assertTrue("Activate does not correctly set date of expiration", today+Duration.DAY.duration==testSP.getDateOfExpiration().getTime());
    }
    
}
