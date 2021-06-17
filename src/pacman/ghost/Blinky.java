package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Position;

/**
 * Blinky is a ghost that behaves in a very aggressive manner towards a hunter.
 * When not chasing the hunter down, Blinky likes to hang out in the top right
 * corner of the board in a red glow.
 */
public class Blinky extends Ghost {

    /**
     * Blinky targets the hunters position. See: PacmanGame.getHunter()
     * @param game - to read positions from.
     * @return hunter position
     */
    public Position chaseTarget(PacmanGame game) {
        return game.getHunter().getPosition();
    }

    /**
     * Blinky's home position is one block outside of the top right of the game board.
     * The top left position of the board is (0, 0).
     * Note: this will be outside of the board.
     * @param game - to read the board from.
     * @return One diagonal block out from the top right corner.
     */
    public Position home(PacmanGame game) {
        Position blinkyHome = new Position(game.getBoard().getWidth()+1, -1);
        return blinkyHome;
    }

    /**
     * Get Blinkys colour.
     * @return "#d54e53"
     */
    @Override
    public String getColour() {
        return "#d54e53";
    }

    /**
     * Get Blinkys type/name.
     * @return GhostType.BLINKY;
     */
    @Override
    public GhostType getType() {
        return GhostType.BLINKY;
    }
}
