package GUI;

import com.Question;
import com.QuestionManager;
import com.QuestionType;
import com.StandardRound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class StandardRoundFrame extends JFrame {
    protected StandardRound round;

    TimerComponent timer;
    Question currentQuestion;
    protected JPanel rootPanel;

    protected JLabel questionTypeLabel;
    protected JLabel questionTextLabel;

    protected RoundedJPanel questionTypePanel;
    protected JPanel questionTextPanel;
    protected JPanel questionPanel;

    protected JPanel answerButtonsPanel;

    protected JPanel paddingLeft = new JPanel();
    protected JPanel paddingRight = new JPanel();



    protected JPanel answersPanel = new JPanel();
    protected JPanel footerPanel = new JPanel();

    int indexLOL = 0;
    protected QuestionManager questionManager;
    protected ArrayList<JButton> answerBtns = new ArrayList<>();

    // TODO: create separate methods for padding left and padding right panels
    public StandardRoundFrame(){
        questionManager = new QuestionManager();
        questionManager.createQuestions();

        setUpComponents();

        setUpQuestionTypePanel();
        setUpQuestionTextPanel();
        setUpQuestionPanel();

        answerButtonsPanel = new JPanel();
        answerButtonsPanel.setLayout(new BoxLayout(answerButtonsPanel, BoxLayout.Y_AXIS));
        answerButtonsPanel.add(Box.createVerticalStrut(25));
        answerButtonsPanel.add(answersPanel);
        answerButtonsPanel.add(Box.createVerticalStrut(25));

        paddingLeft.setPreferredSize(new Dimension(150, 100));
        paddingLeft.setBackground(Color.darkGray);
        paddingRight.setBackground(Color.darkGray);
        paddingRight.setPreferredSize(new Dimension(150, 100));

        setUpAnswersPanel();
        setUpRootPanel();
        setUpFrame();

        prepareNextQuestion();
    }

    private void setUpComponents() {
        setUpQuestionTypeLabel();
        setUpQuestionTextLabel();
    }

    private void setUpQuestionTypeLabel() {
        questionTypeLabel = new JLabel();
        questionTypeLabel.setFont(new Font("Segoe Print", Font.BOLD + Font.ITALIC, 14));
        questionTypeLabel.setAlignmentX(JLabel.CENTER);
        questionTypeLabel.setForeground(Color.WHITE);
    }

    private void setUpQuestionTextLabel() {
        questionTextLabel = new JLabel();
        questionTextLabel.setFont(new Font("Segoe Print", Font.PLAIN, 22));
        questionTextLabel.setAlignmentX(JLabel.CENTER);
        questionTextLabel.setForeground(Color.WHITE);

    }

    private void setUpQuestionTypePanel() {
        questionTypePanel = new RoundedJPanel();
        questionTypePanel.setOpaque(false);
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

        questionPanel.setBackground(Color.DARK_GRAY); //
    }

    private void setUpRootPanel() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(questionPanel, BorderLayout.NORTH);
        rootPanel.add(answerButtonsPanel, BorderLayout.CENTER);
        rootPanel.add(paddingLeft, BorderLayout.WEST);
        rootPanel.add(paddingRight, BorderLayout.EAST);
//        rootPanel.add(answersPanel, BorderLayout.CENTER);
    }

    private void setUpAnswersPanel() {
        answersPanel.setLayout(new GridLayout(2, 2, 50, 25));
    }

    private void setUpFrame() {
        this.setTitle("Buzz! Quiz World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setContentPane(rootPanel);
        this.setVisible(true);


        setUpFooter();

        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(!timer.isOver() ? "The timer is still going!" : "The timer has finished!");
            }
        });
    }

    private void clearButtons() {
        for (JButton button : answerBtns)
            answersPanel.remove(button);
        answerBtns.clear();
    }

    private void prepareNextQuestion() {
        currentQuestion = questionManager.getNextQuestion();
        questionTextLabel.setText(currentQuestion.getQuestionText());
        questionTypeLabel.setText("   " + currentQuestion.getQuestionType().toString() + "    ");
//        System.out.println(questionTextLabel.getSize());

        questionTypePanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        answerButtonsPanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        answersPanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        paddingLeft.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));
        paddingRight.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));

        Collection<String> questionAnswers = currentQuestion.getAnswers().values();

        clearButtons();


        int index = 0;
        for (String i : questionAnswers){
            RoundedJButton button = new RoundedJButton(i);
            button.setFont(new Font("Segoe Print", Font.PLAIN, 20));
            button.addActionListener(this::actionPerformed);
            answerBtns.add(button);
            answersPanel.add(answerBtns.get(index++));
        }

        questionManager.removeAnsweredQuestion();

        // this is for testing puproses, remove for actual production version
        if (indexLOL % 2 == 0) {
            addTimerComponent();
            showTimerComponent();
            showGetReadyMessage();
        }

        indexLOL++;
        // ------------------------------------------------------------------
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
     * uppon being dismissed, starts the timer for the question.
     */
    private void showGetReadyMessage() {
        JOptionPane.showMessageDialog(null, "Timed Round! Get ready to race for points!");
        timer.startTimer();
    }

    private void checkIfCorrectAnswer(int index) {
        if (currentQuestion.isCorrectAnswer(Character.toString("abcdefg".charAt(index))))
            JOptionPane.showMessageDialog(null, "The answer is correct!", "Hurray!", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "The answer is wrong...", "Oops", JOptionPane.ERROR_MESSAGE);
    }


    //@Override
    public void actionPerformed(ActionEvent e) {
        timer.stopTimer();
        checkIfCorrectAnswer(answerBtns.indexOf(e.getSource()));
        hideTimerComponent();
        prepareNextQuestion();
    }
}
