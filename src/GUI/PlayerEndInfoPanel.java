package GUI;

import com.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerEndInfoPanel extends JLabel {

    private Player player;
    private int pos;

    public PlayerEndInfoPanel(Player player, int pos) {
        this.player = player;
        this.pos = pos;
        setUpInfo();
    }

    private void setUpInfo() {
        String info = "(" + pos + ") " + player.getName() + ": " + player.getScore();
        this.setText(info);
        String color;
        switch(pos) {
            case 1:
                color = "#ffd700";
                break;
            case 2:
                color = "#c0c0c0";
                break;
            case 3:
                color = "#cd7f32";
                break;
            default:
                color = "#ffffff";
        }
        this.setForeground(Color.decode(color));
        this.setFont(new Font("Architects Daughter", Font.PLAIN, 28));
        this.setVisible(true);
    }

}
