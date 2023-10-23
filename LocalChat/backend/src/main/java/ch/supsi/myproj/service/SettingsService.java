package ch.supsi.myproj.service;

import ch.supsi.myproj.model.Language;
import ch.supsi.myproj.model.User;
import ch.supsi.myproj.repository.SettingsRepository;

public class SettingsService {
    private final SettingsRepository settingsRepository;

    public SettingsService() {
        settingsRepository = new SettingsRepository();
    }

    public void loadSetting(User user) {
        settingsRepository.loadSettings(user);
    }

    public void writeSettings(User user, Language language) {
        settingsRepository.writeSettings(user, language);
    }

    public Language getLanguage() {
        return settingsRepository.getLanguage();
    }

    public void setLanguage(User user, Language language) {
        settingsRepository.setLanguage(language);
        settingsRepository.writeSettings(user, language);
    }
}

