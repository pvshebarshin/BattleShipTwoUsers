package support;

import game.ocean.Ocean;
import game.support.ShipsCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipsCreatorTest {

    @Test
    void putBattleship() {
        Ocean ocean = new Ocean();
        ShipsCreator.putBattleship(ocean);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getHitCount() == 4);
        assert (ocean.getShipsSunk() == 1);
        assert (ocean.isGameOver());
    }

    @Test
    void putCruiser() {
        Ocean ocean = new Ocean();
        ShipsCreator.putCruiser(ocean);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getHitCount() == 6);
        assert (ocean.getShipsSunk() == 2);
        assert (ocean.isGameOver());
    }

    @Test
    void putDestroyer() {
        Ocean ocean = new Ocean();
        ShipsCreator.putDestroyer(ocean);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getHitCount() == 6);
        assert (ocean.getShipsSunk() == 3);
        assert (ocean.isGameOver());
    }

    @Test
    void putSubmarine() {
        Ocean ocean = new Ocean();
        ShipsCreator.putSubmarine(ocean);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assert (ocean.getHitCount() == 4);
        assert (ocean.getShipsSunk() == 4);
        assert (ocean.isGameOver());
    }
}