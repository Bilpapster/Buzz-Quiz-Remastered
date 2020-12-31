package GUI;

import com.Referee;
import com.StopTheClockRoundLogic;

/**
 * <p>A class that represents the front end of the round type "Stop the Clock" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. There are 5 questions in this
 * round. The round is timed. The class is developed as a sub-class of the <code>PointBuilderRoundViewer</code>
 * class.</p>
 * <p>At first, the player(s) are let to know the type and text of the coming question. A 3-seconds interval follows so
 * as for the player(s) to read the question. Right after, the available options are displayed and the timer starts
 * running!</p>
 *
 * @author Vasileios Papastergios
 * @see StopTheClockRoundLogic
 * @see RoundViewerI
 */
public class StopTheClockRoundViewer extends PointBuilderRoundViewer {

    /**
     * Constructs a <code>StopTheClockRoundViewer</code> object with the given referee.
     *
     * @param referee the referee of the game.
     */
    public StopTheClockRoundViewer(Referee referee) {
        super(referee);
        timer.showTimer();
    }

    /**
     * Initializes the round logic core object. Needs to be overridden for every round type.
     */
    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new StopTheClockRoundLogic(5, referee);
    }

    /**
     * Executes some preparatory actions before moving on to the next question. Updates the background colors and images
     * as well as the texts displayed on the question type and text label. After 3 seconds the available options are
     * revealed and the timer start running.
     */
    @Override
    protected void executeCustomizablePreparationsBeforeNextQuestion() {
        new DelayTimer(3000) {
            @Override
            protected void executeActionsBeforeDelay() {
                updateTextOnAllPlayersScoreLabels();
                updateBackgroundColors();
                updateTextOnQuestionTypeLabel();
                updateTextOnQuestionTextLabel();
                clearTextOnAllAnswerButtons();
                timer.resetTimerLabel();
            }

            @Override
            protected void executeActionsAfterDelay() {
                updateTextOnAllAnswerButtons();
                isUserInteractionEnabled = true;
                timer.startTimer();
            }
        };
    }

    @Override
    protected void enableInteractionAndStartTimer() {
        /* deliberately overridden without body for delayTimer-synchronization purposes */
    }
}
