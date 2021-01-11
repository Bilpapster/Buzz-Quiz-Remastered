package GUI;

/**
 * A simple class that represents a label, which indicates a player's name. Such a kind of label is used in order to
 * indicate the selections made by each one of the players, concerning the current question asked in game. The label
 * displays the name of the player in a single line if the player is unique. In case more than one players have chosen
 * the same answer, their names are displayed in multiple lines, one name per line (both of them aligned on the right)
 * .
 *
 * @author Vasileios Papastergios
 */
public class PlayerSelectionLabel extends CustomizedJLabel {

    /**
     * Default constructor. Constructs a selection label without text.
     */
    public PlayerSelectionLabel() {
        super(FontManager.FontStyle.REGULAR, 24f);
        this.setVisible(false);
        this.clearText();
    }

    /**
     * Clears the text displayed on the label.
     */
    public void clearText() {
        this.setText("");
    }

    /**
     * Overrides the <code>setText</code> method in order to support multiline display, in case the players that
     * have chosen a specific answer are more than one.
     *
     * @param text the text to add on the label
     */
    @Override
    public void setText(String text) {
        // if the text to put on the label is idle (""), that means it asked to clear the label
        if (text.equals("")) {
            super.setText("");
            return;
        }

        // if the text displayed on the label is already idle, then the new text is simply put on it
        if (this.getText().equals("")) {
            super.setText(text);
            return;
        }

        /* if the text displayed on the label is not idle, it is needed to add a new line and put
        the new text at the second line (remain the existent text intect)
         */
        super.setText("<html>" + getText() + "<br>" + text + "</html>");
    }
}
