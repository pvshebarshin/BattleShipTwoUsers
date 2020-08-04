package game.ships;

import javafx.scene.control.Button;
import game.ocean.Ocean;
import server.Server;

/**
 * This is the parent class of all type of ships
 */
public class Ship {
    private int bowRow;
    private int bowColumn;
    protected int length;
    private boolean horizontal;
    boolean[] hit = new boolean[4];
    private int checkindex = 0;

    /**
     * This method returns length of the ship
     * @return length of the ship
     */
    public int getLength(){
        return length;
    }

    /**
     * This method returns the row of ship's bow
     * @return row of ship's bow
     */
    public int getBowRow(){
        return bowRow;
    }

    /**
     * This method returns the column of ship's bow
     * @return column of ship's bow
     */
    public int getBowColumn(){
        return bowColumn;
    }

    /**
     * This method checks is the ship stand horizontally or not
     * @return is the ship stand horizontally or not
     */
    public boolean isHorizontal(){
        return horizontal;
    }

    /**
     * This method sets the row of ship's bow
     * @param row the row of ship's bow
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * This method sets the column of ship's bow
     * @param column the column of ship's bow
     */
    public void setBowColumn(int column){
        this.bowColumn = column;
    }

    /**
     * This method set the spatial orientation of ship
     * @param horizontal the spatial orientation of ship
     */
    public void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }

    /**
     * This method checks is it possible to put the ship in
     * ocean with this argument of bow row, bow column,
     * spatial orientation
     * @param row row of the ship's bow
     * @param column column of the ship's bow
     * @param horizontal ship's spatial orientation
     * @param ocean game ocean
     * @return possibility of putting the ship
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if(horizontal && 9 - column >= getLength() - 1){
            for (int i = column - 1; i <= column + getLength() + 1; i++){
                for(int j = row - 1; j <= row + 1; j++){
                    if(i <= 9 && i >= 0 && j >= 0 && j <= 9){
                        if (ocean.isOccupied(j, i)){
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (!horizontal && 9 - row >= getLength() - 1){
            for (int i = row - 1; i <= row + getLength() + 1; i++){
                for(int j = column - 1; j <= column + 1; j++){
                    if(i <= 9 && i >= 0 && j >= 0 && j <= 9){
                        if (ocean.isOccupied(i, j)){
                            return false;
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method puts ship in argument's place
     * @param row row of the ship's bow
     * @param column column of the ship's bow
     * @param horizontal ship's spatial orientation
     * @param ocean game ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);
        for(int i = 0; i < getLength(); i++){
            hit[i] = true;
            if(isHorizontal()){
                ocean.ships[row][column + i] = this;
            } else {
                ocean.ships[row + i][column] = this;
            }
        }
    }

    /**
     * This method checks is player hits the ship or not
     * @param row row of shooting
     * @param column column of shooting
     * @param ocean game ocean
     * @return is player hits the ship or not
     */
    public boolean shootAt(int row, int column, Ocean ocean) {
        if(ocean.ships[row][column].getShipType().equals("emptysea")){
            this.hit[0] = false;
            return false;
        }
        if(isHorizontal()){
            if(row == getBowRow()){
                int d = column - getBowColumn();
                if(d >= 0 && d < getLength()){
                    hit[d] = false;
                    ocean.hitCount++;
                    if(isSunk()){
                        ocean.deadships++;
                        for(int i = 0; i < getLength(); i++) {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+getBowRow()+(getBowColumn() + i));
                            clickedButton.setStyle("-fx-background-color:black");
                        }
                        for(int i = -1; i < getLength() + 1; i++){
                            try{
                                Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()+1)+(getBowColumn() + i));
                                clickedButton.setStyle("-fx-background-color:yellow");
                            }catch (Exception ex){ }
                            try{
                                Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()-1)+(getBowColumn() + i));
                                clickedButton.setStyle("-fx-background-color:yellow");
                            }catch (Exception ex){ }
                        }
                        try {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+getBowRow()+(getBowColumn()-1));
                            clickedButton.setStyle("-fx-background-color:yellow");
                        }catch (Exception ex){}
                        try {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+getBowRow()+(getBowColumn() + getLength()));
                            clickedButton.setStyle("-fx-background-color:yellow");
                        }catch (Exception ex){}
//                        System.out.printf("You destroyed %s\n", getShipType());
                    }
                    return true;
                }
            }
            return false;
        } else {
            if(column == getBowColumn()){
                int d = row - getBowRow();
                if(d >= 0 && d < getLength()){
                    hit[d] = false;
                    ocean.hitCount++;
                    if(isSunk()){
                        ocean.deadships++;
                        for(int i = 0; i < getLength(); i++) {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()+i)+getBowColumn());
                            clickedButton.setStyle("-fx-background-color:black");
                        }
                        for(int i = -1; i < getLength() + 1; i++){
                            try{
                                Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()+ i)+(getBowColumn()+1 ));
                                clickedButton.setStyle("-fx-background-color:yellow");
                            }catch (Exception ex){ }
                            try{
                                Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow() + i)+(getBowColumn()-1));
                                clickedButton.setStyle("-fx-background-color:yellow");
                            }catch (Exception ex){ }
                        }
                        try {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()-1)+getBowColumn());
                            clickedButton.setStyle("-fx-background-color:yellow");
                        }catch (Exception ex){}
                        try {
                            Button clickedButton = (Button) Server.globalRoot.lookup("#btn"+(getBowRow()+ getLength())+getBowColumn());
                            clickedButton.setStyle("-fx-background-color:yellow");
                        }catch (Exception ex){}
//                        System.out.printf("You destroyed %s\n", getShipType());
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * This method checks is the ship alive or not
     * @return is the ship alive or not
     */
    public boolean isSunk(){
        for (int i = 0; i < getLength(); i++){
            if(hit[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns type of the ship
     * @return returns type of the ship
     */
    public String getShipType() {
        return "";
    }

    /**
     * This method turns the ships into string
     * @return ship witch is turned to string
     */
    public String toString() {
        if(isSunk()) {
            return "Х";
        } else {
            if(hit[checkindex]){
                if(checkindex == getLength() - 1){
                    checkindex = 0;
                } else {
                    checkindex++;
                }
                return "-";
            } else {
                if(checkindex == getLength() - 1){
                    checkindex = 0;
                } else {
                    checkindex++;
                }
                return "S";
            }

        }
    }
}
