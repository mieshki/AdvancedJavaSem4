package pl.jazapp.app.webapp.register;

import pl.jazapp.app.users.UserCreatorService;
import pl.jazapp.app.users.UserSearchService;
import pl.jazapp.app.webapp.HttpResponseHelper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterController {

    @Inject
    private UserCreatorService userCreator;
    @Inject
    private UserSearchService userSearch;

    public String register(RegisterRequest registerRequest) throws IOException {
        System.out.println(String.format("Tried to register with username %s and password %s\n",
                registerRequest.getUsername(),
                registerRequest.getPassword()));

        if(!registerRequest.getPassword().equals(registerRequest.getPasswordRepeated())){
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Passwords do not match.");
            return "/register.xhtml";
        }

        var user = userSearch.findUser(registerRequest.getUsername());
        if(!user.isPresent()) {
            userCreator.createUser(registerRequest);
            return "/login.xhtml?faces-redirect=true";
        }
        else {
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Username is already taken.");
            return "/register.xhtml";
        }
    }
}
