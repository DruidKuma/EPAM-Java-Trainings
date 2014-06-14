
package ua.epam.lab.skipass.types;

import java.util.concurrent.TimeUnit;
import static ua.epam.lab.skipass.SkiPassSystem.BEGINSEASON;
import static ua.epam.lab.skipass.SkiPassSystem.ENDSEASON;

/**
 * Duration of the Ski Pass (in milliseconds) <br>
 * HALFDAY -> for 4 hours
 * DAY -> for 1 day
 * TWODAYS -> for 2 days
 * FIVEDAYS -> for 5 days
 * FORSEASON -> for the whole season (01.04.2014 - 01.06.2014)
 * @author DruidKuma
 */
public enum Duration {
    HALFDAY(TimeUnit.MILLISECONDS.convert(4, TimeUnit.HOURS)),
    DAY(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)), 
    TWODAYS(TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)),
    FIVEDAYS(TimeUnit.MILLISECONDS.convert(5, TimeUnit.DAYS)),
    FORSEASON(ENDSEASON - BEGINSEASON);

    public long duration;

    Duration(long d) {
        duration = d;
    }
}
