package GUI;

import com.Player;

import javax.swing.*;
import java.awt.*;

/**
 * A class that represents a component of the ending frame of our game. Represents an individual final scoreboard.
 *
 * @author Fotios - Dimitrios Malakis
 * @version 2021.01.15
 */
public class PlayerEndInfoPanel extends JLabel {

    private Player player;
    private int pos;

    /**
     * Constructs a final scoreboard component with the given player and the given position.
     *
     * @param player the player to display on the scoreboard
     * @param pos    the position of the player in the current game
     */
    public PlayerEndInfoPanel(Player player, int pos) {
        this.player = player;
        this.pos = pos;
        setUpInfo();
    }

    /**
     * Sets up all needed info for the player.
     */
    private void setUpInfo() {
        String info = "(" + pos + ") " + player.getName() + ": " + player.getScore();
        this.setText(info);
        String color;
        switch (pos) {
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
