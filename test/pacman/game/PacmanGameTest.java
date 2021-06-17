package pacman.game;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.hunter.Hunter;
import pacman.util.Position;

import static org.junit.Assert.*;

public class PacmanGameTest {

    Hunter hunter1;
    PacmanBoard pacmanBoard1;
    PacmanGame pacmanGame;

    @Before
    public void setup() {
        hunter1 = new Hunter();
        pacmanBoard1 = new PacmanBoard(3,3);
        pacmanBoard1.setEntry(new Position(1,1), BoardItem.GHOST_SPAWN);
        pacmanBoard1.setEntry(new Position(0,0), BoardItem.PACMAN_SPAWN);
        pacmanGame = new PacmanGame("My game", "Eugene", hunter1, pacmanBoard1);
    }

    @After
    public void tearDown() {
        hunter1 = null;
        pacmanBoard1 = null;
        pacmanGame = null;
    }

    @Test
    public void pacmanGameConstructorTitleTest() {
    }

    @Test
    public void pacmanGameConstructorAuthorTest() {
    }

    @Test
    public void pacmanGameConstructorHunterTest() {
    }

    @Test
    public void pacmanGameConstructorBoardTest() {
        // Tests if the constructor copied the given board as a new reference
        boolean expected = false;
        assertEquals("PacmanGame constructor was implemented incorrectly",
                expected, pacmanGame.getBoard() == pacmanBoard1);
    }
}
