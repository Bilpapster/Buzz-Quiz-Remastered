import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QuestionTest {

    public QuestionTest() {

    }

    @BeforeClass
    public static void beforeClass() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void questionShouldWork() {
        HashMap<String, String> question1Answers = new HashMap<>();
        question1Answers.put("a", "Yes");
        question1Answers.put("b", "It");
        question1Answers.put("c", "Definitely");
        question1Answers.put("d", "Is");
        Question question1 = new Question("This is a test question", "Yes", question1Answers, QuestionType.History, false);

        assertEquals("This is a test question", question1.getQuestionText());
        assertEquals("Yes", question1.getCorrectAnswer());
        assertEquals(question1Answers.get("a"), question1.getCorrectAnswer());
        assertEquals(QuestionType.History, question1.getQuestionType());

        HashMap<String, String> questionWithPictureAnswers = new HashMap<>();
        questionWithPictureAnswers.put("a", "I think");
        questionWithPictureAnswers.put("b", "It");
        questionWithPictureAnswers.put("c", "Definitely");
        questionWithPictureAnswers.put("d", "Does");
        Question questionWithPicture = new Question("Does this question have a picture?", "Definitely", questionWithPictureAnswers, QuestionType.Technology, true, "image1.png");

        assertEquals(true, questionWithPicture.hasPicture());
        assertEquals("image1.png", questionWithPicture.getPicture());

    }

}
