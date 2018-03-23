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
 * NAME: The Gardening Tool
 * ATTRIBUTES: This critter always wants to reproduce and never wants to fight (unless Algae)
 * MOVEMENT(doTimeStep): Reproduces if critter has enough energy; if not, walks
 * OFFSPRING POSITION: Offspring always spawns below parent
 */
public class Critter3 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        // Reproduces if energy
        if(this.getEnergy() > Params.min_reproduce_energy){
            Critter offspring = new Critter3();
            reproduce(offspring, 6);
        }
        else {
            int random = Critter.getRandomInt(8);
            super.walk(random);
        }
    }

    @Override
    public boolean fight(String opponent) {
        // always fights algae for energy
        if (opponent.equals("@")){
            return true;
        }

        this.run(getRandomInt(8));
        return false;
    }

    public String toString() {
        return "3";
    }

    public void test (List<Critter> l) {

    }
}
