package GUI;
import com.Player;
import com.Question;
import com.QuestionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class GameWindow implements ActionListener {

    ArrayList<Player> listOfPlayers;
    Question currentQuestion;
    TimerComponent timer;
    JFrame gameFrame = new JFrame();
    JLabel questionText = new JLabel();
    JPanel questionPanel = new JPanel();
    JPanel answersPanel = new JPanel();
    JPanel footerPanel = new JPanel();
    int indexLOL = 0;
    QuestionManager questionManager = new QuestionManager();
    ArrayList<JButton> answerBtns = new ArrayList<>();

    // TODO: really need to break down this constructor to smaller functions
    public GameWindow(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
        questionManager.createQuestions();
        gameFrame.setTitle("Buzz! Quiz World!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1280, 720);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setBackground(Color.darkGray);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        answersPanel.setLayout(new GridLayout(2,2));

        questionText.setText("This is a test question");
        questionText.setFont(new Font("Arial Black", Font.BOLD, 26));
        questionText.setHorizontalAlignment(JLabel.CENTER);
        questionPanel.add(questionText);
        gameFrame.add(questionPanel, BorderLayout.NORTH);


        gameFrame.add(answersPanel, BorderLayout.CENTER);
        setUpFooter();


        gameFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(!timer.isOver()? "The timer is still going!" : "The timer has finished!");
            }
        });

        prepareNextQuestion();

    }

    private void clearButtons() {
        for(JButton button: answerBtns)
            answersPanel.remove(button);
        answerBtns.clear();
    }

    private void prepareNextQuestion() {
        currentQuestion = questionManager.getNextQuestion();
        questionText.setText(currentQuestion.getQuestionText());

        Collection<String> questionAnswers = currentQuestion.getAnswers().values();

        clearButtons();

        int index = 0;
        for(String i: questionAnswers) {
            JButton button = new JButton(i);
            button.setFont(new Font("Arial Black", Font.PLAIN, 18));
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
