package GUI;

import com.Player;
import com.Sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainMenuFrame extends JFrame implements ActionListener {

    private CustomizedMainMenuButton player1btn = new CustomizedMainMenuButton("One-player game", 108, 200);
    private CustomizedMainMenuButton player2btn = new CustomizedMainMenuButton("Two-player game", 106, 500);
    private CustomizedMainMenuButton highscoreBtn = new CustomizedMainMenuButton("View high-scores", 106, 800);
    private CustomizedMainMenuButton settingsButton = new CustomizedMainMenuButton("Settings", 136, 1100);

    private JLabel gameTitle;
    private JLabel gameSubTitle;
    private JLabel basedOnOriginalGameLabel;
    private JLabel authorsLabel;

    private JPanel titlePanel;
    private JPanel buttonsPanel;
    private JPanel creditsPanel;
    private BackgroundImagedPanel rootPanel;

    public MainMenuFrame() {
        setUpSoundManager();
        setUpTitleLabel();
        setUpSubTitleLabel();
        setUpCreditsLabel();
        setUpAuthorsLabel();
        setUpCreditsPanel();
        setUpTitlePanel();
        setUpButtons();
        setUpButtonsPanel();
        setUpRootPanel();
        setUpMainMenuFrame();
    }

    private void setUpSoundManager() {
        SoundManager.getManager().playClip("main_menu_theme");
    }

    private void setUpTitleLabel() {
        gameTitle = new JLabel("<html><font color=#e6c260>Buzz!:</font><font color=white> Quiz World!*</font></html>");
        gameTitle.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 36f));
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setUpSubTitleLabel() {
        gameSubTitle = new JLabel("<html><font color=gray>*remastered by Electric Boogaloo Inc<sup>&copy;</sup></font></html>");
        gameSubTitle.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 18f));
        gameSubTitle.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setUpTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(gameTitle);
        titlePanel.add(gameSubTitle);
        titlePanel.add(Box.createVerticalStrut(150));
    }

    private void setUpCreditsLabel() {
        basedOnOriginalGameLabel = new JLabel("<html><font color = white>Based on the original Buzz!: Quiz World game.</font></html>");
        basedOnOriginalGameLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 16f));
    }

    private void setUpAuthorsLabel() {
        authorsLabel = new JLabel("<html><font color = white>Developed by fmalakis and bilpapster.</font></html>");
        authorsLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 16f));
    }

    private void setUpCreditsPanel() {
        creditsPanel = new JPanel();
//        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.X_AXIS));
        creditsPanel.setBackground(Color.DARK_GRAY);
        creditsPanel.add(basedOnOriginalGameLabel);
        creditsPanel.add(Box.createHorizontalGlue());
        creditsPanel.add(authorsLabel);
    }

    private void setUpButtons() {
        player1btn.addActionListener(this);
        player2btn.addActionListener(this);
        highscoreBtn.addActionListener(this);
        settingsButton.addActionListener(this);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(player1btn);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(player2btn);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(highscoreBtn);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(settingsButton);
    }

    private void setUpRootPanel() {
        rootPanel = new BackgroundImagedPanel();
        rootPanel.setBackgroundImage(new ImageIcon("src/resources/Main menu image.png"));
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(titlePanel, BorderLayout.NORTH);
        rootPanel.add(buttonsPanel, BorderLayout.CENTER);
        rootPanel.add(creditsPanel, BorderLayout.SOUTH);
    }

    private void setUpMainMenuFrame() {
        this.setTitle("Buzz! Quiz World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/resources/Buzz-Quiz-World_LOGO.jpg").getImage());
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SoundManager.playClip("button_select");
        if (e.getSource() == player1btn) {
            new PlayerInfoPage(1, this);
        } else if (e.getSource() == player2btn) {
            new PlayerInfoPage(2, this);
        } else if (e.getSource() == highscoreBtn) {
            HighscoreMenu highscoreMenu = new HighscoreMenu(this);
        } else if (e.getSource() == settingsButton) {
            new SettingsFrame(this).setVisible(true);
        }
    }

    private class CustomizedMainMenuButton extends RoundedJButton {

        private static final int  RELOCATION_X = 3;
        private static final int  RELOCATION_Y = 3;
        private static final int  RELOCATION_WIDTH = 6;
        private static final int  RELOCATION_HEIGHT = 0;

        public CustomizedMainMenuButton(String textToDisplay, int endCoordinateX, int initialDelay) {
            super(new Dimension(30, 30));
            this.setVisible(true);
            this.setText(textToDisplay);
            this.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
            this.setMargin(new Insets(0, 15, 20, 20));
            this.setInteractionRelatedListeners();
            TransitionWizard.getWizard().scheduleForwardAnimation(this, 2000, -300, endCoordinateX, initialDelay);
        }

        private void setInteractionRelatedListeners() {
            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    executeActionsOnFocusLoss();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    executeActionsOnFocusGain();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    executeActionsOnFocusLoss();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JButton buttonPressed = (JButton) e.getSource();
                }
            });

            this.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    executeActionsOnFocusGain();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    executeActionsOnFocusLoss();
                }
            });
        }

        private void executeActionsOnFocusGain() {
            setBounds(getX() - RELOCATION_X, getY() - RELOCATION_Y, getWidth() + RELOCATION_WIDTH, getHeight() + RELOCATION_HEIGHT);
        }

        private void executeActionsOnFocusLoss() {
            setBounds(getX() + RELOCATION_X, getY() + RELOCATION_Y, getWidth() - RELOCATION_WIDTH, getHeight() - RELOCATION_HEIGHT);
        }
    }
}
