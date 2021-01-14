package GUI;

import com.Player;
import com.Sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuFrame extends JFrame implements ActionListener {
    CustomizedMainMenuButton player1btn;
    CustomizedMainMenuButton player2btn;
    CustomizedMainMenuButton highscoreBtn;
    CustomizedMainMenuButton settingsButton;

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
        SoundManager soundManager = new SoundManager();
        soundManager.playClip("during_game_theme");
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
        basedOnOriginalGameLabel.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void setUpAuthorsLabel() {
        authorsLabel = new JLabel("<html><font color = white>Developed by fmalakis and bilpapster.</font></html>");
        authorsLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 16f));
        authorsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void setUpCreditsPanel() {
        creditsPanel = new JPanel();
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.X_AXIS));
        creditsPanel.setBackground(Color.DARK_GRAY);
        creditsPanel.add(basedOnOriginalGameLabel);
        creditsPanel.add(Box.createHorizontalGlue());
        creditsPanel.add(authorsLabel);
        creditsPanel.add(Box.createVerticalStrut(10));
    }

    private void setUpButtons() {
        player1btn = new CustomizedMainMenuButton("One-player game", 108, 0);
        player2btn = new CustomizedMainMenuButton("Two-player game", 106, 350);
        highscoreBtn = new CustomizedMainMenuButton("View high-scores", 106, 700);
        settingsButton = new CustomizedMainMenuButton("Settings", 134, 1050);

        highscoreBtn.addActionListener(this::actionPerformed);
        player1btn.addActionListener(this::actionPerformed);
        player2btn.addActionListener(this::actionPerformed);
        settingsButton.addActionListener(this::actionPerformed);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
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
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == player1btn) {
            this.dispose();
            PlayerInfoPage playerInfoPage = new PlayerInfoPage(1);
        } else if (e.getSource() == player2btn) {
            this.dispose();
            PlayerInfoPage playerInfoPage = new PlayerInfoPage(2);
        } else if (e.getSource() == highscoreBtn) {
            this.dispose();
//            HighscoreMenu highscoreMenu = new HighscoreMenu();
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Fotis", 3500));
            players.add(new Player("Giannhs", 5800));
            GameEndingWindow gameEndingWindow = new GameEndingWindow(players);
        } else if (e.getSource() == settingsButton) {
            // openPauseMenu()
        }


    }

    private class CustomizedMainMenuButton extends AnimationButton {
        public CustomizedMainMenuButton(String textToDisplay, int endCoordinateX, int initialDelay) {
            super(textToDisplay, 2000, -200, endCoordinateX, initialDelay, true);
            this.setMargin(new Insets(0, 15, 20, 15));
            this.scheduleForwardAnimation();
        }
    }

}
