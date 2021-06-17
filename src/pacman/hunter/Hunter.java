package pacman.hunter;

import pacman.game.Entity;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.Direction;
import pacman.util.Position;

/**
 * Hunters are entities which are controlled
 * by the player to clear the board and win the game.
 */
public class Hunter extends Entity {
    boolean isDead;
    boolean specialAvailable;
    boolean specialActive;
    int specialDurationTicks;

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

    @Override
    public void move(PacmanGame game) {

    }
}
