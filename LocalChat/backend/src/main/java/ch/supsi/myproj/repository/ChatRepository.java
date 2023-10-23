package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.Chat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatRepository {
    private final Chat chat;

    public ChatRepository(String id) {
        chat = loadChat(id);
    }

    public ChatRepository(Chat chat) {
        this.chat = chat;
    }

    public Chat loadChat(String id) {
        File file = new File(System.getProperty("user.home") + "/LocalChat/chats/" + id + ".json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Chat getChat() {
        return chat;
    }

    public void writeChat() {
        File file = new File(System.getProperty("user.home")+ "/LocalChat/chats/" + chat.getId() + ".json");
        try {
            Files.createDirectories(Path.of(System.getProperty("user.home") + "/LocalChat/chats/"));
            ObjectMapper mapper = new ObjectMapper();
            if (!file.exists())
                file.createNewFile();
            mapper.writeValue(file, chat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
