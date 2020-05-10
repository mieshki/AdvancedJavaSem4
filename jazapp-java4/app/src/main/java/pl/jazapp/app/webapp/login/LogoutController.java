package pl.jazapp.app.webapp.login;

import pl.jazapp.app.webapp.CookieHelper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Named
@RequestScoped
public class LogoutController {
    public String logout() throws IOException {
        CookieHelper.deleteCookie("MYSESSIONID");

        return "/login.xhtml?faces-redirect=true";
    }
}