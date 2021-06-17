package pacman.game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.ghost.*;
import pacman.hunter.Hunter;
import pacman.hunter.Phil;
import pacman.score.ScoreBoard;
import pacman.util.Position;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
        String expected = "My game";
        assertEquals("PacmanGame constructor, incorrect title initialisation",
                expected, pacmanGame.getTitle());
    }

    @Test
    public void pacmanGameConstructorAuthorTest() {
        String expected = "Eugene";
        assertEquals("PacmanGame constructor, incorrect author initialisation",
                expected, pacmanGame.getAuthor());
    }

    @Test
    public void pacmanGameConstructorHunterTest() {
        Hunter expected = hunter1;
        assertEquals("PacmanGame constructor, incorrect hunter initialisation",
                expected, pacmanGame.getHunter());
    }

    @Test
    public void pacmanGameConstructorBoardTest() {
        // Tests if the constructor copied the given board as a new reference
        boolean expected = false;
        assertEquals("PacmanGame constructor was implemented incorrectly",
                expected, pacmanGame.getBoard() == pacmanBoard1);
    }

    @Test
    public void getScoresNoEntriesTest() {
        // getScore() returns the a ScoreBoard object
        List<String> expected = new ArrayList<>();
        assertEquals("getScore() was not implemented correctly",
                expected, pacmanGame.getScores().getEntriesByName());
    }

    @Test
    public void getScoreOneEntryTest() {
        List<String> expected = new ArrayList<>();
        expected.add("Eugene : 100");
        pacmanGame.getScores().setScore("Eugene", 100);
        assertEquals("getScore() was not implemented correctly",
                expected, pacmanGame.getScores().getEntriesByName());
    }

    @Test
    public void getScoreTwoEntryTest() {
        List<String> expected = new ArrayList<>();
        expected.add("Eugene : 100");
        expected.add("Noobmaster69 : 300");
        pacmanGame.getScores().setScore("Eugene", 100);
        pacmanGame.getScores().setScore("Noobmaster69", 300);
        assertEquals("getScore() was not implemented correctly",
                expected, pacmanGame.getScores().getEntriesByName());
    }

    @Test
    public void getLevelZeroTest() {
        int expected = 0;
        assertEquals("Current level of the game should be 0",
                expected, pacmanGame.getLevel());
    }

    @Test
    public void getLevelOneTest() {
        int expected = 1;
        pacmanGame.setLevel(1);
        assertEquals("Current level of the game should be 1",
                expected, pacmanGame.getLevel());
    }

    @Test
    public void getLevelFiveTest() {
        int expected = 5;
        pacmanGame.setLevel(5);
        assertEquals("Current level of the game should be 5",
                expected, pacmanGame.getLevel());
    }

    @Test
    public void getLivesDefaultTest() {
        int expected = 4;
        assertEquals("The amount of lives player has should be 4 by default",
                expected, pacmanGame.getLives());
    }

    @Test
    public void setLivesThreeTest() {
        int expected = 3;
        pacmanGame.setLives(3);
        assertEquals("The amount of lives player has should be set to 3",
                expected, pacmanGame.getLives());
    }

    @Test
    public void getHunterTest() {
        Hunter expected = hunter1;
        assertEquals("getHunter() was implemented incorrectly",
                expected, pacmanGame.getHunter());
    }

    @Test
    public void getGhostsTest() {
        // note: Constructor PacmanGame() creates one of each type of ghost at the start of the game
        List<Ghost> expected = new ArrayList<>();
        expected.add(new Blinky());
        expected.add(new Clyde());
        expected.add(new Inky());
        expected.add(new Pinky());
        assertEquals("getGhosts() was implemented incorrectly",
                expected, pacmanGame.getGhosts());
    }

    @Test
    public void getGhostsModifyingTest() {
        // Adding, removing elements to this list should not affect the internal copy.
        boolean expected = true;
        List<Ghost> temp = new ArrayList<>();
        temp.add(new Blinky());
        temp.add(new Clyde());
        temp.add(new Inky());
        temp.add(new Pinky());
        pacmanGame.getGhosts().add(new Blinky()); // adding another Blinky
        assertEquals("The original list of ghosts was modified via getGhosts()",
                expected, temp.equals(pacmanGame.getGhosts()));
    }

    @Test
    public void resetLiveTest() {
        int expected = 4;
        pacmanGame.setLives(3);
        pacmanGame.reset();
        assertEquals("reset() does not set the number back to 4",
                expected, pacmanGame.getLives());
    }

    @Test
    public void resetLevelTest() {
        int expected = 0;
        pacmanGame.setLevel(5);
        pacmanGame.reset();
        assertEquals("reset() does not set the level back to 0",
                expected, pacmanGame.getLevel());
    }

    @Test
    public void resetScoreBoardTest() {
        // ScoreBoard.getScore() should return 0
        int expected = 0;
        pacmanGame.getScores().increaseScore(100); // modifying the scoreboard
        pacmanGame.reset();
        assertEquals("reset() did not set the current score in ScoreBoard back to 0",
                expected, pacmanGame.getScores().getScore());
    }

    // reset(), PacmanBoard.reset(), entities, entity positions, tick value untested
    // setGhostsFrighten() untested
    // getTicks() untested
    // tick() untested
}
