package pl.jazapp.app;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class ExampleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (isUserLogged(req) || isSiteAllowed(req)){
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
            //res.getWriter().println("qwdwqdwqd");
        }

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
        Cookie[] cookies = req.getCookies();

        for(Cookie cookie : cookies){
            if (cookie.getName().equals("JSESSIONID"))
                return true;
        }

        return false;
    }
}
