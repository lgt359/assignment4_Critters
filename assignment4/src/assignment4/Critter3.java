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
        if (getEnergy() > 10) return true;
        return false;
    }

    public String toString() {
        return "3";
    }

    public void test (List<Critter> l) {

    }
}
