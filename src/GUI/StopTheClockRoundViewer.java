package GUI;

import com.Referee;
import com.StopTheClockRoundLogic;

public class StopTheClockRoundViewer extends PointBuilderRoundViewer {
    public StopTheClockRoundViewer(Referee referee) {
        super(referee);
        showTimerComponent();
    }

    @Override
    protected void displayNextQuestion() {

        new DelayTimer(3000) {
            @Override
            protected void executeActionsBeforeDelay() {
                referee.executeActionsBeforeNextQuestion();
                currentQuestion = referee.getQuestion();
                clearTextOnAllSelectionLabels();
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
                timer.startTimer();
            }
        };
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new StopTheClockRoundLogic(5, referee);
    }
}
