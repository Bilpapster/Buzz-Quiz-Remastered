package GUI;

import com.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class StandardRoundFrame extends JFrame {
    protected StandardRound roundLogic;
    protected Referee referee;

    protected TimerComponent timer;
    protected Question currentQuestion;

    protected JLabel questionTypeLabel;
    protected JLabel questionTextLabel;

    protected RoundedJPanel questionTypePanel;
    protected JPanel questionTextPanel;
    protected JPanel questionPanel;
    protected BackgroundImagedPanel answerButtonsPanel;
    protected JPanel answersPanel;
    protected JPanel paddingLeft;
    protected JPanel paddingRight;
    protected JPanel footerPanel = new JPanel();
    protected JPanel rootPanel;

    int indexLOL = 0;
    protected ArrayList<JButton> answerButtons = new ArrayList<>();

    public StandardRoundFrame(Referee referee){

        this.referee = referee;
        currentQuestion = referee.getQuestion();
        this.roundLogic = new StandardRound(5, referee);

        setUpComponents();
        setUpQuestionTypePanel();
        setUpQuestionTextPanel();
        setUpQuestionPanel();
        setUpAnswersPanel();
        setUpPaddings();
        setUpAnswerButtonsPanel();
        setUpRootPanel();
        setUpFrame();
        setUpFooter();
        prepareNextQuestion();
    }

    private void setUpComponents() {
        setUpQuestionTypeLabel();
        setUpQuestionTextLabel();
    }

    private void setUpQuestionTypeLabel() {
        questionTypeLabel = new JLabel();
        questionTypeLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 16f).deriveFont(Font.ITALIC));
        questionTypeLabel.setAlignmentX(JLabel.CENTER);
        questionTypeLabel.setForeground(Color.WHITE);
    }

    private void setUpQuestionTextLabel() {
        questionTextLabel = new JLabel();
        questionTextLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.MEDIUM, 22f));
        questionTextLabel.setAlignmentX(JLabel.CENTER);
        questionTextLabel.setForeground(Color.WHITE);

    }

    private void setUpQuestionTypePanel() {
        questionTypePanel = new RoundedJPanel();
        questionTypePanel.setLayout(new BoxLayout(questionTypePanel, BoxLayout.Y_AXIS));
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 5)), CENTER_ALIGNMENT);
        questionTypePanel.add(questionTypeLabel, JPanel.CENTER_ALIGNMENT);
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 8)), CENTER_ALIGNMENT);
        questionTypePanel.setAlignmentX(CENTER_ALIGNMENT);
        questionTypePanel.setForeground(Color.WHITE);
    }

    private void setUpQuestionTextPanel() {
        questionTextPanel = new JPanel();
        questionTextPanel.setOpaque(false);
        questionTextPanel.add(questionTextLabel);
    }

    private void setUpQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(Box.createVerticalStrut(10));
        questionPanel.add(questionTypePanel);
        questionPanel.add(Box.createVerticalStrut(6));
        questionPanel.add(questionTextPanel);
        questionPanel.add(Box.createVerticalStrut(10));

        questionPanel.setBackground(Color.DARK_GRAY);
    }

    private void setUpAnswersPanel() {
        answersPanel = new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new GridLayout(2, 2, 50, 25));

        for (int answerButton = 0; answerButton < 4; answerButton++) {
            RoundedJButton button = new RoundedJButton("");
            button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
            button.addMouseListener(new CustomizedButtonListener());
            answerButtons.add(button);
            answersPanel.add(answerButtons.get(answerButton));
        }
    }

    private void setUpAnswerButtonsPanel() {
        answerButtonsPanel = new BackgroundImagedPanel("resources/testScience.png");

        answerButtonsPanel.setLayout(new BorderLayout());
        answerButtonsPanel.add(Box.createVerticalStrut(25), BorderLayout.NORTH);
        answerButtonsPanel.add(answersPanel, BorderLayout.CENTER);
        answerButtonsPanel.add(Box.createVerticalStrut(25), BorderLayout.SOUTH);
        answerButtonsPanel.add(paddingLeft, BorderLayout.WEST);
        answerButtonsPanel.add(paddingRight, BorderLayout.EAST);
    }

    private void setUpPaddings() {
        setUpPaddingLeft();
        setUpPaddingRight();
    }

    private void setUpPaddingLeft() {
        paddingLeft = new JPanel();
        paddingLeft.setOpaque(false);
        paddingLeft.setPreferredSize(new Dimension(180, 100));
    }

    private void setUpPaddingRight() {
        paddingRight = new JPanel();
        paddingRight.setOpaque(false);
        paddingRight.setPreferredSize(new Dimension(180, 100));
    }

    private void setUpRootPanel() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(questionPanel, BorderLayout.NORTH);
        rootPanel.add(answerButtonsPanel, BorderLayout.CENTER);
        addRootPanelListeners();
    }

    private void addRootPanelListeners() {
        addRootPanelMouseListener();
        addRootPanelKeyListener();
    }

    private void addRootPanelMouseListener() {
        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(!timer.isOver() ? "The timer is still going!" : "The timer has finished!");
            }
        });
    }

    private void addRootPanelKeyListener() {
        rootPanel.setFocusable(true);
        if (referee.getAlivePlayersInRound().size() > 1) {
            rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._O_P_K_L, referee.getAlivePlayersInRound().get(1)));
        }
        rootPanel.addKeyListener(new CustomizedKeyboardListener(KeyboardSet._Q_W_A_S, referee.getAlivePlayersInRound().get(0)));
    }

    private void setUpFrame() {
        this.setTitle("Buzz! Quiz World!");
        ImageIcon iconImage = new ImageIcon("resources/Buzz-Quiz-World_LOGO.jpg");
        this.setIconImage(iconImage.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPanel);
        this.setVisible(true);
    }

    private void prepareNextQuestion() {
        referee.executeActionsBeforeNextQuestion();
        currentQuestion = referee.getQuestion();
        updateTexts();
        updateBackgroundColors();

        // this is for testing purposes, remove for actual production version
        if (indexLOL % 2 == 0) {
            addTimerComponent();
            showTimerComponent();
            showGetReadyMessage();
            timer.startTimer();
        }
        indexLOL++;
    }

    private void updateTexts() {
        questionTextLabel.setText(currentQuestion.getQuestionText());
        questionTypeLabel.setText( "   " + currentQuestion.getQuestionType().toString() + "     ");

        int index = 0;
        for (String answer : currentQuestion.getAnswers().values()) {
            answerButtons.get(index++).setText(answer);
        }
    }

    private void updateBackgroundColors() {
        Color backgroundColor = QuestionType.getColorOf(currentQuestion.getQuestionType());
        questionTypePanel.setBackground(backgroundColor);
        answerButtonsPanel.setBackgroundImage(QuestionType.getBackgroundImageOf(currentQuestion.getQuestionType()));
    }

    private void restoreForegroundDataForAllAnswerButtons() {
        for (JButton button : answerButtons) {
            restoreForegroundDataForAnswerButton(button);
        }
    }

    private void restoreForegroundDataForAnswerButton(JButton button) {
        button.setForeground(Color.BLACK);
        button.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
    }

    /**
     * This method generates a new timer component and adds it to the
     * footer panel
     */
    private void addTimerComponent() {
        timer = new TimerComponent();
        footerPanel.add(timer.getMainPanel());
    }

    /**
     * Sets up the footer of the page which is going to house
     * the timer component, then hides it (can also house more components
     * in the future as needed)
     */
    private void setUpFooter() {
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setVisible(false);
        rootPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Shows the footer of the page containing the timer
     */
    private void showTimerComponent() {
        footerPanel.setVisible(true);
    }

    /**
     * Hides the footer of the page containing the timer, and
     * clears it from the current timer object
     */
    private void hideTimerComponent() {
        footerPanel.setVisible(false);
        footerPanel.removeAll();
    }

    /**
     * This method displays an information message before a timed round begins, after which,
     * upon being dismissed, starts the timer for the question.
     */
    private void showGetReadyMessage() {
        JOptionPane.showMessageDialog(null, "Timed Round! Get ready to race for points!");
        timer.startTimer();
    }

    public void actionPerformed() {
        if (referee.haveAllPlayersAnswered()) {
            timer.stopTimer();
            roundLogic.giveCredits();
            hideTimerComponent();
            restoreForegroundDataForAllAnswerButtons();
            prepareNextQuestion();
        }
        rootPanel.requestFocus();
    }

    protected class CustomizedButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            referee.setAnswerData(referee.getAlivePlayersInRound().get(0), buttonSource.getText());
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
                referee.setAnswerData(playerAssociated, answerButtons.get(keyButtonAssociation.get(e.getKeyChar())).getText());
                actionPerformed();
            }
        }
    }
}
