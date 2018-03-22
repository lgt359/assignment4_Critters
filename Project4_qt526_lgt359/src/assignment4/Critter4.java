package assignment4;

import java.util.*;

/**
 * NAME: The Drunk Rock
 * ATTRIBUTES: This critter always wants to fight
 * MOVEMENT: Always runs and charges randomly
 */
public class Critter4 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);
        super.run(random);
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    public String toString() {
        return "R";
    }

    public void test (List<Critter> l) {

    }
}
