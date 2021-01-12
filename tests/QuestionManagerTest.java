import com.Question;
import com.QuestionManager;
import com.QuestionType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QuestionManagerTest {

    public QuestionManagerTest() {
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
    public void questionManagerShouldWork() {
        QuestionManager questionManager = new QuestionManager();

        //first question
        HashMap<String, String> question1Answers = new HashMap<>();
        question1Answers.put("a", "This");
        question1Answers.put("b", "Is");
        question1Answers.put("c", "A placeholder");
        question1Answers.put("d", "com.Question");
        Question question1 = new Question("What is this?", "A placeholder", question1Answers, QuestionType.History, false);

        questionManager.addNewQuestion(question1);

        HashMap<String, String> question2Answers = new HashMap<>();
        question2Answers.put("a", "I don't");
        question2Answers.put("b", "Know what");
        question2Answers.put("c", "To put");
        question2Answers.put("d", "Here");
        Question question2 = new Question("What am I doing?", "Know what", question2Answers, QuestionType.History, false);

        questionManager.addNewQuestion(question2);

        assertEquals(question1, questionManager.getNextQuestion());
        assertEquals(2, questionManager.questionsRemaining());
        questionManager.removeAnsweredQuestion();
        assertEquals(1, questionManager.questionsRemaining());
        assertEquals(question2, questionManager.getNextQuestion());
        questionManager.removeAnsweredQuestion();

        questionManager.createQuestions();
        assertEquals(109 ,questionManager.questionsRemaining());


    }
}
