package ch.supsi.myproj.guiclient.actionhandler;

import ch.supsi.myproj.guiclient.ApplicationFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Optional;

public class LogOutActionHandler implements EventHandler<ActionEvent> {
    ApplicationFX app = new ApplicationFX();

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out...");
        alert.setHeaderText(ApplicationFX.resourceBundle.getString("are_you_sure"));
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get().equals(ButtonType.OK)) {
            ApplicationFX.contacts = new ListView<>();
            ApplicationFX.chat.getChildren().clear();
            ApplicationFX.nameReceiver.setText("");
            ApplicationFX.stage.setScene(app.loginPage());
        }
    }
}
