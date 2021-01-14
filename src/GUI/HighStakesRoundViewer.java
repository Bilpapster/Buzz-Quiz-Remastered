package GUI;

import com.Constants;
import com.HighStakesRoundLogic;
import com.Player;
import com.Referee;

/**
 * <p>A class that represents the front end of the round type "High Stakes" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. There are 5 questions in this
 * round. The class is developed as a sub-class of the <code>PointBuilderRoundViewer</code> class.</p>
 * <p>At first, the player(s) are let to know the type of the coming question. Right after, they are asked to place a
 * bet. After the bet placement, the question text and the available options are revealed and the player(s) submit their
 * answers.There is a 3-seconds interval between two successive questions.</p>
 *
 * @author Vasileios Papastergios
 * @see HighStakesRoundLogic
 * @see RoundViewerI
 */
public class HighStakesRoundViewer extends PointBuilderRoundViewer {
    public HighStakesRoundViewer(Referee referee) {
        super(referee);
    }

    /**
     * Initializes the round logic core object.
     */
    @Override
    protected void initializeRoundLogic() {
        roundLogic = new HighStakesRoundLogic(Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND, referee);
    }

    /**
     * Executes some preparatory actions before moving on to the next question. Reveals only the question type of the
     * coming question and sets the question text to "???". Invokes the selection frames for all alive players in
     * round. After the bet placement, the rest of the question (text and available answers) are displayed.
     */
    @Override
    protected void executeCustomizablePreparationsBeforeNextQuestion() {
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
    }
}
