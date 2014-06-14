
package ua.epam.coffeemachine.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ua.epam.coffeemachine.repository.mysql.MySQLRepFactory;

/**
 * Manager of Connection Pool (currently BoneCP)
 * @author Yuriy Miedviediev
 * @version 1.0 Build 03.06.2014
 */
public final class ConnectionPoolManager implements ServletContextListener {
 
    /**
     * Takes place when application is just deployed
     * @param sce Servlet Context event
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MySQLRepFactory.startConPool();
    }
 
    /**
     * Takes place when application is going to shut down
     * @param sce Servlet Context event
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MySQLRepFactory.destroyConPool();
    }
 
}
