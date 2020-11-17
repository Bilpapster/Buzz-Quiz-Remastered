package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A class representing a specific type of round, where, besides answering correctly,
 * the order of players according to their answering time does matter and defines their reward.
 * The class is implemented as sub-class of the StopTheClock class.
 */
public class QuickAnswerRound extends StopTheClockRound {

    private HashSet<Player> playersAnsweredCorrectly;
    private HashMap<Player, Integer> orderOfAnsweringCorrectly;

    /**
     * Constructs a com.StopTheClockRound object with given attributes.
     *
     * @param numberOfQuestions the number of questions in round
     * @param players           the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object, responsible for communicating with players via console
     */
    public QuickAnswerRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(numberOfQuestions, players, questionManager, parser);
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
        return ("In this round you are going to be asked " + this.getNumberOfQuestionsInRound() + " questions.\n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!\n" +
                "The quicker to answer the question correctly gets 1000 points, while the second correct player gets the half of them, 500!\n" +
                "No points available for other players, even if they answer correctly, so be as quick as possible!\n");
    }

    @Override
    public void giveCredits() {

        this.playersAnsweredCorrectly.clear();
        for (Player player : players) {
            if (questionManager.getNextQuestion().isCorrectAnswer(answersGivenByPlayers.get(player))) {
                playersAnsweredCorrectly.add(player);
            }
        }
        refreshOrder();
        super.giveCredits();
    }

    private void refreshOrder() {
        this.orderOfAnsweringCorrectly.clear();
        for (Player player : playersAnsweredCorrectly) {
            int answeringOrder = 1;
            for (Player otherPlayer : playersAnsweredCorrectly) {
                if (milliSecondsElapsedOnAnswer.get(otherPlayer) < milliSecondsElapsedOnAnswer.get(player)) {
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
     * No points reward in any other case, despite answering correctly.
     *
     * @param player the player that has answered correctly the current question
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        if (orderOfAnsweringCorrectly.get(player) == 1) {
            System.out.print(player.getName() + ": Correct! +" + creditPoints + ". ");
            player.updateScore(creditPoints);
        } else if (orderOfAnsweringCorrectly.get(player) == 2) {
            System.out.print(player.getName() + ": Correct! +" + (creditPoints / 2) + ". ");
            player.updateScore(creditPoints / 2);
        } else {
            System.out.print(player.getName() + ": Correct! No points because you answered in order " + orderOfAnsweringCorrectly.get(player) + ", looser!");
        }
        System.out.println(" Answering time: " + ((long) (milliSecondsElapsedOnAnswer.get(player)/1000)) + " seconds.");
    }
}
