
package ua.epam.coffeemachine.util;

import java.util.ResourceBundle;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Language Initializer for the Coffee Machine System
 * @author Yuriy Miedviediev
 * @version 1.0 Build 04.06.2014
 */
public class InitializeLanguage implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ResourceBundle rb = ResourceBundle.getBundle("lang_en");
        sce.getServletContext().setAttribute("lang", rb); 
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
