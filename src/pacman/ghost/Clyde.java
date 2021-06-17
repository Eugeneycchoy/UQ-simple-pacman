package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Position;

/**
 * Clyde is a ghost that behaves in a very scared manner when close to a hunter.
 * When not chasing the hunter down, clyde likes to hang out in the bottom left
 * corner of the board in a orange glow.
 */
public class Clyde extends Ghost {

    /**
     * Clyde will target the hunter if equal to or greater than a distance of 8 away from the hunter.
     * Otherwise if closer than 8 it will target its home position.
     * See: home(PacmanGame) See: PacmanGame.getHunter()
     * @param game - to read positions from.
     * @return home if less than 8 away from hunter,
     * otherwise hunter position.
     */
    public Position chaseTarget(PacmanGame game) {
        Position target;
        if (game.getHunter().getPosition().distance(this.getPosition()) >= 8) {
            target = game.getHunter().getPosition();
        } else {
            target = this.home(game);
        }
        return target;
    }

    /**
     * Clyde's home position is one block outside of the bottom left of the game board.
     * Where the top left position of the board is (0, 0).
     * Note: this will be outside of the board.
     * @param game - to read the board from.
     * @return One diagional block out from the bottom left corner.
     */
    public Position home(PacmanGame game) {
        return new Position(game.getBoard().getWidth() - 1,
                game.getBoard().getHeight() + 1);
    }

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
