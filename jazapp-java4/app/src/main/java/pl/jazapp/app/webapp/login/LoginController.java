package pl.jazapp.app.webapp.login;

import pl.jazapp.app.webapp.CookieHelper;
import pl.jazapp.app.webapp.UsersDatabase;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.UUID;

@Named
@RequestScoped
public class LoginController {
    public String login(LoginRequest loginRequest) {
        System.out.println(String.format("Tried to login with username %s and password %s", loginRequest.getUsername(), loginRequest.getPassword()));

        boolean canLogin = UsersDatabase.checkCredentials(loginRequest);

        if(canLogin){
            CookieHelper.setCookie("MYSESSIONID", UUID.randomUUID().toString(), 3600);
            return "/index.xhtml?faces-redirect=true";
        }
        else{
            return "/login.xhtml";
        }

    }
}
