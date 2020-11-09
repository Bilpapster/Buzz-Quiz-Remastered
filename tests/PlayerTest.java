import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    public PlayerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
    }


    /**
     * Tests the construction of Player
     */
    @Test
    public void playerShouldBeCreated() {
        Player testPlayer1 = new Player("Fotis");
        String expectedPlayerName1 = "Fotis";
        assertEquals(expectedPlayerName1, testPlayer1.getName());
        Player testPlayer2 = new Player("Vasillis");
        String expectedPlayerName2 = "Vasillis";
        assertEquals(expectedPlayerName2, testPlayer2.getName());
        int expectedScore = 0;
        assertEquals(expectedScore, testPlayer1.getScore());
        assertEquals(expectedScore, testPlayer2.getScore());
        testPlayer1.updateScore(150);
        testPlayer1.updateScore(-50);
        int expectedUpdatedScore = 100;
        assertEquals(expectedUpdatedScore, testPlayer1.getScore());
    }

}
