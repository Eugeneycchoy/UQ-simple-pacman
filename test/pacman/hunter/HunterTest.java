package pacman.hunter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import pacman.ghost.Blinky;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.Direction;
import pacman.util.Position;

import static org.junit.Assert.*;

public class HunterTest {

    Hunter defaultHunter;

    Hunter originalHunter; // This is the Hunter to be deep copied
    Hunter copyHunter;

    Hunter hunterSpecialNotAvailable;
    Hunter hunterSpecialAvailable;

    Ghost ghost1;

    @Before
    public void setup() {
        defaultHunter = new Hunter();

        originalHunter = new Hunter();
        originalHunter.isDead = true;
        originalHunter.activateSpecial(20);
        originalHunter.setPosition(new Position(2,2));
        originalHunter.setDirection(Direction.RIGHT);
        copyHunter = new Hunter(originalHunter);

        hunterSpecialNotAvailable = new Hunter();
        hunterSpecialNotAvailable.specialDurationTicks = 5;
        hunterSpecialNotAvailable.specialAvailable = false;

        hunterSpecialAvailable = new Hunter();
        hunterSpecialAvailable.specialAvailable = true;

        ghost1 = new Blinky();
    }

    @After
    public void tearDown() {
        defaultHunter = null;
        originalHunter = null;
        copyHunter = null;
        hunterSpecialNotAvailable = null;
        hunterSpecialAvailable = null;
        ghost1 = null;
    }

    @Test
    public void defaultHunterConstructorAliveTest() {
        boolean expected = false; // expect isDead to be false
        assertEquals("Default constructor does not initialise hunter to be alive",
                expected, defaultHunter.isDead());
    }

    @Test
    public void defaultHunterConstructorSpecialTest() {
        boolean expected = false; // expect isSpecialActive to be true
        assertEquals("Default constructor does not initialise hunter to has his special active",
                expected, defaultHunter.isSpecialActive());
    }

    @Test
    public void defaultHunterConstructorPositionTest() {
        Position expected = new Position(0,0); // expected the default position to be at (0,0)
        assertEquals("Default constructor does not initialise hunter to be at the position of (0,0)",
                expected, defaultHunter.getPosition());
    }

    @Test
    public void defaultHunterConstructorDirectionTest() {
        Direction expected = Direction.UP; // expected the default facing direction to be Direction.UP
        assertEquals("Default constructor does not initialise hunter to be facing Direction.UP",
                expected, defaultHunter.getDirection());
    }

    @Test
    public void copyHunterConstructorAliveOrDeadTest() {
        boolean expected = true; // original hunter is set to be dead
        assertEquals("Dead or Alive state between the originalHunter and copyHunter does not match",
                expected, copyHunter.isDead());
    }

    @Test
    public void copyHunterConstructorSpecialActiveTest() {
        boolean expected = true; // original hunter is using his special
        assertEquals("Special active state between originalHunter and copyHunter does not match",
                expected, copyHunter.isSpecialActive());
    }

    @Test
    public void copyHunterConstructorSpecialDurationTest() {
        int expected = 20; // special duration of originalHunter has been set to be 20
        assertEquals("Special duration between originalHunter and copyHunter does not match",
                expected, copyHunter.getSpecialDurationRemaining());
    }

    @Test
    public void copyHunterConstructorPositionTest() {
        Position expected = new Position(2,2);
        assertEquals("Positions between originalHunter and copyHunter do not match",
                expected, copyHunter.getPosition());
    }

    @Test
    public void copyHunterConstructorDirectionTest() {
        Direction expected = Direction.RIGHT;
        assertEquals("Directions between originalHunter and copyHunter do not match",
                expected, copyHunter.getDirection());
    }

    @Test
    public void isDeadTest() {
        boolean expected = true; // originalHunter is set to be dead
        assertEquals("isDead() is not working correctly on originalHunter",
                expected, originalHunter.isDead());
    }

    @Test
    public void activateSpecialHasUsedBeforeTest() {
        int expected = 5;
        // try to set the special duration to 5 but hunter's used his special already
        hunterSpecialNotAvailable.activateSpecial(10);
        assertEquals("activateSpecial() does not work correctly for hunterSpecialNotAvailable",
                expected, hunterSpecialNotAvailable.getSpecialDurationRemaining());
    }

    @Test
    public void activateSpecialHasNotUsedBeforeTest() {
        int expected = 10;
        hunterSpecialAvailable.activateSpecial(10);
        assertEquals("activateSpecial() does not work correctly for hunterSpecialAvailable",
                expected, hunterSpecialAvailable.getSpecialDurationRemaining());
    }

    @Test
    public void activateSpecialDurationZeroTest() {
        int expected = 5;
        hunterSpecialAvailable.specialDurationTicks = 5;
        hunterSpecialAvailable.activateSpecial(0);
        assertEquals("activateSpecial() should not set specialDurationTicks to be duration if it is equal or less than 0",
                expected, hunterSpecialAvailable.getSpecialDurationRemaining());
    }

    @Test
    public void getSpecialDurationRemainingTest() {
        int expected = 5;
        hunterSpecialAvailable.specialDurationTicks = 5;
        assertEquals("getSpecialDurationRemaining() does not work correctly with hunterSpecialAvailable",
                expected, hunterSpecialAvailable.getSpecialDurationRemaining());
    }

    @Test
    public void isSpecialActiveFalseTest() {
        boolean expected = false;
        assertEquals("isSpecialActive() is not working correctly for defaultHunter",
                expected, defaultHunter.isSpecialActive());
    }

    @Test
    public void isSpecialActiveTrueTest() {
        boolean expected = true;
        defaultHunter.activateSpecial(5); // set isSpecialActive to be true for defaultHunter
        assertEquals("isSpecialActive() is not working correctly for defaultHunter",
                expected, defaultHunter.isSpecialActive());
    }

    @Test(expected = NullPointerException.class)
    public void hitNullGhostTest() {
        ghost1 = null;
        defaultHunter.hit(ghost1);
        Assert.fail();
    }

    @Test
    public void hitFrightenedGhostTest() {
        boolean expected = true; // the ghost should be killed
        ghost1.setPosition(new Position(1,1));
        defaultHunter.setPosition(new Position(1,1));
        ghost1.setPhase(Phase.FRIGHTENED, Phase.FRIGHTENED.getDuration());
        defaultHunter.hit(ghost1);
        assertEquals("default hunter hit ghost1 which is in a FRIGHTENED state but did not kill it",
                expected, ghost1.isDead());
    }

    @Test
    public void hitNonFrightenedGhostTest1() {
        boolean expected = true; // the hunter should be killed
        ghost1.setPosition(new Position(1,1));
        defaultHunter.setPosition(new Position(1,1));
        defaultHunter.hit(ghost1);
        assertEquals("Default hunter was not killed hitting a non frigtened ghost",
                expected, defaultHunter.isDead());
    }

    @Test
    public void hitNonFrightenedGhostTest2() {
        boolean expected = false; // the ghost should not be killed
        ghost1.setPosition(new Position(1,1));
        defaultHunter.setPosition(new Position(1,1));
        defaultHunter.hit(ghost1);
        assertEquals("Ghosts not in a frightened phase should not be killed when getting hit by hunter",
                expected, ghost1.isDead());
    }

    @Test
    public void resetAliveTest() {
        boolean expected = false; // isDead() for originalHunter should be false after calling reset()
        originalHunter.reset();
        assertEquals("originalHunter should be alive after calling reset()",
                expected, originalHunter.isDead());
    }

    @Test
    public void resetSpecialTest() {
        boolean expected = true; // specialAvailable should be reset back to true
        originalHunter.reset();
        assertEquals("specialAvailable is not true",
                expected, originalHunter.specialAvailable);
    }

    @Test
    public void resetSpecialActiveTest() {
        boolean expected = false; // specialActive should be reset back to false
        originalHunter.reset();
        assertEquals("specialActive is not false",
                expected, originalHunter.isSpecialActive());
    }

    @Test
    public void resetSpecialDurationTicksTest() {
        int expected = 0; // specialDurationTicks should be reset back to 0
        originalHunter.reset();
        assertEquals("specialDurationTicks is not 0",
                expected, originalHunter.getSpecialDurationRemaining());
    }

    @Test
    public void resetPositionTest() {
        Position expected = new Position(0,0);
        originalHunter.reset();
        assertEquals("Position was not reset back to (0,0)",
                expected, originalHunter.getPosition());
    }

    @Test
    public void resetDirectionTest() {
        Direction expected = Direction.UP;
        originalHunter.reset();
        assertEquals("Direction was not reset back to UP",
                expected, originalHunter.getDirection());
    }
}
