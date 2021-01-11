package com;

/**
 * <p>A class that represents the logic core of the round type "Stop the Clock" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
 * At least two players are needed for this round.
 * The class extends the <code>PointBuilderRoundLogic</code> class, extending its functionality, in order to
 * comply with the following round rules.</p>
 * <p>The player(s) are asked a number of questions (5) and earn credit points on correct answer. The round is timed
 * and the player(s) have 5 seconds to answer the question. In case of a correct answer, the players are rewarded with
 * as many points as the time remaining (in milliseconds - when they answered) times 0.2. That means if a player
 * answers correctly a question, and at the time of answering were 2.5 seconds remaining, they will be rewarded with
 * 2500 (millis)*0.2 = 500 points</p>
 * <p>There is no point penalty on wrong answer</p>
 *
 * @author Vasilis Papastergios
 * @see PointBuilderRoundLogic
 */
public class StopTheClockRoundLogic extends PointBuilderRoundLogic {

    /**
     * Constructs a <code>StopTheClockRoundLogic</code>> object with given attributes.
     *
     * @param numberOfQuestions the number of questions in the round.
     * @param referee           the referee of the round
     */
    public StopTheClockRoundLogic(int numberOfQuestions, Referee referee) {
        super(numberOfQuestions, referee);
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("Stop the clock: In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() +
                " questions. You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. There is a 3-seconds time " +
                "interval for the players to read the question. At the end of this interval, the available options " +
                "are revealed and the clock starts running\n " +
                "Answering the question correctly will add to your score as many points as the remaining  " +
                "milliseconds times 0.2!\n There is no point penalty on wrong answer.");
    }

    /**
     * Getter for the official name of the round, as presented in the original .
     * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game.
     *
     * @return the official name of the round as String
     */
    @Override
    public String getOfficialName() {
        return "Stop the Clock";
    }

    /**
     * <p>Executes all necessary actions on a player that has answered correctly a question of the round.</p>
     * <p>Updates the player's score, by adding the number of credit points defined by the round rules.</p>
     *
     * @param player the player that has answered the current question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        creditPoints = (int) ((referee.getTimeElapsedOnAnswerForPlayer(player)) * 0.2);
        if (creditPoints > 0) {
            player.updateScore(creditPoints);
        }
    }
}
