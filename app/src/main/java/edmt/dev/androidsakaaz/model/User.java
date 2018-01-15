package edmt.dev.androidsakaaz.model;

/**
 * Created by 18kabeerh1 on 19/10/2017.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;

    public User(String name) {
        Name = name;
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User() {
    }
}
