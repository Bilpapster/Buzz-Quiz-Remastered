import com.BoilingPointRoundLogic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoilingPointRoundLogicTest extends PointBuilderRoundLogicTest {

    @Test
    public void giveCreditsTest() {
        BoilingPointRoundLogic testInstance = new BoilingPointRoundLogic(referee);

        for (int question = 0; question < 4; question++) {
            testInstance.giveCredits();
            assertEquals(1000, players.get(0).getScore());
            assertEquals(0, players.get(1).getScore());
            assertEquals(false, testInstance.isOver());
        }

        testInstance.giveCredits();
        assertEquals(6000, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(true, testInstance.isOver());
    }
}
