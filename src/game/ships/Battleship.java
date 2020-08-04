package game.ships;

public class Battleship extends Ship {

    /**
     * This is constructor of Battleship
     */
    public Battleship(){
        length = 4;
        for (int i = 0; i < getLength(); i++){
            hit[i] = true;
        }
    }


    @Override
    public String getShipType(){
        return "battleship";
    }

}

