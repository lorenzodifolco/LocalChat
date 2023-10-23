package ch.supsi.myproj.guiclient.actionhandler;


import ch.supsi.myproj.guiclient.ApplicationFX;
import ch.supsi.myproj.model.User;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import static ch.supsi.myproj.guiclient.ApplicationFX.*;

public class LoginActionHandler implements EventHandler<ActionEvent> {

    ApplicationFX app = new ApplicationFX();
    @Override
    public void handle(ActionEvent actionEvent) {
        String userName = usernameTextField.getText();
        User user = userController.getByUsername(userName);
        if (user == null) {
            usernameTextField.clear();
            usernameTextField.setPromptText(resourceBundle.getString("utenteNoExist"));
        } else
            stage.setScene(app.chatScene(user));
    }
}
