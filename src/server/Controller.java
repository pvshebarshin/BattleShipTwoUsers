package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import game.ocean.Ocean;

public class Controller {
    private boolean isGame = false;

    @FXML
    Button btn00 = new Button();
    @FXML
    TextField rowText = new TextField();
    @FXML
    TextField columnText = new TextField();
    @FXML
    private int _row;
    private int _column;
    private int row;
    private int column;
    String style = btn00.getStyle();
    private int currentDeadShips = 0;
    static Ocean ocean = new Ocean();

    @FXML
    TextArea messeges = new TextArea();
    @FXML
    private Label stat;
//    @FXML
    public void fillData() {
        ocean.placeAllShipsRandomly();
        stat.setText("Hits - 0\n" +
                "Shoots - 0\n" +
                "Dead ships - 0\n"+
                "Alive ships - 10");
        messeges.setText("Welcome to Battleship game!\n" +
                "To start the game without a mouse, click Control\n");
        columnText.setText("");
        columnText.requestFocus();
    }

    void ifEndOfGame(){
        if(ocean.isGameOver()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setHeaderText(null);
            alert.setContentText("You won!");
            alert.showAndWait();
        }
    }

    public void onClick(ActionEvent actionEvent) {
        if(ocean.isGameOver() || !isGame)
            return;
        Button clickedButton = ((Button) actionEvent.getSource());
        String data = clickedButton.getId();
        data = data.substring(3);
        row = Character.getNumericValue(data.charAt(0));
        column = Character.getNumericValue(data.charAt(1));
        if(!clickedButton.getStyle().equals(style)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No point shooting here");
            alert.showAndWait();
            return;
        }
        if(ocean.shootAt(row, column)){
            clickedButton.setStyle("-fx-background-color: red");
            messeges.appendText("(" + row + "," + column +") - Hit!!!\n");
            if(currentDeadShips < ocean.getShipsSunk()){
                currentDeadShips++;
                clickedButton.setStyle("-fx-background-color: black");
                messeges.appendText("You destroyed " + ocean.ships[row][column].getShipType()+'\n');
            }
        }else{
            clickedButton.setStyle("-fx-background-color: yellow");
            messeges.appendText("(" + row + "," + column +") - Miss...\n");
        }
        printStat();
        ifEndOfGame();
    }

    void printStat(){
        stat.setText("Hits - " + ocean.getHitCount() + "\n" +
                "Shoots - " + ocean.getShotsFired() + "\n" +
                "Dead ships - " + ocean.getShipsSunk() + "\n"+
                "Alive ships - " + (10 - ocean.getShipsSunk()));
    }

    public void onPress(ActionEvent actionEvent) {
        if(ocean.isGameOver() || !isGame)
            return;
        if(!checkText(rowText.getText()) || !checkText(columnText.getText()))
            return;
        int inputRow = Integer.parseInt(rowText.getText());
        int inputColumn = Integer.parseInt(columnText.getText());
        if(ocean.shootAt(inputRow, inputColumn)){
            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+inputRow+inputColumn);
            clickedButton.setStyle("-fx-background-color: red");
            messeges.appendText("(" + inputRow + "," + inputColumn +") - Hit!!!\n");
            if(currentDeadShips < ocean.getShipsSunk()){
                currentDeadShips++;
                clickedButton.setStyle("-fx-background-color: black");
                messeges.appendText("You destroyed " + ocean.ships[inputRow][inputColumn].getShipType()+'\n');
            }
        }else{
            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+inputRow+inputColumn);
            clickedButton.setStyle("-fx-background-color: yellow");
            messeges.appendText("(" + inputRow + "," + inputColumn +") - Miss...\n");
        }
        rowText.setText("");
        columnText.setText("");
        printStat();
        ifEndOfGame();
    }

    static boolean checkText(String str){
        try {
            int res;
            res = Integer.parseInt(str);
            if(res < 0 || res > 9) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("images/icon.png"));
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Number must be in segment [0;9]");
                alert.showAndWait();
                return false;
            }
            return true;
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You wrote incorrect data");
            alert.showAndWait();
            return false;
        }
    }

    public void onRowEnter(ActionEvent actionEvent) {
        if(ocean.isGameOver() || isGame)
            return;
        if(!checkText(rowText.getText()))
            return;
        _row = Integer.parseInt(rowText.getText());
        columnText.requestFocus();
    }

    public void onColumnClick(ActionEvent actionEvent) {
        if(ocean.isGameOver() || isGame)
            return;
        if(!checkText(columnText.getText()))
            return;
        _column = Integer.parseInt(columnText.getText());
        Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+_row+_column);
        if(!clickedButton.getStyle().equals(style)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/icon.png"));
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No point shooting here");
            alert.showAndWait();
            rowText.requestFocus();
            rowText.setText("");
            columnText.setText("");
            return;
        }
        rowText.requestFocus();


        if(ocean.shootAt(_row, _column)){
            clickedButton = (Button) Server.globalRoot.lookup("#btn"+_row+_column);
            clickedButton.setStyle("-fx-background-color: red");
            messeges.appendText("(" + _row + "," + _column +") - Hit!!!\n");
            if(currentDeadShips < ocean.getShipsSunk()){
                currentDeadShips++;
                clickedButton.setStyle("-fx-background-color: black");
                messeges.appendText("You destroyed " + ocean.ships[_row][_column].getShipType()+'\n');
            }
        }else{
            clickedButton = (Button) Server.globalRoot.lookup("#btn"+_row+_column);
            clickedButton.setStyle("-fx-background-color: yellow");
            messeges.appendText("(" + _row + "," + _column +") - Miss...\n");
        }
        rowText.setText("");
        columnText.setText("");
        printStat();
        ifEndOfGame();
    }

    public void press(KeyEvent keyEvent) {
        rowText.requestFocus();
    }

    public void onClickOnMyGrount(ActionEvent actionEvent) {
    }
}


