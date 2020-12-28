package com;

public class StandardRound implements RoundI {

    protected int numberOfQuestionsInRound;                     // the number of questions in round in total
    protected int numberOfQuestionsRemaining;                   // the number of questions remaining for the round to end
    protected int creditPoints;                                 // the points earned on correct answer
    protected Referee referee;                                  // the referee of the round

    /**
     * Constructs a comStandardRound object with given attributes
     *
     * @param numberOfQuestions the number of questions in the round
     * @param referee           the referee of the round for communication with round view objects
     */
    public StandardRound(int numberOfQuestions, Referee referee) {
        this.numberOfQuestionsInRound = numberOfQuestions;
        this.numberOfQuestionsRemaining = numberOfQuestions;
        this.referee = referee;
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

    @Override
    public String getOfficialName() {
        return "Point Builder";
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
     * Prints a success message and updates the player's score, by adding credit points.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered the current question correctly
     */
    protected void executeActionsOnCorrectAnswer(Player player) {
        player.updateScore(creditPoints);
    }

    /**
     * Executes all necessary actions on a player that has answer wrong the current question of the round.
     * Prints a failure message.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered the current question wrong
     */
    protected void executeActionsOnWrongAnswer(Player player) {

    }

    /**
     * Executes all necessary actions at the end of a question.
     * Decreases remaining questions by one and removes the question from the game, to avoid coming across it again later on game.
     * Only for inside-class and inside-subclasses use.
     */
    protected void executeActionsOnEndOfQuestion() {
        this.numberOfQuestionsRemaining--;
    }
}