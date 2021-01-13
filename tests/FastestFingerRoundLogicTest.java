import com.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastestFingerRoundLogicTest extends PointBuilderRoundLogicTest {


    public FastestFingerRoundLogicTest() {
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
            millisElapsed+=20;
        }
        /*
        At the end of the iteration, both players have answered the question correctly. However, Mr Tsoumakas was
        faster than his opponent so we expect that he is rewarded with 1000 points (going from 1000 to 2000).
        On the other hand, pleyer2 (John Doe) has not answered correctly, but in more time than his opponent. Thus, he
        should be rewarded with 500 points, going from 0 to 500 points.
         */
    }

    @Test
    public void getNumberOfQuestionsInRoundTest() {
        FastestFingerRoundLogic testInstance = new FastestFingerRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals(numberOfQuestionsInRound, testInstance.getNumberOfQuestionsInRound());
    }

    @Test
    public void getDescriptionTest() {
        FastestFingerRoundLogic testInstance = new FastestFingerRoundLogic(numberOfQuestionsInRound, referee);
        String roundDescription = "In this round you are going to be asked " + this.numberOfQuestionsInRound +
                " questions. " +
                "You will have 5 seconds to answer!\n"
                + "At first you are let to know the category and the question itself. There is a 3-seconds time " +
                "interval for the players to read the question. At the end of this interval, the available options " +
                "are revealed and the clock starts running\n " +
                "The quicker to answer the question correctly gets 1000 points, while the second correct player gets " +
                "half of them, 500!\n" +
                "No points available for other players, even if they answer correctly, so be as quick as possible!\n";

        assertEquals(roundDescription, testInstance.getDescription());
    }

    @Test
    public void getOfficialNameTest() {
        FastestFingerRoundLogic testInstance = new FastestFingerRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals("Fastest Finger", testInstance.getOfficialName());
    }


    @Test
    public void giveCreditsTest() {
        FastestFingerRoundLogic testInstance = new FastestFingerRoundLogic(numberOfQuestionsInRound, referee);
        testInstance.giveCredits();
        assertEquals(2000, players.get(0).getScore());
        assertEquals(500, players.get(1).getScore());
        assertEquals(2, testInstance.getNumberOfQuestionsRemaining());
    }

    @Test
    public void isOverTest() {
        FastestFingerRoundLogic testInstance = new FastestFingerRoundLogic(numberOfQuestionsInRound, referee);
        for (int questionsInRound = 1; questionsInRound <= numberOfQuestionsInRound - 1; questionsInRound++) {
            testInstance.giveCredits();
            assertEquals(false, testInstance.isOver());
        }
        testInstance.giveCredits();
        assertEquals(true, testInstance.isOver());
    }
}

