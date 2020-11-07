/**
 * A second (temporary) class for representing a player in our game. Based on the original Player class,
 * PlayerTest has just a few additions, in order to test Parser class. Only for testing purposes.
 */
public class PlayerTest {
    private int score; // same as Player
    private String name; // same as Player

    private Parser parser; // added to test Parser class

    /**
     * same as Player
     * @param name
     */
    public PlayerTest(String name) {
        this.score = 0;
        this.name = name;
    }

    /**
     * Creates a PlayerTest object with a name given by user and initializes the score to 0.
     * Utilizes the Parser class
     */
    public PlayerTest() {
        this.score = 0;
        this.name = parser.askForName();
    }

    /**
     * same as Player
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * same as Player
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * same as Player
     */
    public void printScore() {
        System.out.printf("%s's score: %d points.%n", name, score);
    }

    /**
     * same as Player
     * @param points
     */
    public void updateScore(int points) {
        score += points;
    }

}
