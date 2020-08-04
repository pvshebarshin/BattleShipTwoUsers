package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Server extends Application {
    public static boolean readyToPlay = false;
    public static boolean okToFire = false;
    public static Parent globalRoot;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartServer.fxml"));
        globalRoot = root;
        primaryStage.getIcons().add(new Image("images/icon.png"));
        primaryStage.setTitle("BattleShip");
        primaryStage.setScene(new Scene(root, 266, 140));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
