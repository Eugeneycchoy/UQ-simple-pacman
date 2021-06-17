package pacman.game;

import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.ghost.*;
import pacman.hunter.Hunter;
import pacman.score.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * PacmanGame stores the game's state and acts as the model for the entire game.
 */
public class PacmanGame {

    // 4 lives for the player by default
    private final int DEFAULT_LIVES = 4;

    // 200 points for each ghost eaten
    private final int GHOST_SCORE = 200;

    private String title;
    private String author;
    private Hunter hunter;
    private PacmanBoard board;
    private List<Ghost> ghosts = new ArrayList<>();
    private int ticks = 0;
    private ScoreBoard scoreBoard = new ScoreBoard();
    private int level = 0;
    private int lives = DEFAULT_LIVES;

    /**
     * Creates a new game with the given parameters and
     * spawns one of each type of ghost (Blinky, Clyde, Inky, Pinky).
     * The ghosts should be spawned at the ghost spawn point.
     * @param title - of the game board.
     * @param author - of the game board.
     * @param hunter - for the current game.
     * @param board - to be copied for this game.
     */
    public PacmanGame(String title, String author, Hunter hunter, PacmanBoard board) {
        this.title = title;
        this.author = author;
        this.hunter = hunter;
        this.board = new PacmanBoard(board);
        ghosts.addAll(List.of(new Blinky(), new Pinky(), new Clyde(), new Inky()));
        for (Ghost ghost : ghosts) {
            ghost.setPosition(this.board.getGhostSpawn());
        }
    }

    /**
     * @return title of the map.
     * @ensures result != null
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return author of the map.
     * @ensures result != null
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Gets the current pacman board.
     * @return a mutable reference to the board.
     */
    public PacmanBoard getBoard() {
        return this.board;
    }

    /**
     * Gets the number of times that tick has been called in the current game.
     * @return the current game tick value.
     */
    public int getTick() {
        return this.ticks;
    }

    /**
     * Gets the current score board.
     * @return a mutable reference to the score board.
     */
    public ScoreBoard getScores() {
        return this.scoreBoard;
    }

    /**
     * Gets the current level of the game
     * @return current level of the game.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the level of the game.
     * @param level - to be set to.
     * @ensures newLevel = max(0, givenLevel)
     */
    public void setLevel(int level) {
        this.level = Math.max(0, level);
    }

    /**
     * Get the amount of lives the player currently has
     * @return amount of lives the player currently has.
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the lives of the current player.
     * @param lives - to be set to.
     * @ensures newLives = max(0, givenLives)
     */
    public void setLives(int lives) {
        this.lives = Math.max(0, lives);
    }

    /**
     * Gets the hunter
     * @return a mutable reference to the hunter.
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Gets the list of ghosts in the game
     * Note: Adding, removing elements to this list
     * should not affect the internal copy.
     * @return a list of ghosts in the game.
     */
    public List<Ghost> getGhosts() {
        return new ArrayList<>(ghosts);
    }

    /**
     *Tick If we do not have any lives (getLives() == 0) then do nothing. Otherwise we do the following in this order:
     * The Hunter moves Hunter.move(PacmanGame).
     * For each ghost in the game, call Hunter.hit(Ghost)
     * The Ghosts that are alive move on even ticks Ghost.move(PacmanGame) getTick().
     * For each Ghost in the game, call Hunter.hit(Ghost) on the game's hunter.
     * For each ghost which is dead:
     * Reset the ghost.
     * Set the ghost's position to the ghost spawn position on the current board.
     * Add 200 points to the score.
     * If the hunter is dead, then decrease the lives and reset all the entities and place them at their spawn points.
     * If the board is empty, then increase the level and set the ticks to 0 and reset the board and entities placing them at their spawn points.
     * If we did not increase the level then increase the tick value. See getTick()
     * Note: game should start at a tick count of zero.
     */
    public void tick() {
        if (lives == 0) {
            return; // do nothing
        }

        this.hunter.move(this);

        // Check if we killed any ghost
        for (var ghost : ghosts) {
            hunter.hit(ghost);
        }

        // move each ghost
        if (ticks % 2 == 0) {
            for (var ghost : ghosts) {
                if (!ghost.isDead()) {
                    ghost.move(this);
                }
            }
        }

        // check if pacman is colliding with a ghost
        for (var ghost : ghosts) {
            hunter.hit(ghost);
        }

        // respawn ghosts.
        for (var ghost : ghosts) {
            if (ghost.isDead()) {
                ghost.reset();
                ghost.setPosition(board.getGhostSpawn());
                scoreBoard.increaseScore(GHOST_SCORE);
            }
        }

        if (hunter.isDead()) {
            setLives(lives - 1);
            reset();
        }

        // next level
        if (board.isEmpty()) {
            setLevel(level + 1);
            reset();
        } else {
            ticks++;
        }
    }

    /**
     * Resets the Game in the following way:
     * - Lives is set to the default of 4.
     * - Level is set to 0.
     * - ScoreBoard is reset ScoreBoard.reset()
     * - PacmanBoard is reset PacmanBoard.reset()
     * - All entities are reset
     * - All entity positions are set to their spawn locations.
     * - The tick value is reset to zero.
     */
    public void reset() {
        this.lives = DEFAULT_LIVES;
        this.level = 0;
        this.scoreBoard.reset();
        this.board.reset();
        this.hunter.reset();
        this.hunter.setPosition(this.getBoard().getPacmanSpawn());
        for (Ghost ghost : ghosts) {
            ghost.reset();
            ghost.setPosition(this.getBoard().getGhostSpawn());
        }
        this.ticks = 0;
    }

    /**
     * For each ghost in the game,
     * set its phase to be Phase.FRIGHTENED with a duration
     * of Phase.FRIGHTENED.getDuration();
     */
    public void setGhostsFrightened() {
        for (Ghost ghost : ghosts) {
            ghost.setPhase(Phase.FRIGHTENED, Phase.FRIGHTENED.getDuration());
        }
    }

}
