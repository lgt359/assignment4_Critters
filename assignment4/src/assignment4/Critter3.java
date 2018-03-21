package assignment4;

import java.util.*;

public class Critter3 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);
        super.run(random);
    }

    @Override
    public boolean fight(String opponent) {
        if (opponent.equals("@")){
            return true;
        }

        if (!hasMoved) {
            run(getRandomInt(8));
            return false;
        }
        else {
            this.setEnergy(this.getEnergy() - Params.run_energy_cost);
            return false;
        }
    }

    public String toString() {
        return "3";
    }

    public void test (List<Critter> l) {

    }
}
