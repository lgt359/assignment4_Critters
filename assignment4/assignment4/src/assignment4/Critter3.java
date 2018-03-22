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

        this.runAway(0);
        return false;
    }

    public String toString() {
        return "3";
    }

    public void test (List<Critter> l) {

    }
}
