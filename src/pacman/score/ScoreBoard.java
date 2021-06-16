package pacman.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * ScoreBoard contains previous scores and
 * the current score of the PacmanGame.
 * A score is a name and value that a
 * valid name only contains the following characters:
 * A to Z
 * a to z
 * 0 to 9
 * and must have a length greater than 0.
 * The value is a integer that is equal to or greater than 0.
 */
public class ScoreBoard {
    private Map<String, Integer> scores;
    private int currentOverallScore;

    /**
     * Creates a score board that has no entries and a current score of 0.
     */
    public ScoreBoard() {
        scores = new TreeMap<>();
        currentOverallScore = 0;
    }

    /**
     * Gets the stored entries ordered by Name in lexicographic order. The format of the list should be:
     * Score name with a single space afterwards
     * A single colon
     * A space then the value of the score with no leading zeros.
     * Example:
     *
     *      ScoreBoard board = new ScoreBoard();
     *      board.setScore("Fred", 100);
     *      board.setScore("fred", 20);
     *      board.setScore("Fred", 24);
     *
     *      List<String> scores = board.getEntriesByName();
     *      System.out.println(scores);
     *
     *      // this outputs:
     *      // [Fred : 24, fred : 20]
     *
     * @return List of scores formatted as
     * "NAME : VALUE" in the order described above or an empty list if no entries are stored.
     */
    public List<String> getEntriesByName() {
        List<String> result = new ArrayList<>();
        for (String name : scores.keySet()) {
            result.add(name + " : " + scores.get(name));
        }
        return result;
    }

    /**
     * Gets the stored entries ordered by the score in descending order ( 9999 first then 9998 and so on ...) then in lexicographic order of the name if the scores match. The format of the list should be:
     * Score name with a single space afterwards
     * A single colon
     * A space then the value of the score with no leading zeros.
     * Example:
     *
     *      ScoreBoard board = new ScoreBoard();
     *      board.setScore("Alfie", 100);
     *      board.setScore("richard", 20);
     *      board.setScore("Alfie", 24);
     *      board.setScore("ben", 20);
     *
     *      List<String> scores = board.getEntriesByScore();
     *      System.out.println(scores);
     *
     *      // this outputs
     *      // [Alfie : 24, ben : 20, richard : 20]
     *
     * @return List of scores formatted
     * as "NAME : VALUE" in the order described above or an empty list if no entries are stored.
     */
    public List<String> getEntriesByScore() {
        var items = new ArrayList<>(scores.entrySet());
        items.sort((o1, o2) -> {
            var valCompare = o2.getValue().compareTo(o1.getValue());

            // Compare by name is score is the same
            if (valCompare == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }

            return valCompare;
        });

        var scores = new ArrayList<String>();
        for (var entry : items) {
            scores.add(String.format("%s : %s", entry.getKey(),
                    entry.getValue()));
        }

        return scores;
    }

    /**
     * Sets the score for the given name if:
     * - name is not null
     * - name is a valid score name
     * - score is equal to or greater than zero.
     * This should override any score stored for the given name if name and score are valid.
     *
     * @param name - of scorer.
     * @param score - to set to the given name.
     */
    public void setScore(String name, int score) {
        if ((name != null) && (validName(name)) && (score >= 0)) {
            scores.put(name, score);
        }
    }

    /**
     * Sets a collection of scores if "scores" is not null,
     * otherwise no scores are modified. For each score contained in the scores if:
     * - name is not null
     * - name is a valid score name
     * - score is equal to or greater than zero.
     *
     * the score will be set and override any stored score for the given name,
     * otherwise it will be skipped.
     * @param scores - to add.
     */
    public void setScores(Map<String, Integer> scores) {
        if (scores != null) {
            for (String name : scores.keySet()) {
                if ((!(name == null)) && validName(name) && (scores.get(name) >= 0)) {
                    this.scores.put(name, scores.get(name));
                }
            }
        }
    }

    /**
     * Increases the score if the given additional is greater than 0.
     * No change to the current score if additional is less than or equal to 0.
     * @param additional - score to add.
     */
    public void increaseScore(int additional) {
        if (additional > 0) {
            this.currentOverallScore += additional;
        }
    }

    /**
     * Get the current score.
     * @return the current score.
     */
    public int getScore() {
        return this.currentOverallScore;
    }

    /**
     * Set the current score to 0.
     */
    public void reset() {
        this.currentOverallScore = 0;
    }

    /**
     * Checks if the name is of valid input range
     * @param name - name to be checked
     * @return true if it is valid, false otherwise.
     */
    private boolean validName(String name) {
        // regex checks string contains only alphanumericals, and at least 1.
        return name.matches("[a-zA-Z0-9]+");
    }
}
