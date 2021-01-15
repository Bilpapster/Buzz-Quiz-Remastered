package GUI;

import com.Sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class that represents the main menu frame of our game. The frame displays the buttons with all the available
 * actions, game modes and settings.
 *
 * @author Dimitrios - Fotios Malakis
 * @author Vasileios Papastergios
 * @version 2021.01.15
 */
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

    /**
     * Constructs a main menu frame and displays it.
     */
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

    /**
     * Sets up the sound manager of the frame and starts playing the main menu theme.
     */
    private void setUpSoundManager() {
        SoundManager.getManager().playClip("main_menu_theme");
    }

    /**
     * Sets up the title displayed on te frame.
     */
    private void setUpTitleLabel() {
        gameTitle = new JLabel("<html><font color=#e6c260>Buzz!:</font><font color=white> Quiz World!*</font></html>");
        gameTitle.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 36f));
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Sets up the subtitle displayed on the frame.
     */
    private void setUpSubTitleLabel() {
        gameSubTitle = new JLabel("<html><font color=gray>*remastered by Electric Boogaloo Inc<sup>&copy;</sup></font></html>");
        gameSubTitle.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 18f));
        gameSubTitle.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Sets up the panel containing the title and the subtitle displayed on the frame.
     */
    private void setUpTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(gameTitle);
        titlePanel.add(gameSubTitle);
        titlePanel.add(Box.createVerticalStrut(150));
    }

    /**
     * Sets up the label containing info about the credits.
     */
    private void setUpCreditsLabel() {
        basedOnOriginalGameLabel = new JLabel("<html><font color = white>Based on the original Buzz!: Quiz World game.</font></html>");
        basedOnOriginalGameLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 16f));
    }

    /**
     * Sets up the label containing info about the authors.
     */
    private void setUpAuthorsLabel() {
        authorsLabel = new JLabel("<html><font color = white>Developed by fmalakis and bilpapster.</font></html>");
        authorsLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 16f));
    }

    /**
     * Sets up the panel containing the credits.
     */
    private void setUpCreditsPanel() {
        creditsPanel = new JPanel();
        creditsPanel.setBackground(Color.DARK_GRAY);
        creditsPanel.add(basedOnOriginalGameLabel);
        creditsPanel.add(Box.createHorizontalGlue());
        creditsPanel.add(authorsLabel);
    }

    /**
     * Sets up the buttons displayed on the frame.
     */
    private void setUpButtons() {
        player1btn.addActionListener(this);
        player2btn.addActionListener(this);
        highscoreBtn.addActionListener(this);
        settingsButton.addActionListener(this);
    }

    /**
     * Sets up the panel containing the buttons of the frame.
     */
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

    /**
     * Sets up the root (main) panel of the frame.
     */
    private void setUpRootPanel() {
        rootPanel = new BackgroundImagedPanel();
        rootPanel.setBackgroundImage(new ImageIcon("src/resources/Main menu image.png"));
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(titlePanel, BorderLayout.NORTH);
        rootPanel.add(buttonsPanel, BorderLayout.CENTER);
        rootPanel.add(creditsPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the frame itself.
     */
    private void setUpMainMenuFrame() {
        this.setTitle("Buzz! Quiz World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/resources/Buzz-Quiz-World_LOGO.jpg").getImage());
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPanel);
        this.setVisible(true);
    }

    /**
     * Guides the program's execution, depending on the user button selections.
     *
     * @param e
     */
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

    /**
     * A simple class that represents a button on the main menu of our game. The button is rounded and enters the scene
     * in an animation.
     *
     * @author Vasileios Papastergios
     * @versio 2021.01.15
     */
    private class CustomizedMainMenuButton extends RoundedJButton {

        private static final int RELOCATION_X = 3;
        private static final int RELOCATION_Y = 3;
        private static final int RELOCATION_WIDTH = 6;
        private static final int RELOCATION_HEIGHT = 0;

        /**
         * Constructs an main menu button object with the given text and the given animation data.
         *
         * @param textToDisplay  the text displayed on the button
         * @param endCoordinateX the ending coordinate x of the animation
         * @param initialDelay   the initial delay to schedule animation
         */
        public CustomizedMainMenuButton(String textToDisplay, int endCoordinateX, int initialDelay) {
            super(new Dimension(30, 30));
            this.setVisible(true);
            this.setText(textToDisplay);
            this.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
            this.setMargin(new Insets(0, 15, 20, 20));
            this.setInteractionRelatedListeners();
            TransitionWizard.getWizard().scheduleForwardAnimation(this, 2000, -300, endCoordinateX, initialDelay);
        }

        /**
         * Adds the listeners, associated with the user interaction and the special sound effects.
         */
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


        /**
         * Executes all actions needed when the button gains focus. The button gains size and goes a little up.
         */
        private void executeActionsOnFocusGain() {
            setBounds(getX() - RELOCATION_X, getY() - RELOCATION_Y, getWidth() + RELOCATION_WIDTH, getHeight() + RELOCATION_HEIGHT);
        }

        /**
         * Executes all actions needed when the button loses focus. The button returns to its initial size.
         */
        private void executeActionsOnFocusLoss() {
            setBounds(getX() + RELOCATION_X, getY() + RELOCATION_Y, getWidth() - RELOCATION_WIDTH, getHeight() - RELOCATION_HEIGHT);
        }
    }
}
