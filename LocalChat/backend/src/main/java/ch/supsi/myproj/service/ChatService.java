package ch.supsi.myproj.service;

import ch.supsi.myproj.model.Chat;
import ch.supsi.myproj.model.Contact;
import ch.supsi.myproj.model.Message;
import ch.supsi.myproj.repository.ChatRepository;
import ch.supsi.myproj.repository.UserRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ChatService {
    private final UserRepository userRepository = new UserRepository();
    private final ArrayList<ChatRepository> chats = new ArrayList<>();

    private void loadUserChats(String username) {
        chats.removeAll(chats);
        ArrayList<String> chatsID = userRepository.loadChatsID(new File(System.getProperty("user.home") + "/LocalChat/" + username + "/chats.json"));
        for (String id : chatsID)
            chats.add(new ChatRepository(id));
    }

    private void newChat(Contact receiver, Contact sender, Message message) {
        Chat chat = new Chat();
        chat.setId(UUID.randomUUID().toString());
        chat.setContact1(sender);
        chat.setContact2(receiver);
        chat.addMessage(message);
        ChatRepository chatRepository = new ChatRepository(chat);
        chatRepository.writeChat();
        chats.add(chatRepository);
        userRepository.writeChatsContacts(chat);
    }

    public ChatRepository getChat(String contact1, String contact2) {
        loadUserChats(contact1);
        for (ChatRepository cR : chats) {
            if ((cR.getChat().getContact1().getUsername().equals(contact2) ||
                    cR.getChat().getContact2().getUsername().equals(contact2)))
                return cR;
        }
        return new ChatRepository(new Chat());
    }

    public void newMessage(String receiver, String sender, String message) {
        if (message.equals("") || message.equals(" "))
            return;
        ArrayList<Contact> contacts = userRepository.loadContacts(new File(System.getProperty("user.home") + "/LocalChat/" + sender + "/contacts.json"));
        Message msg = new Message();
        Contact contactReceiver = new Contact();
        Contact contactSender = new Contact();
        contactReceiver.setUsername(receiver);
        contactSender.setUsername(sender);
        msg.setMessage(message);
        msg.setReceiver(contactReceiver);
        msg.setTime(LocalDateTime.now().toString());
        if (contacts.stream().noneMatch(c -> c.getUsername().equals(receiver)))
            newChat(contactReceiver, contactSender, msg);
        else {
            ChatRepository chatRepository = getChat(sender, receiver);
            chatRepository.getChat().addMessage(msg);
            chatRepository.writeChat();
        }
    }
}
