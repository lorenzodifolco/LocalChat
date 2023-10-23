package ch.supsi.myproj.guiclient.cellpresentations;

import ch.supsi.myproj.model.Student;
import ch.supsi.myproj.objectpresentation.Presentable;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CustomDetailListCell extends ListCell<Student> {
    private final HBox content;
    private final Text name;
    private final Presentable<Student> presentation;

    public CustomDetailListCell(Presentable<Student> presentation) {
        super();
        name = new Text();
        content = new HBox();
        content.getChildren().add(name);
        content.setSpacing(10);
        this.presentation = presentation;
    }

    @Override
    protected void updateItem(Student item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            name.setText(presentation.present(item));
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }

}
