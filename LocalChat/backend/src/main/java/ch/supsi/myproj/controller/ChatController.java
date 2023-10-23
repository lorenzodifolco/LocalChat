package ch.supsi.myproj.controller;

import ch.supsi.myproj.model.Chat;
import ch.supsi.myproj.service.ChatService;

public class ChatController {
    private final ChatService chatService = new ChatService();

    public Chat getChat(String contact1, String contact2) {
        return chatService.getChat(contact1,contact2).getChat();
    }

    public void newMessage(String receiver, String sender, String message) {
        chatService.newMessage(receiver, sender, message);
    }
}
