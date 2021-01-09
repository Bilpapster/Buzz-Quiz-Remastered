package GUI;

import com.HighscoreManager;
import com.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class GameEndingWindow extends JFrame implements ActionListener {

    private ArrayList<Player> listOfPlayers;

    protected JPanel mainPanel;
    protected JLabel title;
    protected JButton backToMenuBtn;
    protected JPanel footerPanel;
    protected JPanel playerCardsPanel;
    protected ArrayList<PlayerEndInfoPanel> playerEndInfoPanels;
    protected HighscoreManager highscoreManager;

    public GameEndingWindow(ArrayList<Player> listOfPlayers) {
        highscoreManager = new HighscoreManager();
        this.listOfPlayers = listOfPlayers;
        calculatePositions();

        setUpFrame();
    }

    private void setUpFrame() {
        this.setTitle("Buzz! Quiz World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);

        setUpMainPanel();
        this.getContentPane().setBackground(Color.darkGray);
        setUpHeader();
        setUpFooterPanel();
        setUpFooter();
        setUpPlayerCardsPanel();
        setUpPlayerCards();

        this.setVisible(true);
    }

    private void setUpMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
    }

    private void setUpHeader() {
        title = new JLabel();
        title.setText("Game is over, " + listOfPlayers.get(0).getName() + " has won!");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 24));
        title.setBackground(Color.darkGray);
        title.setForeground(Color.white);
        mainPanel.add(title, BorderLayout.NORTH);
    }

    private void setUpFooterPanel() {
        footerPanel = new JPanel();
        footerPanel.setLayout(new GridBagLayout());
        footerPanel.setBackground(Color.darkGray);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void setUpFooter() {
        backToMenuBtn = new JButton("Back to main menu");
        backToMenuBtn.addActionListener(this::actionPerformed);
        backToMenuBtn.setPreferredSize(new Dimension(480,60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 680;
        gbc.gridheight= 80;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        footerPanel.add(backToMenuBtn, gbc);
    }

    private void setUpPlayerCardsPanel() {
        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new GridBagLayout());
        playerCardsPanel.setBackground(Color.darkGray);
        mainPanel.add(playerCardsPanel, BorderLayout.CENTER);
    }

    private void setUpPlayerCards() {
        playerEndInfoPanels = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;

        int pos = 1;
        for(Player i: listOfPlayers)
            playerEndInfoPanels.add(new PlayerEndInfoPanel(i, pos++));

        for(PlayerEndInfoPanel i: playerEndInfoPanels) {
            playerCardsPanel.add(i, gbc);
            gbc.gridy++;
        }

    }

    private void calculatePositions() {
        listOfPlayers.sort((o1, o2) -> o2.getScore() - o1.getScore());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenuFrame mainMenuFrame = new MainMenuFrame();
        highscoreManager.updateHighscores(listOfPlayers);
        this.dispose();
    }
}
