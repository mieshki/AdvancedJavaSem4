package pl.jazapp.app;

import pl.jazapp.app.users.UserEntity;
import pl.jazapp.app.users.UserSearchService;
import pl.jazapp.app.webapp.CookieHelper;
import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.UserContext;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebFilter("*")
public class SecurityFilter extends HttpFilter {
    @Inject
    UserSearchService userSearch;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // http://localhost:8080/app/resources/test.txt
        // let resources be possible to access without permissions
        if (isResourceRequest(req)){
            chain.doFilter(req, res);
        } else if(isSiteOnlyForAdministrator(req) && isAdministrator() && isUserLogged(req)) {
            chain.doFilter(req, res);
        } else if ((isSiteAllowed(req) || isUserLogged(req)) && !isSiteOnlyForAdministrator(req)){
            chain.doFilter(req, res);
        } else {
            if(isUserLogged(req)) {
                res.sendRedirect(req.getContextPath() + "/");
            }
            else {
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            }
        }
    }

    private boolean isAdministrator(){
        Optional<UserEntity> user = userSearch.findUser(UserContext.getId());
        if(user.isEmpty()){
            return false;
        }

        UserContext.setRole(user.get().getRole().trim());

        return UserContext.getRole().contains("ADMIN");
    }

    private boolean isSiteOnlyForAdministrator(HttpServletRequest req){
        if(req.getServletPath().contains("departments/edit.xhtml")){
            return true;
        } else if (req.getServletPath().contains("categories/edit.xhtml")){
            return true;
        }

        return false;
    }

    private boolean isResourceRequest(HttpServletRequest req) {
        return req.getServletPath().startsWith(ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        switch(req.getServletPath()){
            case "/login.xhtml":
            case "/register.xhtml":
            case "/index.xhtml":
            case "":
                return true;
            default:
                return false;
        }
    }

    public boolean isUserLogged(HttpServletRequest req) {
        if(!UserContext.isLogged())
            return false;

        Cookie[] cookies = req.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("MYSESSIONID")){
                    cookie.setMaxAge(600);
                    return true;
                }
            }
        }

        return false;
    }
}
