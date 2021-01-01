package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * A utility class that provides easy access to the custom font of the game. The font used for the game is
 * "Aka Acid Dosis", which is distributed under the SIL Open Font License (OFL)
 *
 * @author Vasileios Papastergios
 */
public class FontManager {
    private static final HashMap<FontStyle, Font> availableFonts = new HashMap<>();
    private static final String basicFontDirectoryPath = "src/resources/Fonts/AkaAcidDosis";

    /**
     * A simple enumeration representing the available font styles of the custom font.
     */
    public enum FontStyle {
        EXTRA_LIGHT, LIGHT, REGULAR, MEDIUM, SEMI_BOLD, BOLD, EXTRA_BOLD
    }

    static {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            for (FontStyle fontStyle : FontStyle.values()) {
                Font availableFont = Font.createFont(Font.TRUETYPE_FONT, new File(basicFontDirectoryPath + "-" + fontStyle.toString() + ".ttf"));
                ge.registerFont(availableFont);
                availableFonts.put(fontStyle, availableFont);
            }

        } catch (IOException | FontFormatException e) {
            /* in case an exception is thrown, play the game with a back-up, emergency font, that is supported for sure */
            Font emergencyFont = new Font("Arial Black", Font.PLAIN, 20);
            for (FontStyle fontStyle : FontStyle.values()) {
                availableFonts.put(fontStyle, emergencyFont);
            }
        }
    }

    /**
     * Getter for all styles of the custom font.
     *
     * @param fontStyle the desired style of the custom font
     * @param fontSize  the desired size of the custom font
     * @return the custom font with the desired style and size
     */
    public static Font getCustomizedFont(FontStyle fontStyle, float fontSize) {
        return availableFonts.get(fontStyle).deriveFont(fontSize);
    }
}
