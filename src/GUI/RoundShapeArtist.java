package GUI;

import javax.swing.*;
import java.awt.*;


/**
 * An auxiliary class that provides a static method, in order to draw round-shaped components.
 * The class is utilized by the customized round buttons and round panels of the game, in order to
 * feature uniformity, in terms of design.
 *
 * @author Vasileios Papastergios
 */
public class RoundShapeArtist {

    public static void drawRoundedShapeAndStroke(JComponent component, Dimension arcs, Graphics g) {
        int strokeSize = 1;
        Color shadowColor = Color.BLACK;
        boolean shady = true;
        boolean highQuality = true;
        int shadowGap = 5;
        int shadowOffset = 4;
        int shadowAlpha = 150;

        int width = component.getWidth();
        int height = component.getHeight();

        Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(shadowOffset, shadowOffset, width - strokeSize - shadowOffset, height - strokeSize - shadowOffset, arcs.width, arcs.height);
        } else {
            shadowGap = 1;
        }

        graphics.setColor(component.getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(component.getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        graphics.setStroke(new BasicStroke());
    }
}
