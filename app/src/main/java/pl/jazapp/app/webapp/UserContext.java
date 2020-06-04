package pl.jazapp.app.webapp;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserContext implements Serializable {
    private static String fullName;
    public String getFullName()
    {
        return fullName;
    }
    public static void setFullName(String newFullName){
        fullName = newFullName;
    }
}
