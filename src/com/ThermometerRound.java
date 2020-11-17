package com;

import java.util.ArrayList;
import java.util.HashMap;

public class ThermometerRound extends StandardRound {

    private ArrayList<Player> stillAlivePlayers;
    private HashMap<Player, Integer> correctAnswersInRound;
    private Boolean winnerFound;
    private int winningScore;

    /**
     * Constructs a com.StandardRound object with given attributes
     *
     * @param players         array list of the players involved in the round
     * @param questionManager a question-managing object, responsible for the questions of the round
     * @param parser          a parsing object responsible for communicating with players via console
     */
    public ThermometerRound(ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        super(0, players, questionManager, parser);

        stillAlivePlayers = new ArrayList<>();
        stillAlivePlayers.addAll(players);

        correctAnswersInRound = new HashMap<>();
        for (Player player : players) {
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
     * Reads the answers given by players, executing data validation.
     * Stores their answers to the answers' HashMap, by over-writing the new answers on the already stored answers from the previous question.
     */
    @Override
    public void readAnswers() {
        answersGivenByPlayers.clear();
        for (Player player : stillAlivePlayers) {
            System.out.print(player.getName() + ", it is your turn. ");
            answersGivenByPlayers.put(player, parser.askForAnswer(questionManager.getNextQuestion().getAnswerKeySet()));
        }
    }

    /**
     * For every player checks whether they have answered the current question correctly or not.
     * Invokes necessary actions on both cases (correct or wrong answer).
     */
    @Override
    public void giveCredits() {

        /* The method's code is deliberately written abstract for inheritance and code re-use purposes.
            Utilizes 3 simpler protected methods that build up to the total giveCredits task and can be overridden by sub-classes. */

        for (Player player : stillAlivePlayers) {
            if (questionManager.getNextQuestion().isCorrectAnswer(answersGivenByPlayers.get(player))) {
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
        System.out.print(player.getName() + ": Correct!");
        addOneCorrectAnswerTo(player);
        System.out.println();
    }

    /**
     * Executes all necessary actions at the end of a question.
     * Checks if the winner of the round is can be declared.
     */
    @Override
    protected void executeActionsOnEndOfQuestion() {
        ArrayList<Player> playersReachedWinningZone = new ArrayList<>();
        for (Player player : players) {
            if (correctAnswersInRound.get(player) == winningScore) {
                playersReachedWinningZone.add(player);
            }
        }

        if (playersReachedWinningZone.size() == 1) {
            for (Player player : playersReachedWinningZone) {
                System.out.println(player.getName() + " is the winner of the round! +" + creditPoints);
                player.updateScore(creditPoints);
                winnerFound = true;
            }
        } else if (playersReachedWinningZone.size() > 1) {
            stillAlivePlayers.clear();
            stillAlivePlayers.addAll(playersReachedWinningZone);
            winningScore++;
        }

        for (Player player : stillAlivePlayers) {
            System.out.println(player.getName() + ": " + correctAnswersInRound.get((player)) + " correct answers in this round.");
        }
        questionManager.removeAnsweredQuestion();
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
