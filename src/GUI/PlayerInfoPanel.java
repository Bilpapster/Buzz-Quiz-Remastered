package GUI;

import javax.swing.*;
import java.awt.*;

class PlayerInfoPanel {

    private JPanel rootPanel;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private String name;
    private int score;

    public PlayerInfoPanel(String name) {
        this.name = name;
        this.score = 0;
        setUpLabels();
    }

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

    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(String.format("%,d", score));
    }

    public void updateName(String name) {
        this.name = name;
        nameLabel.setText(name);
    }
}
