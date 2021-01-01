package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A simple class that represents a label, which indicates a player's name. Such a king of label is used in order to
 * indicate the selections made by each one of the players, concerning the current question asked in game. The label
 * displays the name of the player in a single line if the player is unique. In case more than one players have chosen
 * the same answer, their names are displayed in multiple lines, one name per line.
 *
 * @author Vasileios Papastergios
 */
public class PlayerSelectionLabel extends JLabel {

    /**
     * Default constructor. Constructs a selection label without text.
     */
    public PlayerSelectionLabel() {
        this.setVisible(false);
        this.clearText();
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 24f));
    }

    /**
     * Clears the text displayed on the label.
     */
    public void clearText() {
        this.setText("");
    }

    /**
     * Overrides the <code>setText</code> method in order to suppoer multiline display, in case the players that
     * have chosen a specific answer are more than one.
     *
     * @param text the text to add on the label
     */
    @Override
    public void setText(String text) {
        if (text.equals("")) {
            super.setText("");
            return;
        }

        if (this.getText().equals("")) {
            super.setText(text);
        } else {
            super.setText("<html>" + getText() + "<br>" + text + "</html>");
        }
    }
}
