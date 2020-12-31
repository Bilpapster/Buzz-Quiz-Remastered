package GUI;

import com.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    private ArrayList<RoundViewerI> rounds;
    private ArrayList<Player> players;
    private int currentRoundIndex = 0;

    public GameFrame(ArrayList<RoundViewerI> rounds, ArrayList<Player> players) throws HeadlessException {

        this.setVisible(false);
        this.rounds = rounds;
        this.players = players;

        this.setTitle("Buzz! Quiz World!");
        ImageIcon iconImage = new ImageIcon("resources/Buzz-Quiz-World_LOGO.jpg");
        this.setIconImage(iconImage.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
    }

    public void setRootPanel(JPanel rootPanel) {
        this.setContentPane(rootPanel);
    }

    public void start() {
        this.setContentPane(rounds.get(0).getRootPanel());
        rounds.get(0).play();
        this.setVisible(true);
    }

    public void playNextRound() {
        currentRoundIndex++;
        if (currentRoundIndex < rounds.size()) {
            this.setContentPane(rounds.get(currentRoundIndex).getRootPanel());
            rounds.get(currentRoundIndex).getRootPanel().requestFocus();
            rounds.get(currentRoundIndex).play();
        } else {
            dispose();
            new GameEndingWindow(players);
        }
    }


}
