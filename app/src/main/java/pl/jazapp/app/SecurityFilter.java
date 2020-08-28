package pl.jazapp.app;

import pl.jazapp.app.webapp.CookieHelper;

import javax.faces.application.ResourceHandler;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter("*")
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // http://localhost:8080/app/resources/test.txt
        // let resources be possible to access without permissions
        if (isResourceRequest(req)){
            chain.doFilter(req, res);
        } else if (isSiteAllowed(req) || isUserLogged(req)){
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        }
    }

    private boolean isResourceRequest(HttpServletRequest req) {
        return req.getServletPath().startsWith(ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        switch(req.getServletPath()){
            case "/login.xhtml":
            case "/register.xhtml":
                return true;
            default:
                return false;
        }
    }

    public boolean isUserLogged(HttpServletRequest req) {
        /*Cookie[] cookies = req.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("MYSESSIONID")){
                    cookie.setMaxAge(600);
                    return true;
                }
            }
        }

        return false;*/
        return true;
    }
}
