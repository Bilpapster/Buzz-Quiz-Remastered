package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayerSelectionLabel extends JLabel {

    public PlayerSelectionLabel() {
        this.setVisible(false);
        this.clearText();
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 24f));
    }

    public void clearText() {
        this.setText("");
    }

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
