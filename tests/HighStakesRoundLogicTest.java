import com.HighStakesRoundLogic;
import com.Player;
import com.PointBuilderRoundLogic;
import org.junit.Test;

import static org.junit.Assert.*;

public class HighStakesRoundLogicTest extends PointBuilderRoundLogicTest {

    @Test
    public void getNumberOfQuestionsInRoundTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals(numberOfQuestionsInRound, testInstance.getNumberOfQuestionsInRound());
    }

    @Test
    public void giveCreditsTest() {
        HighStakesRoundLogic testInstance = new HighStakesRoundLogic(numberOfQuestionsInRound, referee);
        int betPlaced = 750;
        for (Player player : referee.getAlivePlayersInRound()) {
            testInstance.setBetForPlayer(player, betPlaced);
            betPlaced -= 250;
        }
        testInstance.giveCredits();
        assertEquals(1750, players.get(0).getScore());
        assertEquals(-500, players.get(1).getScore());
        assertEquals(2, testInstance.getNumberOfQuestionsRemaining());
    }

    @Test
    public void isOverTest() {
        HighStakesRoundLogic testInstance = new HighStakesRoundLogic(numberOfQuestionsInRound, referee);
        int betPlaced = 750;
        for (Player player : referee.getAlivePlayersInRound()) {
            testInstance.setBetForPlayer(player, betPlaced);
            betPlaced -= 250;
        }
        for (int questionsInRound = 1; questionsInRound <= numberOfQuestionsInRound - 1; questionsInRound++) {
            testInstance.giveCredits();
            assertEquals(false, testInstance.isOver());
        }
        testInstance.giveCredits();
        assertEquals(true, testInstance.isOver());
    }

}

