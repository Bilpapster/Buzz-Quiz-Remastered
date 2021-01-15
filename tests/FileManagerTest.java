import com.FileManagers.FileManager;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FileManagerTest {

    FileManager fileManager;

    public FileManagerTest() {
        try {
            fileManager = new FileManager("tests/TestFiles/test_scores.txt", "tests/TestFiles/test_questions.txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getQuestionsFromFileShouldWork() {

        ArrayList<String> expectedQuestions = new ArrayList<>();
        expectedQuestions.add("\"Fe\" is the chemical symbol for which common element?/Copper/Iron/Oxygen/Hydrogen/Iron/Science/false/none");
        expectedQuestions.add("Approximately how many Earths could fit inside the sun?/356.000/1.3 Million/864.000/2 Million/1.3 Million/Science/false/none");
        expectedQuestions.add("Ascorbic acid is more commonly referred to as what?/Gluten/Lactose/Vitamin C/Aspirin/Vitamin C/Science/false/none");

        ArrayList<String> actualQuestions = fileManager.getQuestionsFromFile();

        assertEquals(expectedQuestions, actualQuestions);

    }

    @Test
    public void getHighScoresFromFileShouldWork() {

        LinkedHashMap<String, Integer> expectedHighscores = new LinkedHashMap<>();
        expectedHighscores.put("MrTsoumakas", 589875);
        expectedHighscores.put("Papster", 78);
        expectedHighscores.put("Fotis", -5);

        assertEquals(expectedHighscores, fileManager.getHighscoresFromFile());

    }


    @Test
    public void getHighscoreFileNameShouldWork() {
        assertEquals("tests/TestFiles/test_scores.txt", fileManager.getHighscoreFileName());
    }

    @Test
    public void getQuestionsFileNameShouldWork() {
        assertEquals("tests/TestFiles/test_questions.txt", fileManager.getQuestionsFileName());
    }

}
