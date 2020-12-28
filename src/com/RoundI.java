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
     * Getter for the official name of the round
     *
     * @return the official name of the round
     */
    String getOfficialName();

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
