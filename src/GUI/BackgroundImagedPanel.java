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

    /**
     * Constructs a background imaged panel with the given image name.
     *
     * @param backgroundImage the string name of the background image
     */
    public BackgroundImagedPanel(String backgroundImage) {
        this(new ImageIcon(backgroundImage).getImage());
    }

    /**
     * Constructs a background image with no image. <code>setBackgroundImage</code> must be called before making
     * this panel visible.
     */
    public BackgroundImagedPanel() {
    }

    /**
     * Constructs a background imaged panel with the given image.
     *
     * @param backgroundImage the backgroun image of the panel.
     */
    public BackgroundImagedPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Constructs a background imaged panel with the given <code>imageIcon</code>
     *
     * @param backgroundImage the imageIcon. whose image to set as background
     */
    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage.getImage();
    }

    /**
     * Draws the background image on the panel, based on the dimensions of the panel at the current time.
     *
     * @param g a graphics object to paint the panel with
     */
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}