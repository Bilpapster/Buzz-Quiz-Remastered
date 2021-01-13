import com.Constants;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ConstantsTest {
    @Test
    public void staticArraySoundPathsTest() {
        assertEquals(Constants.SOUND_PATHS[0], "src/resources/SoundClips/correct_answer.wav");
        assertEquals(Constants.SOUND_PATHS[1], "src/resources/SoundClips/wrong_answer.wav");
        assertEquals(Constants.SOUND_PATHS[2], "src/resources/SoundClips/main_menu_theme.wav");
        assertEquals(Constants.SOUND_PATHS[3], "src/resources/SoundClips/during_game_theme.wav");
    }
}
