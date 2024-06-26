package pl.jazapp.app.webapp.login;

import pl.jazapp.app.users.UserCreatorService;
import pl.jazapp.app.users.UserLoginService;
import pl.jazapp.app.users.UserSearchService;
import pl.jazapp.app.webapp.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private UserLoginService userLogin;

    public String login(LoginRequest loginRequest) throws IOException {
        System.out.println(String.format("Tried to login with username %s and password %s", loginRequest.getUsername(), loginRequest.getPassword()));

        boolean canLogin = userLogin.canLoginUser(loginRequest);

        if (canLogin) {
            CookieHelper.setCookie("MYSESSIONID", UUID.randomUUID().toString(), 600);

            var user = userLogin.findUser(loginRequest.getUsername()).get();

            String welcomeString = userLogin.getWelcomeString(loginRequest);

            UserContext.setLogged(true);
            UserContext.setFullName(welcomeString);
            UserContext.setId(user.getId());
            UserContext.setRole(user.getRole().trim());

            return "/index.xhtml?faces-redirect=true";
        } else {
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Wrong username or password.");
            return "/login.xhtml?faces-redirect=true";
        }
    }
}