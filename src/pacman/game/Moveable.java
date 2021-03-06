package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 * An object that can move with a current position and a direction.
 */
public interface Moveable {

    /**
     * Sets the position of the entity, if position is null the position is not set.
     * @param position - to set to the Moveable
     */
    void setPosition(Position position);

    /**
     * Gets the current position of the Moveable
     */
    Position getPosition();

    /**
     * Sets the direction of the entity,
     * if direction is null the direction is not set and remains the same.
     */
    void setDirection(Direction direction);

    /**
     * Gets the direction that this Moveable is facing.
     */
    Direction getDirection();

    /**
     * Moves the Movable object using information provided in the PacmanGame.
     * @param game - information needed to decide movement.
     * @requires - game != null.
     */
    void move(PacmanGame game);
}
