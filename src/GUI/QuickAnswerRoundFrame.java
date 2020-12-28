package GUI;

import com.BettingRound;
import com.QuickAnswerRound;
import com.Referee;

public class QuickAnswerRoundFrame extends StandardRoundFrame {
    public QuickAnswerRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new QuickAnswerRound(5, referee);
    }
}
