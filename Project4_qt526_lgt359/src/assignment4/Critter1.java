package assignment4;

import java.util.*;

/**
 * NAME: The Straight-Edge
 * ATTRIBUTES: This critter only fights when it has energy > 50
 * MOVEMENT: Only moves horizontally/vertically (random)
 */
public class Critter1 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);

        // up down left right
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

        if (getEnergy() > 50) return true;
        else {
            this.run(getRandomInt(8));
            return false;
        }
    }

    public String toString() {
        return "S";
    }

    public void test (List<Critter> l) {

    }
}
