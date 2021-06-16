package pacman.ghost;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import pacman.util.Direction;
import pacman.util.Position;
import static org.junit.Assert.*;

public class GhostTest {

    Ghost ghost1;

    @Before
    public void setup() {
        ghost1 = new Blinky();
    }

    @After
    public void tearDown() {
        ghost1 = null;
    }

    @Test
    public void getPhaseTest() {
        Phase expected = Phase.SCATTER;
        assertEquals("",
                expected, ghost1.getPhase());
    }

    @Test
    public void setPhaseTest() {
        Phase expected = Phase.FRIGHTENED;
        ghost1.setPhase(Phase.FRIGHTENED, 2);
        Phase actual = ghost1.getPhase();
        assertEquals("setPhase(Phase newPhase, int duration) is not working correctly for ghost1",
                expected, actual);
    }

    @Test
    public void phaseInfoTest() {
        String expected = "CHASE:4";
        ghost1.setPhase(Phase.CHASE, 4);
        assertEquals("phaseInfo() is not working correctly for ghost1",
                expected, ghost1.phaseInfo());
    }

    @Test
    public void killTest() {
        boolean expected = true;
        ghost1.kill();
        assertEquals("kill() is not working correctly for ghost1",
                expected, ghost1.isDead());
    }

    @Test
    public void isDeadTest() {
        boolean expected = false;
        assertEquals("isDead() is not working correctly for ghost1",
                expected, ghost1.isDead());
    }

    @Test
    public void resetAliveTest() {
        boolean expected = false;
        ghost1.kill(); // ghost1 is dead
        ghost1.reset(); // ghost1 should be reset to be alive
        assertEquals("reset() is not setting ghost1 back to be alive",
                expected, ghost1.isDead());
    }

    @Test
    public void resetPhaseTest() {
        Phase expected = Phase.SCATTER;
        ghost1.setPhase(Phase.FRIGHTENED, 5); // ghost1 is in a FRIGHTENED phase
        ghost1.reset(); // ghost1 should be reset back to SCATTERED
        assertEquals("reset() is not setting ghost1 back to the SCATTER phase",
                expected, ghost1.getPhase());

    }

    @Test
    public void resetDirectionTest() {
        Direction expected = Direction.UP;
        ghost1.setDirection(Direction.DOWN); // Direction is set to DOWN
        ghost1.reset(); // Direction should be reset back to UP
        assertEquals("reset() is not setting ghost1 backing to be facing UP",
                expected, ghost1.getDirection());
    }

    @Test
    public void resetPositionTest() {
        Position expected = new Position(0,0);
        ghost1.setPosition(new Position(1,1)); // Position is set to (1,1)
        ghost1.reset(); // Position should now be reset back to (0,0)
        assertEquals("reset() is not setting back the position to be (0,0) for ghost1",
                expected, ghost1.getPosition());
    }
}