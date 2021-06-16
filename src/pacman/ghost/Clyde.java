package pacman.ghost;

/**
 * Clyde is a ghost that behaves in a very scared manner when close to a hunter.
 * When not chasing the hunter down, clyde likes to hang out in the bottom left
 * corner of the board in a orange glow.
 */
public class Clyde extends Ghost {

    /**
     * Get Clydes colour.
     * @return "#e78c45"
     */
    @Override
    public String getColour() {
        return "#e78c45";
    }

    /**
     * Get Clydes type/name.
     * @return GhostType.CLYDE;
     */
    @Override
    public GhostType getType() {
        return GhostType.CLYDE;
    }
}
