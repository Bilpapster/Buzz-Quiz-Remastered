package com;

/**
 * A class representing a specific type of round.
 */
public class StopTheClockRound extends StandardRound {

    /**
     * Constructs a com.StopTheClockRound object with given attributes.
     * @param numberOfQuestions the number of questions in the round.
     * @param referee the referee of the round
     */
    public StopTheClockRound(int numberOfQuestions, Referee referee) {
        super(numberOfQuestions, referee);
    }

    /**
     * Getter for the round description.
     *
     * @return the round description
     */
    @Override
    public String getDescription() {
        return ("In this round you are going to be asked " + this.getNumberOfQuestionsRemaining() + " questions. You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. Press enter to show available options and make the clock running!\n" +
                "Answering the question correctly will add to your score as many points as the remaining  miliseconds times 0.2!\n");
    }

    /**
     * Executes all necessary actions on a player that has answer correctly a question of the round.
     * Prints a success message and updates the player's score, by adding credit points, according to how fast the player had answered.
     * Only for inside-class and inside-subclasses use.
     *
     * @param player the player that has answered a question correctly
     */
    @Override
    protected void executeActionsOnCorrectAnswer(Player player) {
        creditPoints = (int) ((5000 - referee.getTimeElapsedOnAnswerForPlayer(player))*0.2);
        if (creditPoints > 0) {
            player.updateScore(creditPoints);
        }
    }
}
