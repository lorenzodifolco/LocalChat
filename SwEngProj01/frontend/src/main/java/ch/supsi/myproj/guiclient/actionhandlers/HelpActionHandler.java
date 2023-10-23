package ch.supsi.myproj.guiclient.actionhandlers;

import ch.supsi.myproj.utils.AppInfoLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.Properties;

public class HelpActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Properties appInfo = AppInfoLoader.getAppInfo();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The force");
        alert.setHeaderText(
                "version: " + appInfo.getProperty("project.version") +
                "\nbuild: " + appInfo.getProperty("project.build") + " (UTC)"
        );

        alert.show();
    }

}
