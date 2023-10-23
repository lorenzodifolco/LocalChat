package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.Chat;

import ch.supsi.myproj.model.Contact;

import ch.supsi.myproj.model.User;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;

import java.util.ArrayList;

public class UserRepository {
    private static ArrayList<User> users;
    private static final File file = new File(System.getProperty("user.home") + "/LocalChat/users.json");

    public UserRepository() {
        users = loadUsers();
    }

    public  User getByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void writeUser(User user) {
        users.add(user);
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!file.exists())
                file.createNewFile();
            mapper.writeValue(file, users);
            Files.createDirectories(Path.of(System.getProperty("user.home") + "/LocalChat/" + user.getUsername() + "/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> loadChatsID(File chatID) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (chatID.exists())
                return mapper.readValue(chatID, new TypeReference<>() {
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<Contact> loadContacts(File contacts) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (contacts.exists())
                return mapper.readValue(contacts, new TypeReference<>() {
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void writeChatsContacts(Chat chat) {
        File chatID1 = new File(System.getProperty("user.home") + "/LocalChat/" + chat.getContact1().getUsername() + "/chats.json");
        File chatID2 = new File(System.getProperty("user.home") + "/LocalChat/" + chat.getContact2().getUsername() + "/chats.json");
        File contacts1 = new File(System.getProperty("user.home") + "/LocalChat/" + chat.getContact1().getUsername() + "/contacts.json");
        File contacts2 = new File(System.getProperty("user.home") + "/LocalChat/" + chat.getContact2().getUsername() + "/contacts.json");

        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<String> tempChats = loadChatsID(chatID1);
            if (tempChats.stream().noneMatch(s -> s.equals(chat.getId()))) {
                tempChats.add(chat.getId());
                if (!chatID1.exists())
                    chatID1.createNewFile();
                mapper.writeValue(chatID1, tempChats);
            }
            tempChats = loadChatsID(chatID2);
            if (tempChats.stream().noneMatch(s -> s.equals(chat.getId()))) {
                tempChats.add(chat.getId());
                if (!chatID2.exists())
                    chatID2.createNewFile();
                mapper.writeValue(chatID2, tempChats);
            }
            ArrayList<Contact> tempContacts = loadContacts(contacts1);
            if (tempContacts.stream().noneMatch(contact -> contact.getUsername().equals(chat.getContact2().getUsername()))) {
                tempContacts.add(chat.getContact2());
                if (!contacts1.exists())
                    contacts1.createNewFile();
                mapper.writeValue(contacts1, tempContacts);
            }
            tempContacts = loadContacts(contacts2);
            if (tempContacts.stream().noneMatch(contact -> contact.getUsername().equals(chat.getContact1().getUsername()))) {
                tempContacts.add(chat.getContact1());
                if (!contacts2.exists())
                    contacts2.createNewFile();
                mapper.writeValue(contacts2, tempContacts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<User> loadUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!Files.exists(Path.of(System.getProperty("user.home") + "/LocalChat/")))
                Files.createDirectories(Path.of(System.getProperty("user.home") + "/LocalChat/"));
            if (file.exists())
                return mapper.readValue(file, new TypeReference<>() {
                });
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}