package pl.jazapp.app.webapp.login;

import pl.jazapp.app.webapp.CookieHelper;
import pl.jazapp.app.webapp.HttpResponseHelper;
import pl.jazapp.app.webapp.UsersDatabase;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Named
@RequestScoped
public class LoginController {
    public String login(LoginRequest loginRequest) throws IOException {
        System.out.println(String.format("Tried to login with username %s and password %s", loginRequest.getUsername(), loginRequest.getPassword()));

        boolean canLogin = UsersDatabase.checkCredentials(loginRequest);

        if (canLogin) {
            CookieHelper.setCookie("MYSESSIONID", UUID.randomUUID().toString(), 600);

            return "/index.xhtml";
        } else {
            //FacesContext.getCurrentInstance().getExternalContext().getFlash()
            //        .put("error-message", "Wrong username or password.");
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Wrong username or password.");
            return "/login.xhtml?faces-redirect=true";
        }
    }
}