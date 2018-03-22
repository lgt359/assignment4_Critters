package assignment4;

import java.util.*;

/**
 * NAME: The Diagonal
 * ATTRIBUTES: This critter only fights when it has energy > 50
 * MOVEMENTS: Only moves diagonally (random)
 */
public class Critter2 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int random = Critter.getRandomInt(8);

        // diagonals
        if(random%2 == 0){
            random++;
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
        return "D";
    }

    public void test (List<Critter> l) {

    }
}
