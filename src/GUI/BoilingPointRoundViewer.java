package GUI;

import com.BoilingPointRoundLogic;
import com.Player;
import com.Referee;

/**
 * <p>A class that represents the front end of the round type "Boiling Point" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. There is no standard number of
 * questions in this round, since the round ends when one player reaches 5 correct answers. The class is developed as a
 * sub-class of the <code>PointBuilderRoundViewer</code> class.</p>
 * <p>The round view is identical with the Point Builder round.</p>
 *
 * @author Vasileios Papastergios
 * @see com.PointBuilderRoundLogic
 * @see RoundViewerI
 */
public class BoilingPointRoundViewer extends PointBuilderRoundViewer {
    public BoilingPointRoundViewer(Referee referee) {
        super(referee);
    }

    /**
     * Initializes the round logic core object. Needs to be overridden for every round type.
     */
    @Override
    protected void initializeRoundLogic() {
        roundLogic = new BoilingPointRoundLogic(referee);
    }

    /**
     * Updates the text on all labels displaying the players' score.
     */
    @Override
    protected void updateTextOnAllPlayersScoreLabels() {
        super.updateTextOnAllPlayersScoreLabels();
        for (Player player : referee.getAlivePlayersInRound()) {
            playerInfoPanels.get(player).updateName(
                    player.getName() +
                            "  (" +
                            ((BoilingPointRoundLogic) roundLogic).getNumberOfCorrectAnswersOfPlayer(player) +
                            ")");
        }
    }
}
