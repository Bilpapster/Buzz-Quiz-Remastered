package GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;

/**
 * A simple class that represents a panel, painted with a background image. Extends the <code>JPanel</code> class.
 *
 * @author Vasileios Papastergios
 */
public class BackgroundImagedPanel extends JPanel {

    private Image backgroundImage;

    public BackgroundImagedPanel(String backgroundImage) {
        this(new ImageIcon(backgroundImage).getImage());
    }

    public BackgroundImagedPanel() {
    }

    public BackgroundImagedPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage.getImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }

}