package ch.supsi.myproj.model;

import java.util.ArrayList;

public class Chat extends ModelObject{

    private String id;

    private Contact contact1;

    private Contact contact2;

    private ArrayList<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContact1(Contact contact1) {
        this.contact1 = contact1;
    }

    public void setContact2(Contact contact2) {
        this.contact2 = contact2;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Contact getContact1() {
        return contact1;
    }

    public Contact getContact2() {
        return contact2;
    }
}
