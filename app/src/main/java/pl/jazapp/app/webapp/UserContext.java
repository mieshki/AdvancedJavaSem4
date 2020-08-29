package pl.jazapp.app.webapp;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserContext implements Serializable {
    private static Long id = 0L;
    private static String role = "DEFAULT";

    private static String fullName;

    private static boolean isLogged = false;

    public String getFullName()
    {
        return fullName;
    }
    public static void setFullName(String newFullName){
        fullName = newFullName;
    }
    //public static User actualUser;
    //public static void setActualUser()

    public static Long getId() {
        return id;
    }

    public static void setId(Long idNew) {
        id = idNew;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String roleNew) {
        role = roleNew;
    }


    public static boolean isLogged() {
        return isLogged;
    }

    public static void setLogged(boolean logged) {
        isLogged = logged;
    }

}
