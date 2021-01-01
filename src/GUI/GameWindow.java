package GUI;

import com.Player;
import com.Question;
import com.QuestionType;
import com.QuestionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


/**
 * Not used or needed anymore, can be deleted (Papster)
 */
public class GameWindow implements ActionListener {

    ArrayList<Player> listOfPlayers;
    Question currentQuestion;
    TimerComponent timer;
    JFrame gameFrame = new JFrame();
    JLabel questionType = new JLabel();
    JLabel questionText = new JLabel();
    RoundedJPanel questionTypePanel = new RoundedJPanel();
    JPanel questionPanel = new JPanel();
    JPanel answersPanel = new JPanel();
    JPanel footerPanel = new JPanel();
    int indexLOL = 0;
    QuestionManager questionManager = new QuestionManager();
    ArrayList<JButton> answerBtns = new ArrayList<>();

    public GameWindow(ArrayList<Player> listOfPlayers) {

        this.listOfPlayers = listOfPlayers;
        questionManager.createQuestions();

        setUpQuestionTypePanel();
        setUpQuestionPanel(); 
        setUpAnswersPanel();
        setUpFrame();

        prepareNextQuestion();
    }

    private void setUpQuestionTypePanel() {
        questionType.setFont(new Font("Segoe Print", Font.BOLD + Font.ITALIC, 14));
        questionType.setHorizontalAlignment(JLabel.CENTER);
        questionType.setAlignmentX(JLabel.CENTER);
        questionType.setForeground(Color.WHITE);

        questionTypePanel.setForeground(Color.WHITE);
        questionTypePanel.setLayout(new BoxLayout(questionTypePanel, BoxLayout.Y_AXIS));
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 4)));
        questionTypePanel.add(questionType, JPanel.CENTER_ALIGNMENT);
        questionTypePanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        questionTypePanel.add(Box.createRigidArea(new Dimension(1, 6)));
    }

    private void setUpQuestionPanel() {
        questionText.setFont(new Font("Segoe Print", Font.PLAIN, 22));
        questionText.setAlignmentX(JLabel.CENTER);
        questionText.setHorizontalAlignment(JLabel.CENTER);

        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        questionPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        questionPanel.add(questionTypePanel, Component.CENTER_ALIGNMENT);
        questionPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        questionPanel.add(questionText, Component.CENTER_ALIGNMENT);
        questionPanel.add(Box.createRigidArea(new Dimension(1, 6)));
    }

    private void setUpAnswersPanel() {
        answersPanel.setLayout(new GridLayout(2, 2));
    }

    private void setUpFrame() {
        gameFrame.setTitle("Buzz! Quiz World!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1280, 720);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setBackground(Color.darkGray);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        gameFrame.add(questionPanel, BorderLayout.NORTH);
        gameFrame.add(answersPanel, BorderLayout.CENTER);
        setUpFooter();

        gameFrame.addMouseListener(new MouseAdapter() {
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
        questionText.setText(currentQuestion.getQuestionText());
        questionType.setText("  " + currentQuestion.getQuestionType().toString() + "   ");
        questionTypePanel.setBackground(QuestionType.getColorOf(currentQuestion.getQuestionType()));

        Collection<String> questionAnswers = currentQuestion.getAnswers().values();

        clearButtons();

        int index = 0;
        for (String i : questionAnswers) {
            JButton button = new JButton(i);
            button.setPreferredSize(new Dimension(10, 10));
            button.setMaximumSize(new Dimension(10, 10));
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
        gameFrame.add(footerPanel, BorderLayout.SOUTH);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stopTimer();
        checkIfCorrectAnswer(answerBtns.indexOf(e.getSource()));
        hideTimerComponent();
        prepareNextQuestion();
    }
}
