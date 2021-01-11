package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A very simple class that represents a JLabel in the front end of our game. The class is developed as a subclass of
 * the JLabel one, in order to provide easy instances of a type of JLabel with specific attributes and appearance.
 * At their creation, objects of this class have the customized font of our game, white foreground color and they are
 * aligned centrally. All these attributes can be altered through the known methods of the JLabel class.
 * The class is mainly developed for avoiding code repetition, since this uniform type of labels is used quite ofter
 * throughout the front end of our game.
 *
 * @author Vasileios Papastergios
 *
 */
public class CustomizedJLabel extends JLabel {
    public CustomizedJLabel(FontManager.FontStyle fontStyle, float fontSize) {
        this.setFont(FontManager.getCustomizedFont(fontStyle, fontSize));
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }
}
