import com.Player;
import com.Referee;
import com.StopTheClockRoundLogic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StopTheClockRoundLogicTest extends PointBuilderRoundLogicTest{

    public StopTheClockRoundLogicTest() {
        this.players.clear();

        // Mr Tsoumakas deserves to start the game with 1000 points
        this.players.add(new Player("Mr Tsoumakas", 1000));

        // anybody else starts the game with 0 points, as usual.
        this.players.add(new Player("John Doe"));

        referee = new Referee(players);
        referee.executeActionsBeforeNextQuestion();
        String correctAnswer = referee.getCorrectAnswerOfCurrentQuestion();

        long millisElapsed = 1234L;
        for (Player player : players) {
            referee.setAnswerData(player, correctAnswer, millisElapsed);
            millisElapsed = 500135L;
        }
        /*
        At the end of the iteration, both players have answered the question correctly. However, player1 (Mr Tsoumakas)
        has answered the question in less time than pleyer2 (John Doe). In particular, Mr Tsoumakas should be rewarded
        with (5000 - 1234)*0.2 = 753 points, going from 1000 to 1753 points.
        We also expect that player2 (John Doe), is rewarded with 0 points, despite answering correctly, since he
        answered when the time was over.
         */
    }

    @Test
    public void getNumberOfQuestionsInRoundTest() {
        StopTheClockRoundLogic testInstance = new StopTheClockRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals(numberOfQuestionsInRound, testInstance.getNumberOfQuestionsInRound());
    }

    @Test
    public void getDescriptionTest() {
        StopTheClockRoundLogic testInstance = new StopTheClockRoundLogic(numberOfQuestionsInRound, referee);
        String roundDescription = "Stop the clock: In this round you are going to be asked " +
                this.numberOfQuestionsInRound +
                " questions. You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. There is a 3-seconds time " +
                "interval for the players to read the question. At the end of this interval, the available options " +
                "are revealed and the clock starts running\n " +
                "Answering the question correctly will add to your score as many points as the remaining  " +
                "milliseconds times 0.2!\n There is no point penalty on wrong answer.";

        assertEquals(roundDescription, testInstance.getDescription());
    }

    @Test
    public void getOfficialNameTest() {
        StopTheClockRoundLogic testInstance = new StopTheClockRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals("Stop the Clock", testInstance.getOfficialName());
    }


    @Test
    public void giveCreditsTest() {
        StopTheClockRoundLogic testInstance = new StopTheClockRoundLogic(numberOfQuestionsInRound, referee);
        testInstance.giveCredits();
        assertEquals(1753, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(2, testInstance.getNumberOfQuestionsRemaining());
    }

    @Test
    public void isOverTest() {
        StopTheClockRoundLogic testInstance = new StopTheClockRoundLogic(numberOfQuestionsInRound, referee);
        for (int questionsInRound = 1; questionsInRound <= numberOfQuestionsInRound - 1; questionsInRound++) {
            testInstance.giveCredits();
            assertEquals(false, testInstance.isOver());
        }
        testInstance.giveCredits();
        assertEquals(true, testInstance.isOver());
    }
}
