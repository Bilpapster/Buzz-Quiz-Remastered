import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BettingRound extends StandardRound {
    private Set<String> acceptableBets;

    public BettingRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        initializeAcceptableBetsSet();
    }

    private void initializeAcceptableBetsSet() {
        this.acceptableBets = new HashSet<String>();
        acceptableBets.add("250");
        acceptableBets.add("500");
        acceptableBets.add("750");
        acceptableBets.add("1000");
    }

    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestions() + " questions.%n"
                + "At first, you are let to know the category of the question and you are asked to place a bet " + acceptableBets.toString() +
                " Answering correctly will add to your score as many points as your bet!%n" +
                " But be careful! Answering the question wrong will cost you your bet!%n"
                + "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        this.setPointsEarnedOnCorrectAnswer(Integer.parseInt(parser.askForBet(acceptableBets)));
        questionManager.getNextQuestion().displayQuestionBody();
        questionManager.getNextQuestion().displayOptions();
    }

    @Override
    public void giveCredits(String givenAnswer) {
        if (questionManager.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (Player player : players) {
                System.out.println("Correct! +" + getPointsEarnedOnCorrectAnswer() + " points!");
                player.updateScore(getPointsEarnedOnCorrectAnswer());
            }
        } else {
            for (Player player : players) {
                System.out.println("Wrong...! - "+ getPointsEarnedOnCorrectAnswer() + " points!");
                player.updateScore(getPointsEarnedOnCorrectAnswer() * (-1));
            }
        }
        questionManager.removeAnsweredQuestion();
    }
}
