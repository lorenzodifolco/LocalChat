package ch.supsi.myproj.model;

public class User extends ModelObject {
    private String username;

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username.toUpperCase();
    }

    public String getUsername() {
        return username;
    }


}
