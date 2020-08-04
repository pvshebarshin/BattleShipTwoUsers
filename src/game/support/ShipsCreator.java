package game.support;

import game.ocean.Ocean;
import game.ships.Battleship;
import game.ships.Cruiser;
import game.ships.Destroyer;
import game.ships.Submarine;

import java.util.Random;

/**
 * This is support class.
 * It helps to put ships in ocean
 */
public class ShipsCreator {
    private static Random random = new Random();

    /**
     * This method puts Battleship
     * @param ocean ocean there method will put Battleship
     */
    public static void putBattleship(Ocean ocean){
        Battleship battleship = new Battleship();
        int row, column;
        boolean horizontal;
        boolean checkbattleship;
        do{
            row = (int)(Math.random() * 10);
            column = (int)(Math.random() * 10);
            horizontal = random.nextBoolean();
            checkbattleship = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
            if(checkbattleship){
                battleship.placeShipAt(row, column, horizontal, ocean);
            }
        } while (!checkbattleship);
    }

    /**
     * This method puts Cruisers
     * @param ocean ocean there method will put Cruisers
     */
    public static void putCruiser(Ocean ocean){
        Cruiser cruiser;
        int row, column;
        boolean horizontal;
        boolean checkcruiser;
        for(int i = 0; i < 2; i++){
            cruiser = new Cruiser();
            do{
                row = (int)(Math.random() * 10);
                column = (int)(Math.random() * 10);
                horizontal = random.nextBoolean();
                checkcruiser = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
                if(checkcruiser){
                    cruiser.placeShipAt(row, column, horizontal, ocean);
                }
            } while (!checkcruiser);
        }
    }

    /**
     * This method puts Destroyers
     * @param ocean ocean there method will put Destroyers
     */
    public static void putDestroyer(Ocean ocean){
        Destroyer destroyer;
        int row, column;
        boolean horizontal;
        boolean checkdestroyer;
        for(int i = 0; i < 3; i++){
            destroyer = new Destroyer();
            do{
                row = (int)(Math.random() * 10);
                column = (int)(Math.random() * 10);
                horizontal = random.nextBoolean();
                checkdestroyer = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
                if(checkdestroyer){
                    destroyer.placeShipAt(row, column, horizontal, ocean);
                }
            } while (!checkdestroyer);
        }
    }

    /**
     * This method puts Submarines
     * @param ocean ocean there method will put Submarines
     */
    public static void putSubmarine(Ocean ocean){
        Submarine submarine;
        int row, column;
        boolean horizontal;
        boolean checksubmarine;
        for(int i = 0; i < 4; i++){
            submarine = new Submarine();
            do{
                row = (int)(Math.random() * 10);
                column = (int)(Math.random() * 10);
                horizontal = random.nextBoolean();
                checksubmarine = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
                if(checksubmarine){
                    submarine.placeShipAt(row, column, horizontal, ocean);
                }
            } while (!checksubmarine);
        }
    }
}
