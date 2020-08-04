package game.ships;

public class EmptySea extends Ship {

    /**
     * This is constructor of EmptySea
     */
    public EmptySea(){
        length = 1;
        for (int i = 0; i < getLength(); i++){
            hit[i] = true;
        }
    }

    @Override
    public String toString(){
        if(isSunk()) {
            return "*";
        } else {
            return "-";
        }
    }

    @Override
    public String getShipType() { return "emptysea"; }

}
