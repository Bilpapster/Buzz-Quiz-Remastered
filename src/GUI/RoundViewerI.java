package GUI;

import javax.swing.*;

public interface RoundViewerI {
    JPanel getRootPanel();
    void setParentFrame(GameFrame parentFrame);
    void play();
}
