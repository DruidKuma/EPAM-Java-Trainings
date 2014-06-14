
package ua.epam.coffeemachine.web.filter;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for changing languages of the Web-Service
 * @author Yuriy Miedviediev
 * @version 1.0 Build 04.06.2014
 */
@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/pages/changelang"})
public class LanguageFilter implements Filter {
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        //Set wanted language to the session
        ResourceBundle rb = ResourceBundle.getBundle("lang_"+request.getParameter("language"));
        req.getSession().setAttribute("lang", rb);

        //redirect to the previous page
        ((HttpServletResponse)response).sendRedirect(req.getHeader("referer"));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
