package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

/**
 * A GUI class for the betting window that appears during the Betting Round in which players can select the bet they
 * will make for the coming round
 */
public class BetSelectionWindow implements ActionListener {

    JDialog dialog;
    JLabel title;
    ArrayList<JButton> betButtons;
    JPanel buttonsPanel;
    final int[] betValues = {500, 750, 1000};
    private int finalBet;

    /**
     * Initializes the betting modal
     * @param parentFrame the parent JFrame of the modal
     */
    public BetSelectionWindow(final JFrame parentFrame) {
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

        dialog.setVisible(true);
        dialog.pack();
    }

    /**
     * Sets up the header for the betting window
     */
    private void setUpHeader() {
        title = new JLabel();
        title.setText("Place your bet!");
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
}
