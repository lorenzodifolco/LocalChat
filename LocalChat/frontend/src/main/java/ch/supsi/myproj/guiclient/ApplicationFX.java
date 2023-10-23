package ch.supsi.myproj.guiclient;

import ch.supsi.myproj.controller.ChatController;
import ch.supsi.myproj.controller.SettingsController;
import ch.supsi.myproj.controller.UserController;
import ch.supsi.myproj.guiclient.actionhandler.*;
import ch.supsi.myproj.guiclient.graphicelement.Bubble;
import ch.supsi.myproj.model.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jfr.SettingControl;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


public class ApplicationFX extends Application {

    public static Locale currentLanguage = new Locale("en");
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("Language", currentLanguage);

    public static SettingsController settingsController = new SettingsController();
    public static UserController userController = new UserController();
    public static ChatController chatController = new ChatController();

    public static Stage stage;

    public static final int APP_WIDTH = 600;
    public static final int APP_HEIGHT = 800;
    public static final int CHAT_WIDTH = 400;
    public static final int CHAT_HEIGHT = 625;
    public static final int TOPLABEL_HEIGHT = 100;
    public static Label contactLabel;
    public static Label nameReceiver;
    public static ListView<Contact> contacts;
    public static VBox chat = new VBox();
    public static ScrollPane scrollingChat;
    public static TextField newMessage;
    public static TextField search;
    public static Label searchIcon;
    public static HBox searchBar;
    public static TextField usernameTextField;
    public static Button send;
    public static Contact receiver;
    public static Contact sender;

    public ApplicationFX() {
        contactLabel = new Label();
        nameReceiver = new Label();
        contacts = new ListView<>();
        scrollingChat = new ScrollPane(chat);
        search = new TextField();
        searchIcon = new Label();
        searchBar = new HBox();
        usernameTextField = new TextField();
        send = new Button();


        //TEXT FIELD
        usernameTextField.setMinSize(200, 50);
        usernameTextField.setPrefSize(400, 50);
        usernameTextField.setMaxSize(450, 300);

        //TOPLABEL SETTINGS
        contactLabel.setPrefSize(APP_WIDTH - CHAT_WIDTH, TOPLABEL_HEIGHT);
        contactLabel.setMaxSize(APP_WIDTH - CHAT_WIDTH, TOPLABEL_HEIGHT);
        contactLabel.setAlignment(Pos.CENTER);
        contactLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        contactLabel.setTextFill(Color.WHITE);
        contactLabel.setFont(Font.font("Arial", 20));
        contactLabel.setText(resourceBundle.getString("contact"));

        nameReceiver.setPrefSize(CHAT_WIDTH, TOPLABEL_HEIGHT);
        nameReceiver.setMaxSize(CHAT_WIDTH, TOPLABEL_HEIGHT);
        nameReceiver.setAlignment(Pos.CENTER);
        nameReceiver.setBackground(new Background(new BackgroundFill(Color.rgb(255, 100, 100), CornerRadii.EMPTY, Insets.EMPTY)));
        nameReceiver.setTextFill(Color.WHITE);
        nameReceiver.setFont(Font.font("Arial", 20));


        contacts.setPrefSize(APP_WIDTH - CHAT_WIDTH, CHAT_HEIGHT);
        contacts.setMaxSize(APP_WIDTH - CHAT_WIDTH, CHAT_HEIGHT);
        contacts.setFixedCellSize(75);

        chat.setPrefSize(CHAT_WIDTH - 20, CHAT_HEIGHT);
        chat.setMaxSize(CHAT_WIDTH - 20, CHAT_HEIGHT);
        chat.setAlignment(Pos.TOP_CENTER);
        chat.setSpacing(5);
        Image sfondo = new Image("/images/sfondochat.jpg");
        chat.setBackground(new Background(new BackgroundImage(sfondo, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        scrollingChat.setPrefSize(CHAT_WIDTH, CHAT_HEIGHT);
        scrollingChat.setMaxSize(CHAT_WIDTH, CHAT_HEIGHT);
        search.setPromptText(resourceBundle.getString("search"));
        search.setPrefSize(APP_WIDTH - CHAT_WIDTH - 50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        search.setMaxSize(APP_WIDTH - CHAT_WIDTH - 50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        searchIcon.setPrefSize(50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        searchIcon.setMaxSize(50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        Image searchImg = new Image("/images/searchicon.png");
        ImageView searchView = new ImageView(searchImg);
        searchView.setFitHeight(35);
        searchView.setFitWidth(35);
        searchView.setPreserveRatio(true);
        searchIcon.setGraphic(searchView);
        searchIcon.setAlignment(Pos.CENTER);
        searchBar.setPrefSize(APP_WIDTH - CHAT_WIDTH, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        searchBar.setMaxSize(APP_WIDTH - CHAT_WIDTH, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        Image sendIcon = new Image("/images/sendicon.png");
        ImageView sendView = new ImageView(sendIcon);
        sendView.setFitWidth(45);
        sendView.setFitHeight(45);
        send.setPrefSize(50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        send.setMaxSize(50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        send.setGraphic(sendView);


    }

    public Scene loginPage() {
        Text localChat = new Text("Local Chat");
        localChat.setUnderline(true);
        localChat.setStyle("-fx-font: 35 arial");
        Text username = new Text("Username");


        //LOGIN BUTTON
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(150, 75);
        loginButton.setOnAction(new LoginActionHandler());

        //REGISTER BUTTON
        Button registerButton = new Button("Register");
        registerButton.setPrefSize(150, 75);
        registerButton.setOnAction(new RegisterActionHandler());

        HBox buttons = new HBox(50, loginButton, registerButton);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        VBox vbox = new VBox(50, localChat, username, usernameTextField, buttons);
        vbox.setAlignment(Pos.CENTER);

        return new Scene(vbox);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle(resourceBundle.getString("title"));
        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);

        Scene loginScene = loginPage();
        stage.setScene(loginScene);
        stage.show();
    }


    public Scene chatScene(User user) {
        newMessage = new TextField();
        newMessage.setPrefSize(CHAT_WIDTH - 50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);
        newMessage.setMaxSize(CHAT_WIDTH - 50, APP_HEIGHT - CHAT_HEIGHT - TOPLABEL_HEIGHT);

        settingsController.loadSetting(user);
        currentLanguage = new Locale(settingsController.getLanguage().getEn());
        resourceBundle = ResourceBundle.getBundle("Language", currentLanguage);
        MenuBar menuBar = menuBar(user);
        contactList(user);
        searchBar.getChildren().clear();
        search.setOnKeyPressed(new SearchContactEventHandler());
        searchBar.getChildren().addAll(search, searchIcon);
        VBox left = new VBox(contactLabel, contacts, searchBar);


        HBox message = new HBox(newMessage, send);
        VBox right = new VBox(nameReceiver, scrollingChat, message);
        HBox body = new HBox(left, right);
        VBox vBox = new VBox(menuBar, body);

        return new Scene(vBox);
    }

    public MenuBar menuBar(User user) {
        Menu menuFile = new Menu("File");
        MenuItem logout = new MenuItem(resourceBundle.getString("logout"));
        logout.setOnAction(new LogOutActionHandler());
        menuFile.getItems().add(logout);

        Menu menuEdit = new Menu(resourceBundle.getString("edit"));
        Menu preferences = new Menu(resourceBundle.getString("preferences"));
        MenuItem italiano = new MenuItem("italiano");
        italiano.setOnAction(event -> {
            settingsController.writeSettings(user, Language.ITALIANO);
            contacts = new ListView<>();
            chat.getChildren().clear();
            nameReceiver.setText("");
            stage.setScene(chatScene(user));
        });
        MenuItem english = new MenuItem("english");
        english.setOnAction(event -> {
            settingsController.writeSettings(user, Language.ENGLISH);
            contacts = new ListView<>();
            chat.getChildren().clear();
            nameReceiver.setText("");
            stage.setScene(chatScene(user));
        });
        preferences.getItems().add(italiano);
        preferences.getItems().add(english);
        menuEdit.getItems().add(preferences);

        Menu menuHelp = new Menu(resourceBundle.getString("help"));
        MenuItem about = new MenuItem("About");
        about.setOnAction(new HelpActionHandler());
        menuHelp.getItems().add(about);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuHelp);
        menuBar.setUseSystemMenuBar(true);
        return menuBar;
    }

    public void contactList(User user) {
        ArrayList<Contact> contacts1 = userController.getContacts(user.getUsername());
        for (Contact c : contacts1)
            contacts.getItems().add(c);
        sender = new Contact();
        sender.setUsername(user.getUsername());
        contacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                receiver = (Contact) observableValue.getValue();
                nameReceiver.setText(receiver.toString());
                Chat chatPrivata = chatController.getChat(sender.getUsername(), receiver.getUsername());
                chat.getChildren().clear();
                for (Message m : chatPrivata.getMessages()) {
                    showMessage(m);
                }
                send.setOnAction(new SendButtonActionHandler());
                newMessage.setOnKeyPressed(new SendEnterKeyEventHandler());
            }
        });

    }

    public void showMessage(Message m) {
        HBox messageLine = new HBox();
        if (m.getReceiver().getUsername().equals(sender.getUsername())) {
            messageLine.getChildren().add(new Bubble(m.getMessage(), m.getTime().substring(11, 16), Color.rgb(255, 0, 0)));
            messageLine.setAlignment(Pos.BASELINE_LEFT);
        } else {
            messageLine.getChildren().add(new Bubble(m.getMessage(), m.getTime().substring(11, 16), Color.rgb(255, 82, 98)));
            messageLine.setAlignment(Pos.BASELINE_RIGHT);
        }
        chat.getChildren().add(messageLine);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
