package pacman.util;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class PositionTest {

    Position position1;
    Position position2;
    Position position3;
    Position position4;

    @Before
    public void setup() {
        position1 = new Position(1,1);
        position2 = new Position(3,3);
    }

    @After
    public void tearDown() {
        position1 = null;
    }

    @Test
    public void positionConstructorXTest() {
        int expected = 1;
        assertEquals("Constructor or getX() was not implemented correctly",
                expected, position1.getX());
    }

    @Test
    public void positionConstructorYTest() {
        int expected = 1;
        assertEquals("Constructor or getY() was not implemented correctly",
                expected, position1.getY());
    }

    @Test
    public void testDistance() {
        Position pos1 = new Position(10, 20);
        Position pos2 = new Position (15, 20);
        double distance = Math.sqrt(Math.pow(10-15,2) + Math.pow(20-20, 2));
        double dist = pos1.distance(pos2);
        double margin = 0.002;
        if (distance + margin < dist || dist < distance - margin) {
            fail();
        }
    }

    @Test
    public void addTest() {
        Position expected = new Position(4,4);
        // position1 is (1,1)
        // position2 is (3,3)
        assertEquals("add() was not implemented correctly",
                expected, position1.add(position2));
    }

    @Test
    public void multiplyTest() {
        Position expected = new Position(6, 4);
        position3 = new Position(3,2);
        assertEquals("multiply() was not implemented correctly",
                expected, position3.multiply(2));
    }

    @Test
    public void equalsTest() {
        boolean expected = true;
        position3 = new Position(2,2);
        position4 = new Position(2,2);
        assertEquals("equals() was not implemented correctly",
                expected, position3.equals(position4));
    }

    @Test
    public void hashCodeTest() {
        position3 = new Position(2,2);
        position4 = new Position(2,2);
        assertEquals("hashCode() was not implemented correctly",
                position3.hashCode(), position4.hashCode());
    }

    @Test
    public void toStringTest() {
        String expected = "1,1";
        assertEquals("toString() was not implemented correctly",
                expected, position1.toString());
    }
}
