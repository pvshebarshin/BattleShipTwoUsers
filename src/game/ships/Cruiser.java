package game.ships;

public class Cruiser extends Ship {

    /**
     * This is constructor of Cruiser
     */
    public Cruiser(){
        length = 3;
        for (int i = 0; i < getLength(); i++){
            hit[i] = true;
        }
    }

    @Override
    public String getShipType(){
        return "cruiser";
    }
}

