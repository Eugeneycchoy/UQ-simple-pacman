package pacman.hunter;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import pacman.util.Direction;
import pacman.util.Position;

import static org.junit.Assert.*;

public class HungryTest {

    Hunter hungry1;

    @Before
    public void setup() {
        hungry1 = new Hungry();
    }

    @After
    public void tearDown() {
        hungry1 = null;
    }

    @Test
    public void hungryConstructorSpecialAvailableTest() {
        // specialAvailable should be initialised as true
        boolean expected = true;
        assertEquals("specialAvailable should be initialised as true",
                expected, hungry1.specialAvailable);
    }

    @Test
    public void hungryConstructorSpecialActiveTest() {
        // isSpecialActive() should return false
        boolean expected = false;
        assertEquals("specialActive should be initialised as true",
                expected, hungry1.isSpecialActive());
    }

    @Test
    public void toStringTest() {
        String expected = "4,5,LEFT,12,HUNGRY";
        hungry1.setPosition(new Position(4,5));
        hungry1.setDirection(Direction.LEFT);
        hungry1.specialDurationTicks = 12;
        assertEquals("toString() was not implemented correctly",
                expected, hungry1.toString());
    }

}
