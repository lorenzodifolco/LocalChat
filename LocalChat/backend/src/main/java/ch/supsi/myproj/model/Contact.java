package ch.supsi.myproj.model;

public class Contact extends ModelObject {
    private String username;

    public Contact() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return getUsername().toUpperCase();
    }
}