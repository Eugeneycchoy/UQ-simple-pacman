package pacman.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardItemTest {
    BoardItem item1;
    BoardItem item2;
    BoardItem item3;
    BoardItem item4;
    BoardItem item5;
    BoardItem item6;
    BoardItem item7;

    @Before
    public void setup() {
        item1 = BoardItem.NONE;
        item2 = BoardItem.WALL;
        item3 = BoardItem.BIG_DOT;
        item4 = BoardItem.DOT;
        item5 = BoardItem.PACMAN_SPAWN;
        item6 = BoardItem.GHOST_SPAWN;
        item7 = BoardItem.BIG_DOT_SPAWN;
    }

    @Test
    public void getScoreDotTest() {
        int expected = 10;
        int actual = item4.getScore();
        assertEquals("getScore() for BoardItem.DOT is not working correctly",
                expected, actual);
    }

    @Test
    public void getPathableWallTest() {
        boolean expected = true;
        boolean actual = item1.getPathable();
        assertEquals("getPathable() for BoardItem.WALL is not working correctly",
                expected, actual);
    }

    @Test
    public void getCharPacmanSpawnTest() {
        char expected = 'P';
        char actual = item5.getChar();
        assertEquals("getChar() for BoardItem.PACMAN_SPAWN is not working correctly",
                expected, actual);
    }

    @Test
    public void getItemTest() {
        BoardItem expected = BoardItem.GHOST_SPAWN;
        assertEquals("BoardItem.getItem for BoardItem.GHOST_SPAWN is not working correctly",
                expected, BoardItem.getItem('$'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getItemExceptionTest() {
        BoardItem.getItem('!');
        Assert.fail();
    }
}
