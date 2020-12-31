package com;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * <p>A class that represents the logic core of the round type "High Stakes" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
 * The class extends the <code>PointBuilderRoundLogic</code> class, extending its functionality, in order to
 * comply with the following round rules.</p>
 * <p>The player(s) are asked a number of questions (5), earn credit points on correct answer and lose
 * credit points on wrong answer. For every question in the round, the players are, firstly, let to know
 * the category of the question and are asked to place a bet. The available bets are [250, 500, 750, 1000]  points.</p>
 * <p>After the bet placement, the question itself, as well as the available answers are revealed to the player(s).
 * Every player that answers correctly gets double its bet back. That means the final credit points are the points
 * before betting plus the amount of the bet.</p>
 * <p>However, in case a player answers the question wrong, loses its bet. That means the final points are
 * calculated by subtracting the amount of bet from the previously owned credit points. </p>
 *
 * @author Vasilis Papastergios
 * @see PointBuilderRoundLogic
 */
public class HighStakesRoundLogic extends PointBuilderRoundLogic {

    /* stores the acceptable bets that players can place. Remains intact during round. */
    private TreeMap<String, Integer> acceptableBets;

    /* stores the bets placed by players at the current question. Values can change during round. */
    private HashMap<Player, Integer> betsPlacedByPlayers;

    /**
     * Constructs a <code>HighStakesRoundLogic</code> object with given attributes.
     *
     * @param numberOfQuestions the number of questions in round
     * @param referee           the referee of the round
     */
    public HighStakesRoundLogic(int numberOfQuestions, Referee referee) {
        super(numberOfQuestions, referee);
        betsPlacedByPlayers = new HashMap<>();
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("HighStakes: In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() +
                " questions.\n" +
                "At first, you are let to know the category of the question and you are asked to place a bet " +
                " Answering correctly will add to your score as many points as your bet!\n" +
                " But, be careful! Answering the question wrong will cost you your bet!\n");
    }

    /**
     * Getter for the official name of the round, as presented in the original .
     * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
     *
     * @return the official name of the round as String
     */
    @Override
    public String getOfficialName() {
        return "High Stakes";
    }

    /**
     * <p>Executes all necessary actions on a player that has answered correctly a question of the round.</p>
     * <p>Updates the player's score, by adding the amount of bet they had placed before the question announcement.</p>
     * <p>Only for inside-class and inside-subclasses use.</p>
     *
     * @param player the player that has answered the current question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        player.updateScore(this.betsPlacedByPlayers.get(player));
    }

    /**
     * <p>Executes all necessary actions on a player that has answered wrong the current question of the round.</p>
     * <p>Updates the player's score, by subtracting from their points the amount of bet they had placed before the
     * question announcement.</p>
     *
     * @param player the player that has answered the current question wrong
     */
    @Override
    protected void executeActionsOnWrongAnswer(Player player) {
        player.updateScore(-this.betsPlacedByPlayers.get(player));
    }

    /**
     * Simple accessor (setter) for the bet placed by one player involved in the round.
     *
     * @param player    the player that has placed a bet
     * @param betPlaced the amount of the bet
     */
    public void setBetForPlayer(Player player, Integer betPlaced) {
        this.betsPlacedByPlayers.put(player, betPlaced);
    }
}
