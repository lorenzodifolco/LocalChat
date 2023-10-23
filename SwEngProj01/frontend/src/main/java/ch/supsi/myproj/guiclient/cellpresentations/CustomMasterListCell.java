package ch.supsi.myproj.guiclient.cellpresentations;

import ch.supsi.myproj.model.Course;
import ch.supsi.myproj.objectpresentation.Presentable;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CustomMasterListCell extends ListCell<Course> {

    private final HBox content;
    private final Text name;
    private final Presentable<Course> presentation;

    public CustomMasterListCell(Presentable<Course> presentation) {
        super();
        name = new Text();
        content = new HBox();
        content.getChildren().add(name);
        content.setSpacing(10);
        this.presentation = presentation;
    }

    @Override
    protected void updateItem(Course item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            name.setText(presentation.present(item));
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
