package assignment4;

import java.util.*;

public class Critter2 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);

        //diagnols
        if(random%2 == 0){
            random++;
        }
        super.walk(random);
    }

    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("@")){
            return true;
        }

        /*if (!hasMoved) {
            if (getEnergy() > 50) return true;

            else {
                this.runAway(0);
                return false;
            }
        }
        else {
            if (getEnergy() > 50) return true;

            else {
                this.runAway(0);
                this.setEnergy(this.getEnergy() - Params.walk_energy_cost);
                return false;
            }

        } */

        if (getEnergy() > 50) return true;
        else {
            this.runAway(0);
            return false;
        }
    }

    public String toString() {
        return "2";
    }

    public void test (List<Critter> l) {

    }
}
