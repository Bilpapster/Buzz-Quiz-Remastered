package GUI;

import com.FastestFingerRoundLogic;
import com.Referee;

public class FastestFingerRoundViewer extends StopTheClockRoundViewer {
    public FastestFingerRoundViewer(Referee referee) {
        super(referee);
    }

    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new FastestFingerRoundLogic(5, referee);
    }
}
