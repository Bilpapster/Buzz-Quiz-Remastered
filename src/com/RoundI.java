package com;

/**
 * An interface, representing a round in our game.
 */
public interface RoundI {

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    String getDescription();

    /**
     * Checks whether the round is over or not.
     *
     * @return true if the round is over, else false.
     */
    Boolean isOver();

    /**
     * Takes care of updating the players' score.
     */
    void giveCredits();
}
