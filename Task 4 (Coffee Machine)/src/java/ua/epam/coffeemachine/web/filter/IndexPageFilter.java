
package ua.epam.coffeemachine.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Temporary Filter for the index page
 * @author Yuriy Miedviediev
 * @version 1.0 Build 04.06.2014
 */
@WebFilter(filterName = "IndexPageFilter", urlPatterns = {"/"})
public class IndexPageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).sendRedirect("pages/login");
    }

    @Override
    public void destroy() {
    }
    
   
}
