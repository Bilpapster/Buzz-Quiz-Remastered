package GUI;

import com.Sound.SoundManager;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {

    private JPanel settingsPanel;
    private JFrame parentFrame;

    public SettingsFrame(SoundManager soundManager, JFrame parentFrame) {

        super();
        settingsPanel = new SettingsPanel(soundManager);
        this.parentFrame = parentFrame;
        setUpFrame();

    }

    private void setUpFrame() {
        this.setTitle("Settings menu");
        this.setLocationRelativeTo(parentFrame);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(settingsPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

}
