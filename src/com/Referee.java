package com;

import java.util.ArrayList;
import java.util.HashMap;

public class Referee {
    private QuestionManager questionManager;
    private Question currentQuestion;
    private ArrayList<Player> playersInGame;
    private ArrayList<Player> alivePlayersInRound;
    private Boolean playersNeedToBeReset;
    private HashMap<Player, Long> milliSecondsElapsedOnAnswer;
    private HashMap<Player, String> answersGivenByPlayers;    // the players' answers to the current question

    public Referee(ArrayList<Player> playersInGame) {
        this.questionManager = new QuestionManager();
        questionManager.createQuestions();
        currentQuestion = questionManager.getNextQuestion();

        this.playersInGame = playersInGame;
        this.alivePlayersInRound = new ArrayList<>();
        alivePlayersInRound.addAll(playersInGame);

        playersNeedToBeReset = false;

        milliSecondsElapsedOnAnswer = new HashMap<>();
        answersGivenByPlayers = new HashMap<>();
    }

    public void setAnswerData(Player player, String answer, long timeElapsedOnAnswer) {
        answersGivenByPlayers.putIfAbsent(player, answer);
        milliSecondsElapsedOnAnswer.putIfAbsent(player, timeElapsedOnAnswer);
    }

    public void setAnswerData(Player player, String answer) {
        this.setAnswerData(player, answer, -1);
    }

    private void resetAllAnswerData() {
        answersGivenByPlayers.clear();
        milliSecondsElapsedOnAnswer.clear();
    }

    private void resetAllRoundData() {
        if (playersNeedToBeReset) {
            alivePlayersInRound.clear();
            alivePlayersInRound.addAll(playersInGame);
            playersNeedToBeReset = false;
        }
        resetAllAnswerData();
    }

    public void removeFromAlivePlayers(Player playerToRemove) {
        alivePlayersInRound.remove(playerToRemove);
        this.playersNeedToBeReset = true;
    }

    public ArrayList<Player> getAlivePlayersInRound() {
        return alivePlayersInRound;
    }

    public Boolean hasPlayerAnsweredCorrectly(Player player) {
        return answersGivenByPlayers.get(player).equals(currentQuestion.getCorrectAnswer());
    }

    public Long getTimeElapsedOnAnswerForPlayer(Player player) {
        return milliSecondsElapsedOnAnswer.get(player);
    }

    public Question getQuestion() {
        return currentQuestion;
    }

    private void prepareNextQuestion() {
        questionManager.removeAnsweredQuestion();
        currentQuestion = questionManager.getNextQuestion();
    }

    public void executeActionsBeforeNextQuestion() {
        resetAllAnswerData();
        prepareNextQuestion();
    }

    public void executeActionsBeforeNextRound() {
        resetAllRoundData();
        prepareNextQuestion();
    }

    public boolean haveAllPlayersAnswered() {
        return this.alivePlayersInRound.size() == this.answersGivenByPlayers.keySet().size();
    }

    public String getCorrectAnswerOfCurrentQuestion() {
        return currentQuestion.getCorrectAnswer();
    }
}
