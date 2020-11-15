import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a specific type of round.
 */
public class StopTheClockRound extends StandardRound {

    protected HashMap<Player, Long> milliSecondsElapsedOnAnswer;

    /**
     * Constructs a StopTheClockRound object with given attributes.
     *
     * @param numberOfQuestions the number of questions in the round.
     * @param players           the players involved in the round.
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with player(s) via console
     */
    public StopTheClockRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        this.milliSecondsElapsedOnAnswer = new HashMap<>();
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() + " questions. You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!\n" +
                "Answering the question correctly will add to your score as many points as the remaining  miliseconds times 0.2!\n");
    }

    /**
     * Displays the current question to players. The category and the question body are displayed at first.
     * The players are asked to press enter, in order for the available options to show up.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        questionManager.getNextQuestion().displayQuestionBody();
        parser.getEnter();
        questionManager.getNextQuestion().displayOptions();
    }

    /**
     * Reads the answers given by players, executing data validation.
     * Stores their answers to the answers' HashMap, by over-writing the new answers on the already stored answers from the previous question.
     */
    @Override
    public void readAnswers() {
        for (Player player : players) {
            System.out.print(player.getName() + ", it is your turn. ");
            long startTimeInMillis = System.currentTimeMillis();
            answersGivenByPlayers.put(player, parser.askForAnswer(questionManager.getNextQuestion().getAnswerKeySet()));
            this.milliSecondsElapsedOnAnswer.put(player, System.currentTimeMillis() - startTimeInMillis);
        }
    }

    /**
     * Executes all necessary actions on a player that has answer correctly a question of the round.
     * Prints a success message and updates the player's score, by adding credit points, according to how fast the player had answered.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered a question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        creditPoints = 5000 - ((int) (this.milliSecondsElapsedOnAnswer.get(player) * 0.2));
        System.out.print(player.getName() + ": Correct! +" + creditPoints);
        System.out.println(" Answering time: " + ((long) (milliSecondsElapsedOnAnswer.get(player)/1000)) + " seconds.");
        player.updateScore(creditPoints);
        System.out.println();
    }
}
