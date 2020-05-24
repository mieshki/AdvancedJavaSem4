package pl.jazapp.app.webapp;

import pl.jazapp.app.webapp.register.RegisterRequest;


public class User {

    public User (RegisterRequest req){
        this.username = req.getUsername();
        this.email = req.getEmail();
        this.name = req.getName();
        this.surname = req.getSurname();
        this.birtdate = req.getBirthdate();
        this.password = req.getPassword();
    }

    private String username;
    private String email;
    private String name;
    private String surname;
    private String birtdate;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirtdate() {
        return birtdate;
    }

    public void setBirtdate(String birtdate) {
        this.birtdate = birtdate;
    }
}
