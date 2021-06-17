package pacman.ghost;

import pacman.game.Entity;
import pacman.game.Moveable;
import pacman.game.PacmanGame;
import pacman.util.Direction;
import pacman.util.Position;

/**
 * An Abstract Ghost which is a game entity.
 */
public abstract class Ghost extends Entity implements Moveable {
    private boolean isDead;
    private Phase phase;
    private int phaseDuration;
    private String ghostHexColor;
    private GhostType ghostType;
    private Position position;
    private Direction direction;

    /**
     * Creates a ghost which is alive and starts in the SCATTER phase
     * with a duration of Phase.SCATTER.duration().
     * This ghost also has a default position of (0, 0) and a default direction of facing up.
     */
    public Ghost() {
        super();
        this.isDead = false;
        phase = Phase.SCATTER;
        phaseDuration = Phase.SCATTER.getDuration();
    }

    /**
     * Sets the Ghost Phase and its duration overriding any current phase information.
     * if Phase is null then no changes are made.
     * If the duration is less than zero then the duration is set to 0.
     * @param newPhase - to set the ghost to.
     * @param duration - of ticks for the phase to last for.
     */
    public void setPhase(Phase newPhase, int duration) {
        if (newPhase != null) {
            this.phase = newPhase;
        }
        this.phaseDuration = Math.max(duration, 0);
    }

    /**
     * Get the phase that the ghost currently is in.
     * @return the set phase.
     */
    public Phase getPhase() {
        return this.phase;
    }

    /**
     * Gets the phase info of the ghost.
     * @return the phase and duration formatted as such: "PHASE:DURATION".
     */
    public String phaseInfo() {
        String result = "SCATTER" + ":" + this.phaseDuration;
        switch (this.getPhase()) {
            case FRIGHTENED:
                result = "FRIGHTENED" + ":" + this.phaseDuration;
                break;
            case CHASE:
                result = "CHASE" + ":" + this.phaseDuration;
                break;
            default:
                return result;
        }
        return result;
    }

    /**
     * Gets the ghosts colour.
     * @return hex version of the ghosts colour, e.g. #FFFFFF for white.
     */
    public abstract String getColour();

    /**
     * Gets the ghosts type.
     * @return this ghosts type.
     */
    public abstract GhostType getType();

    /**
     * Move advances the ghost in a direction by one point on the board. The direction this move is made is done as follows:
     * - Decrease the phase duration by 1, and if the duration is now zero, then move to the next phase.
     * - Get the target position. If the phase is CHASE, then get the chaseTarget.
     *   If the phase is SCATTER, then the position is the ghost's home position.
     *   If the phase is FRIGHTENED, then choose a target position with coordinates given by:
     *   targetPositionX = (x*24 mod (2 * board width )) - board width,
     *   targetPositionY = (y*36 mod (2 * board height)) - board height
     *   where x and y are the current coordinates of the ghost.
     * - Choose the direction that the current Ghost position when moved 1 step
     *   has the smallest euclidean distance to the target position.
     *   The board item in the move position must be pathable for it to be chosen.
     *   The chosen direction cannot be opposite to the current direction.
     *   If multiple directions have the same shortest distance,
     *   then choose the direction in the order UP, LEFT, DOWN, RIGHT
     * - Set the direction of the Ghost to the chosen direction.
     * - Set the position of this Ghost to be one forward step in the chosen direction.
     * Note: The next phase after CHASE is SCATTER.
     * Note: The next phase after FRIGHTENED or SCATTER is CHASE.
     * Note: All positions outside the board are to be treated as if they are not pathable.
     * @param game - information needed to decide movement.
     */
    public void move(PacmanGame game) {

    }

    /**
     * Kills this ghost by setting its status to isDead.
     */
    public void kill() {
        this.isDead = true;
    }

    /**
     * Checks if this ghost is dead.
     * @return true if dead, false otherwise.
     */
    public boolean isDead() {
        return this.isDead;
    }

    /**
     * Resets the ghost back to an initial state where:
     * It is alive
     * With a Phase of SCATTER with duration SCATTER.getDuration()
     * Facing in the Direction.UP
     * With a Position of ( 0, 0 )
     */
    public void reset() {
        this.isDead = false;
        this.phase = Phase.SCATTER;
        this.phaseDuration = Phase.SCATTER.getDuration();
        setPosition(new Position(0,0));
        setDirection(Direction.UP);
    }
}
