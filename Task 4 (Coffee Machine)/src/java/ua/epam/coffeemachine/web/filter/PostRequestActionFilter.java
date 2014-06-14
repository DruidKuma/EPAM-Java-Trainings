
package ua.epam.coffeemachine.web.filter;

import java.io.IOException;
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
 * Filter for changing language on Action pages with default POST request method
 * @author Yuriy Miedviediev
 * @version 1.0 Build 04.06.2014
 */
@WebFilter(filterName = "PostRequestActionFilter", urlPatterns = {"/pages/buy", "/pages/confirm", "/pages/coffee"})
public class PostRequestActionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {       
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getMethod().equals("GET")) {
            ((HttpServletResponse)response).sendRedirect("login");
        }
        else chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
  
}
