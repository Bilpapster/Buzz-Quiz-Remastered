package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A simple class that represents a round-shaped panel in our game. Objects of this class are utilized in
 * the panel displaying the type of current question to player(s). T
 * he class is developed as a sub-class of the <code>JPanel</code> class.
 *
 * @author Vasileios Papapstergios
 */
public class RoundedJPanel extends JPanel {

    private Dimension arcs;

    /**
     * Constructs a rounded panel with given arc dimensions.
     *
     * @param arcs the dimensions for the panel arcs.
     */
    public RoundedJPanel(Dimension arcs) {
        this.arcs = arcs;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setAlignmentX(CENTER_ALIGNMENT);
        RoundShapeArtist.drawRoundedShapeAndStroke(this, arcs, g);
    }
}
