import com.*;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;


import java.awt.*;
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
        referee.executeActionsBeforeNextQuestion();
        Color color = new Color(1, 2, 3); //dummy color
        if (referee.getQuestion().getQuestionType() == QuestionType.History) {
            color = new Color(218, 83, 44);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Movies_and_Series) {
            color = new Color(126, 56, 120);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Sports) {
            color = new Color(30, 144, 255);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Science) {
            color = new Color(0, 163, 0);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Technology) {
            color = new Color(169, 3, 8);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Music) {
            color = new Color(255, 0, 151);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Food_and_Culture) {
            color = new Color(254, 75, 3);
        } else if (referee.getQuestion().getQuestionType() == QuestionType.Geography) {
            color = new Color(51, 85, 139);
        }

        assertEquals(color, QuestionType.getColorOf(referee.getQuestion().getQuestionType()));
        assertEquals(new ImageIcon(Constants.BACKGROUND_IMAGES_PATH + referee.getQuestion().getQuestionType().toString() + ".png").toString(),
                QuestionType.getBackgroundImageOf(referee.getQuestion().getQuestionType()).toString());
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
    public void getDescriptionTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        String roundDescription = " Point Builder: In this round you are going to be asked " + this.numberOfQuestionsInRound +
                " questions.\n"
                + "For every correct answer, you earn 1000 points!\n"
                + "No point penalty on wrong answer.";

        assertEquals(roundDescription, testInstance.getDescription());
    }

    @Test
    public void getOfficialNameTest() {
        PointBuilderRoundLogic testInstance = new PointBuilderRoundLogic(numberOfQuestionsInRound, referee);
        assertEquals("Point Builder", testInstance.getOfficialName());
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
