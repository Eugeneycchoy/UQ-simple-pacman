package pacman.score;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    ScoreBoard scoreBoard1;

    @Before
    public void setup() {
        scoreBoard1 = new ScoreBoard();
    }

    @After
    public void tearDown() {
        scoreBoard1 = null;
    }

    @Test
    public void scoreBoardConstructorScoreTest() {
        // checks if the score is equal to zero
        int expected = 0;
        assertEquals("ScoreBoard() did not initialise scoreboard with the score set to 0",
                expected, scoreBoard1.getScore());
    }

    @Test
    public void scoreBoardConstructorSizeTest() {
        // checks if the size of the list is 0
        int expected = 0;
        assertEquals("ScoreBoard() did not initialise scoreboard with an empty entry list",
                expected, scoreBoard1.getEntriesByName().size());
    }

    @Test
    public void getEntriesByNameTest() {
        List<String> expected = new ArrayList<>();
        expected.add("Fred : 24");
        expected.add("fred : 20");

        scoreBoard1.setScore("Fred", 100);
        scoreBoard1.setScore("fred", 20);
        scoreBoard1.setScore("Fred", 24);
        List<String> scores = scoreBoard1.getEntriesByName();
        assertEquals("getEntriesByName() was not implemented correctly",
                expected, scores);
    }

    @Test
    public void getEntriesByScoreTest() {
        List<String> expected = new ArrayList<>();
        expected.add("Alfie : 24");
        expected.add("ben : 20");
        expected.add("richard : 20");

        scoreBoard1.setScore("Alfie", 100);
        scoreBoard1.setScore("richard", 20);
        scoreBoard1.setScore("Alfie", 24);
        scoreBoard1.setScore("ben", 20);
        List<String> scores = scoreBoard1.getEntriesByScore();
        assertEquals("getEntriesByScore() was not implemented correctly",
                expected, scores);
    }

    @Test
    public void setScoreTest() {
        String expected = "Eugene : 100";
        scoreBoard1.setScore("Eugene", 100);
        assertEquals("setScore() did not work correctly",
                expected, scoreBoard1.getEntriesByName().get(0));
    }

    @Test
    public void setScoresOverrideTest() {
        String expected = "Eugene : 120";
        scoreBoard1.setScore("Eugene", 100); // the initial entry

        Map<String, Integer> entries = new HashMap<>();
        entries.put("Eugene", 120);
        scoreBoard1.setScores(entries); // Overrides Eugene's score to 120

        assertEquals("",
                expected, scoreBoard1.getEntriesByName().get(0));
    }

    @Test
    public void setScoresSkipTest() {
        // Entry is skipped if it is an invalid input
        // score is a negative number
        List<String> expected = new ArrayList<>();
        expected.add("Eugene : 100");

        Map<String, Integer> entries = new HashMap<>();
        entries.put("Eugene", -100);
        scoreBoard1.setScore("Eugene", 100);
        scoreBoard1.setScores(entries);

        assertEquals("setScores() was not implemented correctly",
                expected, scoreBoard1.getEntriesByName());
    }

    @Test
    public void increaseScoreTest() {
        int expected = 100;
        scoreBoard1.increaseScore(100);
        assertEquals("increaseScore() was not implemented correctly, typical input",
                expected, scoreBoard1.getScore());
    }

    @Test
    public void increaseScoreTest2() {
        int expected = 100;
        scoreBoard1.increaseScore(100);
        scoreBoard1.increaseScore(0);
        assertEquals("increaseScore() was not implemented correctly, boundary case: 0 input",
                expected, scoreBoard1.getScore());
    }

    @Test
    public void increaseScoreTest3() {
        int expected = 100;
        scoreBoard1.increaseScore(100);
        scoreBoard1.increaseScore(-1000);
        assertEquals("increaseScore() was not implemented correctly, boundary case: negative number input",
                expected, scoreBoard1.getScore());
    }

    @Test
    public void getScoreTest() {
        int expected = 100;
        scoreBoard1.increaseScore(100);
        assertEquals("getScore() was not implemented correctly",
                expected, scoreBoard1.getScore());
    }

    @Test
    public void resetTest() {
        int expected = 0;
        scoreBoard1.increaseScore(100);
        scoreBoard1.reset();
        assertEquals("reset() was not implemented correctly, current score is not 0",
                expected, scoreBoard1.getScore());
    }

}
