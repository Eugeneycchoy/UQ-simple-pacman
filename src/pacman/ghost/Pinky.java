package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Position;

/**
 * Pinky is a cunning ghost that tries to ambush the hunter.
 * When not chasing the hunter down, Pinky likes to hang out in the
 * top left corner of the board in a pink glow.
 */
public class Pinky extends Ghost {

    /**
     * Pinky will chase 4 blocks in front of the hunter's current direction.
     * @param game - to read positions from.
     * @return the position 4 blocks in front of hunter position.
     */
    public Position chaseTarget(PacmanGame game) {
        Position target = game.getHunter().getPosition();
        return target.add(game.getHunter().getDirection().offset().multiply(4));
    }

    /**
     * Pinky's home position is one block outside of
     * the top left of the game board.
     * Where the top left position of the board is (0, 0).
     * Note: this will be outside of the board.
     * @param game - to read the board from.
     * @return One diagional block out from the top left corner.
     */
    public Position home(PacmanGame game) {
        return new Position(game.getBoard().getWidth() - 1, game.getBoard().getHeight() - 1);
    }

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
