package GUI;

import javax.swing.*;

/**
 * An interface that represents the front end (view) part of a round type in our game.
 *
 * @author Vasileios Papastergios
 */
public interface RoundViewerI {
    /**
     * Getter for the root panel of the viewer.
     *
     * @return the root panel of the viewer.
     */
    JPanel getRootPanel();

    /**
     * Setter for the parent frame of the viewer.
     *
     * @param parentFrame the parent frame that hosts the root panel of the viewer.
     */
    void setParentFrame(GameFrame parentFrame);

    /**
     * Invokes all necessary steps in order to start playing the round, in interaction with the players.
     */
    void play();
}
