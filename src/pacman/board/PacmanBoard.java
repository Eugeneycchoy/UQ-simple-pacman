package pacman.board;

import pacman.util.Position;

/**
 * Represents the Pac Man game board.
 * The board can be any size,
 * it is set out as a grid with each space containing only one BoardItem.
 * Game boards are by default surrounded
 * by a BoardItem.WALL with every other space being BoardItem.NONE.
 * Example: a board with width 1 and height 1 would look like this:
 *
 *      X
 *
 * A board with width 4 and height 4 would look like this:
 *      XXXX
 *      X00X
 *      X00X
 *      XXXX
 *
 * The coordinate positions for the board is the
 * top left position is (0, 0) and the bottom right position is
 * (getWidth-1, getHeight-1).
 */
public class PacmanBoard {
    /**
     * Width of the game board
     */
    private int width;

    /**
     * Height of the game board
     */
    private int height;

    /**
     * The board grid
     */
    private BoardItem[][] board;

    private Position pacmanSpawn;
    private Position ghostSpawn;

    /**
     * Constructor taking the width and height creating a board that is filled with
     * BoardItem.NONE except a 1 block wide border wall around the entire board ( BoardItem.WALL ).
     * @param width - the horizontal size of the board which is greater than zero.
     * @param height - the vertical size of the board which is greater than zero.
     * @throws IllegalArgumentException - when height || width is less than or equal to 0.
     */
    public PacmanBoard(int width, int height) throws IllegalArgumentException {
        if ((width <= 0) || (height <= 0)) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.board = new BoardItem[width][height];

        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height; j++) {
                if ((i == 0 || i == width - 1) || (j == 0 || j == height - 1)) {
                    this.board[i][j] = BoardItem.WALL;
                } else {
                    this.board[i][j] = BoardItem.NONE;
                }
            }
        }
    }

    /**
     * Constructor taking an existing PacmanBoard and making a deep copy.
     * A deep copy should have the same getWidth, getHeight and board as the given board.
     * When a change is made to the other board this should not change this copy.
     * @param other - copy of an existing PacmanBoard.
     * @throws NullPointerException - if copy is null.
     */
    public PacmanBoard(PacmanBoard other) throws NullPointerException {
        if (other == null) {
            throw new NullPointerException();
        }

        this.width = other.width;
        this.height = other.height;
        this.board = new BoardItem[width][height];

        for (int i = 0; i < width; i++) {
            this.board[i] = other.board[i].clone();
        }
    }

    /**
     * Gets the width of the board
     * @return width of the game board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the board
     * @return height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets a tile on the board to an item.
     * If the item to be set is a BoardItem.PACMAN_SPAWN and the board already
     * contains a BoardItem.PACMAN_SPAWN then the old BoardItem.PACMAN_SPAWN
     * should be made a BoardItem.NONE. If the item to be set is a
     * BoardItem.GHOST_SPAWN and the board already contains a BoardItem.GHOST_SPAWN
     * then the old BoardItem.GHOST_SPAWN should be made a BoardItem.NONE.
     * @param position - the portion to place the item.
     * @param item - the board item that is to be placed at the position.
     * @throws IndexOutOfBoundsException - when the position trying to be set is not within the board.
     * @throws NullPointerException - when the position or item is null.
     */
    public void setEntry(Position position, BoardItem item)
            throws IndexOutOfBoundsException, NullPointerException {
        if (position == null || item == null) {
            throw new NullPointerException();
        }
        if (item == BoardItem.PACMAN_SPAWN) {
            if (getPacmanSpawn() != null) {
                board[getPacmanSpawn().getX()][getPacmanSpawn().getY()] = BoardItem.NONE;
            }
            board[position.getX()][position.getY()] = BoardItem.PACMAN_SPAWN;
            this.pacmanSpawn = new Position(position.getX(), position.getY());
        } else if (item == BoardItem.GHOST_SPAWN) {
            if (getGhostSpawn() != null) {
                board[getGhostSpawn().getX()][getGhostSpawn().getY()] = BoardItem.NONE;
            }
            board[position.getX()][position.getY()] = BoardItem.GHOST_SPAWN;
            this.ghostSpawn = new Position(position.getX(), position.getY());
        } else {
            board[position.getX()][position.getY()] = item;
        }
    }

    /**
     * Returns what item the board has on a given position.
     * @param position - wanting to be checked
     * @return BoardItem at the location given.
     * @throws IndexOutOfBoundsException - when the position is not within the board.
     * @throws NullPointerException - if position is null.
     */
    public BoardItem getEntry(Position position)
            throws IndexOutOfBoundsException, NullPointerException {
        if (position == null) {
            throw new NullPointerException();
        }
        return board[position.getX()][position.getY()];
    }

    /**
     * Tries to eat a dot off the board and returns the item that it ate/tried to eat.
     * If a BoardItem.DOT is eaten then it is replaced with a BoardItem.NONE.
     * If a BoardItem.BIG_DOT is eaten then it is replaced with a BoardItem.BIG_DOT_SPAWN.
     * If the item is any other BoardItem then do nothing and return the item.
     * @param position - to eat.
     * @return the item that was originally the position before trying to eat.
     * @throws IndexOutOfBoundsException - if position is null.
     * @throws NullPointerException - when the position trying to be eaten is not within the board.
     */
    public BoardItem eatDot(Position position) throws IndexOutOfBoundsException, NullPointerException {
        BoardItem item = this.board[position.getX()][position.getY()];
        if (item == BoardItem.DOT) {
            setEntry(position, BoardItem.NONE);
            return BoardItem.DOT;
        } else if (item == BoardItem.BIG_DOT) {
            setEntry(position, BoardItem.BIG_DOT_SPAWN);
            return BoardItem.BIG_DOT;
        } else {
            return item;
        }
    }

    /**
     * Get the spawn position for pacman.
     * @return the postion of pacmans spawn or null if none found.
     */
    public Position getPacmanSpawn() {
        return pacmanSpawn;
    }

    /**
     * Get the spawn position for the ghosts.
     * @return the position of the ghost spawn or null if none found.
     */
    public Position getGhostSpawn() {
        return ghostSpawn;
    }

    /**
     * Checks if the board contains any pickup items.
     * @return true if the board does not contain any DOT's or BIG_DOT's.
     */
    public boolean isEmpty() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((this.board[i][j] == BoardItem.BIG_DOT)
                        || (this.board[i][j] == BoardItem.DOT)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Resets the board to place a DOT in every position that
     * has no item ( NONE BoardItem ) and respawns BIG_DOT's in
     * the BIG_DOT_SPAWN locations. Leaves walls, pacman spawns and ghost spawns intact.
     */
    public void reset() {
        // place DOTs where NONEs are
        // respawn BIG_DOTS in BIG_DOT_SPAWNS
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (this.board[i][j] == BoardItem.NONE) {
                    this.board[i][j] = BoardItem.DOT;
                }
                if (this.board[i][j] == BoardItem.BIG_DOT_SPAWN) {
                    this.board[i][j] = BoardItem.BIG_DOT;
                }
            }
        }
    }
}
