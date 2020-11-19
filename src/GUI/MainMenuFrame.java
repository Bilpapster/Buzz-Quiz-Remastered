package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuFrame extends JFrame implements ActionListener {
    JButton player1btn;
    JButton player2btn;
    JButton highscoreBtn;

    public MainMenuFrame() {
        this.setTitle("Buzz! Quiz World!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.darkGray);
        this.setLocationRelativeTo(null);

        ImageIcon gameImage = new ImageIcon("resources/1887880-box_buzzqw.png");
        ImageIcon iconImage = new ImageIcon("resources/Buzz_Quiz_World.jpg");
        this.setIconImage(iconImage.getImage());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.darkGray);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.darkGray);
        JPanel paddingLeft = new JPanel();
        paddingLeft.setPreferredSize(new Dimension(150, 100));
        paddingLeft.setBackground(Color.darkGray);
        JPanel paddingRight = new JPanel();
        paddingRight.setBackground(Color.darkGray);
        paddingRight.setPreferredSize(new Dimension(150, 100));
        JPanel creditsPanel = new JPanel();

        JLabel gameTitle = new JLabel("<html><font color=#e6c260>Buzz!</font><font color=white> Quiz World!</font></html>");
        gameTitle.setFont(new Font("MV Boli", Font.PLAIN, 32));
        gameTitle.setIconTextGap(20);
        titlePanel.add(gameTitle);
        gameTitle.setIcon(gameImage);
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setVerticalAlignment(JLabel.TOP);
        gameTitle.setHorizontalTextPosition(JLabel.CENTER);
        gameTitle.setVerticalTextPosition(JLabel.TOP);

        JLabel creditsLabel = new JLabel("developed by fmalakis and bilpapster");
        creditsPanel.add(creditsLabel);
        creditsLabel.setHorizontalTextPosition(JLabel.CENTER);

        player1btn = new JButton("Start 1-player game");
        player1btn.setBounds(200, 100, 100, 50);
        player2btn = new JButton("Start 2-player game");
        highscoreBtn = new JButton("View high-scores");
        highscoreBtn.addActionListener(this::actionPerformed);
        player1btn.addActionListener(this::actionPerformed);
        player2btn.addActionListener(this::actionPerformed);

        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.add(player1btn);
        GridBagConstraints btn1C = new GridBagConstraints();
        btn1C.fill = GridBagConstraints.CENTER;
        btn1C.ipadx = 150;
        btn1C.ipady = 40;
        btn1C.gridx = 0;
        btn1C.gridy = 0;
        btn1C.insets = new Insets(0,0,5,0);
        buttonsPanel.add(player1btn, btn1C);
        btn1C.gridx = 0;
        btn1C.gridy = 1;
        buttonsPanel.add(player2btn, btn1C);
        btn1C.gridy = 2;
        buttonsPanel.add(highscoreBtn, btn1C);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(paddingLeft, BorderLayout.WEST);
        this.add(paddingRight, BorderLayout.EAST);
        this.add(creditsPanel, BorderLayout.SOUTH);


        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == player1btn) {
            this.dispose();
            PlayerInfoPage playerInfoPage = new PlayerInfoPage(1);
        } else if (e.getSource() == player2btn) {
            this.dispose();
            PlayerInfoPage playerInfoPage = new PlayerInfoPage(2);
        } else if (e.getSource() == highscoreBtn) {
            try {
                HighscoreMenu highscoreMenu = new HighscoreMenu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }
}
