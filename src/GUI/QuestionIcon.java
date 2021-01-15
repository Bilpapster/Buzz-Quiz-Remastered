package GUI;

import javax.swing.*;

/**
 * A class that represents an imaged question in our game.
 *
 * @author Fotios - Dimitrios Malakis
 * @version 2021.01.15
 */
public class QuestionIcon extends JLabel {

    private String image;

    /**
     * Constructor for the imaged question.
     *
     * @param image the image of the question.
     */
    public QuestionIcon(String image) {
        this.image = image;
        addIcon();
        this.setVisible(true);
    }

    /**
     * adds icon to the question
     */
    private void addIcon() {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("GUI/resources/" + image));
        this.setIcon(imageIcon);
    }

}
