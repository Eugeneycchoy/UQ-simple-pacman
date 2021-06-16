package pacman.util;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void offsetLeftTest() {
        Position expected = new Position(-1, 0);
        assertEquals("offset() was not implemented correctly for Direction.LEFT",
                expected, Direction.LEFT.offset());
    }

    @Test
    public void offsetRightTest() {
        Position expected = new Position(1, 0);
        assertEquals("offset() was not implemented correctly for Direction.RIGHT",
                expected, Direction.RIGHT.offset());
    }

    @Test
    public void offsetDownTest() {
        Position expected = new Position(0, 1);
        assertEquals("offset() was not implemented correctly for Direction.DOWN",
                expected, Direction.DOWN.offset());
    }

    @Test
    public void offsetUpTest() {
        Position expected = new Position(0, -1);
        assertEquals("offset() was not implemented correctly for Direction.UP",
                expected, Direction.UP.offset());
    }

    @Test
    public void oppositeLeftTest() {
        Direction expected = Direction.RIGHT;
        assertEquals("opposite() was not implemented correctly for Direction.LEFT",
                expected, Direction.LEFT.opposite());
    }

    @Test
    public void oppositeRightTest() {
        Direction expected = Direction.LEFT;
        assertEquals("opposite() was not implemented correctly for Direction.RIGHT",
                expected, Direction.RIGHT.opposite());
    }

    @Test
    public void oppositeDownTest() {
        Direction expected = Direction.UP;
        assertEquals("opposite() was not implemented correctly for Direction.DOWN",
                expected, Direction.DOWN.opposite());
    }

    @Test
    public void oppositeUpTest() {
        Direction expected = Direction.DOWN;
        assertEquals("opposite() was not implemented correctly for Direction.UP",
                expected, Direction.UP.opposite());
    }

}
