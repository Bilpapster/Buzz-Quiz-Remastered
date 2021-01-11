package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A simple class that represents a player info panel in our game. The panel contains as info the player' name and
 * score.
 *
 * @author Vasileios Papastergios
 */
class PlayerInfoPanel {

    private JPanel rootPanel = new JPanel();
    private JLabel nameLabel = new CustomizedJLabel(FontManager.FontStyle.SEMI_BOLD, 22f);
    private JLabel scoreLabel = new CustomizedJLabel(FontManager.FontStyle.SEMI_BOLD, 22f);
    private String name;
    private int score = 0;

    /**
     * Constructs an info panel with the given player name. The player's score is set to 0.
     *
     * @param name the player's name.
     */
    public PlayerInfoPanel(String name) {
        this.name = name;
        setUpLabels();
        setUpRootPanel();
    }

    /**
     * Sets up the labels for the name and the score of the player.
     */
    private void setUpLabels() {
        nameLabel.setText(name);

        scoreLabel.setText(String.format("%,d", score));
        scoreLabel.setForeground(Color.ORANGE);
    }

    /**
     * Sets up the root panel of the component by adding the label containing the name
     * and the score the one under the other, aligned centrally.
     */
    private void setUpRootPanel() {
        rootPanel.setOpaque(false);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.add(Box.createVerticalStrut(6));
        rootPanel.add(new AuxiliaryDummyPanel(nameLabel));  // dummy panel for central alignment
        rootPanel.add(Box.createVerticalStrut(6));
        rootPanel.add(new AuxiliaryDummyPanel(scoreLabel)); // dummy panel for central alignment
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

    /**
     * A very simple class representing an auxiliary dummy panel that contains (displays) a JLabel
     * object. The panel provides central alignment for the contained label, which is its only usage.
     *
     * @author Vasileios Papastergios
     */
    private class AuxiliaryDummyPanel extends JPanel {
        public AuxiliaryDummyPanel(JLabel labelToContain) {
            this.setOpaque(false);
            this.add(labelToContain);
        }
    }
}
