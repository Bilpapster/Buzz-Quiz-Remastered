package com;

import java.util.ArrayList;
import java.util.HashMap;

public class StandardRound implements RoundI {

    protected int numberOfQuestionsInRound;                     // the number of questions in round in total
    protected int numberOfQuestionsRemaining;                   // the number of questions remaining for the round to end
    protected ArrayList<Player> players;                        // the players involved in the round
    protected HashMap<Player, String> answersGivenByPlayers;    // the players' answers to the current question
    protected QuestionManager questionManager;                  // the question manager of the round
    protected Parser parser;                                    // the console parser of the round
    protected int creditPoints;                                 // the points earned on correct answer

    /**
     * Constructs a com.StandardRound object with given attributes
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           array list of the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with players via console
     */
    public StandardRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        this.numberOfQuestionsInRound = numberOfQuestions;
        this.numberOfQuestionsRemaining = numberOfQuestions;
        this.answersGivenByPlayers = new HashMap<>();
        this.players = players;
        this.questionManager = questionManager;
        this.parser = parser;
        this.creditPoints = 1000;
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("In this round you are going to be asked " + this.numberOfQuestionsInRound + " questions.\n"
                + "For every correct answer, you earn " + this.creditPoints + " points!\n");
    }

    /**
     * Decides whether the round is finished or not.
     *
     * @return true if the round is over, else false.
     */
    @Override
    public Boolean isOver() {
        return !(this.numberOfQuestionsRemaining > 0);
    }

    /**
     * Getter for the number of questions in the round.
     *
     * @return the number of questions in the round in total.
     */
    public int getNumberOfQuestionsInRound() {
        return this.numberOfQuestionsInRound;
    }

    /**
     * Getter fot the number of questions remaining in the round.
     *
     * @return the number of questions remaining for the round to end.
     */
    public int getNumberOfQuestionsRemaining() {
        return this.numberOfQuestionsRemaining;
    }

    /**
     * Getter for the players involved in the round
     *
     * @return an array list containing the players involved in the round
     */
    @Override
    public ArrayList<Player> getPlayers() {
        return this.players;
    }


    /**
     * Displays the current question to players. The category, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        questionManager.getNextQuestion().displayQuestionBody();
        questionManager.getNextQuestion().displayOptions();
    }

    /**
     * Reads the answers given by players, executing data validation.
     * Stores their answers to the answers' HashMap, by over-writing the new answers on the already stored answers from the previous question.
     */
    @Override
    public void readAnswers() {
        for (Player player : players) {
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

        for (Player player : players) {
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
     * Prints a success message and updates the player's score, by adding credit points.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered the current question correctly
     */
    protected void executeActionsOnCorrectAnswer(Player player) {
        System.out.print(player.getName() + ": Correct! +" + creditPoints);
        player.updateScore(creditPoints);
        System.out.println();
    }

    /**
     * Executes all necessary actions on a player that has answer wrong the current question of the round.
     * Prints a failure message.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered the current question wrong
     */
    protected void executeActionsOnWrongAnswer(Player player) {
        System.out.print(player.getName() + ": Wrong ...");
        System.out.println();
    }

    /**
     * Executes all necessary actions at the end of a question.
     * Decreases remaining questions by one and removes the question from the game, to avoid coming across it again later on game.
     * Only for inside-class and inside-subclasses use.
     */
    protected void executeActionsOnEndOfQuestion() {
        this.numberOfQuestionsRemaining--;
        questionManager.removeAnsweredQuestion();
    }
}