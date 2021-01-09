package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A simple class that represents a round-shaped button in our game. Objects of this class are utilized in
 * the answer buttons displayed to the player(s). The class is developed as a sub-class of the <code>JButton</code>
 * class.
 *
 * @author Vasileios Papapstergios
 */
public class RoundedJButton extends JButton {
    private Dimension arcs;

    /**
     * Constructs a rounded button with given arc dimensions.
     *
     * @param arcs the dimensions for the button arcs.
     */
    public RoundedJButton(Dimension arcs) {
        this.arcs = arcs;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        RoundShapeArtist.drawRoundedShapeAndStroke(this, arcs, g);

        FontMetrics fm = graphics.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        graphics.drawString(getText(), x, y);

        setBorderPainted(false);
    }
}
