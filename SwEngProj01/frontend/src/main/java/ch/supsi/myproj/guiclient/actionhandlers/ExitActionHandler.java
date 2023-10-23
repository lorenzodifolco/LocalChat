package ch.supsi.myproj.guiclient.actionhandlers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ExitActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit...");
        alert.setHeaderText("Are you sure?");

        Optional<ButtonType> res = alert.showAndWait();
        if (res.get().equals(ButtonType.OK)) {
            System.exit(1);
        }
    }

}