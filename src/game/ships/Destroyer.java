package game.ships;

public class Destroyer extends Ship {

    /**
     * This is constructor of Destroyer
     */
    public  Destroyer(){
        length = 2;
        for (int i = 0; i < getLength(); i++){
            hit[i] = true;
        }
    }

    @Override
    public String getShipType(){
        return "destroyer";
    }

}
