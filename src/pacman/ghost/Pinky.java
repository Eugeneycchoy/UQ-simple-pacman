package pacman.ghost;

/**
 * Pinky is a cunning ghost that tries to ambush the hunter.
 * When not chasing the hunter down, Pinky likes to hang out in the
 * top left corner of the board in a pink glow.
 */
public class Pinky extends Ghost {

    /**
     * Get Pinky colour.
     * @return "#c397d8"
     */
    @Override
    public String getColour() {
        return "#c397d8";
    }

    /**
     * Get Pinkys type/name.
     * @return GhostType.PINKY
     */
    @Override
    public GhostType getType() {
        return GhostType.PINKY;
    }
}
