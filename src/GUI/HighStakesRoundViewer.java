package GUI;

import com.HighStakesRoundLogic;
import com.Player;
import com.Referee;

public class HighStakesRoundViewer extends PointBuilderRoundViewer {
    public HighStakesRoundViewer(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        roundLogic = new HighStakesRoundLogic(5, referee);
    }

    @Override
    protected void displayNextQuestion() {
        referee.executeActionsBeforeNextQuestion();
        currentQuestion = referee.getQuestion();
        clearTextOnAllSelectionLabels();
        updateTextOnQuestionTypeLabel();
        updateTextOnAllPlayersScoreLabels();
        questionTextLabel.setText("???");
        clearTextOnAllAnswerButtons();
        updateBackgroundColors();
        timer.resetTimerLabel();

        for (Player player : referee.getAlivePlayersInRound()) {
            BetSelectionWindow betSelectionWindow = new BetSelectionWindow(this.parentFrame, player);
            ((HighStakesRoundLogic) roundLogic).setBetForPlayer(player, betSelectionWindow.getFinalBet());
        }
        updateTextOnQuestionTextLabel();
        updateTextOnAllAnswerButtons();
        timer.startTimer();
    }
}
