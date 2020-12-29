package GUI;

import com.BettingRound;
import com.Player;
import com.Referee;

public class BettingRoundFrame extends StandardRoundFrame {
    public BettingRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        roundLogic = new BettingRound(5, referee);
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
            ((BettingRound) roundLogic).setBetForPlayer(player, betSelectionWindow.getFinalBet());
        }
        updateTextOnQuestionTextLabel();
        updateTextOnAllAnswerButtons();
        timer.startTimer();
    }
}
