package pacman.util;

/**
 * Direction represents directions in a 2D plane.
 * Each direction stores a relative position:
 * LEFT: X = -1, Y = 0
 * RIGHT: X = 1, Y = 0
 * UP: X = 0, Y = -1
 * DOWN: X = 0, Y = 1
 */
public enum Direction {

    /**
     * Facing down
     */
    DOWN(new Position(0, 1)),
    /**
     * Facing left
     */
    LEFT(new Position(-1, 0)),
    /**
     * Facing right
     */
    RIGHT(new Position(1, 0)),
    /**
     * Facing up
     */
    UP(new Position(0, -1));

    /**
     * Direction offset.
     */
    private Position offset;

    /**
     * Create a direction with the given position offset.
     * @param offset offset to be set
     */
    Direction(Position offset) {
        this.offset = offset;
    }

    /**
     * Gets the offset associated with this direction.
     * @return relative position offset.
     */
    public Position offset() {
        return this.offset;
    }

    /**
     * Gets the opposite direction to this direction.
     * @return the opposite direction
     * e.g. up returns down,
     * left returns right.
     * If no opposite direction exists then return this direction.
     */
    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
