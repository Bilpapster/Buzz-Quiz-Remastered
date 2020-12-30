package GUI;

import com.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PointBuilderRoundViewer implements RoundViewerI {
    protected RoundLogicI roundLogic;
    protected Referee referee;

    protected GameFrame parentFrame;

    protected TimerComponent timer;
    protected Question currentQuestion;

    protected JLabel questionTypeLabel;
    protected JLabel questionTextLabel;
    protected JLabel playerNameLabel;

    protected RoundedJPanel questionTypePanel;
    protected JPanel questionTextPanel;
    protected JPanel questionPanel;
    protected BackgroundImagedPanel answerButtonsPanel;
    protected JPanel answersPanel;
    protected JPanel paddingLeft;
    protected JPanel paddingRight;
    protected JPanel footerPanel = new JPanel();
    protected JPanel rootPanel;

    protected HashMap<Player, PlayerInfoPanel> playerInfoPanels = new HashMap<>();
    protected HashMap<JButton, JLabel> playersSelectionLabels = new HashMap<>();

    protected ArrayList<JButton> answerButtons = new ArrayList<>();
    protected ArrayList<JLabel> selectionLabels = new ArrayList<>();

    //TODO: Really need to clean up the code to more methods and classes
    //TODO: Add JavaDoc

    public PointBuilderRoundViewer(Referee referee) {
        this.referee = referee;
        currentQuestion = referee.getQuestion();
        initializeRoundLogic();
        setUpComponents();
        setUpQuestionTypePanel();
        setUpQuestionTextPanel();
        setUpQuestionPanel();
        setUpAnswersPanel();
        setUpPaddings();
        setUpAnswerButtonsPanel();
        setUpFooter();
        setUpRootPanel();
    }

    public void setParentFrame(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    protected void initializeRoundLogic() {
        this.roundLogic = new PointBuilderRoundLogic(5, referee);
    }

    protected void setUpComponents() {
        setUpQuestionTypeLabel();
        setUpQuestionTextLabel();
    }

    protected void setUpQuestionTypeLabel() {
        questionTypeLabel = new JLabel();
        questionTypeLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 16f).deriveFont(Font.ITALIC));
        questionTypeLabel.setAlignmentX(JLabel.CENTER);
        questionTypeLabel.setForeground(Color.WHITE);
    }

    protected void setUpQuestionTextLabel() {
        questionTextLabel = new JLabel();
        questionTextLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.MEDIUM, 24f));
        questionTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTextLabel.setForeground(Color.WHITE);

    }

    protected void setUpQuestionTypePanel() {
        questionTypePanel = new RoundedJPanel();
        questionTypePanel.setLayout(new BoxLayout(questionTypePanel, BoxLayout.Y_AXIS));
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 5)), Component.CENTER_ALIGNMENT);
        questionTypePanel.add(questionTypeLabel, JPanel.CENTER_ALIGNMENT);
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 8)), Component.CENTER_ALIGNMENT);
        questionTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTypePanel.setForeground(Color.WHITE);
    }

    protected void setUpQuestionTextPanel() {
        questionTextPanel = new JPanel();
        questionTextPanel.setOpaque(false);
        questionTextPanel.add(questionTextLabel);
    }

    protected void setUpQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(questionTypePanel);
        questionPanel.add(Box.createVerticalStrut(8));
        questionPanel.add(questionTextPanel);
        questionPanel.add(Box.createVerticalStrut(20));

        questionPanel.setBackground(Color.DARK_GRAY);
    }

    protected void setUpAnswersPanel() {
        answersPanel = new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new GridLayout(2, 2, 15, 15));

        for (int answerButton = 0; answerButton < 4; answerButton++) {
            RoundedJButton button = new RoundedJButton("");
            button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
            button.addMouseListener(new CustomizedButtonListener());
            answerButtons.add(button);
            answersPanel.add(answerButtons.get(answerButton));
            selectionLabels.add(new PlayerSelectionLabel());
        }
    }

    protected void setUpAnswerButtonsPanel() {
        answerButtonsPanel = new BackgroundImagedPanel();
        answerButtonsPanel.setLayout(new BorderLayout());
        answerButtonsPanel.add(Box.createVerticalStrut(60), BorderLayout.NORTH);
        answerButtonsPanel.add(answersPanel, BorderLayout.CENTER);
        answerButtonsPanel.add(Box.createVerticalStrut(60), BorderLayout.SOUTH);
        answerButtonsPanel.add(paddingLeft, BorderLayout.WEST);
        answerButtonsPanel.add(paddingRight, BorderLayout.EAST);
    }

    protected void setUpPaddings() {
        setUpPaddingLeft();
        setUpPaddingRight();
    }

    protected void setUpPaddingLeft() {
        paddingLeft = new JPanel();
        paddingLeft.setOpaque(false);
        paddingLeft.setPreferredSize(new Dimension(240, 100));
        paddingLeft.setLayout(new GridLayout(2, 1));

        for (int i = 0; i < 4; i+=2) {
            paddingLeft.add(selectionLabels.get(i));
            this.playersSelectionLabels.put(answerButtons.get(i), selectionLabels.get(i));
        }
    }

    protected void setUpPaddingRight() {
        paddingRight = new JPanel();
        paddingRight.setOpaque(false);
        paddingRight.setPreferredSize(new Dimension(240, 100));
        paddingRight.setLayout(new GridLayout(2, 1));

        for (int i = 1; i < 4; i+=2) {
            paddingRight.add(selectionLabels.get(i));
            this.playersSelectionLabels.put(answerButtons.get(i), selectionLabels.get(i));
        }
    }

    protected void setUpRootPanel() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(questionPanel, BorderLayout.NORTH);
        rootPanel.add(answerButtonsPanel, BorderLayout.CENTER);
        rootPanel.add(footerPanel, BorderLayout.SOUTH);
        addRootPanelListeners();
    }

    protected void addRootPanelListeners() {
        addRootPanelMouseListener();
        addRootPanelKeyListener();
    }

    protected void addRootPanelMouseListener() {
        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(!timer.isOver() ? "The timer is still going!" : "The timer has finished!");
            }
        });
    }

    protected void addRootPanelKeyListener() {
        rootPanel.setFocusable(true);
        if (referee.getAlivePlayersInRound().size() > 1) {
            rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._O_P_K_L, referee.getAlivePlayersInRound().get(1)));
        }
        rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._Q_W_A_S, referee.getAlivePlayersInRound().get(0)));
    }

    protected void displayNextQuestion() {
        referee.executeActionsBeforeNextQuestion();
        currentQuestion = referee.getQuestion();
        clearTextOnAllSelectionLabels();
        updateBackgroundColors();
        updateTexts();
        timer.startTimer();
    }

    public void play() {
        referee.executeActionsBeforeNextRound();
        rootPanel.setVisible(true);
        playRound();
    }

    protected void playRound() {
        if (!roundLogic.isOver()) {
            displayNextQuestion();
        } else {
            parentFrame.playNextRound();
        }
    }

    protected void updateTexts() {

        updateTextOnQuestionTextLabel();
        updateTextOnQuestionTypeLabel();
        updateTextOnAllAnswerButtons();
        updateTextOnAllPlayersScoreLabels();
    }

    protected void updateTextOnQuestionTextLabel() {
        questionTextLabel.setText(currentQuestion.getQuestionText());
    }

    protected void updateTextOnQuestionTypeLabel() {
        questionTypeLabel.setText("   " + currentQuestion.getQuestionType().toString() + "     ");
    }

    protected void updateTextOnAllAnswerButtons() {
        int index = 0;
        for (String answer : currentQuestion.getAnswers().values()) {
            answerButtons.get(index++).setText(answer);
        }
    }

    protected void updateTextOnAllPlayersScoreLabels() {

        for (Player player : referee.getAlivePlayersInRound()) {
            playerInfoPanels.get(player).updateScore(player.getScore());
        }
    }

    protected void clearTextOnAllAnswerButtons() {
        for (JButton button : answerButtons) {
            clearTextOnAnswerButton(button);
        }
    }

    protected void clearTextOnAllSelectionLabels() {
        for (JLabel selectionLabel : selectionLabels) {
            clearTextOnSelectionLabel(selectionLabel);
        }
    }

    protected void clearTextOnSelectionLabel(JLabel selectionLabel) {
        selectionLabel.setText("");
    }

    protected void clearTextOnAnswerButton(JButton button) {
        button.setText("");
    }

    protected void showTextOnAllSelectionLabels() {
        for (JLabel selectionLabel : selectionLabels) {
            showTextOnSelectionLabel(selectionLabel);
        }
    }

    protected void showTextOnSelectionLabel(JLabel selectionLabel) {
        selectionLabel.setVisible(true);
    }

    protected void updateBackgroundColors() {
        questionTypePanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        answerButtonsPanel.setBackgroundImage(QuestionType.getBackgroundImageOf(currentQuestion.getQuestionType()));
        answerButtonsPanel.repaint();
    }

    protected void restoreForegroundDataForAllAnswerButtons() {
        for (JButton button : answerButtons) {
            restoreForegroundDataForAnswerButton(button);
        }
    }

    protected void restoreForegroundDataForAnswerButton(JButton button) {
        button.setForeground(Color.BLACK);
        button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
    }

    /**
     * Sets up the footer of the page which is going to house
     * the timer component, then hides it (can also house more components
     * in the future as needed)
     */
    protected void setUpFooter() {
        setUpPlayerNameLabel();
        setUpTimerComponent();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setLayout(new GridLayout(1, 3));

        for (Player player : referee.getAlivePlayersInRound()) {
            PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(player.getName());
            playerInfoPanels.put(player, playerInfoPanel);
        }

        footerPanel.add(playerInfoPanels.get(referee.getAlivePlayersInRound().get(0)).getRootPanel());

        JLabel roundLabel = new JLabel(roundLogic.getOfficialName());
        roundLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 22f));
        roundLabel.setForeground(Color.WHITE);
        JPanel roundPanel = new JPanel();
        roundPanel.setOpaque(false);
        roundPanel.add(roundLabel);

        JPanel auxiliaryMiddlePanel = new JPanel();
        auxiliaryMiddlePanel.setOpaque(false);
        auxiliaryMiddlePanel.setLayout(new BoxLayout(auxiliaryMiddlePanel, BoxLayout.Y_AXIS));
        auxiliaryMiddlePanel.add(Box.createVerticalStrut(6));
        auxiliaryMiddlePanel.add(roundPanel);
        auxiliaryMiddlePanel.add(timer.getMainPanel());

        footerPanel.add(auxiliaryMiddlePanel);

        if (referee.getAlivePlayersInRound().size() > 1) {
            footerPanel.add(playerInfoPanels.get(referee.getAlivePlayersInRound().get(1)).getRootPanel());
        } else {
            JPanel dummyPanel = new JPanel();
            dummyPanel.setOpaque(false);
            footerPanel.add(dummyPanel); // dummy panel for alignment
        }
        hideTimerComponent();
    }

    protected void setUpPlayerNameLabel() {
        playerNameLabel = new JLabel();
        playerNameLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 20f));
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerNameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    protected void setUpTimerComponent() {
        timer = new TimerComponent();
    }

    /**
     * Shows the footer of the page containing the timer
     */
    protected void showTimerComponent() {
        timer.getMainPanel().setVisible(true);
    }

    /**
     * Hides the footer of the page containing the timer, and
     * clears it from the current timer object
     */
    protected void hideTimerComponent() {
        timer.hideTimer();
    }

    /**
     * This method displays an information message before a timed round begins, after which,
     * upon being dismissed, starts the timer for the question.
     */
    protected void showGetReadyMessage() {
        JOptionPane.showMessageDialog(null, "Timed Round! Get ready to race for points!");
        timer.startTimer();
    }

    public void actionPerformed() {
        if (referee.haveAllPlayersAnswered()) {
            timer.stopTimer();
            showTextOnAllSelectionLabels();
            revealCorrectAnswer();
        }
        rootPanel.requestFocus();
    }

    protected void revealCorrectAnswer() {
        new DelayTimer(3000) {
            private JButton correctAnswerButton = new JButton();

            @Override
            protected void executeActionsBeforeDelay() {
                for (JButton button : answerButtons) {
                    if (button.getText().equals(referee.getCorrectAnswerOfCurrentQuestion())) {
                        correctAnswerButton = button;
                        button.setBackground(Color.GREEN.brighter().brighter());
                        break;
                    }
                }
                roundLogic.giveCredits();
                updateTextOnAllPlayersScoreLabels();
            }

            @Override
            protected void executeActionsAfterDelay() {
                for (JButton button : answerButtons) {
                    button.setBackground(new JButton().getBackground());
                    restoreForegroundDataForAllAnswerButtons();
                }
                playRound();
            }
        };
    }


    protected class CustomizedButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            referee.setAnswerData(referee.getAlivePlayersInRound().get(0), buttonSource.getText(), timer.getMillisAfterLaunch());
            playersSelectionLabels.get(e.getSource()).setText(referee.getAlivePlayersInRound().get(0).getName());
            playersSelectionLabels.get(e.getSource()).setVisible(false);
            actionPerformed();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            buttonSource.setForeground(questionTypePanel.getBackground());
            buttonSource.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 24f));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            restoreForegroundDataForAnswerButton(buttonSource);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.mouseReleased(e);
        }
    }

    protected class CustomizedKeyboardListener extends KeyAdapter {
        private final HashMap<Character, Integer> keyButtonAssociation;
        private final Player playerAssociated;

        public CustomizedKeyboardListener(KeyboardSet keyboardSet, Player playerAssociated) {
            this.keyButtonAssociation = new HashMap<>();
            this.playerAssociated = playerAssociated;
            String pureSeriesOfCharacters = keyboardSet.getPureSeriesOfCharacters();

            for (int i = 0; i < pureSeriesOfCharacters.length(); i++) {
                keyButtonAssociation.put(pureSeriesOfCharacters.charAt(i), i);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (keyButtonAssociation.containsKey(e.getKeyChar())) {
                referee.setAnswerData(playerAssociated, answerButtons.get(keyButtonAssociation.get(e.getKeyChar())).getText(), timer.getMillisAfterLaunch());

                playersSelectionLabels.get(answerButtons.get(keyButtonAssociation.get(e.getKeyChar()))).setText(playerAssociated.getName());
                playersSelectionLabels.get(answerButtons.get(keyButtonAssociation.get(e.getKeyChar()))).setVisible(false);

                actionPerformed();
            }
        }
    }

    protected class PlayerInfoPanel {

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
}
