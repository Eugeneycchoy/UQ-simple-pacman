package pacman.ghost;
import org.junit.Test;
import static org.junit.Assert.*;

public class PhaseTest {

    @Test
    public void getDurationChaseTest() {
        int expected = 20;
        assertEquals("getDuration() for Phase.CHASE is not working correctly",
                expected, Phase.CHASE.getDuration());
    }

    @Test
    public void getDurationScatterTest() {
        int expected = 10;
        assertEquals("getDuration() for Phase.SCATTER is not working correctly",
                expected, Phase.SCATTER.getDuration());
    }

    @Test
    public void getDurationFrightenedTest() {
        int expected = 30;
        assertEquals("getDuration() for Phase.FRIGHTENED is not working correctly",
                expected, Phase.FRIGHTENED.getDuration());
    }
}
