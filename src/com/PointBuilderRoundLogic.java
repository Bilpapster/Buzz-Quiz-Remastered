package com;

/**
 * <p>A class that represents the logic core of the round type "Point Builder" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
 * The class implements the <code>RoundLogicI</code> interface, according to the following round rules.</p>
 * <p>The player(s) are asked a number of questions (5) and earn credit point (1000) on every correct answer.
 * No timer or order of answering involved in round credits.</p>
 * <p>There are no negative consequences for a player on wrong answer.</p>
 *
 * @author Vasileios Papastergios
 */
public class PointBuilderRoundLogic implements RoundLogicI {

    /* the number of questions in round, in total */
    protected int numberOfQuestionsInRound;

    /* the number of questions remaining for the round to end */
    protected int numberOfQuestionsRemaining;

    /* the points earned on correct answer */
    protected int creditPoints;

    /* the referee of the round */
    protected Referee referee;

    /**
     * Constructs a PointBuilderRound object with given attributes
     *
     * @param numberOfQuestions the number of questions in round
     * @param referee           the referee of the round for communication with round view objects
     */
    public PointBuilderRoundLogic(int numberOfQuestions, Referee referee) {
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
        return (" Point Builder: In this round you are going to be asked " + this.numberOfQuestionsInRound +
                " questions.\n"
                + "For every correct answer, you earn " + this.creditPoints + " points!\n"
                + "No point penalty on wrong answer.");
    }

    /**
     * Getter for the official name of the round, as presented in the original .
     * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
     *
     * @return the official name of the round as String
     */
    @Override
    public String getOfficialName() {
        return "Point Builder";
    }

    /**
     * Decides whether the round is finished or not.
     *
     * @return <code>true</code> if the round is over, else <code>false</code>.
     */
    @Override
    public Boolean isOver() {
        return !(this.numberOfQuestionsRemaining > 0);
    }

    /**
     * Getter for the number of questions in round.
     *
     * @return the number of questions in round in total.
     */
    public int getNumberOfQuestionsInRound() {
        return this.numberOfQuestionsInRound;
    }

    /**
     * Getter fot the number of questions remaining in round.
     *
     * @return the number of questions remaining for the round to end.
     */
    public int getNumberOfQuestionsRemaining() {
        return this.numberOfQuestionsRemaining;
    }

    /**
     * Takes care of updating the player(s)' score, based on the data stored in the referee object.
     * Update is done according to the round rules: 1000 points on correct answer, no point on wrong one.
     * The method should be invoked only when the referee object has stored all the needed data for the current question,
     * as well as the player(s)' answers. The method should be invoked once for every question in round, just before
     * displaying the next question.
     */
    @Override
    public void giveCredits() {

        /* The method's code is deliberately written abstract for inheritance and code re-use purposes.
            Utilizes 3 simpler protected methods that build up to the total giveCredits task and can be overridden by
            sub-classes. */

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
     * <p>Executes all necessary actions on a player that has answered correctly a question of the round.</p>
     * <p>Updates the player's score, by adding the number of credit points defined by the round rules (1000).</p>
     * <p>Only for inside-class and inside-subclasses use.</p>
     *
     * @param player the player that has answered the current question correctly
     */
    protected void executeActionsOnCorrectAnswer(Player player) {
        player.updateScore(creditPoints);
    }

    /**
     * <p>Executes all necessary actions on a player that has answered wrong the current question of the round.</p>
     * In this type of round the method is idle, since there is no point addition/removal for a player on wrong answer.
     * However, the method is declared in this class, because it is going to be utilized by sub-classes, through inheritance.
     * <p>Only for inside-class and inside-subclasses use.</p>
     *
     * @param player the player that has answered the current question wrong
     */
    protected void executeActionsOnWrongAnswer(Player player) {
        /* Left blank in purpose. No actions needed on wrong answer. */
    }

    /**
     * <p>Executes all necessary actions at the end of a question.
     * Decreases remaining questions by one.</p>
     * <p>Only for inside-class and inside-subclasses use.</p>
     */
    protected void executeActionsOnEndOfQuestion() {
        this.numberOfQuestionsRemaining--;
    }
}