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

    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() + " questions. You will have 5 seconds to answer!%n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!%n" +
                "Answering the question correctly will add to your score as many points as the remaining  miliseconds times 0.2!%n" +
                "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        questionManager.getNextQuestion().displayQuestionBody();
        parser.getEnter();
        questionManager.getNextQuestion().displayOptions();
    }

    @Override
    public void readAnswers() {
        for (Player player : players) {
            System.out.print(player.getName() + ", it is your turn. ");
            Long startTimeInMillis = System.currentTimeMillis();
            answersGivenByPlayers.put(player, parser.askForAnswer(questionManager.getNextQuestion().getAnswerKeySet()));
            this.milliSecondsElapsedOnAnswer.put(player, System.currentTimeMillis() - startTimeInMillis);
        }
    }

    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        creditPoints = (int) (this.milliSecondsElapsedOnAnswer.get(player) * 0.2);
        System.out.print(player.getName() + ": Correct! +" + creditPoints);
        player.updateScore(creditPoints);
        System.out.println();
    }
}
