package ch.supsi.myproj.guiclient;

import ch.supsi.myproj.guiclient.actionhandlers.ExitActionHandler;
import ch.supsi.myproj.guiclient.actionhandlers.HelpActionHandler;
import ch.supsi.myproj.guiclient.cellpresentations.CustomDetailListCell;
import ch.supsi.myproj.guiclient.cellpresentations.CustomMasterListCell;
import ch.supsi.myproj.model.Course;
import ch.supsi.myproj.model.Student;
import ch.supsi.myproj.objectpresentation.CourseUpperCasePresentation;
import ch.supsi.myproj.objectpresentation.StudentCapsPresentation;
import ch.supsi.myproj.service.StudentsCoursesService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ApplicationFX extends Application {

    private final ListView<Course> listViewMaster;
    private final ListView<Student> listViewDetail;

    public ApplicationFX() {
        StudentsCoursesService studentsCoursesService = new StudentsCoursesService();

        // courses list view
        ObservableList<Course> observableCourses = FXCollections.observableArrayList();
        observableCourses.addAll(studentsCoursesService.getCourses());

        listViewMaster = new ListView<>();
        listViewMaster.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> listView) {
                return new CustomMasterListCell(new CourseUpperCasePresentation());
            }
        });

        listViewMaster.setPrefSize(200,200);
        listViewMaster.setItems(observableCourses);

        // students list view
        ObservableList<Student> observableStudents = FXCollections.observableArrayList();

        listViewDetail = new ListView<>();
        listViewDetail.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> listView) {
                return new CustomDetailListCell(new StudentCapsPresentation());
            }
        });

        listViewDetail.setPrefSize(200,200);
        listViewDetail.setItems(observableStudents);

        // course change listener
        listViewMaster.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                Course course = (Course)observableValue.getValue();
                observableStudents.clear();
                observableStudents.addAll(studentsCoursesService.getStudentsByCourseId(course.getId()));
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("may this app be with you");

        // menu bar
        Menu menuFile = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new ExitActionHandler());
        menuFile.getItems().add(exitItem);

        Menu menuHelp = new Menu( "Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(new HelpActionHandler());
        menuHelp.getItems().add(aboutItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuHelp);
        menuBar.setUseSystemMenuBar(true);

        // master-detail panel
        FlowPane masterDetailPane = new FlowPane();
        masterDetailPane.getChildren().add(listViewMaster);
        masterDetailPane.getChildren().add(listViewDetail);

        // main box panel
        VBox mainBox = new VBox();
        mainBox.getChildren().add(menuBar);
        mainBox.getChildren().addAll(masterDetailPane);

        // scene
        Scene scene = new Scene(mainBox, 400,200);
        stage.setScene(scene);

        // show
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
