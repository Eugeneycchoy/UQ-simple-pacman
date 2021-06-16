package pacman.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pacman.util.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacmanBoardTest {

    private static int WIDTH = 7;
    private static int HEIGHT = 4;
    private PacmanBoard board;

    @Before
    public void setUp() {
        this.board = new PacmanBoard(WIDTH, HEIGHT);
    }


    @Test
    public void invalidDimensions() {
        var invalidCombos = List.of(
                -1, 1, // width less than zero
                0, 1, // width of 0
                1, -1, // height less than zero
                1, 0 // height of zero
        );

        for (int i = 0; i < invalidCombos.size(); i+=2) {
            try {
                PacmanBoard board = new PacmanBoard(
                        invalidCombos.get(i), invalidCombos.get(i+1));
                Assert.fail(String.format(
                        "Accepted: width( %d ), height ( %d )",
                        invalidCombos.get(i),
                        invalidCombos.get(i+1)));
            } catch (IllegalArgumentException e) {
                // correct
            }
        }
    }

    @Test
    public void copyConstructor() {
        PacmanBoard copy = new PacmanBoard(board);

        Assert.assertEquals(board.getWidth(), copy.getWidth());
        Assert.assertEquals(board.getHeight(), copy.getHeight());

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Assert.assertEquals(
                        board.getEntry(new Position(x, y)),
                        copy.getEntry(new Position(x, y)));
            }
        }

        // Change the copies (0, 0) to be a big dot and check
        // the original has not changed
        copy.setEntry(new Position(0, 0), BoardItem.BIG_DOT);
        Assert.assertNotEquals(
                board.getEntry(new Position(0, 0)),
                copy.getEntry(new Position(0, 0)));

        // Change the boards (0, 1) to be a big dot and check
        // the original has not changed
        board.setEntry(new Position(0, 1), BoardItem.BIG_DOT);
        Assert.assertNotEquals(
                board.getEntry(new Position(0, 1)),
                copy.getEntry(new Position(0, 1)));

    }

    @Test(expected = NullPointerException.class)
    public void copyNull() {
        new PacmanBoard(null);
    }

    @Test
    public void dimensions() {
        Assert.assertEquals(WIDTH, this.board.getWidth());
        Assert.assertEquals(HEIGHT, this.board.getHeight());
    }

    @Test
    public void defaultBoard() {
        for (var i = 0; i < WIDTH; i++) {
            for (var j = 0; j < HEIGHT; j++) {
                if (j == 0 || j == (HEIGHT - 1) ||
                        i == 0 || i == (WIDTH - 1)) {
                    Assert.assertEquals(
                            BoardItem.WALL,
                            board.getEntry(new Position(i, j)));
                } else {
                    Assert.assertEquals(
                            BoardItem.NONE,
                            board.getEntry(new Position(i, j)));
                }
            }
        }
    }

    @Test
    public void basicEntry() {
        // Null
        try {
            board.setEntry(null, null);
            Assert.fail();
        } catch (NullPointerException e) {
            // pass
        }
        try {
            board.getEntry(null);
            Assert.fail();
        } catch (NullPointerException e) {
            // pass
        }

        // Out of bounds
        try {
            board.setEntry(new Position(-200, 1000), BoardItem.NONE);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        try {
            board.getEntry(new Position(-200, 1000));
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            // pass
        }

        // General Case
        board.setEntry(new Position(0, 0), BoardItem.NONE);
        Assert.assertEquals(
                BoardItem.NONE,
                board.getEntry(new Position(0, 0)));
    }

    @Test
    public void spawnEntry() {
        Assert.assertNull(board.getGhostSpawn());
        Assert.assertNull(board.getPacmanSpawn());

        Position origin = new Position(0, 0);
        Position originPlus = new Position(1, 0);

        // Set Spawn
        board.setEntry(origin, BoardItem.PACMAN_SPAWN);
        Assert.assertEquals(origin, board.getPacmanSpawn());

        // Set new Spawn
        board.setEntry(originPlus, BoardItem.PACMAN_SPAWN);
        Assert.assertEquals(BoardItem.NONE, board.getEntry(origin));
        Assert.assertEquals(originPlus, board.getPacmanSpawn());

        // Set Spawn
        board.setEntry(origin, BoardItem.GHOST_SPAWN);
        Assert.assertEquals(origin, board.getGhostSpawn());

        // Set new Spawn
        board.setEntry(originPlus, BoardItem.GHOST_SPAWN);
        Assert.assertEquals(BoardItem.NONE, board.getEntry(origin));
        Assert.assertEquals(originPlus, board.getGhostSpawn());
    }

    @Test
    public void eatDotExceptional() {
        try {
            board.eatDot(null);
            Assert.fail();
        } catch (NullPointerException e) {
            // pass
        }

        try {
            board.eatDot(new Position(-200, 1000));
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    public void eatDot() {
        Position origin = new Position(0, 0);

        board.setEntry(origin, BoardItem.BIG_DOT);
        Assert.assertEquals(BoardItem.BIG_DOT, board.eatDot(origin));
        Assert.assertEquals(BoardItem.BIG_DOT_SPAWN, board.getEntry(origin));

        board.setEntry(origin, BoardItem.DOT);
        Assert.assertEquals(BoardItem.DOT, board.eatDot(origin));
        Assert.assertEquals(BoardItem.NONE, board.getEntry(origin));

        board.setEntry(origin, BoardItem.WALL);
        Assert.assertEquals(BoardItem.WALL, board.eatDot(origin));
        Assert.assertEquals(BoardItem.WALL, board.getEntry(origin));

    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(board.isEmpty());

        List<BoardItem> items = List.of(BoardItem.DOT, BoardItem.BIG_DOT);

        // Items that are not "empty"
        for (BoardItem item : items) {
            board.setEntry(new Position(0, 0), item);
            Assert.assertFalse(
                    String.format("Thinks a board with %s is empty", item),
                    board.isEmpty());
        }


        List<BoardItem> emptyItems = new ArrayList<>(
                Arrays.asList(BoardItem.values()));
        emptyItems.removeAll(items);

        // items that are "empty"
        for (BoardItem item : emptyItems) {
            board.setEntry(new Position(0, 0), item);
            Assert.assertTrue(
                    String.format("Thinks a board with %s is not empty", item),
                    board.isEmpty());
        }
    }

    @Test
    public void resetGeneral() {
        // Board is empty by default
        Assert.assertTrue(board.isEmpty());
        // reset
        board.reset();
        // Board will now not be empty
        Assert.assertFalse(board.isEmpty());

        // Check inside of board for DOTs
        for (int x = 1; x < WIDTH-1; x++) {
            for (int y = 1; y < HEIGHT-1; y++) {
                Assert.assertEquals(
                        BoardItem.DOT,
                        board.getEntry(new Position(x, y)));
            }
        }
    }

    @Test
    public void resetBigDotSpawn() {
        board.setEntry(new Position(0, 0), BoardItem.BIG_DOT_SPAWN);
        board.reset();
        Assert.assertEquals(
                BoardItem.BIG_DOT,
                board.getEntry(new Position(0, 0)));
    }

    @Test
    public void resetSpawns() {
        List<BoardItem> items = List.of(
                BoardItem.GHOST_SPAWN, BoardItem.PACMAN_SPAWN);
        for (BoardItem item : items) {
            board.setEntry(new Position(0, 0), item);
            board.reset();
            Assert.assertEquals(
                    item,
                    board.getEntry(new Position(0, 0)));
        }
    }

    @Test
    public void equalsTrueTest() {
        boolean expected = true;
        PacmanBoard pacmanBoard1 = new PacmanBoard(5,5);
        PacmanBoard pacmanBoard2 = new PacmanBoard(5,5);
        Position position1 = new Position(0,0);
        Position position2 = new Position(0,1);
        pacmanBoard1.setEntry(position1, BoardItem.PACMAN_SPAWN);
        pacmanBoard1.setEntry(position2, BoardItem.BIG_DOT);
        pacmanBoard2.setEntry(position1, BoardItem.PACMAN_SPAWN);
        pacmanBoard2.setEntry(position2, BoardItem.BIG_DOT);
        Assert.assertEquals(expected, pacmanBoard1.equals(pacmanBoard2));
    }

    @Test
    public void equalsFalseTest() {
        boolean expected = false;
        PacmanBoard pacmanBoard1 = new PacmanBoard(5,5);
        PacmanBoard pacmanBoard2 = new PacmanBoard(4,3);
        Position position1 = new Position(0,0);
        Position position2 = new Position(0,1);
        pacmanBoard1.setEntry(position1, BoardItem.PACMAN_SPAWN);
        pacmanBoard1.setEntry(position2, BoardItem.BIG_DOT);
        pacmanBoard2.setEntry(position1, BoardItem.PACMAN_SPAWN);
        pacmanBoard2.setEntry(position2, BoardItem.BIG_DOT);
        Assert.assertEquals(expected, pacmanBoard1.equals(pacmanBoard2));
    }
}