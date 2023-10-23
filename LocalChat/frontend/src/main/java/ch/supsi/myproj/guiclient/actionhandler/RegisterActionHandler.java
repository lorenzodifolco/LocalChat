package ch.supsi.myproj.guiclient.actionhandler;

import ch.supsi.myproj.guiclient.ApplicationFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static ch.supsi.myproj.guiclient.ApplicationFX.*;

public class RegisterActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        String userName = usernameTextField.getText();
        if (!userController.register(userName)) {
            usernameTextField.clear();
            usernameTextField.setPromptText(resourceBundle.getString("registerError"));
        }
    }
}
