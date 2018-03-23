/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Lorrie Tria
 * LGT359
 * Quoc Truong
 * QT526
 *
 * Spring 2018
 */

package assignment4;

import java.util.*;

/**
 * NAME: The Drunk Rock
 * ATTRIBUTES: This critter always wants to fight!!!
 * MOVEMENT(doTimeStep): Always runs and charges randomly
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
        return "4";
    }

    public void test (List<Critter> l) {

    }
}
