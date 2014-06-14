
package ua.epam.coffeemachine.web.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import ua.epam.coffeemachine.domain.User;
import ua.epam.coffeemachine.domain.User.Role;

/**
 * Tag for accessing the navigation links
 * @author Yuriy Miedviediev
 * @version 1.0 Build 05.06.2014
 */
public class LinkAccessTag extends SimpleTagSupport {
    private boolean anon = false;
    private boolean admin = false;
    private boolean user = false;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        User currentUser = (User) getJspContext().findAttribute("user");
        if(anon && currentUser==null) {
            getJspBody().invoke(null);
        }
        if(user && currentUser!=null) {
            getJspBody().invoke(null);
        }
        if(admin && currentUser!=null && currentUser.getRole()==Role.ADMIN) {
            getJspBody().invoke(null);
        }
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
    
}
