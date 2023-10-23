package ch.supsi.myproj.controller;

import ch.supsi.myproj.model.Language;
import ch.supsi.myproj.model.User;
import ch.supsi.myproj.service.SettingsService;

public class SettingsController {
    private final SettingsService settingsService;


    public SettingsController() {
        settingsService = new SettingsService();
    }

    public void writeSettings(User user, Language language) {
        settingsService.writeSettings(user, language);
    }


    public void loadSetting (User user) {
        settingsService.loadSetting(user);
    }

    public Language getLanguage() {
        return settingsService.getLanguage();
    }

    public void setLanguage(User user, Language language) {
        settingsService.setLanguage(user, language);
    }
}
