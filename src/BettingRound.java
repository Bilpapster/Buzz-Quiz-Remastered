import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * A class representing a specific type of round, where the players place a bet before each question.
 * The class is implemented as sub-class of the StandardRound class.
 */
public class BettingRound extends StandardRound {
    private TreeMap<String, Integer> acceptableBets; // stores the acceptable bets that players can place. Remains intact during round.
    private HashMap<Player, Integer> betsPlacedByPlayers; // stores the bets placed by players at the current question. Values change during round.

    /**
     * Constructs a BettingRound object with given attributes.
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object, responsible for communicating with players via console
     */
    public BettingRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        initializeAcceptableBetsSet();

        // initializes all players current bet to 0
        betsPlacedByPlayers = new HashMap<>();
        for (Player player : players) {
            betsPlacedByPlayers.put(player, 0);
        }
    }

    /**
     * Initializes the acceptable bets as a map structure. The structure remains intact till the end of the round.
     * Only for inside-class use.
     */
    private void initializeAcceptableBetsSet() {

        /* the TreeMap structure retains a specific order (descending) for the stored values.
        Here, TreeMap structure is preferred against HashMap, because of the capability of descending-order printing of acceptable bets.
        No big deal, just easier-on-the-eye printing of acceptable bets. */

        this.acceptableBets = new TreeMap<>();
        acceptableBets.put("250", 250);
        acceptableBets.put("500", 500);
        acceptableBets.put("750", 750);
        acceptableBets.put("1000", 1000);
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() + " questions.%n"
                + "At first, you are let to know the category of the question and you are asked to place a bet " + acceptableBets.keySet() +
                " Answering correctly will add to your score as many points as your bet!%n" +
                " But, be careful! Answering the question wrong will cost you your bet!%n");
    }

    /**
     * Displays the current question to players. At first, the question category is displayed and the players are asked to place a bet.
     * After the bet placement, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();

        /* for every player involved in the round, stores their bet for the current question.
        The bets given by players are parsed as Strings, so they need to be converted to Integer, before stored in HashMap. */

        for (Player player : players) {
            System.out.print(player.getName() + ", it is your turn. ");
            betsPlacedByPlayers.put(player, Integer.parseInt(parser.askForBet(acceptableBets.keySet())));
        }

        questionManager.getNextQuestion().displayQuestionBody();
        questionManager.getNextQuestion().displayOptions();
    }

    /**
     * Executes all necessary actions on a player that has answered the current question correctly.
     * Prints success message and updates the player's score, by adding the bet amount.
     * Only for inside-class and sub-classes use.
     *
     * @param player a player that has answered correctly a question in round
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        System.out.print(player.getName() + ": Correct! +" + this.betsPlacedByPlayers.get(player));
        player.updateScore(this.betsPlacedByPlayers.get(player));
        System.out.println();
    }

    /**
     * Executes all necessary actions on a player that has answered a question wrong.
     * Prints failure message and updates the player's score, by subtracting the bet amount.
     * Only for inside-class and sub-classes use.
     *
     * @param player a player that has answered wrong a question in round
     */
    @Override
    protected void executeActionsOnWrongAnswer(Player player) {
        System.out.print(player.getName() + ": Wrong... -" + this.betsPlacedByPlayers.get(player));
        player.updateScore(this.betsPlacedByPlayers.get(player) * (-1));
        System.out.println();
    }
}
