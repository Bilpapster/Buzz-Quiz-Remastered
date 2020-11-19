package GUI;
import com.Player;
import com.Question;
import com.QuestionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameWindow implements ActionListener {

    ArrayList<Player> listOfPlayers;
    Question currentQuestion;
    JFrame gameFrame = new JFrame();
    JLabel questionText = new JLabel();
    JPanel questionPanel = new JPanel();
    JPanel answersPanel = new JPanel();
    QuestionManager questionManager = new QuestionManager();
    ArrayList<JButton> answerBtns = new ArrayList<>();

    public GameWindow(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
        questionManager.createQuestions();
        gameFrame.setTitle("Buzz! Quiz World!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1280, 720);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.getContentPane().setBackground(Color.darkGray);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        answersPanel.setLayout(new GridLayout(2,2));

        questionText.setText("This is a test question");
        questionText.setFont(new Font("Arial Black", Font.BOLD, 26));
        questionText.setHorizontalAlignment(JLabel.CENTER);
        questionPanel.add(questionText);
        gameFrame.add(questionPanel, BorderLayout.NORTH);


        gameFrame.add(answersPanel, BorderLayout.CENTER);

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

    }

    private void checkIfCorrectAnswer(int index) {
        if (currentQuestion.isCorrectAnswer(Character.toString("abcdefg".charAt(index))))
            JOptionPane.showMessageDialog(null, "The answer is correct!", "Hurray!", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "The answer is wrong...", "Oops", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        checkIfCorrectAnswer(answerBtns.indexOf(e.getSource()));
        prepareNextQuestion();
    }
}
