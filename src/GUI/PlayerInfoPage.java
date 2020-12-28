package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.Player;
import com.QuickAnswerRound;
import com.Referee;


public class PlayerInfoPage implements ActionListener {

    int numberOfPlayers;
    JFrame playerInfoFrame = new JFrame();
    JButton submitNamesBtn = new JButton("Start the game!");
    ArrayList<JLabel> playerEnterNameLabels = new ArrayList<>();
    ArrayList<JTextField> playerNameFields = new ArrayList<>();

    public PlayerInfoPage(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        playerInfoFrame.setTitle("Buzz! Quiz World!");
        playerInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerInfoFrame.setSize(480, 240);
        playerInfoFrame.setLayout(new GridLayout(numberOfPlayers*2 + 1, 1));
        playerInfoFrame.getContentPane().setBackground(Color.darkGray);
        playerInfoFrame.setLocationRelativeTo(null);
        playerInfoFrame.setVisible(true);
        for(int i = 0; i < numberOfPlayers; i++) {
            JLabel label = new JLabel("Player " + (i + 1) + " enter your name:");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setFont(new Font("Arial Black", Font.PLAIN, 24));
            label.setForeground(Color.WHITE);
            playerEnterNameLabels.add(label);
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(250, 40));
            textField.setFont(new Font("Arial Black", Font.BOLD, 18));
            textField.addActionListener(this::actionPerformed);
            playerNameFields.add(textField);
        }
        for (int i = 0; i < numberOfPlayers; i ++) {
            playerInfoFrame.add(playerEnterNameLabels.get(i));
            playerInfoFrame.add(playerNameFields.get(i));
        }
        submitNamesBtn.setHorizontalAlignment(JButton.CENTER);
        submitNamesBtn.addActionListener(this::actionPerformed);
        playerInfoFrame.add(submitNamesBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nameInputsAreValid()) {
            submitPlayerInfo();
        } else {
            JOptionPane.showMessageDialog(null, "All players must enter their names!", "Oops", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean nameInputsAreValid() {
        for(JTextField i: playerNameFields)
            if (i.getText().equals(""))
                return false;
        return true;
    }

    private void submitPlayerInfo() {
        ArrayList<Player> listofPlayers = new ArrayList<>();
        for (JTextField i : playerNameFields) {
            listofPlayers.add(new Player(i.getText()));
        }
        playerInfoFrame.dispose();

        Referee referee = new Referee(listofPlayers);

//        StandardRoundFrame standardRoundFrame = new StandardRoundFrame(referee);
//        standardRoundFrame.play();

//        BettingRoundFrame bettingRoundFrame = new BettingRoundFrame(referee);
//        bettingRoundFrame.play();

//        StopTheClockRoundFrame stopTheClockRoundFrame = new StopTheClockRoundFrame(referee);
//        stopTheClockRoundFrame.play();

//        QuickAnswerRoundFrame quickAnswerRoundFrame = new QuickAnswerRoundFrame(referee);
//        quickAnswerRoundFrame.play();

        ThermometerRoundFrame thermometerRoundFrame = new ThermometerRoundFrame(referee);
        thermometerRoundFrame.play();
    }

}
