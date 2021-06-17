package pacman.hunter;

import pacman.board.BoardItem;
import pacman.game.Entity;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.Direction;
import pacman.util.Position;

import java.util.Objects;

/**
 * Hunters are entities which are controlled
 * by the player to clear the board and win the game.
 */
public class Hunter extends Entity {
    boolean isDead;
    boolean specialAvailable;
    boolean specialActive;
    int specialDurationTicks;

    // SPECIAL_DURATION has the default value of 20.
    public static final int SPECIAL_DURATION = 20;

    /**
     * Creates a Hunter setting the hunter to be alive with the following conditions:
     * The hunter has not used it's special yet. The hunter also does not have its special active.
     * This hunter has a position of (0, 0) with a direction of UP.
     */
    public Hunter() {
        super();
        this.isDead = false;
        this.specialAvailable = true;
        this.specialActive = false;
    }

    /**
     * Creates a Hunter where the following attributes are
     * the same between this hunter and the original:
     * - Dead/Alive status
     * - Whether the hunter has used its special ability yet.
     * - The duration remaining of the special ability.
     * - The position and direction.
     * @param original - hunter to copy.
     */
    public Hunter(Hunter original) {
        super();
        this.isDead = original.isDead;
        this.specialAvailable = original.specialAvailable;
        this.specialActive = original.specialActive;
        this.specialDurationTicks = original.specialDurationTicks;
        setPosition(original.getPosition());
        setDirection(original.getDirection());
    }

    /**
     * Tells if the hunter is dead.
     * @return true if dead, false otherwise.
     */
    public boolean isDead() {
        return this.isDead;
    }

    /**
     * Activates the hunter's special if the hunter hasn't already used its special before.
     * If the hunter has already used its special then do not change the special duration.
     * If the duration for the special is greater than zero then use the hunter's special
     * and set the special's duration to the given duration.
     * If the duration for the special is zero or lower than do not change the special duration
     * and do not use up the hunter's special.
     * @param duration - to activate the special for.
     */
    public void activateSpecial(int duration) {
        if (specialAvailable && duration > 0) {
            this.specialDurationTicks = duration;
            specialAvailable = false;
        }
    }

    /**
     * Gets how many ticks of our special ability is remaining.
     * @return the amount of ticks remaining for the special.
     */
    public int getSpecialDurationRemaining() {
        return this.specialDurationTicks;
    }

    /**
     * Checks if the special is currently active.
     * @return true if the special ability has a duration remaining that is greater than 0 ticks.
     */
    public boolean isSpecialActive() {
        return this.specialDurationTicks > 0;
    }

    /**
     * Checks to see if the hunter is at the same position of the ghost.
     * If the ghost and hunter do have the same position then
     * if the ghost is Phase.FRIGHTENED the ghost is killed Ghost.kill()
     * otherwise the ghost kills the hunter.
     * If the ghost and hunter are not at the same position then do nothing.
     * @param ghost - to check if were colliding with.
     * @throws NullPointerException - is ghost is null.
     */
    public void hit(Ghost ghost) throws NullPointerException {
        if (this.getPosition().equals(ghost.getPosition())) {
            if (ghost.getPhase() == Phase.FRIGHTENED) {
                ghost.kill();
            } else {
                this.isDead = true;
            }
        }
    }

    /**
     * Resets this hunter to be:
     * - Alive
     * - With a special that has not been used yet
     * - A special that is not active ( duration of 0 )
     * - With a Direction of Direction.UP
     * - With a Position of ( 0, 0 )
     */
    public void reset() {
        this.isDead = false;
        this.specialAvailable = true;
        this.specialActive = false;
        this.specialDurationTicks = 0;
        this.setDirection(Direction.UP);
        this.setPosition(new Position(0,0));
    }

    /**
     * Moves the Hunter across the board.
     * If the BoardItem one position forward in the hunter's current direction is pathable,
     * move the hunter into this position. Otherwise the hunter stays in its current position.
     *
     * After moving, the hunter will eat the item that occupied the block and will
     * add its score to the game score.
     *
     * Lastly the special duration will be decreased by 1 if it is greater than 0.
     *
     * Note: The hunter cannot move off of the board, any position outside the board is not pathable.
     * @param game - information needed to decide movement.
     */
    @Override
    public void move(PacmanGame game) {
        // targetPos is one position forward where the hunter is facing
        Position targetPos = this.getPosition().add(this.getDirection().offset());
        BoardItem boardItem;
        try {
            boardItem = game.getBoard().eatDot(targetPos);
        } catch (IndexOutOfBoundsException e) {
            // can't move, if boardItem is non-existent
            return;
        }

        if (boardItem.getPathable()) {
            // If boardItem is pathable, move hunter to its position
            this.setPosition(targetPos);
        }

        if (boardItem == BoardItem.BIG_DOT) {
            // If boardItem is a big dot, set the ghosts to be in the frightened phase
            game.setGhostsFrightened();
        }

        // Increase the score according to the item the hunter ate
        game.getScores().increaseScore(boardItem.getScore());

        // If hunter is in a special phase, decrease its duration by 1
        if (specialDurationTicks > 0) {
            this.specialDurationTicks -= 1;
        }
    }

    /**
     * Checks if another object instance is equal to this. Hunters are equal if they have the same:
     * - Alive/dead value.
     * - Special duration.
     * - Special used status.
     * - Direction.
     * - Position.
     * @param o - object to compare
     * @return true if same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hunter)) {
            return false;
        }
        Hunter otherHunter = (Hunter) o;
        return (this.isDead() == otherHunter.isDead())
                && (this.getSpecialDurationRemaining() == otherHunter.getSpecialDurationRemaining())
                && (this.specialAvailable == otherHunter.specialAvailable)
                && (this.getDirection().equals(otherHunter.getDirection()))
                && (this.getPosition().equals(otherHunter.getPosition()));
    }

    /**
     * For two objects that are equal the hash should also be equal.
     * For two objects that are not equal the hash does not have to be different.
     * @return hash of the Hunter.
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                isDead,
                specialDurationTicks,
                specialAvailable,
                getDirection(),
                getPosition());
    }

    /**
     * Represents this Hunter in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,specialDuration". DIRECTION is the uppercase enum type value.
     * Example: "4,5,LEFT,12"
     * @return "x,y,DIRECTION,specialDuration"
     */
    @Override
    public String toString() {
        return "" + this.getPosition().getX() +
                "," + this.getPosition().getY() +
                "," + this.getDirection().name().toUpperCase() +
                "," + this.getSpecialDurationRemaining();
    }
}
