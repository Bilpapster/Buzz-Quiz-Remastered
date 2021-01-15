package GUI;

import javax.swing.*;
import java.awt.*;

public class GUIGame {

    public GUIGame() {
        this.setUpMenuGUI();
    }

    public void setUpMenuGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenuFrame mainMenuFrame = new MainMenuFrame();
            }
        });
    }
}
