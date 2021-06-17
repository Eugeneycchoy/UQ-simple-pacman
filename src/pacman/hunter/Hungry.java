package pacman.hunter;

import pacman.ghost.Ghost;

/**
 * A Hungry hunter that has a special ability that allows the hunter
 * to eat ghosts temporarily without them being in a Frightened state.
 */
public class Hungry extends Hunter {
    /**
     * Creates a Hungry Hunter with its special ability. see Hunter()
     */
    public Hungry() {
        super();
    }

    /**
     * Creates a Hungry Hunter by copying the internal state of another hunter.
     * see Hunter(Hunter)
     * @param original - hunter to copy from
     */
    public Hungry(Hunter original) {
        super(original);
    }

    /**
     * If Hungry's special is active then if we are in the same tile of a ghost,
     * that ghost will be killed. Otherwise we behave as a normal Hunter. see Hunter.hit(Ghost)
     * @param ghost - to check if were colliding with.
     */
    public void hit(Ghost ghost) {
        if ((this.isSpecialActive()) && (this.getPosition().equals(ghost.getPosition()))) {
            ghost.kill();
        } else {
            super.hit(ghost);
        }
    }

    /**
     * Represents this Hungry in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,specialDuration,HUNGRY".
     * DIRECTION is the uppercase enum type value. Example: "4,5,LEFT,12,HUNGRY"
     * @return "x,y,DIRECTION,specialDuration,HUNGRY"
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
