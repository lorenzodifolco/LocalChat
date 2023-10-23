package ch.supsi.myproj.repository;

import ch.supsi.myproj.model.Language;
import ch.supsi.myproj.model.Settings;
import ch.supsi.myproj.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SettingsRepository {
    private  Settings settings;

    public SettingsRepository() {
        this.settings = new Settings();
        settings.setLanguage(Language.ENGLISH);
    }

    public void loadSettings(User user) {
        try {
            File file = new File(System.getProperty("user.home") + "/LocalChat/" + user.getUsername() + "/settings.json");
            ObjectMapper mapper = new ObjectMapper();
            settings = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            settings.setLanguage(Language.ENGLISH);
        }
    }

    public void writeSettings(User user,Language language) {
        settings.setLanguage(language);
        File file = new File(System.getProperty("user.home") + "/LocalChat/" + user.getUsername() + "/settings.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!file.exists())
                file.createNewFile();
            mapper.writeValue(file, settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Language getLanguage() {
        return settings.getLanguage();
    }

    public void setLanguage(Language language) {
        settings.setLanguage(language);
    }
}
