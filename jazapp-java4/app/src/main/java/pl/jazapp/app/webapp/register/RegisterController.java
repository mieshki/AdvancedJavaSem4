package pl.jazapp.app.webapp.register;

import pl.jazapp.app.webapp.UsersDatabase;
import pl.jazapp.app.webapp.login.HttpResponseHelper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterController {

    public String register(RegisterRequest registerRequest) throws IOException {
        System.out.println(String.format("Tried to register with username %s and password %s\n",
                registerRequest.getUsername(),
                registerRequest.getPassword()));

        if(!registerRequest.getPassword().equals(registerRequest.getPasswordRepeated())){
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Passwords do not match.");
            return "/register.xhtml";
        }

        if(UsersDatabase.addNewUser(registerRequest))
            return "/login.xhtml?faces-redirect=true";
        else{
            HttpResponseHelper.setResponse(FacesContext.getCurrentInstance(), "Username is already taken.");
            return "/register.xhtml";
        }

    }
}
