package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A class representing the settings frame for use in the main menu
 */
public class SettingsFrame extends JDialog {

    private JPanel settingsPanel;
    private JFrame parentFrame;

    /**
     * Default constructor which constructs the frame and sets all its elements
     * @param parentFrame the parent frame of this component, for centering
     */
    public SettingsFrame(final JFrame parentFrame) {

        super(parentFrame);
        settingsPanel = new SettingsPanel();
        this.parentFrame = parentFrame;
        setUpFrame();

    }

    /**
     * Sets up the frame
     */
    private void setUpFrame() {
        this.setTitle("Settings menu");
        this.setBounds(180, 180, 680, 200);
        this.setLocationRelativeTo(parentFrame);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(settingsPanel, BorderLayout.CENTER);
        this.setAlwaysOnTop(true);
    }

}
