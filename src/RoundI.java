import java.util.ArrayList;

public interface RoundI {

    /**
     * Prints description of the round and/or any soecial instructions the player is needed to know.
     */
    void printDescription();

    /**
     * Returns the number of questions in the round.
     * @return number of questions in the round.
     */
    int getNumberOfQuestions();

    /**
     * Getter for the players structure of the round.
     * @return an ArrayList containing the the players involved in the round.
     */
    ArrayList<Player> getPlayers();

    /**
     * Lets the player(s) know the question (category, question body and available answers).
     */
    void askQuestion();

    /**
     * Collects the answer given by player(s).
     * @return a valid answer given by player(s)
     */
    String readAnswer();

    /**
     * Takes care of updating the player(s)' score, depending on comparing the given answer to the correct one.
     * @param givenAnswer the answer given by player(s).
     */
    void giveCredits(String givenAnswer);
}