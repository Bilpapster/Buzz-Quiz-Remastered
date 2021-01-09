package com;


/**
 * A simple class representing a player in our game
 *
 * @author Fotios-Dimitrios Malakis
 */
public class Player {
    private int score;
    private String name;


    /**
     * Creates a player object with the given name and initializes the score to 0
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    /**
     * Constructor for debugging purposes. To be removed for final version.
     * @param name
     * @param score
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Getter for the score attribute.
     *
     * @return the current score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the name attribute.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the score of the player, either by reducing it or increasing it
     *
     * @param points the amount of points to add (if its a positive value) or subtract (if its a negative value)
     */
    public void updateScore(int points) {
        score += points;
    }
}
