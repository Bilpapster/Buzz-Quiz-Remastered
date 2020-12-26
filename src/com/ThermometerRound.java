package com;

import java.util.ArrayList;
import java.util.HashMap;

public class ThermometerRound extends StandardRound {

    private HashMap<Player, Integer> correctAnswersInRound;
    private Boolean winnerFound;
    private int winningScore;
    
    /**
     * Constructs a com.StandardRound object with given attributes.
     *
     * @param referee the referee of the round.
     */
    public ThermometerRound(Referee referee) {
        super(0, referee);
        correctAnswersInRound = new HashMap<>();
        for (Player player : referee.getAlivePlayersInRound()) {
            correctAnswersInRound.put(player, 0);
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
     * Decides whether the round is finished or not.
     *
     * @return true if the round is over, else false.
     */
    @Override
    public Boolean isOver() {
        return this.winnerFound;
    }

    /**
     * For every player checks whether they have answered the current question correctly or not.
     * Invokes necessary actions on both cases (correct or wrong answer).
     */
    @Override
    public void giveCredits() {

        /* The method's code is deliberately written abstract for inheritance and code re-use purposes.
            Utilizes 3 simpler protected methods that build up to the total giveCredits task and can be overridden by sub-classes. */

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
     * Prints a success message and updates the player's number of correct answers in round, by adding credit points.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered the current question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        addOneCorrectAnswerTo(player);
    }

    /**
     * Executes all necessary actions at the end of a question.
     * Checks if the winner of the round is can be declared.
     */
    @Override
    protected void executeActionsOnEndOfQuestion() {
        ArrayList<Player> playersReachedWinningZone = new ArrayList<>();
        for (Player player : referee.getAlivePlayersInRound()) {
            if (correctAnswersInRound.get(player) == winningScore) {
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
     * @param player the player who answered the current question correctly.
     */
    private void addOneCorrectAnswerTo(Player player) {
        correctAnswersInRound.put(player, correctAnswersInRound.get(player) + 1);
    }
}
