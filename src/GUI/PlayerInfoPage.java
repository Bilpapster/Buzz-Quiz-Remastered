package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.*;
import com.Sound.SoundManager;

/**
 * Represents a name registration modal frame of our game. Contains text fields for the players in order to declare
 * their names for the current game.
 *
 * @author Fotios - Dimitrios Malakis
 * @version 2021.01.15
 */
public class PlayerInfoPage implements ActionListener {

    int numberOfPlayers;
    JPanel playerInfoPanel = new JPanel();
    JButton submitNamesBtn = new JButton("Start the game!");
    ArrayList<JLabel> playerEnterNameLabels = new ArrayList<>();
    ArrayList<JTextField> playerNameFields = new ArrayList<>();
    JFrame parentFrame;

    /**
     * Constructs a player info frame with given attributes.
     * @param numberOfPlayers the number of players taking part in game.
     * @param parentFrame the parent frame of the current, modal info frame
     */
    public PlayerInfoPage(int numberOfPlayers, JFrame parentFrame) {
        this.numberOfPlayers = numberOfPlayers;
        this.parentFrame = parentFrame;

        playerInfoPanel.setSize(480, 240);
        playerInfoPanel.setLayout(new GridLayout(numberOfPlayers * 2 + 1, 1));
        playerInfoPanel.setBackground(Color.darkGray);
        for (int i = 0; i < numberOfPlayers; i++) {
            JLabel label = new JLabel("Player " + (i + 1) + " enter your name:");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 22f));
            label.setForeground(Color.WHITE);
            playerEnterNameLabels.add(label);
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(250, 40));
            textField.setHorizontalAlignment(SwingConstants.CENTER);
            textField.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 26f));
            textField.setBackground(Color.DARK_GRAY);
            textField.setForeground(Color.WHITE);
            textField.setBorder(null);
            textField.setCaretColor(Color.WHITE);
            textField.addActionListener(this::actionPerformed);
            playerNameFields.add(textField);
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            playerInfoPanel.add(playerEnterNameLabels.get(i));
            playerInfoPanel.add(playerNameFields.get(i));
        }
        submitNamesBtn.setHorizontalAlignment(JButton.CENTER);
        submitNamesBtn.addActionListener(this::actionPerformed);
        playerInfoPanel.add(submitNamesBtn);

        JDialog frame = new JDialog(parentFrame, "Declare your names!", true);
        frame.getContentPane().add(playerInfoPanel);
        frame.pack();
        frame.setSize(480, 240);
        frame.setLocationRelativeTo(parentFrame);
        frame.setVisible(true);
    }

    /**
     * Submits players' info.
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (nameInputsAreValid()) {
            submitPlayerInfo();
        } else {
            JOptionPane.showMessageDialog(null, "All players must enter their names!", "Oops", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the input is valid.
     * @return true if all players have declared their names, else false.
     */
    private boolean nameInputsAreValid() {
        for (JTextField i : playerNameFields)
            if (i.getText().equals(""))
                return false;
        return true;
    }

    /**
     * Submits players' info and starts game
     */
    private void submitPlayerInfo() {
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        for (JTextField i : playerNameFields) {
            listOfPlayers.add(new Player(i.getText()));
        }
        new GameFrame(listOfPlayers);
        parentFrame.dispose();
        SoundManager.stopClip("main_menu_theme");
    }

}
