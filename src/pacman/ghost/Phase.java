package pacman.ghost;

import java.io.Serializable;

/**
 * Phase Defines the different phases a ghost can be in.
 * The phases are defined as "CHASE", "SCATTER" and "FRIGHTENED".
 * - Chase has a duration of 20.
 * - Scatter has a duration of 10.
 * - Frightened has a duration of 30.
 */
public enum Phase implements Serializable, Comparable<Phase> {
    /**
     * CHASE - Phase where the ghosts chase the hunter.
     * FRIGHTENED - Phase where the ghosts are frightened and confused.
     * SCATTER - Phase where the ghosts run home.
     */
    CHASE(20),
    FRIGHTENED(30),
    SCATTER(10);

    /**
     * The ticks left until this phase ends
     */
    private int phaseDuration;

    Phase(int phaseDuration) {
        this.phaseDuration = phaseDuration;
    }

    /**
     * Gets the duration of the phase.
     * @return duration of the phase.
     */
    public int getDuration() {
        return this.phaseDuration;
    }

}
