package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FontManager {
    private static final HashMap<FontStyle, Font> availableFonts = new HashMap<>();
    private static final String basicFontDirectoryPath = "resources/Fonts/AkaAcidDosis";

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
            e.printStackTrace();
        }
    }

    public static Font getCustomizedFont(FontStyle fontStyle, float fontSize) {
        return availableFonts.get(fontStyle).deriveFont(fontSize);
    }
}
