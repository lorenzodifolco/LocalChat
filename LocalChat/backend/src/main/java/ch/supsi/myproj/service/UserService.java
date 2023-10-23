package ch.supsi.myproj.service;

import ch.supsi.myproj.model.Contact;
import ch.supsi.myproj.model.Language;
import ch.supsi.myproj.model.User;
import ch.supsi.myproj.repository.SettingsRepository;
import ch.supsi.myproj.repository.UserRepository;

import java.io.File;
import java.util.ArrayList;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public ArrayList<Contact> getContacts(String username) {
        return userRepository.loadContacts(new File(System.getProperty("user.home") + "/LocalChat/" + username + "/contacts.json"));
    }

    public boolean register(String username) {
        User user = new User();
        user.setUsername(username);
        if (getUsers().stream().map(User::getUsername).anyMatch(u -> u.equals(username)) || username.equals("") || username.equals(" "))
            return false;
        userRepository.writeUser(user);
        SettingsRepository sr = new SettingsRepository();
        sr.writeSettings(user, Language.ENGLISH);
        return true;
    }


}
