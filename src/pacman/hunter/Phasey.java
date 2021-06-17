package pacman.hunter;

import pacman.ghost.Ghost;
import pacman.ghost.Phase;

/**
 * A Phasey hunter with a special ability that allows the hunter
 * to travel through ghosts temporarily without dieing.
 */
public class Phasey extends Hunter {

    /**
     * Creates a Phasey Hunter with its special ability. see Hunter()
     */
    public Phasey() {
        super();
    }

    /**
     * Creates a Phasey Hunter by copying the internal state of another hunter.
     * see Hunter(Hunter)
     * @param original - hunter to copy from
     */
    public Phasey(Hunter original) {
        super(original);
    }

    /**
     * If Phasey's special is active and if a ghost is not Phase.FRIGHTENED
     * then we travel through the ghost without killing them or them killing us.
     * Otherwise we behave as a normal Hunter. see Hunter.hit(Ghost)
     * @param ghost - to check if were colliding with.
     */
    public void hit(Ghost ghost) {
        if (this.isSpecialActive()) {
            if (ghost.getPhase() == Phase.FRIGHTENED) {
                ghost.kill();
            }
        } else {
            super.hit(ghost);
        }
    }

    /**
     * Represents this Phasey in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,specialDuration,PHASEY".
     * DIRECTION is the uppercase enum type value. Example:
     * "4,5,LEFT,12,PHASEY"
     * @return
     */
    @Override
    public String toString() {
        return "" + this.getPosition().getX() +
                "," + this.getPosition().getY() +
                "," + this.getDirection().name().toUpperCase() +
                "," + this.getSpecialDurationRemaining() +
                "," + this.getClass().getSimpleName().toUpperCase();
    }
}
