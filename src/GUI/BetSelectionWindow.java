package GUI;

import com.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;

/**
 * A GUI class for the betting window that appears during the Betting Round in which players can select the bet they
 * will make for the coming round
 */
public class BetSelectionWindow implements ActionListener, MouseListener {

    JDialog dialog;
    JLabel title;
    JLabel footerTitle;
    JLabel potentialWin;
    JLabel potentialLoss;
    JPanel footerPanel;
    ArrayList<JButton> betButtons;
    JPanel buttonsPanel;
    final int[] betValues = {500, 750, 1000};
    final Player currentPlayer;
    private int finalBet;

    /**
     * Initializes the betting modal
     * @param parentFrame the parent JFrame of the modal
     * @param currentPlayer the player that is currently betting
     */
    public BetSelectionWindow(final JFrame parentFrame, Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        setUpFrame(parentFrame);
    }

    /**
     * Sets up the JDialog frame and displays it
     * @param parentFrame
     */
    private void setUpFrame(final JFrame parentFrame) {
        dialog = new JDialog(parentFrame);
        dialog.setTitle("Betting time!");
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setBackground(Color.darkGray);
        dialog.setBounds(180, 180, 480, 180);
        dialog.setLocationRelativeTo(parentFrame);

        setUpHeader();
        setUpButtonPanel();
        setUpBetBtns();
        setUpFooter();

        dialog.setVisible(true);
        dialog.pack();
    }

    /**
     * Sets up the header for the betting window
     */
    private void setUpHeader() {
        title = new JLabel();
        title.setText("Place your bet, " + currentPlayer.getName() + "!");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 21));
        title.setForeground(Color.white);
        dialog.add(title, BorderLayout.NORTH);
    }

    /**
     * Sets up the panep in which the betting buttons are going to be positioned
     */
    private void setUpButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.darkGray);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog.add(buttonsPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the <code>ArrayList</code> containing all the JButtons for each of the allowed
     * betting values found in <code>betValues</code> and adds them to the button panel
     */
    private void setUpBetBtns() {
        betButtons = new ArrayList<>();
        for(Integer i: betValues) {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(100, 40));
            btn.setText(i.toString() + "$");
            btn.addActionListener(this::actionPerformed);
            betButtons.add(btn);
        }
        for (JButton btn: betButtons)
            buttonsPanel.add(btn);
    }

    private void setUpFooter() {
        footerPanel = new JPanel();
        footerPanel.setLayout(new GridBagLayout());
        GridBagConstraints footerPanelConstraints = new GridBagConstraints();

        footerPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        footerPanelConstraints.gridy = 0;
        footerPanelConstraints.gridx = 0;

        footerTitle = new JLabel();
        footerTitle.setText("Your total points after the bet is settled:");

        footerPanel.add(footerTitle, footerPanelConstraints);
        footerPanelConstraints.gridy = 1;

        potentialWin = new JLabel();
        footerPanel.add(potentialWin, footerPanelConstraints);
        footerPanelConstraints.gridx = 1;

        potentialLoss = new JLabel();
        footerPanel.add(potentialLoss, footerPanelConstraints);

        potentialLoss.setVisible(false);
        potentialWin.setVisible(false);
        footerTitle.setVisible(false);

        for(JButton btn: betButtons)
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    potentialWin.setText("On win: " + (betValues[betButtons.indexOf(e.getSource())] + currentPlayer.getScore()));
                    potentialLoss.setText("On loss: " + (currentPlayer.getScore() - betValues[betButtons.indexOf(e.getSource())]));

                    footerTitle.setVisible(true);
                    potentialWin.setVisible(true);
                    potentialLoss.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    footerTitle.setVisible(false);
                    potentialWin.setVisible(false);
                    potentialLoss.setVisible(false);
                }
            });


        dialog.add(footerPanel, BorderLayout.SOUTH);


    }

    /**
     * Used to retrieve a user's choice for a bet <u><b>after a modal has been disposed.</b></u>
     * @return an int representing the user's choice of betting value
     */
    public int getFinalBet() {
        return finalBet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        finalBet = betValues[betButtons.indexOf(e.getSource())];
        dialog.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        footerTitle.setVisible(true);
        potentialWin.setVisible(true);
        potentialLoss.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        footerTitle.setVisible(false);
        potentialWin.setVisible(false);
        potentialLoss.setVisible(false);
    }
}
