package client;

import game.support.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.Controller;

import java.io.IOException;
import java.net.Socket;

public class StartController {
    @FXML
    TextField portText;
    @FXML
    TextField hostText;

    public static int port;
    public static String host;

    public void OnClick(ActionEvent actionEvent) {
        try {
            if(portText.getText().equals(""))
                throw new IllegalArgumentException("You must input port!");
            port = Integer.parseInt(portText.getText());
            host = hostText.getText();
        } catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You wrote incorrect data of port");
            alert.showAndWait();
            return;
        }
        try {
            Game.socket = new Socket(host, port);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../server/sample.fxml"));
            Stage primStage = (Stage) portText.getScene().getWindow();
            primStage.setScene(new Scene(loader.load(), 950, 551));
            Controller controller = loader.getController();
            controller.fillData();
            primStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Some problems with server");
            alert.showAndWait();
            return;
        }
    }
}
