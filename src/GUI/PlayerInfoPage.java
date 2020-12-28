package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.*;


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

        ArrayList<RoundViewerI> rounds = new ArrayList<>();

        StandardRoundFrame round1 = new StandardRoundFrame(referee);
        BettingRoundFrame round2 = new BettingRoundFrame(referee);
        StopTheClockRoundFrame round3 = new StopTheClockRoundFrame(referee);
        QuickAnswerRoundFrame round4 = new QuickAnswerRoundFrame(referee);
        ThermometerRoundFrame round5 = new ThermometerRoundFrame(referee);


        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);
        rounds.add(round4);
        rounds.add(round5);

        GameFrame gameFrame = new GameFrame(rounds, referee.getAlivePlayersInRound());

        round1.setParentFrame(gameFrame);
        round2.setParentFrame(gameFrame);
        round3.setParentFrame(gameFrame);
        round4.setParentFrame(gameFrame);
        round5.setParentFrame(gameFrame);

        gameFrame.start();

//        StandardRoundFrame standardRoundFrame = new StandardRoundFrame(referee);
//        GameFrame gameFrame = new GameFrame(standardRoundFrame.getRootPanel());
//        standardRoundFrame.play();

//        BettingRoundFrame bettingRoundFrame = new BettingRoundFrame(referee);
//        new GameFrame(bettingRoundFrame.getRootPanel());
//        bettingRoundFrame.play();

//        StopTheClockRoundFrame stopTheClockRoundFrame = new StopTheClockRoundFrame(referee);
//        GameFrame gameFrame = new GameFrame(stopTheClockRoundFrame.getRootPanel());
//        stopTheClockRoundFrame.play();

//        QuickAnswerRoundFrame quickAnswerRoundFrame = new QuickAnswerRoundFrame(referee);
//        quickAnswerRoundFrame.play();

//        ThermometerRoundFrame thermometerRoundFrame = new ThermometerRoundFrame(referee);
//        thermometerRoundFrame.play();
    }

}
