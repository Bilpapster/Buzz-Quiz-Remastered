package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class BetSelectionWindow implements ActionListener {

    JDialog dialog;
    JLabel title;
    ArrayList<JButton> betButtons;
    JPanel buttonsPanel;
    final int[] betValues = {500, 750, 1000};
    private int finalBet;

    public BetSelectionWindow(final JFrame parentFrame) {
        setUpFrame(parentFrame);
    }

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

    private void setUpHeader() {
        title = new JLabel();
        title.setText("Place your bet!");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 21));
        title.setForeground(Color.white);
        dialog.add(title, BorderLayout.NORTH);
    }

    private void setUpButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.darkGray);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog.add(buttonsPanel, BorderLayout.CENTER);
    }

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

    public int getFinalBet() {
        return finalBet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        finalBet = betValues[betButtons.indexOf(e.getSource())];
        dialog.dispose();
    }
}
