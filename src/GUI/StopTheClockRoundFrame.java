package GUI;

import com.Referee;
import com.StopTheClockRound;

public class StopTheClockRoundFrame extends StandardRoundFrame {
    public StopTheClockRoundFrame(Referee referee) {
        super(referee);
        this.roundLogic = new StopTheClockRound(10, referee);
    }
}
