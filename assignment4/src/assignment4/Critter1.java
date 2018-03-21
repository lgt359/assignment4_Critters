package assignment4;

import java.util.*;

public class Critter1 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);

        // up down left right
        if(random%2 != 0){
            random--;
        }
        super.walk(random);
    }

    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("@")){
            return true;
        }

        if (!hasMoved) {
            if (getEnergy() > 50) return true;

            else {
                walk(getRandomInt(8));
                return false;
            }
        }
        else {
            if (getEnergy() > 50) return true;

            else {
                this.setEnergy(this.getEnergy() - Params.walk_energy_cost);
                return false;
            }
        }
    }

    public String toString() {
        return "1";
    }

    public void test (List<Critter> l) {

    }
}
