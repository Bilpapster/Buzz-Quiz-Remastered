import com.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class PlayerTest {

    @Test
    public void constructorAndAccessorsTest() {
        Player testInstance = new Player("Mr Tsoumakas");
        assertEquals("Mr Tsoumakas", testInstance.getName());
        assertEquals(0, testInstance.getScore());

        testInstance = new Player("John Doe", 500135);
        assertEquals("John Doe", testInstance.getName());
        assertEquals(500135, testInstance.getScore());

    }

    @Test
    public void updateScoreTest() {
        Player testInstance = new Player("Mr Tsoumakas");
        testInstance.updateScore(500);
        testInstance.updateScore(1000);
        testInstance.updateScore(-123);
        assertEquals(1377, testInstance.getScore());
    }

}
