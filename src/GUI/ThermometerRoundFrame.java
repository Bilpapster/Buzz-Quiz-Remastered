package GUI;

import com.Player;
import com.Referee;
import com.ThermometerRound;

public class ThermometerRoundFrame extends StandardRoundFrame {
    public ThermometerRoundFrame(Referee referee) {
        super(referee);
        roundLogic = new ThermometerRound(referee);
    }

    @Override
    protected void updateTextOnAllPlayersScoreLabels() {
        Player player0 = referee.getAlivePlayersInRound().get(0);
        playerNameLabel.setText(player0.getName() + ": " + String.format("%,d", player0.getScore())+ "  (" + ((ThermometerRound) roundLogic).getCorrectAnswersOfPlayer(player0) + " correct answers)");

        if (referee.getAlivePlayersInRound().size() > 1) {
            Player player1 = referee.getAlivePlayersInRound().get(1);
            playerNameLabel2.setText(player1.getName() + ": " + String.format("%,d", player1.getScore()) + "  (" + ((ThermometerRound) roundLogic).getCorrectAnswersOfPlayer(player1) + " correct answers)");
        }
    }
}
