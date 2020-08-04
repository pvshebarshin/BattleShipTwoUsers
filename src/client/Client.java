package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.PrintStream;

public class Client extends Application {
    public static boolean readyToPlay = false;
    public static boolean okToFire = true;
    public static Parent clientRoot;
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent_root = FXMLLoader.load(getClass().getResource("start.fxml"));
        clientRoot = parent_root;
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setTitle("BattleShip");
        stage.setScene(new Scene(parent_root, 295, 182));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
