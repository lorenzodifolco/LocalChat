package ch.supsi.myproj.model;

import java.time.LocalDateTime;

public class Message extends ModelObject{
    private String message;
    private LocalDateTime time;
    private Contact receiver = new Contact();

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = LocalDateTime.parse(time);
    }

    public void setReceiver(Contact receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time.toString();
    }

    public Contact getReceiver() {
        return receiver;
    }
}