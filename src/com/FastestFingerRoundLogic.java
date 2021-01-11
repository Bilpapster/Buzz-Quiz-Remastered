package com;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>A class that represents the logic core of the round type "Fastest Finger" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
 * At least two players are needed for this round. The round is timed.
 * The class extends the <code>StopTheClockRoundLogic</code> class, extending its functionality, in order to
 * comply with the following round rules.</p>
 * <p>The player(s) are asked a number of questions (5) and earn credit points on correct answer. The amount of credit
 * points is defined by the order of answering. The first player to answer the question correctly gets 1000 credit
 * points. The second player to answer the question correctly gets 500 points. No points for the remaining players,
 * even if they have answered correctly</p>
 * <p>There is no point penalty on wrong answer.</p>
 *
 * @author Vasilis Papastergios
 * @see StopTheClockRoundLogic
 */
public class FastestFingerRoundLogic extends StopTheClockRoundLogic {

    /* stores the players that have answered correctly the current question. */
    private HashSet<Player> playersAnsweredCorrectly;

    /* stores the order of answering among the players that have answered correctly the current question */
    private HashMap<Player, Integer> orderOfAnsweringCorrectly;

    /**
     * Constructs a <code>FastestFingerRoundLogic</code> object with given attributes.
     *
     * @param numberOfQuestions the number of questions in round
     * @param referee           the referee of the round
     */
    public FastestFingerRoundLogic(int numberOfQuestions, Referee referee) {
        super(numberOfQuestions, referee);
        playersAnsweredCorrectly = new HashSet<>();
        orderOfAnsweringCorrectly = new HashMap<>();
        this.creditPoints = 1000;
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {

        return ("In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() + " questions. " +
                "You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. There is a 3-seconds time " +
                "interval for the players to read the question. At the end of this interval, the available options " +
                "are revealed and the clock starts running\n " +
                "The quicker to answer the question correctly gets 1000 points, while the second correct player gets " +
                "half of them, 500!\n" +
                "No points available for other players, even if they answer correctly, so be as quick as possible!\n");
    }

    /**
     * Getter for the official name of the round, as presented in the original .
     * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
     *
     * @return the official name of the round as String
     */
    @Override
    public String getOfficialName() {
        return "Fastest Finger";
    }

    /**
     * <p>Takes care of updating the player(s)' score, based on the data stored in the referee object.
     * Update is done according to the round rules: The first player to answer the question correctly gets 1000 credit
     * points. The second player to answer the question correctly gets 500 points. No points for the remaining players,
     * even if they have answered correctly.</p>
     * <p>The method should be invoked only when the referee object has stored all the needed data for the current question,
     * as well as the player(s)' answers. The method should be invoked once for every question in round, just before
     * displaying the next question.</p>
     */
    @Override
    public void giveCredits() {

        this.playersAnsweredCorrectly.clear();
        for (Player player : referee.getAlivePlayersInRound()) {
            if (referee.hasPlayerAnsweredCorrectly(player)) {
                playersAnsweredCorrectly.add(player);
            }
        }
        refreshOrder();
        super.giveCredits();
    }

    /**
     * Private utility method that decides the order of answering among the players that answered correctly the current
     * question.
     */
    private void refreshOrder() {
        this.orderOfAnsweringCorrectly.clear();
        for (Player player : playersAnsweredCorrectly) {
            int answeringOrder = 1;
            for (Player otherPlayer : playersAnsweredCorrectly) {
                if (referee.getTimeElapsedOnAnswerForPlayer(otherPlayer) > referee.getTimeElapsedOnAnswerForPlayer(player)) {
                    answeringOrder++;
                }
            }
            orderOfAnsweringCorrectly.put(player, answeringOrder);
        }
    }

    /**
     * Executes all necessary actions on a player that has answered the current question correctly.
     * If the player was the fastest to answer correctly, they get rewarded by 1000 points.
     * In case the player was the second fastest to answer correctly, they get rewarded by 500 points.
     * No points reward in any other case, despite possible correct answer.
     *
     * @param player the player that has answered correctly the current question
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        if (orderOfAnsweringCorrectly.get(player) == 1) {
            player.updateScore(creditPoints);
        } else if (orderOfAnsweringCorrectly.get(player) == 2) {
            player.updateScore(creditPoints / 2);
        }
    }
}
