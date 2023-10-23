package ch.supsi.myproj.guiclient.actionhandler;

import ch.supsi.myproj.guiclient.ApplicationFX;
import ch.supsi.myproj.model.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ch.supsi.myproj.guiclient.ApplicationFX.*;


public class SendButtonActionHandler implements EventHandler<ActionEvent> {
    ApplicationFX app = new ApplicationFX();

    @Override
    public void handle(ActionEvent actionEvent) {

        String messageText = newMessage.getText();
        chatController.newMessage(receiver.getUsername(), sender.getUsername(), messageText);
        //chat.getChildren().add(messageText);
        Message m = new Message();
        m.setReceiver(receiver);
        m.setMessage(messageText);
        m.setTime(LocalDateTime.now().toString());
        app.showMessage(m);
        newMessage.clear();

    }

}
