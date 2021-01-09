package GUI;

import javax.swing.*;

public class QuestionIcon extends JLabel {

    private String image;

    public QuestionIcon(String image) {
        this.image = image;
        addIcon();
        this.setVisible(true);
    }

    private void addIcon() {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("GUI/resources/" + image));
        this.setIcon(imageIcon);
    }

}
