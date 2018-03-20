package assignment4;

import java.util.*;

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
        if (getEnergy() > 10) return true;
        return false;
    }

    public String toString() {
        return "1";
    }

    public void test (List<Critter> l) {

    }
}
