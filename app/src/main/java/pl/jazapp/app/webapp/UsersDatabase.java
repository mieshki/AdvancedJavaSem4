package pl.jazapp.app.webapp;

import pl.jazapp.app.webapp.login.LoginRequest;
import pl.jazapp.app.webapp.register.RegisterRequest;

import java.util.ArrayList;
import java.util.List;

public class UsersDatabase {
    private static ArrayList<User> allUsers = new ArrayList<User>(){};

    public static boolean addNewUser(RegisterRequest registerRequest) {
        User newUser = new User(registerRequest);

        // check if username is already taken
        for (User user : allUsers){
            if(user.getUsername().equals(newUser.getUsername()))
                return false;
        }

        allUsers.add(newUser);
        return true;
    }

    public static User findUserByLogin(String login)
    {
        for (User user : allUsers){
            if(user.getUsername().equals(login))
                return user;
        }
        return null;
    }

    public static boolean checkCredentials(LoginRequest loginRequest){
        for (User user : allUsers){
            return user.getUsername().equals(loginRequest.getUsername()) && user.getPassword().equals(loginRequest.getPassword());
        }

        return true;
    }
}
