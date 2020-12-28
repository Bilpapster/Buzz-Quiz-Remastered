package GUI;

import com.QuickAnswerRound;
import com.Referee;

public class QuickAnswerRoundFrame extends StandardRoundFrame {
    public QuickAnswerRoundFrame(Referee referee) {
        super(referee);
        this.roundLogic = new QuickAnswerRound(5, referee);
    }
}
