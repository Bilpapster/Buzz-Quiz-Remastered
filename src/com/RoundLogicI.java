package com;

/**
 * An interface that represents the logic core of a round type in our game.
 *
 * @author Vasilis Papastergios
 */
public interface RoundLogicI {

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
