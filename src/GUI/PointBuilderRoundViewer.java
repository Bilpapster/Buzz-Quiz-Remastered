package GUI;

import com.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * <p>A class that represents the front end of the round type "Point Builder" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. There are 5 questions in this
 * round</p>
 * <p>The question type, text and possible answers are displayed simultaneously to the player(s). The player(s) define
 * their answers using either the mouse or the keyboard. When all players have placed their answers, the correct answer
 * is revealed and the credits are given to the players that answered correctly. There is a 3-seconds interval between
 * two successive questions.</p>
 *
 * @author Vasileios Papastergios
 */
public class PointBuilderRoundViewer implements RoundViewerI {

    /* the logic core of the round */
    protected RoundLogicI roundLogic;

    /* the referee of the round*/
    protected Referee referee;

    /* stores whether user interaction is enabled or not */
    boolean isUserInteractionEnabled = false;

    /* the frame that the panel is displayed in */
    protected GameFrame parentFrame;

    /* the timer component of the round  */
    protected TimerComponent timer = new TimerComponent();

    /* the question that is currently asked to player(s) */
    protected Question currentQuestion;

    /* labels for displaying the type and text of the current question */
    protected JLabel questionTypeLabel = new JLabel();
    protected JLabel questionTextLabel = new JLabel();

    /* the panel components that the root panel of the viewer consists of */
    protected RoundedJPanel questionTypePanel = new RoundedJPanel();
    protected JPanel questionTextPanel = new JPanel();
    protected JPanel questionPanel = new JPanel();
    protected BackgroundImagedPanel answeringAreaPanel = new BackgroundImagedPanel();
    protected JPanel answerButtonsPanel = new JPanel();
    protected JPanel paddingLeft = new JPanel();
    protected JPanel paddingRight = new JPanel();
    protected JPanel footerPanel = new JPanel();

    /* the root panel of the viewer */
    protected JPanel rootPanel = new JPanel();

    /* HashMap structure that store mapping between Players-Info panels, which display the player's name and score */
    protected HashMap<Player, PlayerInfoPanel> playerInfoPanels = new HashMap<>();

    /* HashMap structure that store mapping between answer buttons - labels that display the name of player(s) who chose
    the specific answer */
    protected HashMap<JButton, JLabel> playersSelectionLabels = new HashMap<>();

    /* the buttons displaying the available anwers */
    protected ArrayList<JButton> answerButtons = new ArrayList<>();

    /* the labels indicating which player chose which answer of the available */
    protected ArrayList<JLabel> selectionLabels = new ArrayList<>();

    /**
     * Constructs a <code>PointBuilderRoundViewer</code> object with the given <code>Referee</code> object.
     *
     * @param referee the referee of the round.
     */
    public PointBuilderRoundViewer(Referee referee) {
        this.referee = referee;
        currentQuestion = referee.getQuestion();
        initializeRoundLogic();
        setUpComponents();
        setUpQuestionTypePanel();
        setUpQuestionTextPanel();
        setUpQuestionPanel();
        setUpAnswerButtonsPanel();
        setUpPaddings();
        setUpAnsweringAreaPanel();
        setUpFooter();
        setUpRootPanel();
    }

    /**
     * Setter for the parent frame of the viewer.
     *
     * @param parentFrame the parents frame that displays the root panel of the viewer.
     */
    public void setParentFrame(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Getter for the root panel of the viewer.
     *
     * @return the root panel of the viewer.
     */
    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    /**
     * Initializes the round logic core object. Needs to be overridden for every round type.
     */
    protected void initializeRoundLogic() {
        this.roundLogic = new PointBuilderRoundLogic(5, referee);
    }

    /**
     * Sets up the individual components (JLabels) that display the type and text of the current question.
     */
    protected void setUpComponents() {
        setUpQuestionTypeLabel();
        setUpQuestionTextLabel();
    }

    /**
     * Sets up the basic specs for the label that displays the current question's type.
     */
    protected void setUpQuestionTypeLabel() {
        questionTypeLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 16f).deriveFont(Font.ITALIC));
        questionTypeLabel.setAlignmentX(JLabel.CENTER);
        questionTypeLabel.setForeground(Color.WHITE);
    }

    /**
     * Sets up the basic specs for the label that displays the current question's text.
     */
    protected void setUpQuestionTextLabel() {
        questionTextLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.MEDIUM, 24f));
        questionTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTextLabel.setForeground(Color.WHITE);

    }

    /**
     * Sets up the panel that displays the type of current question, utilizing the previously set up label.
     */
    protected void setUpQuestionTypePanel() {
        questionTypePanel.setLayout(new BoxLayout(questionTypePanel, BoxLayout.Y_AXIS));
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 5)), Component.CENTER_ALIGNMENT);
        questionTypePanel.add(questionTypeLabel, JPanel.CENTER_ALIGNMENT);
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 8)), Component.CENTER_ALIGNMENT);
        questionTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTypePanel.setForeground(Color.WHITE);
    }

    /**
     * Sets up the panel that displays the text of current question, utilizing the previously set up label.
     */
    protected void setUpQuestionTextPanel() {
        questionTextPanel.setOpaque(false);
        questionTextPanel.add(questionTextLabel);
    }

    /**
     * Sets up the panel that contains components relative to the current question text and type. The panel is located
     * on the North side of the root panel.
     */
    protected void setUpQuestionPanel() {
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(questionTypePanel);
        questionPanel.add(Box.createVerticalStrut(8));
        questionPanel.add(questionTextPanel);
        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.setBackground(Color.DARK_GRAY);
    }

    /**
     * Sets up the panel that contains the answer buttons. The panel is located on the center of the root panel.
     */
    protected void setUpAnswerButtonsPanel() {
        answerButtonsPanel.setOpaque(false);
        answerButtonsPanel.setLayout(new GridLayout(2, 2, 15, 15));

        for (int answerButton = 0; answerButton < 4; answerButton++) {
            RoundedJButton button = new RoundedJButton("");
            button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
            button.addMouseListener(new CustomizedButtonListener());
            answerButtons.add(button);
            answerButtonsPanel.add(answerButtons.get(answerButton));
            selectionLabels.add(new PlayerSelectionLabel());
        }
    }

    /**
     * Sets up the total panel that constitutes the answering area. The panel is located on the center of the root panel.
     */
    protected void setUpAnsweringAreaPanel() {
        answeringAreaPanel.setLayout(new BorderLayout());
        answeringAreaPanel.add(Box.createVerticalStrut(60), BorderLayout.NORTH);
        answeringAreaPanel.add(answerButtonsPanel, BorderLayout.CENTER);
        answeringAreaPanel.add(Box.createVerticalStrut(60), BorderLayout.SOUTH);
        answeringAreaPanel.add(paddingLeft, BorderLayout.WEST);
        answeringAreaPanel.add(paddingRight, BorderLayout.EAST);
    }

    /**
     * Sets up the paddings located on the left and right side of the total answering area.
     */
    protected void setUpPaddings() {
        setUpPaddingLeft();
        setUpPaddingRight();
    }

    /**
     * Sets up the left padding, located on the left side of the answering area.
     */
    protected void setUpPaddingLeft() {
        paddingLeft.setOpaque(false);
        paddingLeft.setPreferredSize(new Dimension(240, 100));
        paddingLeft.setLayout(new GridLayout(2, 1));

        for (int i = 0; i < 4; i += 2) {
            paddingLeft.add(selectionLabels.get(i));
            this.playersSelectionLabels.put(answerButtons.get(i), selectionLabels.get(i));
        }
    }

    /**
     * Sets up the right padding, located on the right side of the answering area.
     */
    protected void setUpPaddingRight() {
        paddingRight.setOpaque(false);
        paddingRight.setPreferredSize(new Dimension(240, 100));
        paddingRight.setLayout(new GridLayout(2, 1));

        for (int i = 1; i < 4; i += 2) {
            paddingRight.add(selectionLabels.get(i));
            this.playersSelectionLabels.put(answerButtons.get(i), selectionLabels.get(i));
        }
    }

    /**
     * Sets up the footer of the root panel. The footer contains one or two player info labels (depending on the number
     * of players taking part in the game). The player info label are placed in the left and right side of the footer
     * respectively. The footers middle component is a panel that displays the round name and the timer, if the round is
     * timed.
     */
    protected void setUpFooter() {
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
        timer.hideTimer();
    }

    /**
     * Sets up the root panel of the viewer.
     */
    protected void setUpRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(questionPanel, BorderLayout.NORTH);
        rootPanel.add(answeringAreaPanel, BorderLayout.CENTER);
        rootPanel.add(footerPanel, BorderLayout.SOUTH);
        addRootPanelListeners();
    }

    /**
     * Adds the listeners on the root panel of the viewer.
     */
    protected void addRootPanelListeners() {
        addRootPanelMouseListener();
        addRootPanelKeyListener();
    }

    /**
     * Adds the mouse listener of the root panel. Connects by default the mouse with the 1st player (irrespectively of
     * the number of player taking part in game).
     */
    protected void addRootPanelMouseListener() {
        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rootPanel.setFocusable(true);
            }
        });
    }

    /**
     * Adds the keyboard listener of the root panel. Connects keyboard sets with every player involved in the round.
     */
    protected void addRootPanelKeyListener() {
        rootPanel.setFocusable(true);
        if (referee.getAlivePlayersInRound().size() > 1) {
            rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._O_P_K_L, referee.getAlivePlayersInRound().get(1)));
        }
        rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._Q_W_A_S, referee.getAlivePlayersInRound().get(0)));
    }

    /**
     * Takes care of making all needed steps, in order to display the next question of the round. Updates the current
     * question, the texts on the labels and buttons, starts the timer and enables user interaction.
     */
    protected void displayNextQuestion() {
        executeStandardActionsBeforeNextQuestion();
        executeCustomizablePreparationsBeforeNextQuestion();
        enableInteractionAndStartTimer();
    }

    /**
     * Executes some standard steps before moving on to the next question. Takes care of removing the answer question,
     * updating it and clearing all the displayed data related to the previous question.
     */
    protected void executeStandardActionsBeforeNextQuestion() {
        referee.executeActionsBeforeNextQuestion();
        currentQuestion = referee.getQuestion();
        clearTextOnAllSelectionLabels();
    }

    /**
     * Executes some preparatory actions before moving on to the next question. Updates the background colors and images
     * as well as the texts displayed on the answer buttons and the labels of the root panel.
     */
    protected void executeCustomizablePreparationsBeforeNextQuestion() {
        updateBackgroundColors();
        updateTexts();
    }

    /**
     * Enables user interaction and starts the round timer.
     */
    protected void enableInteractionAndStartTimer() {
        isUserInteractionEnabled = true;
        timer.startTimer();
    }

    /**
     * Starts playing the round.
     */
    public void play() {
        referee.executeActionsBeforeNextRound();
        rootPanel.setVisible(true);
        playRound();
    }

    /**
     * Auxiliary method for inside-class use. If the round is not over keeps playing by invoking the diplay of the next
     * question for the players to answer.
     */
    protected void playRound() {
        if (!roundLogic.isOver()) {
            displayNextQuestion();
        } else {
            parentFrame.playNextRound();
        }
    }

    /**
     * Makes on appropriate text updates on buttons and labels of the root panel.
     */
    protected void updateTexts() {
        updateTextOnQuestionTextLabel();
        updateTextOnQuestionTypeLabel();
        updateTextOnAllAnswerButtons();
        updateTextOnAllPlayersScoreLabels();
    }

    /**
     * Updates the text on the label displaying the text of current question.
     */
    protected void updateTextOnQuestionTextLabel() {
        questionTextLabel.setText(currentQuestion.getQuestionText());
    }

    /**
     * Updates the text on the label displaying the type of current question.
     */
    protected void updateTextOnQuestionTypeLabel() {
        questionTypeLabel.setText("   " + currentQuestion.getQuestionType().toString() + "     ");
    }

    /**
     * Updates the text on the answer button displaying the available options for the current question.
     */
    protected void updateTextOnAllAnswerButtons() {
        int index = 0;
        for (String answer : currentQuestion.getAnswers().values()) {
            answerButtons.get(index++).setText(answer);
        }
    }

    /**
     * Updates the text on all labels diplaying the players' score.
     */
    protected void updateTextOnAllPlayersScoreLabels() {
        for (Player player : referee.getAlivePlayersInRound()) {
            playerInfoPanels.get(player).updateScore(player.getScore());
        }
    }

    /**
     * Clears the text displayed on all answer buttons.
     */
    protected void clearTextOnAllAnswerButtons() {
        for (JButton button : answerButtons) {
            clearTextOnAnswerButton(button);
        }
    }

    /**
     * Clears the text displayed on the given button.
     *
     * @param button the button to clear text from.
     */
    protected void clearTextOnAnswerButton(JButton button) {
        button.setText("");
    }

    /**
     * Clears the text displayed on all labels indicating the players' choice for the current question.
     */
    protected void clearTextOnAllSelectionLabels() {
        for (JLabel selectionLabel : selectionLabels) {
            clearTextOnSelectionLabel(selectionLabel);
        }
    }

    /**
     * Clears the text on the given label.
     *
     * @param selectionLabel the label to clear text from.
     */
    protected void clearTextOnSelectionLabel(JLabel selectionLabel) {
        selectionLabel.setText("");
    }

    /**
     * Makes the text on all selection labels visible.
     */
    protected void showTextOnAllSelectionLabels() {
        for (JLabel selectionLabel : selectionLabels) {
            showTextOnSelectionLabel(selectionLabel);
        }
    }

    /**
     * Makes the text on the given label visible.
     *
     * @param selectionLabel the label to set visible.
     */
    protected void showTextOnSelectionLabel(JLabel selectionLabel) {
        selectionLabel.setVisible(true);
    }

    /**
     * Updates the background color of the question type label and the background image of the answering area.
     */
    protected void updateBackgroundColors() {
        questionTypePanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        answeringAreaPanel.setBackgroundImage(QuestionType.getBackgroundImageOf(currentQuestion.getQuestionType()));
        answeringAreaPanel.repaint();
    }

    /**
     * Restores foreground color of all answer buttons.
     */
    protected void restoreForegroundDataForAllAnswerButtons() {
        for (JButton button : answerButtons) {
            restoreForegroundDataForAnswerButton(button);
        }
    }

    /**
     * Restores the foreground color of the given button.
     *
     * @param button thw button to restore foreground color on.
     */
    protected void restoreForegroundDataForAnswerButton(JButton button) {
        button.setForeground(Color.BLACK);
        button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
    }

    /**
     * Checks whether all alive players in round have placed their answer. In this case, the timer stops, the correct
     * answer is revealed and the method <code>playRound</code> is invoked.
     */
    protected void actionPerformed() {
        if (referee.haveAllPlayersAnswered()) {
            timer.stopTimer();
            isUserInteractionEnabled = false;
            showTextOnAllSelectionLabels();
            revealCorrectAnswer();
        }
        rootPanel.requestFocus();
    }

    /**
     * Reveals the correct answer for the current question and waits for 3 seconds. Afterwards, invokes the
     * <code>playRound</code> method/
     */
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
                    restoreForegroundDataForAnswerButton(button);
                }
                playRound();
            }
        };
    }

    /**
     * A simple customized class that extends the <code>MouseAdapter</code> class.
     *
     * @author Vasileios Papastergios
     */
    protected class CustomizedButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isUserInteractionEnabled) {
                if (!referee.hasPlayerAnswered(referee.getAlivePlayersInRound().get(0))) {
                    JButton buttonSource = (JButton) e.getSource();
                    referee.setAnswerData(referee.getAlivePlayersInRound().get(0), buttonSource.getText(), timer.getMillisAfterLaunch());
                    playersSelectionLabels.get(e.getSource()).setText(referee.getAlivePlayersInRound().get(0).getName());
                    playersSelectionLabels.get(e.getSource()).setVisible(false);
                }
                actionPerformed();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (isUserInteractionEnabled) {
                JButton buttonSource = (JButton) e.getSource();
                buttonSource.setForeground(questionTypePanel.getBackground());
                buttonSource.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 24f));
            }
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

    /**
     * A simple customized class that extends the <code>KeyAdapter</code> class.
     *
     * @author Vasileios Papastergios
     */
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
            if (isUserInteractionEnabled) {
                Character keyPressed = Character.toString(e.getKeyChar()).toLowerCase().charAt(0);  // count user interaction even if the CAPS LOCK is (by mistake) enabled
                if (keyButtonAssociation.containsKey(keyPressed)) {
                    if (!referee.hasPlayerAnswered(playerAssociated)) {
                        referee.setAnswerData(playerAssociated, answerButtons.get(keyButtonAssociation.get(keyPressed)).getText(), timer.getMillisAfterLaunch());
                        playersSelectionLabels.get(answerButtons.get(keyButtonAssociation.get(keyPressed))).setText(playerAssociated.getName());
                        playersSelectionLabels.get(answerButtons.get(keyButtonAssociation.get(keyPressed))).setVisible(false);
                    }
                    actionPerformed();
                }
            }
        }
    }
}
