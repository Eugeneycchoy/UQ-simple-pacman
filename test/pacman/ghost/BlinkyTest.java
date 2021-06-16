package pacman.ghost;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class BlinkyTest {
    Blinky blinky1;

    @Before
    public void setup() {
        blinky1 = new Blinky();
    }

    @After
    public void tearDown() {
        blinky1 = null;
    }

    @Test
    public void getColorTest() {
        String expected = "#d54e53";
        assertEquals("getColour() is not working correctly for blinky1",
                expected, blinky1.getColour());
    }

    @Test
    public void getTypeTest() {
        GhostType expected = GhostType.BLINKY;
        assertEquals("getType() is not working correctly for blinky1",
                expected, blinky1.getType());
    }
}
