package ch.supsi.myproj.guiclient.actionhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HelpActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("LOCAL CHAT");
        alert.setHeaderText(
                "product name: Local_Chat\n" +
                        "version: 0.9\n" +
                        "team: Di Folco Lorenzo, Peluso Manuel"
        );

        alert.show();
    }

}