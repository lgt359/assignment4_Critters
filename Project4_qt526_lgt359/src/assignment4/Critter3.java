/* CRITTERS Critter3.java
 * EE422C Project 4 submission by
 * <Quoc Truong>
 * <qt526>
 * <Lorrie Tria>
 * <lgt359>
 * Spring 2018
 */

package assignment4;

import java.util.*;

/**
 * NAME: The Gardening Tool
 * ATTRIBUTES: This critter always wants to reproduce and never wants to fight(unless Algae)
 * MOVEMENTS: Always tries to reproduce if possible, if not, walks(randomly)
 * OFFSPRING POSITION: Offspring always spawns below parent
 */
public class Critter3 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        if(this.getEnergy() > Params.min_reproduce_energy) {
            Critter baby = new Critter3();
            this.reproduce(baby,6);
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
