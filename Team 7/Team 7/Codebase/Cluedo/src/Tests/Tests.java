package Tests;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import cluedo.Dice;

public class Tests {
    @Test
    void testDice() {
        Dice d = new Dice();
        Assertions.assertTrue(!(d.roll() > 12));
        Assertions.assertTrue(!(d.roll() < 2));
    }
}
