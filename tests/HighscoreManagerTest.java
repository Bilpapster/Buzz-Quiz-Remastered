import com.FileManagers.FileManager;
import com.FileManagers.HighscoreManager;
import com.Player;
import org.junit.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HighscoreManagerTest {

    private static HighscoreManager highscoreManager;
    private static FileManager fileManager;
    private static FileWriter fileWriter;
    private static File highscoreFile;

    @BeforeClass
    public static void beforeClass() throws Exception {
        fileManager = new FileManager("tests/TestFiles/test_updated_scores.txt", "tests/TestFiles/test_questions.txt");
        highscoreManager = new HighscoreManager(fileManager);
        highscoreFile = fileManager.getHighScoreFile();

        ArrayList<Player> initialScores = new ArrayList<>();
        initialScores.add(new Player("MrTsoumakas", 589875));
        initialScores.add(new Player("Papster", 78));
        initialScores.add(new Player("Fotis", -5));

        highscoreManager.updateHighscores(initialScores);

    }

    @AfterClass
    public static void afterClass() throws Exception {
        assertTrue(highscoreFile.delete());
    }

    @Test
    public void updateHighscoresWorks() {

        ArrayList<Player> updatedPlayers = new ArrayList<>();
        updatedPlayers.add(new Player("Fotis", 5));
        updatedPlayers.add(new Player("Papster", -5));
        updatedPlayers.add(new Player("Babis", 589));

        LinkedHashMap<String, Integer> expectedHighscoresInitially = new LinkedHashMap<>();
        expectedHighscoresInitially.put("MrTsoumakas", 589875);
        expectedHighscoresInitially.put("Papster", 78);
        expectedHighscoresInitially.put("Fotis", -5);

        assertEquals(expectedHighscoresInitially, fileManager.getHighscoresFromFile());

        highscoreManager.updateHighscores(updatedPlayers);
        expectedHighscoresInitially.put("Fotis", 5);
        expectedHighscoresInitially.put("Babis", 589);

        assertEquals(expectedHighscoresInitially, fileManager.getHighscoresFromFile());

    }



}
