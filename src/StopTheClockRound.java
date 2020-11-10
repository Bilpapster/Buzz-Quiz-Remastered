import java.util.ArrayList;

/**
 * A class representing a specific type of round.
 */
public class StopTheClockRound extends StandardRound {

    private long milliSecondsElapsedOnAnswer;

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
        this.milliSecondsElapsedOnAnswer = 0;
    }

    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestions() + " questions. You will have 5 seconds to answer!%n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!%n" +
                "Answering the question correctly will add to your score as many points as the remaining  miliseconds times 0.2!%n" +
                "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    protected void setPointsEarnedOnCorrectAnswer(int pointsEarnedOnCorrectAnswer) {
        this.pointsEarnedOnCorrectAnswer = (int) ((5000 - milliSecondsElapsedOnAnswer) * 0.2);
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
    public String readAnswer() {
        long start = System.currentTimeMillis();
        String answerToReturn = super.readAnswer();
        this.milliSecondsElapsedOnAnswer = System.currentTimeMillis() - start;
        this.setPointsEarnedOnCorrectAnswer(0);
        return answerToReturn;
    }

    @Override
    public void giveCredits(String givenAnswer) {
        if (questionManager.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (Player player : players) {
                System.out.println("Correct! +" + getPointsEarnedOnCorrectAnswer() + " points!");
                player.updateScore(getPointsEarnedOnCorrectAnswer());
            }
        } else {
            System.out.println("Wrong...");
        }
        questionManager.removeAnsweredQuestion();
    }
}