package game.ocean;

import game.ships.EmptySea;
import game.ships.Ship;
import game.support.ShipsCreator;

/**
 * This class describes ocean there you will shoot on ships
 */
public class Ocean {
    public Ship[][] ships;
    public int shotsFired;
    public int hitCount;
    public int deadships;

    /**
     * This is the constructor of Ocean object
     */
    public Ocean(){
        shotsFired = 0;
        hitCount = 0;
        deadships = 0;
        ships = new Ship[10][10];
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ships[i][j] = new EmptySea();
            }
        }
    }

    /**
     * This method puts ships randomly in ocean
     */
    public void placeAllShipsRandomly(){
        ShipsCreator.putBattleship(this);
        ShipsCreator.putCruiser(this);
        ShipsCreator.putDestroyer(this);
        ShipsCreator.putSubmarine(this);
    }

    /**
     * This method checks is aria in ocean free or not
     * @param row row of ocean aria
     * @param column column of ocean aria
     * @return is it real to put ship here or not
     */
    public boolean isOccupied(int row, int column){
        return !ships[row][column].getShipType().equals("emptysea");
    }

    /**
     * This method checks is player hit a ship or not
     * @param row row of ocean aria
     * @param column column of ocean aria
     * @return is player hit a ship or not
     */
    public boolean shootAt(int row, int column){
        shotsFired++;
        return !ships[row][column].isSunk() &&
                ships[row][column].shootAt(row, column, this) &&
                !ships[row][column].getShipType().equals("emptysea");
    }

    /**
     * This method returns count of player shoots
     * @return count of player shoots
     */
    public int getShotsFired(){
        return shotsFired;
    }

    /**
     * This method returns count of player hits
     * @return count of player hits
     */
    public int getHitCount(){
        return hitCount;
    }

    /**
     * This method returns count of died ships
     * @return count of died ships
     */
    public int getShipsSunk(){
        return deadships;
    }

    /**
     * This method checks is the game ends or not
     * @return is the game ends or not
     */
    public boolean isGameOver(){
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(!ships[i][j].isSunk() && !ships[i][j].getShipType().equals("emptysea")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method prints that is going on ocean
     */
    public void print(){
        String str = "  ";
        for (int i = 0; i < 10; i++){
            str += i + " ";
        }
        System.out.println(str);
        str = "";
        for (int i = 0; i < 10; i++){
            str += i + " ";
            for (int j = 0; j < 10; j++){
                str += ships[i][j].toString() + " ";
            }
            if (i != 9)
                str += "\n";
        }
        System.out.println(str);
    }

}
