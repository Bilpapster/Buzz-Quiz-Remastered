package GUI;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JDialog {

    private JPanel settingsPanel;
    private JFrame parentFrame;

    public SettingsFrame(final JFrame parentFrame) {

        super(parentFrame);
        settingsPanel = new SettingsPanel();
        this.parentFrame = parentFrame;
        setUpFrame();

    }

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
