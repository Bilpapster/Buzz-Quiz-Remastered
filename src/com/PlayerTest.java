package com;

/**
 * A second (temporary) class for representing a player in our game. Based on the original com.Player class,
 * com.PlayerTest has just a few additions, in order to test com.Parser class. Only for testing purposes.
 */
public class PlayerTest {
    private int score; // same as com.Player
    private String name; // same as com.Player

    private Parser parser = new Parser(); // initialization was missing!

    /**
     * same as com.Player
     * @param name 
     */
    public PlayerTest(String name) {
        this.score = 0;
        this.name = name;
    }

    /**
     * Creates a com.PlayerTest object with a name given by user and initializes the score to 0.
     * Utilizes the com.Parser class
     */
    public PlayerTest() {
        this.score = 0;
        this.name = parser.askForName();
    }

    /**
     * same as com.Player
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * same as com.Player
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * same as com.Player
     */
    public void printScore() {
        System.out.printf("%s's score: %d points.%n", name, score);
    }

    /**
     * same as com.Player
     * @param points
     */
    public void updateScore(int points) {
        score += points;
    }

}
