import java.util.ArrayList;

public class StopTheClockRound extends StandardRound {

    private long miliSecondsElapsedOnAnswer;

    public StopTheClockRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        this.miliSecondsElapsedOnAnswer = 0;
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
        this.pointsEarnedOnCorrectAnswer = (int) ((5000 - miliSecondsElapsedOnAnswer) * 0.2);
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
        this.miliSecondsElapsedOnAnswer = System.currentTimeMillis() - start;
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
