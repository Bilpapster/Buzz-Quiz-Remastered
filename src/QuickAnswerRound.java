import java.util.ArrayList;
import java.util.HashMap;

public class QuickAnswerRound extends StopTheClockRound {

    public QuickAnswerRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        this.creditPoints = 1000;
    }

    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestionsInRound() + " questions.%n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!%n" +
                "The quicker to answer the question correctly gets 1000 points, while the second correct player gets the half of them, 500!%n" +
                "No points available for other players, even if they answer correctly, so be as quick as possible!%n" +
                "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {

        if (getAnsweringOrderOf(player) == 1) {
            System.out.print(player.getName() + ": Correct! +" + creditPoints);
            player.updateScore(creditPoints);
            System.out.println();
        } else if (getAnsweringOrderOf(player) == 2) {
            System.out.print(player.getName() + ": Correct! +" + (creditPoints / 2));
            player.updateScore(creditPoints / 2);
            System.out.println();
        } else {
            System.out.print(player.getName() + ": Correct! No points because you answered in order " + getAnsweringOrderOf(player) + ", looser!");
            System.out.println();
        }

    }

    protected int getAnsweringOrderOf(Player player) {
        int answeringOrder = 1;
        for (Player otherPlayer : players) {
            if (milliSecondsElapsedOnAnswer.get(otherPlayer) < milliSecondsElapsedOnAnswer.get(player)) {
                answeringOrder++;
            }
        }
        return answeringOrder;
    }
}
