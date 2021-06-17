package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

import java.util.Objects;

/**
 * Entity A entity is the animated objects in the game
 * that can traverse the game board and interact with other entities.
 */
public abstract class Entity implements Moveable {
    private Position position;
    private Direction direction;

    /**
     * Creates an entity that is at position (0, 0) and is facing UP.
     */
    public Entity() {
        position = new Position(0,0);
        direction = Direction.UP;
    }

    /**
     * Creates an entity that is at the given position facing in the given direction.
     * If the position is null then the position will be
     * the same as the default position ( 0, 0 ). If the direction
     * is null then the direction will be the same as the default ( UP ).
     * @param position - to be set to.
     * @param direction - to be facing.
     */
    public Entity(Position position, Direction direction) {
        this.position = Objects.requireNonNullElseGet(position, () -> new Position(0, 0));
        this.direction = Objects.requireNonNullElse(direction, Direction.UP);
    }

    /**
     * Sets the position of the entity, if position is null the position is not set.
     * @param position - to set to the Moveable
     */
    @Override
    public void setPosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }

    /**
     * Gets the current position of the Moveable
     * @return current position.
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the direction that this Moveable is facing.
     * @return the current direction of the Movable.
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction of the entity,
     * if direction is null the direction is not set and remains the same.
     * @param direction - to be set.
     */
    @Override
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * Checks if another object instance is equal to this instance.
     * Entities are equal if their positions and directions are equal.
     * @param o - object to compare
     * @return true if same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) o;
        return entity.getPosition().equals(this.getPosition())
                && entity.getDirection().equals(this.getDirection());
    }

    /**
     * For two objects that are equal the hash should also be equal.
     * For two objects that are not equal the hash does not have to be different.
     * @return hash of the Entity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }

    /**
     * Represents this entity in a comma-seperated string format.
     * Format is: "x,y,DIRECTION", where DIRECTION is the uppercase enum type value.
     * Example: 4,5,LEFT
     * @return
     */
    @Override
    public String toString() {
        return ""
                + getPosition().getX()
                + ","
                + getPosition().getY()
                + ","
                + getDirection().name().toUpperCase();
    }
}
