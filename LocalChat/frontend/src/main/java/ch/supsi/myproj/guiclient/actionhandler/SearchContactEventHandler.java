package ch.supsi.myproj.guiclient.actionhandler;

import ch.supsi.myproj.model.Contact;
import ch.supsi.myproj.model.User;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static ch.supsi.myproj.guiclient.ApplicationFX.*;

public class SearchContactEventHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username = search.getText();
            User user = userController.getByUsername(username);
            if (user != null) {
                Contact contact = new Contact();
                contact.setUsername(username);
                contacts.getItems().add(contact);
            } else {
                search.clear();
                search.setPromptText(resourceBundle.getString("utenteNoFound"));
            }
        }
    }
}
