import java.util.Scanner;
import java.util.Set;

/**
 * A simple class representing a parsing object in our game.
 * Objects of this class serve on communicating with user via console.
 */
public class Parser {
    private Scanner inputScanner;

    /**
     * Creates a parsing object for communication via the default input stream
     */
    public Parser() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Shows a helping message to user. Asks for the player's name.
     *
     * @return the players name given by user
     */
    public String askForName() {
        System.out.print("State your name player: ");
        return inputScanner.nextLine();
    }

    /**
     * Shows a helping message to user. Asks for their answer.
     * Does answer validation check, so that it is one of the acceptable ones.
     * The answer is asked repeatedly, until an accepted one is given.
     *
     * @return a valid answer given by user.
     */
    public String askForAnswer(Set<String> answerKeySet) {
        System.out.print("Your answer: ");
        String givenAnswer = inputScanner.nextLine();

        while (!answerKeySet.contains(givenAnswer)) {
            System.out.print("Invalid answer. Valid options: " + answerKeySet.toString() + ". Your answer: ");
            givenAnswer = inputScanner.nextLine();
        }
        return givenAnswer;
    }

    /**
     * Shows a helping message to user. Asks for their bet.
     * Does answer validation check, so that it is one of the acceptable ones.
     * The bet is asked repeatedly, until an acceptable one is given.
     *
     * @param acceptableBetsSet a set of acceptable bets to ask for
     * @return a valid bet given by user.
     */
    public String askForBet(Set<String> acceptableBetsSet) {
        System.out.print("Place your bet: ");
        String givenBet = inputScanner.nextLine();

        while (!acceptableBetsSet.contains(givenBet)) {
            System.out.print("Invalid answer. Valid options: " + acceptableBetsSet.toString() + ". Your bet: ");
            givenBet = inputScanner.nextLine();
        }
        return givenBet;
    }

    /**
     * very simple, auxiliary method for pausing game until player presses enter button
     */
    public void getEnter() {
        inputScanner.nextLine();
    }
}