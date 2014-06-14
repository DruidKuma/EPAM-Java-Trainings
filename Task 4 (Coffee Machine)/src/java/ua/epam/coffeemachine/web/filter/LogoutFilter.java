/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.apache.log4j.Logger;
import ua.epam.coffeemachine.domain.User;

/**
 * Filter for logout action
 * @author Yuriy Miedviediev
 * @version 1.0 Build 04.06.2014
 */
@WebFilter(filterName = "LogoutFilter", urlPatterns = {"/pages/logout"})
public class LogoutFilter implements Filter {
    
    //Log4j logger
    public static final Logger log = Logger.getLogger(LogoutFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String login = ((User)req.getSession().getAttribute("user")).getLogin();
        log.info("User " + login + " has logged out");
        req.getSession().invalidate();
        ((HttpServletResponse) response).sendRedirect("login");
    }

    @Override
    public void destroy() {
    }
}
