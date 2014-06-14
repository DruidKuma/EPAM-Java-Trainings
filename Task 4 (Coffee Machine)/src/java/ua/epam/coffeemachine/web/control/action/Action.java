
package ua.epam.coffeemachine.web.control.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;

/**
 * General interface for all actions (requests)
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public interface Action {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response, ConnectionFactory dao)
            throws ServletException, IOException;
}
