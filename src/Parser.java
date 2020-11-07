import java.util.Scanner;

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
     *
     * @return a valid answer given by user
     */
    public String askForAnswer() {
        System.out.print("Your answer: ");
        String givenAnswer = inputScanner.nextLine();

        while (!isValidAnswer(givenAnswer)) {
            System.out.print("Invalid answer. Valid options: [a, b, c, d]. Your answer: ");
            givenAnswer = inputScanner.nextLine();
        }
        return givenAnswer;
    }

    /**
     * Checks whether a given answer is valid or not. The code can still become a lot smarter.
     *
     * @param givenAnswer a string to check its validity as an answer
     * @return true if the string is a valid answer, else false.
     */
    public Boolean isValidAnswer(String givenAnswer) {

        //TODO (papster) utilize keySet in place of this shit code

        return (givenAnswer.equals("a")
                || givenAnswer.equals("b")
                || givenAnswer.equals("c")
                || givenAnswer.equals("d"));
    }
}