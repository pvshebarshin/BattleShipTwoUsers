package ocean;

import game.ocean.Ocean;
import game.ships.Ship;
import game.ships.Submarine;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    @Test
    void isOccupied() {
        Ocean ocean = new Ocean();
        Ship ship = new Submarine();
        ship.placeShipAt(1, 1 , true, ocean);
        assert (ocean.isOccupied(1,1));
        assert (!ocean.isOccupied(2,1));
    }

    @Test
    void shootAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Submarine();
        ship.placeShipAt(1, 1 , true, ocean);
        assert (!ship.isSunk());
        ocean.shootAt(1,1);
        assert (ship.isSunk());
    }

    @Test
    void getShotsFired() {
        Ocean ocean = new Ocean();
        assert (ocean.getShotsFired() == 0);
        for(int i = 0; i < 10; i++){
            ocean.shootAt(1,i);
        }
        assert (ocean.getShotsFired() == 10);
    }

    @Test
    void getHitCount() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        assert (ocean.getHitCount() == 0);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getHitCount() == 20);
    }

    @Test
    void getShipsSunk() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        assert (ocean.getShipsSunk() == 0);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getShipsSunk() == 10);
    }

    @Test
    void isGameOver() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        assert (!ocean.isGameOver());
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.isGameOver());
    }
}