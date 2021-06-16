package pacman.board;

/**
 * This enum defines different items that are placed on the board.
 * Items may have a pickup score if the item can be picked up.
 * Items must define whether they are path-able.
 */
public enum BoardItem {
    NONE(true, 0, '0'),
    WALL(false, 0, 'X'),
    DOT(true, 10, '1'),
    BIG_DOT(true, 15, 'B'),
    BIG_DOT_SPAWN(true, 0, 'b'),
    GHOST_SPAWN(true, 0, '$'),
    PACMAN_SPAWN(true, 0, 'P');

    /**
     * Scores associated with this item
     */
    private int score;

    /**
     * True or false of whether this item can
     * be traveled through
     */
    private boolean isPathable;

    /**
     * Character key associated with this item
     */
    private char characterKey;

    /**
     *
     * @param score
     */
    BoardItem(boolean isPathable, int score, char characterKey) {
        this.isPathable = isPathable;
        this.score = score;
        this.characterKey = characterKey;
    }

    /**
     * gets the score of the item.
     * @return the score associated with an item.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * gets the path-able nature of the item.
     * @return whether the item is path-able.
     */
    public boolean getPathable() {
        return this.isPathable;
    }

    /**
     * gets the character key of the item.
     * @return the character key associated with the item.
     */
    public char getChar() {
        return this.characterKey;
    }

    /**
     * Takes a character and returns the associated BoardItem
     * as presented in the Enum comment's "Enum definition" table. see BoardItem
     * @param key - a character that represents the board item.
     *            The acceptable characters are defined in the Enum's "Enum definition" table.
     * @return the board Item associated with the character
     * @throws IllegalArgumentException - if the character is not part of the supported Items
     */
    public static BoardItem getItem(char key) throws IllegalArgumentException {
        return switch (key) {
            case '0' -> NONE;
            case 'X' -> WALL;
            case '1' -> DOT;
            case 'B' -> BIG_DOT;
            case 'b' -> BIG_DOT_SPAWN;
            case '$' -> GHOST_SPAWN;
            case 'P' -> PACMAN_SPAWN;
            default -> throw new IllegalArgumentException();
        };
    }




}
