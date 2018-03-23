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
 * NAME: The Straight-Edge
 * ATTRIBUTES: This critter only fights when it has enough energy (>50)
 * MOVEMENT(doTimeStep): Always moves horizontally or vertically (randomly)
 */
public class Critter1 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);

        // up, down, left, right
        if(random%2 != 0){
            random--;
        }
        super.walk(random);
    }

    @Override
    public boolean fight(String opponent) {
        // always fights algae for energy
        if (opponent.equals("@")){
            return true;
        }

        // fights if has enough energy
        if (this.getEnergy() > 50) return true;
        // runs away
        else {
            this.run(getRandomInt(8));
            return false;
        }
    }

    public String toString() {
        return "1";
    }

    public void test (List<Critter> l) {

    }
}
