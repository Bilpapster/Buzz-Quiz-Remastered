package GUI;

import com.QuickAnswerRound;
import com.Referee;
import com.StopTheClockRound;

public class StopTheClockRoundFrame extends StandardRoundFrame {
    public StopTheClockRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new StopTheClockRound(10, referee);
    }
}
