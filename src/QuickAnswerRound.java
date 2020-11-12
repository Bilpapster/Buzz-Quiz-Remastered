import java.util.ArrayList;

/**
 * A class representing a specific type of round, where, besides answering correctly,
 * the order of players according to their answering time does matter and defines their reward.
 * The class is implemented as sub-class of the StopTheClock class.
 */
public class QuickAnswerRound extends StopTheClockRound {


    /**
     * Constructs a StopTheClockRound object with given attributes.
     *
     * @param numberOfQuestions the number of questions in round
     * @param players           the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object, responsible for communicating with players via console
     */
    public QuickAnswerRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
        this.creditPoints = 1000;
    }

    /**
     * Prints the round description. Asks from the player to press enter.
     */
    @Override
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestionsInRound() + " questions.%n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!%n" +
                "The quicker to answer the question correctly gets 1000 points, while the second correct player gets the half of them, 500!%n" +
                "No points available for other players, even if they answer correctly, so be as quick as possible!%n" +
                "Press enter to start round ");
        parser.getEnter();
    }

    /**
     * Executes all necessary actions on a player that has answered the current question correctly.
     * If the player was the fastest to answer correctly, they get rewarded by 1000 points.
     * In case the player was the second fastest to answer correctly, they get rewarded by 500 points.
     * No points reward in any other case, despite answering correctly.
     *
     * @param player the player that has answered correctly the current question
     */
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

    /**
     * Based on the milliseconds elapsed for the players to answer the current question,
     * returns the order of the given player. Returns 1 for the fastest and <numberOfPlayers> for the least fast.
     *
     * @param player the player to get the order of
     * @return the player's order of answer
     */
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
