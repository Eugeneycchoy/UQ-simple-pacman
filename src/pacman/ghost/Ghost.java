package pacman.ghost;

import pacman.game.Entity;
import pacman.game.Moveable;
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
