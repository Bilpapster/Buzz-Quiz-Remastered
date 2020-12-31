package GUI;

import com.BoilingPointRoundLogic;
import com.Player;
import com.Referee;

public class BoilingPointRoundViewer extends PointBuilderRoundViewer {
    public BoilingPointRoundViewer(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        roundLogic = new BoilingPointRoundLogic(referee);
    }

    @Override
    protected void updateTextOnAllPlayersScoreLabels() {
        super.updateTextOnAllPlayersScoreLabels();
        for (Player player : referee.getAlivePlayersInRound()) {
            playerInfoPanels.get(player).updateName(player.getName() + "  (" + ((BoilingPointRoundLogic) roundLogic).getNumberOfCorrectAnswersOfPlayer(player) + ")");
        }
    }
}
