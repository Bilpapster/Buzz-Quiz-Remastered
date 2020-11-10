import java.util.ArrayList;
import java.util.TreeMap;

/**
 * A class representing a specific type of round, where the players place a bet for each question.
 * The class is implemented as sub-class of the StandardRound class.
 */
public class BettingRound extends StandardRound {
    private TreeMap<String, Integer> acceptableBets;

    /**
     * Constructs a BettingRound object with given attributes.
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with player(s) via console
     */
    public BettingRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        initializeAcceptableBetsSet();
    }

    /**
     * Initializes the acceptable bets as a map structure.
     */
    private void initializeAcceptableBetsSet() {
        this.acceptableBets = new TreeMap<String, Integer>();
        acceptableBets.put("250", 250);
        acceptableBets.put("500", 500);
        acceptableBets.put("750", 750);
        acceptableBets.put("1000", 1000);
    }

    /**
     * Prints the round description. Asks for the player to press enter.
     */
    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestions() + " questions.%n"
                + "At first, you are let to know the category of the question and you are asked to place a bet " + acceptableBets.keySet() +
                " Answering correctly will add to your score as many points as your bet!%n" +
                " But be careful! Answering the question wrong will cost you your bet!%n"
                + "Press enter to start round ");
        parser.getEnter();
    }

    /**
     * Displays the question to player(s). At first, the question category is displayed and the player(s) are asked to place a bet.
     * After the bet placement, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        this.setPointsEarnedOnCorrectAnswer(Integer.parseInt(parser.askForBet(acceptableBets.keySet())));
        questionManager.getNextQuestion().displayQuestionBody();
        questionManager.getNextQuestion().displayOptions();
    }

    /**
     *
     * @param givenAnswer the answer to compare against the correct one, in order to give credits or not
     */
    @Override
    public void giveCredits(String givenAnswer) {
        if (questionManager.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (Player player : players) {
                System.out.println("Correct! +" + getPointsEarnedOnCorrectAnswer() + " points!");
                player.updateScore(getPointsEarnedOnCorrectAnswer());
            }
        } else {
            for (Player player : players) {
                System.out.println("Wrong...! - " + getPointsEarnedOnCorrectAnswer() + " points!");
                player.updateScore(getPointsEarnedOnCorrectAnswer() * (-1));
            }
        }
        questionManager.removeAnsweredQuestion();
    }
}