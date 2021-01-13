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
    public void getDescriptionTest() {
        HighStakesRoundLogic testInstance = new HighStakesRoundLogic(numberOfQuestionsInRound, referee);
        String roundDescription = "HighStakes: In this round you are going to be asked " + this.numberOfQuestionsInRound +
                " questions.\n" +
                "At first, you are let to know the category of the question and you are asked to place a bet " +
                " Answering correctly will add to your score as many points as your bet!\n" +
                " But, be careful! Answering the question wrong will cost you your bet!\n";

        assertEquals(roundDescription, testInstance.getDescription());
    }

    @Test
    public void getOfficialNameTest() {
        HighStakesRoundLogic testInstance = new HighStakesRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals("High Stakes", testInstance.getOfficialName());
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

