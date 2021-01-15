package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Basic class from which the game launches
 */
public class GUIGame {

    /**
     * Launches the game
     */
    public GUIGame() {
        this.setUpMenuGUI();
    }

    /**
     * Spawns a thread and runs the game in it
     */
    public void setUpMenuGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenuFrame mainMenuFrame = new MainMenuFrame();
            }
        });
    }
}
