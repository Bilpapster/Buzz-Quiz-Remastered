package com;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>A class that represents the logic core of the round type "Boiling point" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. At least two players are
 * needed for this round.</p>
 *
 * <p>The class extends the <code>PointBuilderRoundLogic</code> class, extending its functionality, in order to
 * comply with the following round rules.</p>
 *
 * <p>The player(s) are asked questions until one of them reaches 5 correct answers. The player is rewarded with 5000
 * points, while the other players gain no credit points. In case there are more than one players that reach the
 * winning zone (5 correct answers) simultaneously, those players keep answering question until one of them reaches
 * higher score than the other ones. Only the players involved in draw keep playing, whilst the others are no longer
 * alive in the round (they can compete regularly in the next round).</p>
 *
 * @author Vasilis Papastergios
 * @see PointBuilderRoundLogic
 */
public class BoilingPointRoundLogic extends PointBuilderRoundLogic {

    /* stores the number of correct answers that every player involved in the round has accomplished up to that point */
    private HashMap<Player, Integer> numberOfCorrectAnswersInRound;

    /* stores whether the winner is found or not */
    private Boolean winnerFound;

    /* stores the winning score for the round. At start it is 5, but increases by one in case more than one players
     * reach 5 correct answers simultaneously */
    private int winningScore;

    /**
     * Constructs a PointBuilderRoundLogic object with given attributes.
     *
     * @param referee the referee of the round.
     */
    public BoilingPointRoundLogic(Referee referee) {
        super(0, referee);
        numberOfCorrectAnswersInRound = new HashMap<>();
        for (Player player : referee.getAlivePlayersInRound()) {
            numberOfCorrectAnswersInRound.put(player, 0);
        }
        this.winnerFound = false;
        this.winningScore = 5;
        this.creditPoints = 5000;
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("In this round you are going to be asked questions, till someone reaches (at least) five correct answers!\n"
                + "But, be careful! You need to have at least one correct answer more than all the other players, in order to win the round!\n"
                + "The winner gets 5000 points!\n");
    }

    /**
     * Getter for the official name of the round, as presented in the original .
     * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
     *
     * @return the official name of the round as String
     */
    @Override
    public String getOfficialName() {
        return "Boiling Point";
    }

    /**
     * Decides whether the round is finished or not.
     *
     * @return <code>true</code> if the round is over, else <code>false</code>.
     */
    @Override
    public Boolean isOver() {
        return this.winnerFound;
    }

    /**
     * For every player checks whether they have answered the current question correctly or not. based on the data
     * stored in the <code>referee</code> object.
     * Invokes necessary actions on both cases (correct or wrong answer).
     */
    @Override
    public void giveCredits() {
        for (Player player : referee.getAlivePlayersInRound()) {
            if (referee.hasPlayerAnsweredCorrectly(player)) {
                executeActionsOnCorrectAnswer(player);
            } else {
                executeActionsOnWrongAnswer(player);
            }
        }
        executeActionsOnEndOfQuestion();
    }

    /**
     * Executes all necessary actions on a player that has answer correctly a question of the round.
     * Updates the player's number of correct answers in round, by adding increasing by one the correct answers of them
     * in the round.
     *
     * @param player the player that has answered the current question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        addOneCorrectAnswerTo(player);
    }

    /**
     * Executes all necessary actions at the end of a question.
     * Checks if the winner of the round can be declared.
     */
    @Override
    protected void executeActionsOnEndOfQuestion() {
        ArrayList<Player> playersReachedWinningZone = new ArrayList<>();
        for (Player player : referee.getAlivePlayersInRound()) {
            if (numberOfCorrectAnswersInRound.get(player) == winningScore) {
                playersReachedWinningZone.add(player);
            }
        }

        if (playersReachedWinningZone.size() > 0) {
            if (playersReachedWinningZone.size() == 1) {
                for (Player player : playersReachedWinningZone) {
                    player.updateScore(creditPoints);
                    winnerFound = true;
                }
            } else {
                for (Player player : referee.getAlivePlayersInRound()) {
                    if (!playersReachedWinningZone.contains(player)) {
                        referee.removeFromAlivePlayers(player);
                    }
                }
                winningScore++;
            }
        }
    }

    /**
     * Increases by one the number of correct answers given by the player in the round.
     * Only for inside-class use.
     *
     * @param player the player who has answered the current question correctly.
     */
    private void addOneCorrectAnswerTo(Player player) {
        numberOfCorrectAnswersInRound.put(player, numberOfCorrectAnswersInRound.get(player) + 1);
    }

    /**
     * Getter for the number of correct answers of a player in the round.
     *
     * @param player the player to get the correct answers of
     * @return the number of correct answers of the player, if the player in involved in the round, else returns -1.
     */
    public int getNumberOfCorrectAnswersOfPlayer(Player player) {
        if (numberOfCorrectAnswersInRound.containsKey(player)) {
            return numberOfCorrectAnswersInRound.get(player);
        }
        return -1;
    }
}
