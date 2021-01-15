package GUI;

import com.Constants;

import javax.swing.*;
import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class PlayerNamesRegistrationPanel {
    private int numberOfPlayersCurrentlyPlaying = 0;
    private ArrayList<JTextField> allAvailableNameFields = new ArrayList<>();

    private final int startCoordinateX = 100;
    private final int endCoordinateX = 500;
    private final int animationDuration = 2000;

    private JTextField playerOneTextField = new JTextField("Player One's Name");
    private JTextField playerTwoTextField = new JTextField("Player Two's Name");

    private JPanel rootPanel = new JPanel();

    public PlayerNamesRegistrationPanel() {
        playerOneTextField.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 26f));
        playerOneTextField.setMaximumSize(new Dimension(250, 40));
        playerOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneTextField.setForeground(Color.WHITE);
        playerOneTextField.setOpaque(false);
        playerOneTextField.setVisible(false);
        playerOneTextField.setBounds(100, playerOneTextField.getY(), playerOneTextField.getWidth(), playerOneTextField.getHeight());

        playerTwoTextField.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 26f));
        playerTwoTextField.setMaximumSize(new Dimension(250, 40));
        playerTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoTextField.setForeground(Color.WHITE);
        playerTwoTextField.setOpaque(false);
        playerTwoTextField.setVisible(false);
        playerTwoTextField.setBounds(100, playerTwoTextField.getY(), playerTwoTextField.getWidth(), playerTwoTextField.getHeight());


        rootPanel.setOpaque(false);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.add(playerOneTextField);
        rootPanel.add(Box.createVerticalStrut(40));
        rootPanel.add(playerTwoTextField);

    }


    public void showPlayers(int newNumberOfPlayers) {

        if (newNumberOfPlayers == 1) {
            playerOneTextField.setVisible(true);
            playerOneTextField.repaint();
            playerTwoTextField.setVisible(false);
            playerTwoTextField.repaint();
        } else {
            playerOneTextField.setVisible(true);
            playerOneTextField.repaint();
            playerTwoTextField.setVisible(true);
            playerTwoTextField.repaint();
        }

//        System.out.println("Mphka - " + this.numberOfPlayersCurrentlyPlaying);
//
//
//        for (int player = this.numberOfPlayersCurrentlyPlaying; player < newNumberOfPlayers; player++) {
//            System.out.println(player);
//            allAvailableNameFields.get(player).setVisible(true);
//            allAvailableNameFields.get(player).repaint();
//        }
//
//        for (int player = newNumberOfPlayers - 1; player > this.numberOfPlayersCurrentlyPlaying-1; player--) {
//            allAvailableNameFields.get(player).setVisible(false);
//        }
//        this.numberOfPlayersCurrentlyPlaying = newNumberOfPlayers;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
