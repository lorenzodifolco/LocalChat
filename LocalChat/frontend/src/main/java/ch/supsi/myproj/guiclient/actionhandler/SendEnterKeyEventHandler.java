package ch.supsi.myproj.guiclient.actionhandler;

import ch.supsi.myproj.guiclient.ApplicationFX;
import ch.supsi.myproj.model.Message;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.LocalDateTime;

import static ch.supsi.myproj.guiclient.ApplicationFX.*;

public class SendEnterKeyEventHandler implements EventHandler<KeyEvent> {
    ApplicationFX app = new ApplicationFX();

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String messageText = newMessage.getText();
            chatController.newMessage(receiver.getUsername(), sender.getUsername(), messageText);
            Message m = new Message();
            m.setReceiver(receiver);
            m.setMessage(messageText);
            m.setTime(LocalDateTime.now().toString());
            app.showMessage(m);
            newMessage.clear();
        }
    }
}
