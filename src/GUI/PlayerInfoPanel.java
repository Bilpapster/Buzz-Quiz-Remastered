package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A simple class that represents a player info panel in our game. The panel contains as info the player' name and
 * score.
 *
 * @author Vasileios Papastergios
 */
class PlayerInfoPanel {

    private JPanel rootPanel;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private String name;
    private int score;

    /**
     * Constructs an info panel with the given player name. The player's score is set to 0.
     *
     * @param name the player's name.
     */
    public PlayerInfoPanel(String name) {
        this.name = name;
        this.score = 0;
        setUpLabels();
    }

    /**
     * Sets up the labels for the name and the score of the player.
     */
    private void setUpLabels() {
        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 22f));
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.add(nameLabel);

        scoreLabel = new JLabel(String.format("%,d", score));
        scoreLabel.setForeground(Color.ORANGE);
        scoreLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 22f));
        JPanel scorePanel = new JPanel();
        scorePanel.setOpaque(false);
        scorePanel.add(scoreLabel);

        rootPanel = new JPanel();
        rootPanel.setOpaque(false);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.add(Box.createVerticalStrut(6));
        rootPanel.add(namePanel);
        rootPanel.add(Box.createVerticalStrut(6));
        rootPanel.add(scorePanel);
        rootPanel.add(Box.createVerticalStrut(10));
    }

    /**
     * Getter for the root panel of the class. This is the one that can be added on other panels of frames.
     *
     * @return the root panel of the info panel.
     */
    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    /**
     * Updates the values displayed on the score label of the player.
     *
     * @param score the score value to update.
     */
    public void updateScore(int score) {
        if (this.score != score) {
            this.score = score;
            scoreLabel.setText(String.format("%,d", score));
        }
    }

    /**
     * Updates the name displayed on the name label of the player.
     *
     * @param name the name value to update.
     */
    public void updateName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            nameLabel.setText(name);
        }
    }
}
