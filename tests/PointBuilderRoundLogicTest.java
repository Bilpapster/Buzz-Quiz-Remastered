import com.Player;
import com.PointBuilderRoundLogic;
import com.Referee;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;

public class PointBuilderRoundLogicTest {

    protected ArrayList<Player> players = new ArrayList<>();
    protected Referee referee;
    protected int numberOfQuestionsInRound = 3;

    public PointBuilderRoundLogicTest() {
        // Mr Tsoumakas deserves to start the game with 1000 points
        this.players.add(new Player("Mr Tsoumakas", 1000));

        // anybody else starts the game with 0 points, as usual.
        this.players.add(new Player("John Doe"));

        referee = new Referee(players);
        referee.executeActionsBeforeNextQuestion();
        String correctAnswer = referee.getCorrectAnswerOfCurrentQuestion();

        long millisElapsed = 1234L;
        String suffix = "";
        for (Player player : players) {
            referee.setAnswerData(player, correctAnswer + suffix, millisElapsed);
            millisElapsed+=20;
            suffix = "#NOT_CORRECT_ANY_MORE";
        }
        /*
        At the end of the iteration, player1 (Mr Tsoumakas) has answered the question correctly, while pleyer2
        (John Doe) has not answered correctly. Thus, we expect that Mr Tsoumakas is rewarded with 1000 points, thus
        going from 1000 to 2000 points. No point reward for player2 (John Doe), so we expect that he remains to 0 points.
         */
    }

    @Test
    public void getNumberOfQuestionsInRoundTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals(numberOfQuestionsInRound, testInstance.getNumberOfQuestionsInRound());
    }

    @Test
    public void giveCreditsTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        testInstance.giveCredits();
        assertEquals(2000, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(2, testInstance.getNumberOfQuestionsRemaining());
    }

    @Test
    public void isOverTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        for (int questionsInRound = 1; questionsInRound <= numberOfQuestionsInRound - 1; questionsInRound++) {
            testInstance.giveCredits();
            assertEquals(false, testInstance.isOver());
        }
        testInstance.giveCredits();
        assertEquals(true, testInstance.isOver());
    }
}
