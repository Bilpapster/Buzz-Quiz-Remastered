import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ThermometerRound extends StandardRound {

    private HashMap<Player, Integer> correctAnswersInRound;
    private Boolean winnerFound;
    private int winningScore;

    public ThermometerRound(ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(0, players, questionManager, parser);
        this.creditPoints = 5000;
        this.winnerFound = false;
        this.winningScore = 5;
        correctAnswersInRound = new HashMap<>();

        for (Player player : players) {
            correctAnswersInRound.put(player, 0);
        }
    }

    /**
     * Printing method for the round description. Written hard coded TEMPORARILY.
     */
    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked questions, till someone reaches (at least) five correct answers!%n"
                + "But, be careful! You need to have at least one correct answer more than all the other players, in order to win the round!%n"
                + "The winner gets 5000 points!%n"
                + "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    public Boolean isOver() {
        return this.winnerFound;
    }

    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        System.out.print(player.getName() + ": Correct!");
        addOneCorrectAnswerTo(player);
        System.out.println();
    }

    @Override
    protected void executeActionsOnWrongAnswer(Player player) {
        System.out.print(player.getName() + ": Wrong...");
        System.out.println();
    }

    @Override
    protected void executeActionsOnEndOfQuestion() {
        HashSet<Player> playersReachedWinningZone = new HashSet<>();
        for (Player player : players) {
            if (correctAnswersInRound.get(player) == winningScore) {
                playersReachedWinningZone.add(player);
            }
        }

        if (playersReachedWinningZone.size() == 1) {
            for (Player player : playersReachedWinningZone) {
                System.out.println(player.getName() + " is the winner of the round! +" + getCreditPoints());
                player.updateScore(creditPoints);
                winnerFound = true;
            }
        } else if (playersReachedWinningZone.size() > 1) {
            players.clear();
            players.addAll(playersReachedWinningZone);
            winningScore++;
        }

        for (Player player : players) {
            System.out.println(player.getName() + ": " + correctAnswersInRound.get((player)) + " correct answers in this round.");
        }
        questionManager.removeAnsweredQuestion();
    }

    private void addOneCorrectAnswerTo(Player player) {
        correctAnswersInRound.put(player, correctAnswersInRound.get(player) + 1);
    }
}
