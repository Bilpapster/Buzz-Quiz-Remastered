package GUI;

import com.Referee;
import com.StopTheClockRoundLogic;

public class StopTheClockRoundViewer extends PointBuilderRoundViewer {
    public StopTheClockRoundViewer(Referee referee) {
        super(referee);
        timer.showTimer();
    }

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
        /* overridden without body for delayTimer-synchronization purposes */
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new StopTheClockRoundLogic(5, referee);
    }
}
