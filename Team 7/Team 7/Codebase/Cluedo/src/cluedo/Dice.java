package cluedo;

import java.util.Random;

public class Dice {
    Random random = new Random();

    /**
     * Returns a random whole number between 2 and 12.
     * @return int
     */
    public int roll() {
        int diceA = random.nextInt(5) + 1;
        int diceB = random.nextInt(5) + 1;
        return diceA + diceB;
    }
}
