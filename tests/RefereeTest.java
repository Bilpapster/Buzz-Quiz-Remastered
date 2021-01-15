import com.Player;
import com.Question;
import com.Referee;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RefereeTest {
    private ArrayList<Player> players = new ArrayList<>();

    public RefereeTest() {
        players.add(new Player("Mr Tsoumakas", 1000));
        players.add(new Player("John Doe"));
    }

    @Test
    public void hasPlayerAnsweredTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        String correctAnswer = testInstance.getCorrectAnswerOfCurrentQuestion();
        testInstance.setAnswerData(players.get(0), correctAnswer, 1234);
        testInstance.setAnswerData(players.get(0), "Another try to answer, that should not be accepted", 500135);
        assertTrue(testInstance.hasPlayerAnswered(players.get(0)));
        assertFalse(testInstance.hasPlayerAnswered(players.get(1)));

    }

    @Test
    public void hasPlayerAnsweredCorrectlyTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        String correctAnswer = testInstance.getCorrectAnswerOfCurrentQuestion();
        testInstance.setAnswerData(players.get(0), correctAnswer, 1234);
        testInstance.setAnswerData(players.get(0), "Another try to answer, that should not be accepted", 500135);
        assertTrue(testInstance.hasPlayerAnsweredCorrectly(players.get(0)));
        assertFalse(testInstance.hasPlayerAnsweredCorrectly(players.get(1)));

        testInstance.setAnswerData(players.get(1), "not the correct answer", 1234);
        assertFalse(testInstance.hasPlayerAnsweredCorrectly(players.get(1)));
    }

    @Test
    public void executeActionsBeforeNextQuestionTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        Question previousQuestion = testInstance.getQuestion();
        testInstance.setAnswerData(players.get(0), "An answer", 1234);
        testInstance.setAnswerData(players.get(1), "Another answer", 1234);

        testInstance.executeActionsBeforeNextQuestion();
        Question currentQuestion = testInstance.getQuestion();
        assertNotEquals(previousQuestion.getQuestionText(), currentQuestion.getQuestionText());
        assertFalse(testInstance.hasPlayerAnswered(players.get(0)));
        assertFalse(testInstance.hasPlayerAnswered(players.get(1)));
    }

    @Test
    public void removeFromAlivePlayersTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        testInstance.removeFromAlivePlayers(players.get(0));
        assertFalse(testInstance.getAlivePlayersInRound().contains(players.get(0)));
        assertTrue(testInstance.getAlivePlayersInRound().contains(players.get(1)));
        testInstance.removeFromAlivePlayers(players.get(0));
        assertFalse(testInstance.getAlivePlayersInRound().contains(players.get(0)));
        testInstance.removeFromAlivePlayers(players.get(1));
        assertEquals(0, testInstance.getAlivePlayersInRound().size());
    }

    @Test
    public void executeActionsBeforeNextRoundTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        Question previousQuestion = testInstance.getQuestion();
        testInstance.removeFromAlivePlayers(players.get(0));

        testInstance.executeActionsBeforeNextRound();
        Question currentQuestion = testInstance.getQuestion();
        assertNotEquals(previousQuestion.getQuestionText(), currentQuestion.getQuestionText());
        assertTrue(testInstance.getAlivePlayersInRound().contains(players.get(0)));
    }

    @Test
    public void haveAllPlayersAnswered() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        assertFalse(testInstance.haveAllPlayersAnswered());

        testInstance.setAnswerData(players.get(0), "an answer", 1234);
        assertFalse(testInstance.haveAllPlayersAnswered());
        testInstance.setAnswerData(players.get(0), "another answer by the same player", 1234);
        assertFalse(testInstance.haveAllPlayersAnswered());

        testInstance.setAnswerData(players.get(1), "an answer", 1234);
        assertTrue(testInstance.haveAllPlayersAnswered());
    }

    @Test
    public void getNumberOfCorrectAndWrongAnswersTest() {
        Referee testInstance = new Referee(players);
        testInstance.executeActionsBeforeNextQuestion();
        String correctAnswer = testInstance.getCorrectAnswerOfCurrentQuestion();
        testInstance.setAnswerData(players.get(0), correctAnswer, 1234);
        testInstance.setAnswerData(players.get(0), correctAnswer + "#not", 1234);
        assertEquals(1, testInstance.getNumberOfCorrectAnswers());
        assertEquals(1, testInstance.getNumberOfWrongAnswers());
    }
}
