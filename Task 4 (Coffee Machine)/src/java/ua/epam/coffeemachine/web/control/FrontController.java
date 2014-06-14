
package ua.epam.coffeemachine.web.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.coffeemachine.repository.mysql.ConnectionFactory;
import ua.epam.coffeemachine.repository.mysql.MySQLRepFactory;
import ua.epam.coffeemachine.web.control.action.Action;
import ua.epam.coffeemachine.web.control.action.ActionFactory;

/**
 * Main Controller For All Pages
 * @author Yuriy Miedviediev
 * @version 1.0 Build 01.06.2014
 */
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ConnectionFactory dao = new MySQLRepFactory();
        Action action = ActionFactory.getAction(request);
        String view = action.handleRequest(request, response, dao);
        dispatch(request, response, view);
    }
    
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String view)
            throws ServletException, IOException {
        String path = "/WEB-INF/views/";
        String extension = ".jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path + view + extension);
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
