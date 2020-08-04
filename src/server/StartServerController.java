package server;

import game.support.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServerController {
    @FXML
    TextField serverPortText = new TextField();

    public static int serverPort;
    public void OnClick(ActionEvent actionEvent) {
        try {
            if(serverPortText.getText().equals(""))
                throw new IllegalArgumentException("You must input port!");
            serverPort = Integer.parseInt(serverPortText.getText());
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
            Game.serverSocket = new ServerSocket(serverPort);
            Game.socket = Game.serverSocket.accept();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Stage primStage = (Stage) serverPortText.getScene().getWindow();
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
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            return;
        }
    }
}
