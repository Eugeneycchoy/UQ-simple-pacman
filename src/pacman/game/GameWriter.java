package pacman.game;

import pacman.board.PacmanBoard;
import pacman.ghost.Ghost;
import pacman.ghost.GhostType;
import pacman.score.ScoreBoard;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

/**
 * Writes the PacmanGame to a standard format.
 * @ass2
 */
public class GameWriter {

    public static void write(Writer writer, PacmanGame game)
            throws IOException {
        writeBoardBlock(writer, game.getBoard());
        writer.write(System.lineSeparator());
        writeGameBlock(writer, game);
        writer.write(System.lineSeparator());
        writeScoresBlock(writer, game.getScores());
    }

    /*
     * Writes out a Board Block.
     */
    private static void writeBoardBlock(Writer writer, PacmanBoard board)
            throws IOException {

        writer.write(String.format("[Board]%n"));
        writer.write(String.format("%d,%d%n",
                board.getWidth(), board.getHeight()));
        writer.write(board.toString());
        writer.write(System.lineSeparator());
    }

    /*
     * Writes out a scores block.
     */
    private static void writeScoresBlock(Writer writer, ScoreBoard scores)
            throws IOException {

        writer.write(String.format("[Scores]%n"));

        // separate score entries with newlines
        String output = String.join(System.lineSeparator(), scores.getEntriesByName());
        writer.write(output);
    }

    /*
     * Writes out a Game block.
     */
    private static void writeGameBlock(Writer writer, PacmanGame game)
            throws IOException {

        writer.write(String.format("[Game]%n"));
        writer.write(String.format("title = %s%n",
                game.getTitle()));
        writer.write(String.format("author = %s%n",
                game.getAuthor()));
        writer.write(String.format("lives = %d%n",
                game.getLives()));
        writer.write(String.format("level = %d%n",
                game.getLevel()));
        writer.write(String.format("score = %d%n",
                game.getScores().getScore()));

        writer.write(String.format("hunter = %s%n",
                game.getHunter().toString()));

        var ghostMap = new HashMap<GhostType, Ghost>();

        for (var ghost : game.getGhosts()) {
            ghostMap.put(ghost.getType(), ghost);
        }

        var ghosts = List.of(
                GhostType.BLINKY, GhostType.INKY,
                GhostType.PINKY, GhostType.CLYDE);

        for (var ghostType : ghosts) {
            var ghost = ghostMap.get(ghostType);
            writer.write(String.format("%s = %s%n",
                    ghost.getType().toString().toLowerCase(),
                    ghost.toString()));
        }
    }

}
