package GUI;

import com.Referee;
import com.StopTheClockRound;

public class StopTheClockRoundFrame extends StandardRoundFrame {
    public StopTheClockRoundFrame(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new StopTheClockRound(5, referee);
    }
}
