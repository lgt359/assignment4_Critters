package assignment4;

import java.util.*;

public class Critter4 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);
        super.walk(random);
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    public String toString() {
        return "4";
    }

    public void test (List<Critter> l) {

    }
}
