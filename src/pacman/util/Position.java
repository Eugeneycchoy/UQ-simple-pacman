package pacman.util;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    /**
     * Creates a position at the given x and y coordinates.
     * @param x - location
     * @param y - location
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X axis location.
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y axis location.
     * @return y position
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the Euclidean distance from this point to the given other point.
     * @param other - point used to calculate the euclidean distance.
     * @return the euclidean distance.
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow((other.getX() - this.getX()), 2)
                + Math.pow((other.getY() - this.getY()), 2));
    }

    /**
     * Adds two positions together.
     * @param other - position to add to this one.
     * @return This position + other.
     */
    public Position add(Position other) {
        this.x += other.getX();
        this.y += other.getY();
        return this;
    }

    /**
     * Multiplies by a factor on the x and y axis.
     * @param factor - to multiple the axis by.
     * @return a new position with the x axis scaled by factor and y axis scaled by factor.
     */
    public Position multiply(int factor) {
        return new Position(this.getX() * factor, this.getY() * factor);
    }

    /**
     * Checks if two positions are equal.
     * @param other - object to compare against.
     * @return true if x == this.x and also y == this.y, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }
        return (this.getX() == ((Position) other).getX())
                && (this.getY() == ((Position) other).getY());
    }

    /**
     * Calculates the hash of the position.
     * @return hash of this position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a comma-seperated string representation of a Position.
     * Format: "x,y" Example: "3,4"
     * @return "x,y"
     */
    @Override
    public String toString() {
        return "" + getX() + "," + getY();
    }
}
