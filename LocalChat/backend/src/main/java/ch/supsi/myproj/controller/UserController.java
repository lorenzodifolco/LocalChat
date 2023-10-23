package ch.supsi.myproj.controller;

import ch.supsi.myproj.model.Contact;
import ch.supsi.myproj.model.User;
import ch.supsi.myproj.service.UserService;

import java.util.ArrayList;

public class UserController {
    private final UserService userService = new UserService();

    public boolean register(String username) {
        return userService.register(username);
    }

    public User getByUsername(String username) {
        return userService.getByUsername(username);
    }

    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    public ArrayList<Contact> getContacts(String username) {
        return userService.getContacts(username);
    }
}
