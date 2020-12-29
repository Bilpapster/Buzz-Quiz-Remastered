package GUI;

import com.QuickAnswerRound;
import com.Referee;

public class QuickAnswerRoundFrame extends StopTheClockRoundFrame {
    public QuickAnswerRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new QuickAnswerRound(5, referee);
    }
}
