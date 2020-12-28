package GUI;

import com.Player;
import com.Referee;
import com.ThermometerRound;

public class ThermometerRoundFrame extends StandardRoundFrame {
    public ThermometerRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        roundLogic = new ThermometerRound(referee);
    }

    @Override
    protected void updateTextOnAllPlayersScoreLabels() {
        super.updateTextOnAllPlayersScoreLabels();
        for (Player player : referee.getAlivePlayersInRound()) {
            playerInfoPanels.get(player).updateName(player.getName() + "  (" + ((ThermometerRound) roundLogic).getCorrectAnswersOfPlayer(player) + ")");
        }
    }
}
