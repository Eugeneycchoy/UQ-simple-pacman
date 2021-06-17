package pacman.game;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import pacman.ghost.Blinky;
import pacman.hunter.Hunter;
import pacman.util.Direction;
import pacman.util.Position;

import static org.junit.Assert.*;

public class EntityTest {

    Entity entity1;

    Position position1;
    Entity entity2;

    @Before
    public void setup() {
        entity1 = new Hunter();

        position1 = new Position(0, 1);
        entity2 = new Hunter();
        entity2.setPosition(position1);
        entity2.setDirection(Direction.DOWN);
    }

    @After
    public void tearDown() {
        entity1.setPosition(new Position(0,0));
        entity2.setDirection(Direction.DOWN);
    }

    @Test
    public void getPositionTest() {
        Position expected = new Position(0,0);
        assertEquals("getPosition() is not working correctly for entity1",
                expected, entity1.getPosition());
    }

    @Test
    public void setPositionTest() {
        Position expected = new Position(1,1);
        entity1.setPosition(new Position(1,1));
        assertEquals("setPosition() is not working correctly for entity1",
                expected, entity1.getPosition());
    }

    @Test
    public void getDirectionTest() {
        Direction expected = Direction.DOWN;
        assertEquals("getDirection() is not working correctly for entity2",
                expected, entity2.getDirection());
    }

    @Test
    public void setDirectionTest() {
        Direction expected = Direction.RIGHT;
        entity2.setDirection(Direction.RIGHT);
        assertEquals("setDirection() for entity2 is not working correctly",
                expected, entity2.getDirection());
    }

    @Test
    public void equalTest() {
        boolean expected = false; // entity1 and entity2 have different positions and directions
        assertEquals("equals() was not implemented correctly",
                expected, entity1.equals(entity2));
    }

    @Test
    public void hashCodeTest() {
        Entity entity3 = new Hunter();
        Entity entity4 = new Blinky();
        boolean expected = true; // entity3 and entity4 should be equal
        assertEquals("hashCode() or equals() was not implemented correctly",
                expected, entity3.hashCode() == entity4.hashCode());
    }

    @Test
    public void toStringTest() {
        Entity entity3 = new Hunter();
        entity3.setPosition(new Position(4,5));
        entity3.setDirection(Direction.LEFT);
        String expected = "4,5,LEFT";
        assertEquals("toString() was not implemented correctly",
                expected, entity3.toString());
    }
}