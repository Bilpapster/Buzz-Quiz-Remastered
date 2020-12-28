package GUI;

import com.BettingRound;
import com.Player;
import com.Referee;

public class BettingRoundFrame extends StandardRoundFrame {
    public BettingRoundFrame(Referee referee) {
        super(referee);
        roundLogic = new BettingRound(5, referee);
    }

    @Override
    protected void playNextQuestion() {
        referee.executeActionsBeforeNextQuestion();
        currentQuestion = referee.getQuestion();
        updateTextOnQuestionTypeLabel();
        updateTextOnQuestionTextLabel();
        updateTextOnAllPlayersScoreLabels();
        clearTextOnAllAnswerButtons();
        updateBackgroundColors();

        for (Player player : referee.getAlivePlayersInRound()) {
            BetSelectionWindow betSelectionWindow = new BetSelectionWindow(this, player);
            ((BettingRound) roundLogic).setBetForPlayer(player, betSelectionWindow.getFinalBet());
        }
        updateTextOnAllAnswerButtons();
        timer.startTimer();
    }
}
