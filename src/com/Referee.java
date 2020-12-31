package com;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>A class that represents a "referee" object in our game. The referee plays conducive role in the communication
 * between the model (round logic classes) and the view (round viewer classes). </p>
 * <p>The referee class could be described as a data storage class. The object stores data concerning the answers
 * given by players (who answered and what is their answer) as well as the time stamps associated (time elapsed on
 * answer).</p>
 * <p>In action, a referee object is responsible for communicating with the back-end of the game, and, especially,
 * the question-managing related tasks. The round viewer classes (front-end) use the referee object as a data storage
 * on which they "write" the answering data of players, based on the players' interaction with GUI.</p>
 * <p>On the other hand, the round logic classes (back-end) use the referee object as a Read-only storage item, from
 * which they read the answering data provided by the front end, in order to give the appropriate credits to players.
 * </p>
 * <p>Special attention is needed when using the above functionalities in combination. It is absolutely important that
 * the read-only methods of the <code>Referee</code> object are invoked only when data writing has been successfully
 * completed by the front-end. Otherwise, the referee can contain "garbage", unfinished or no data at all.</p>
 *
 * @author Vasileios Papastergios
 */
public class Referee {

    /* the question managing object of the game */
    private QuestionManager questionManager = new QuestionManager();

    /* the question that is currently asked to the players */
    private Question currentQuestion;

    /* the players taking part in game, in total */
    private ArrayList<Player> playersInGame;

    /* the players that are alive in the current round up to the moment. May differ from the players in game in total */
    private ArrayList<Player> alivePlayersInRound = new ArrayList<>();

    /* stores whether it is needed to reset the alive players in round, before starting next round */
    private Boolean playersNeedToBeReset = false;

    /* stores the answering time of all the players alive in round for the current question */
    private HashMap<Player, Long> milliSecondsElapsedOnAnswer = new HashMap<>();

    /* stores the answers given by the alive players in round for the current question */
    private HashMap<Player, String> answersGivenByPlayers = new HashMap<>();

    /**
     * Constructs a <code>Referee</code> object with given players in game, in total.
     *
     * @param playersInGame the players taking part in game, in total.
     */
    public Referee(ArrayList<Player> playersInGame) {
        this.questionManager.createQuestions();
        this.currentQuestion = questionManager.getNextQuestion();
        this.playersInGame = playersInGame;
        this.alivePlayersInRound.addAll(playersInGame);
    }

    /**
     * Setter for the answer data for a player, concerning the currently playing question.
     * The setter should be invoked only by a front-end class.
     *
     * @param player              the player to set data for
     * @param answer              the player's answer to the current question
     * @param timeElapsedOnAnswer the time elapsed on answer for the player
     */
    public void setAnswerData(Player player, String answer, long timeElapsedOnAnswer) {
        answersGivenByPlayers.putIfAbsent(player, answer);
        milliSecondsElapsedOnAnswer.putIfAbsent(player, timeElapsedOnAnswer);
    }

    /**
     * Resets all answer data for alive players in round. Only for inside-class use.
     */
    private void resetAllAnswerData() {
        answersGivenByPlayers.clear();
        milliSecondsElapsedOnAnswer.clear();
    }

    /**
     * Resets all the current-round-related data that were stored. Only for inside-class use.
     */
    private void resetAllRoundData() {
        if (playersNeedToBeReset) {
            alivePlayersInRound.clear();
            alivePlayersInRound.addAll(playersInGame);
            playersNeedToBeReset = false;
        }
        resetAllAnswerData();
    }

    /**
     * Removes a player from the list of alive players in round. Invoke <code>executeActionsBeforeNextQuestion</code>
     * in order to restore the alive players list, before starting next round.
     *
     * @param playerToRemove the player to remove from the alive players' list
     */
    public void removeFromAlivePlayers(Player playerToRemove) {
        alivePlayersInRound.remove(playerToRemove);
        this.playersNeedToBeReset = true;
    }

    /**
     * Getter for the players that are alive in round up to that moment.
     *
     * @return a list of the alive players in round up to that moment.
     */
    public ArrayList<Player> getAlivePlayersInRound() {
        return alivePlayersInRound;
    }

    /**
     * Checks whether a player has answered the current question correctly or not.
     *
     * @param player the player to check answer for.
     * @return <code>true</code> if the player has answered correctly the current question, else <code>false</code>.
     */
    public boolean hasPlayerAnsweredCorrectly(Player player) {
        return answersGivenByPlayers.get(player).equals(currentQuestion.getCorrectAnswer());
    }

    /**
     * Checks whether a player has already answered the current question or not.
     *
     * @param player the player to check about answering
     * @return <code>true</code> if the given player has already answered the current question, else <code>false</code>.
     */
    public boolean hasPlayerAnswered(Player player) {
        return answersGivenByPlayers.containsKey(player);
    }

    /**
     * Getter for the answering time of a player, concerning the current question.
     *
     * @param player the player to get the answering time of
     * @return the answering time of the given player, concerning the current question.
     */
    public Long getTimeElapsedOnAnswerForPlayer(Player player) {
        return milliSecondsElapsedOnAnswer.get(player);
    }

    /**
     * Getter for the question that is currently asked/playing.
     *
     * @return the currently playing question
     */
    public Question getQuestion() {
        return currentQuestion;
    }

    /**
     * Executes all needed actions in order to prepare for the next question. Removes the answered question from the
     * question manager and and updates the current question reference. Only for inside-class use.
     */
    private void prepareNextQuestion() {
        questionManager.removeAnsweredQuestion();
        currentQuestion = questionManager.getNextQuestion();
    }

    /**
     * Executes all needed actions before moving on to the next question of the round. Resets all answer data stored
     * and prepares the next question.
     */
    public void executeActionsBeforeNextQuestion() {
        resetAllAnswerData();
        prepareNextQuestion();
    }

    /**
     * Executes all needed actions before moving on to the next round of the game. Resets all stored data related to
     * the round and prepares the next question.
     */
    public void executeActionsBeforeNextRound() {
        resetAllRoundData();
        prepareNextQuestion();
    }

    /**
     * Checks whether all the alive players in round have answered the current question or not.
     *
     * @return <code>true</code> if all the alive players in round have answered the current question, else
     * <code>false</code>.
     */
    public boolean haveAllPlayersAnswered() {
        return this.alivePlayersInRound.size() == this.answersGivenByPlayers.keySet().size();
    }

    /**
     * Getter for the correct answer of the current question.
     *
     * @return the correct answer of the current question
     */
    public String getCorrectAnswerOfCurrentQuestion() {
        return currentQuestion.getCorrectAnswer();
    }
}
