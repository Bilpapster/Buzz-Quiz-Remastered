import com.BoilingPointRoundLogic;
import com.Constants;
import com.Player;
import com.Referee;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoilingPointRoundLogicTest {

    protected ArrayList<Player> players = new ArrayList<>();
    protected Referee referee;
    protected int numberOfQuestionsInRound = 3;

    public BoilingPointRoundLogicTest() {
        // Mr Tsoumakas deserves to start the game with 1000 points
        this.players.add(new Player("Mr Tsoumakas", 1000));

        // anybody else starts the game with 0 points, as usual.
        this.players.add(new Player("John Doe"));

        this.players.add(new Player("Third Player"));

        referee = new Referee(players);
        referee.executeActionsBeforeNextQuestion();
        String correctAnswer = referee.getCorrectAnswerOfCurrentQuestion();

        referee.setAnswerData(players.get(0), correctAnswer, 1234);
        referee.setAnswerData(players.get(1), "not correct answer", 1235);
        referee.setAnswerData(players.get(2), correctAnswer, 1236);
    }

    @Test
    public void getDescriptionTest() {
        BoilingPointRoundLogic testInstance = new BoilingPointRoundLogic(referee);
        String roundDescription = "In this round you are going to be asked questions, till someone reaches (at least) " +
                "five correct answers!\n"
                + "But, be careful! You need to have at least one correct answer more than all the other players, " +
                "in order to win the round!\n"
                + "The winner gets 5000 points!\n";

        assertEquals(roundDescription, testInstance.getDescription());
    }

    @Test
    public void getOfficialNameTest() {
        BoilingPointRoundLogic testInstance = new BoilingPointRoundLogic(referee);
        assertEquals("Boiling Point", testInstance.getOfficialName());
    }

    @Test
    public void giveCreditsTest() {
        BoilingPointRoundLogic testInstance = new BoilingPointRoundLogic(referee);

        for (int question = 0; question < Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND; question++) {
            testInstance.giveCredits();
            assertEquals(1000, players.get(0).getScore());
            assertEquals(0, players.get(1).getScore());
            assertEquals(0, players.get(2).getScore());
            assertTrue(referee.hasPlayerAnsweredCorrectly(players.get(0)));
            assertFalse(referee.hasPlayerAnsweredCorrectly(players.get(1)));
            assertTrue(referee.hasPlayerAnsweredCorrectly(players.get(2)));
            assertEquals(question+1, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(0)));
            assertEquals(0, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(1)));
            assertEquals(question+1, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(2)));
            assertFalse(testInstance.isOver());
        }

        testInstance.giveCredits();
        assertEquals(1000, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(0, players.get(2).getScore());
        assertFalse(referee.getAlivePlayersInRound().contains(players.get(1)));
        assertEquals(Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND + 1, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(0)));
        assertEquals(-1, testInstance.getNumberOfCorrectAnswersOfPlayer(new Player("Non existent player")));
        assertEquals(Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND + 1, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(2)));

        referee.executeActionsBeforeNextQuestion();
        referee.setAnswerData(players.get(0), "not correct", 1234);
        referee.setAnswerData(players.get(2), referee.getCorrectAnswerOfCurrentQuestion(), 1234);
        testInstance.giveCredits();
        assertEquals(Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND + 1, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(0)));
        assertEquals(Constants.NUMBER_OF_QUESTIONS_IN_A_ROUND + 2, testInstance.getNumberOfCorrectAnswersOfPlayer(players.get(2)));
        assertEquals(1000, players.get(0).getScore());
        assertEquals(5000, players.get(2).getScore());
        assertTrue(testInstance.isOver());

        referee.executeActionsBeforeNextRound();
        assertTrue(referee.getAlivePlayersInRound().contains(players.get(0)));
        assertTrue(referee.getAlivePlayersInRound().contains(players.get(1)));
        assertTrue(referee.getAlivePlayersInRound().contains(players.get(2)));

    }

}
