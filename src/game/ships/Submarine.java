package game.ships;

public class Submarine extends Ship {

    /**
     * This is constructor of Submarine
     */
    public  Submarine(){
        length = 1;
        for (int i = 0; i < getLength(); i++){
            hit[i] = true;
        }
    }

    @Override
    public String getShipType() { return "submarine"; }
}
