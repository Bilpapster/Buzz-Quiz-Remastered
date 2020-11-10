import java.util.ArrayList;

/**
 * An interface, representing a round in our game.
 */
public interface RoundI {

    /**
     * Prints description of the round and/or any special instructions the player is needed to know.
     */
    void printDescription();

    /**
     * Checks whether the round is over or not.
     *
     * @return true if the round is over, else false.
     */
    Boolean isOver();

    /**
     * Getter for the players structure of the round.
     *
     * @return an ArrayList containing the the players involved in the round.
     */
    ArrayList<Player> getPlayers();

    /**
     * Lets the player(s) know the question (category, question body and available answers).
     */
    void askQuestion();

    /**
     * Collects the answers given by players involved in the round.
     */
    void readAnswers();

    /**
     * Takes care of updating the players' score.
     */
    void giveCredits();
}
