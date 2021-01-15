package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenuFrame extends SettingsFrame implements ActionListener {

    private JLabel pauseLabel;
    private JPanel buttonsPanel;
    private JButton backToMainMenuBtn;
    private JButton returnToGameBtn;
    private boolean willQuit = false;

    public PauseMenuFrame(JFrame parentFrame) {
        super(parentFrame);
        super.setBounds(180, 180, 680, 260);
        super.setLocationRelativeTo(parentFrame);
        setUpBtns();
        setUpPauseLabel();
    }

    public void setUpPauseLabel() {
        pauseLabel = new JLabel();
        super.setTitle("Pause menu");
        pauseLabel.setText("Game Paused");
        pauseLabel.setHorizontalAlignment(JLabel.CENTER);
        pauseLabel.setBackground(Color.darkGray);
        pauseLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 26f));
        pauseLabel.setVisible(true);
        super.add(pauseLabel, BorderLayout.NORTH);
        super.setVisible(false);
        this.setVisible(true);
    }

    private void setUpBtns() {

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));

        returnToGameBtn = new JButton();
        returnToGameBtn.setText("Resume game");
        returnToGameBtn.addActionListener(this);
        returnToGameBtn.setVisible(true);
        buttonsPanel.add(returnToGameBtn);

        backToMainMenuBtn = new JButton();
        backToMainMenuBtn.setText("Quit game");
        backToMainMenuBtn.addActionListener(this);
        backToMainMenuBtn.setVisible(true);
        buttonsPanel.add(backToMainMenuBtn);

        super.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public boolean isWillQuit() {
        return willQuit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenuBtn) {
            willQuit = true;
            this.dispose();
        } else if (e.getSource() == returnToGameBtn) {
            this.dispose();
        }
    }
}
