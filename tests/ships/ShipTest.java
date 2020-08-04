package ships;

import game.ocean.Ocean;
import game.ships.*;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @org.junit.jupiter.api.Test
    void getLength() {
        Ship b = new Battleship();
        Ship c = new Cruiser();
        Ship d = new Destroyer();
        Ship s = new Submarine();
        assert (b.getLength() == 4);
        assert (c.getLength() == 3);
        assert (d.getLength() == 2);
        assert (s.getLength() == 1);
    }

    @org.junit.jupiter.api.Test
    void getBowRow() {
        Ship ship = new Ship();
        ship.setBowRow(4);
        assert (ship.getBowRow() == 4);
    }

    @org.junit.jupiter.api.Test
    void getBowColumn() {
        Ship ship = new Ship();
        ship.setBowColumn(4);
        assert (ship.getBowColumn() == 4);
    }

    @org.junit.jupiter.api.Test
    void isHorizontal() {
        Ship ship = new Ship();
        ship.setHorizontal(true);
        assert (ship.isHorizontal());
    }

    @org.junit.jupiter.api.Test
    void setBowRow() {
        Ship ship = new Ship();
        ship.setBowRow(4);
        assert (ship.getBowRow() == 4);
    }

    @org.junit.jupiter.api.Test
    void setBowColumn() {
        Ship ship = new Ship();
        ship.setBowColumn(4);
        assert (ship.getBowColumn() == 4);
    }

    @org.junit.jupiter.api.Test
    void setHorizontal() {
        Ship ship = new Ship();
        ship.setHorizontal(true);
        assert (ship.isHorizontal());
    }

    @org.junit.jupiter.api.Test
    void okToPlaceShipAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        assert (ship.okToPlaceShipAt(1,1,true, ocean));
        assert (!ship.okToPlaceShipAt(9,9,true, ocean));
    }

    @org.junit.jupiter.api.Test
    void placeShipAt() {
        Ocean ocean = new Ocean();
        Ship battleship = new Battleship();
        battleship.placeShipAt(1, 2, true, ocean);
        assert (battleship.getBowRow() == 1);
        assert (battleship.getBowColumn() == 2);
        assert (battleship.isHorizontal());
    }

    @org.junit.jupiter.api.Test
    void shootAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Submarine();
        ship.placeShipAt(1, 1, true, ocean);
        assert (!ship.isSunk());
        ship.shootAt(1,1, ocean);
        assert (ship.isSunk());
    }

    @org.junit.jupiter.api.Test
    void isSunk() {
        Ocean ocean = new Ocean();
        Ship ship = new Submarine();
        ship.placeShipAt(1, 1, true, ocean);
        assert (!ship.isSunk());
        ship.shootAt(1,1, ocean);
        assert (ship.isSunk());
    }

    @org.junit.jupiter.api.Test
    void getShipType() {
        Ship b = new Battleship();
        Ship c = new Cruiser();
        Ship d = new Destroyer();
        Ship s = new Submarine();
        Ship e = new EmptySea();
        assert (b.getShipType().equals("battleship"));
        assert (c.getShipType().equals("cruiser"));
        assert (d.getShipType().equals("destroyer"));
        assert (s.getShipType().equals("submarine"));
        assert (e.getShipType().equals("emptysea"));
    }

}