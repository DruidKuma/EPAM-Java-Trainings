/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.epam.lab.skipass;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static ua.epam.lab.skipass.SkiPassSystem.BEGINSEASON;
import ua.epam.lab.skipass.types.Duration;
import ua.epam.lab.skipass.types.HalfDay;
import ua.epam.lab.skipass.types.SkiPassType;

/**
 * SkiPass for Half of The Day (either 9:00-13:00 or 13:00-17:00)
 * @author DruidKuma
 */
public class HalfDaySkiPass extends SkiPass {
    
    private final HalfDay part;
    
    public HalfDaySkiPass(int ID, SkiPassType type, HalfDay partOfDay) {
        super(ID, type, Duration.HALFDAY);
        part = partOfDay;
    }
    
    /**
     * Activate the SkiPass <br>
     * Called only once with first usage of the SkiPass
     */
    @Override
    public void activate() {
        
        //Calendar for tracking the correct date and time
        Calendar c = new GregorianCalendar();
        
        //Set proper Date
        if(new Date().getTime() <= BEGINSEASON) {
            c.setTime(new Date(BEGINSEASON));
        }
        else {
            c.setTime(new Date());
        }
        
        //Correct the Date depending on the duration (first half or second half)
        //Setting DOA and DOE
        c.set(Calendar.HOUR_OF_DAY, part.beginTime);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        this.dateOfActivation = c.getTime();
        c.add(Calendar.HOUR_OF_DAY, 4);
        this.dateOfExpiration = c.getTime();
        
        this.active = true;   
    }
}
