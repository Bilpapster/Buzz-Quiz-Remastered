import java.util.ArrayList;
import java.util.Scanner;

public class StandardRound implements RoundI {

    private int numberOfQuestions;
    private ArrayList<Player> players;
    private QuestionManager questionTeller;
    private Parser parser;
    private static final int pointsEarnedOnCorrectAnswer = 1000;

    /**
     * Constructs a StandardRound object with given attirbutes
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           array list of the players involved in the round
     * @param questionTeller    a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with player(s) via console
     */
    public StandardRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionTeller, Parser parser) {
        this.numberOfQuestions = numberOfQuestions;
        this.players = players;
        this.questionTeller = questionTeller;
        this.parser = parser;
    }

    /**
     * Getter for the round description. Written hard coded TEMPORARILY.
     */
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestions() + " questions.%n"
                + "For every correct answer, you earn " + this.pointsEarnedOnCorrectAnswer + " points!%n"
                + "Press enter to start round ");
        parser.getEnter();
    }

    /**
     * Getter for the number of questions in the round.
     *
     * @return the number of questions in the round
     */
    @Override
    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    /**
     * Getter for the players involved in the round
     *
     * @return an array list containing the players involved in the round
     */
    @Override
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Displays the question to be answered, as well as the available choices.
     * The category, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionTeller.getNextQuestion().displayCategory();
        questionTeller.getNextQuestion().displayQuestionBody();
        questionTeller.getNextQuestion().displayOptions();
    }

    /**
     * Reads the answer given by player, executing data validation.
     *
     * @return a valid answer given by the player.
     */
    @Override
    public String readAnswer() {
        return parser.askForAnswer(questionTeller.getNextQuestion().getAnswerKeySet());
    }

    /**
     * Updates the points of the player (if needed).
     * Prints a message, based on the correctness of the given answer.
     * Discards the current question as answered, so that it does not show up again, during the game.
     *
     * @param givenAnswer the answer to compare against the correct one, in order to give credits or not
     */
    @Override
    public void giveCredits(String givenAnswer) {
        if (questionTeller.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (Player player : players) {
                System.out.println("Correct!");
                player.updateScore(1000);
            }
        } else {
            System.out.println("Wrong...");
        }
        questionTeller.removeAnsweredQuestion();
    }
}


