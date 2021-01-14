package GUI;

import javax.swing.*;
import java.awt.*;

public class AnimationButton extends RoundedJButton {
    private Timer timer;
    private int animationDuration;
    private long animationStartTime;
    private int startCoordinateX;
    private int endCoordinateX;
    private int initialDelay;

    public AnimationButton(String textToDisplay, int animationDuration, int startCoordinateX, int endCoordinateX, int initialDelay, boolean autoStartAnimation) {
        super(new Dimension(30, 30));
        this.setText(textToDisplay);
        this.setVisible(false);
        this.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.REGULAR, 22f));
        this.animationDuration = Math.abs(animationDuration);
        this.startCoordinateX = startCoordinateX;
        this.endCoordinateX = endCoordinateX;
        this.initialDelay = initialDelay;
    }

    public void scheduleForwardAnimation() {
        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runForwardAnimation();
            }
        };
    }

    public void scheduleReverseAnimation() {

        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runReverseAnimation();

            }
        };
    }

    private void runForwardAnimation() {
        this.setVisible(true);
        this.setBounds(startCoordinateX, this.getBounds().y, this.getWidth(), this.getHeight());
        this.repaint();
        animationStartTime = System.currentTimeMillis();
        timer = new Timer(0, e -> {
            long currentTime = System.currentTimeMillis();
            long timeElapsedAfterLaunch = currentTime - animationStartTime;
            if (timeElapsedAfterLaunch > animationDuration) {
                timer.stop();
                timer.setRepeats(false);
                return;
            }
            float fraction = (float) timeElapsedAfterLaunch / animationDuration;
            int coordinateX = (int) ((endCoordinateX - startCoordinateX) * (fraction) + startCoordinateX);
            this.setBounds(coordinateX, this.getBounds().y, this.getWidth(), this.getHeight());
            this.repaint();
        });
        timer.start();
    }

    public void runReverseAnimation() {
        this.setVisible(true);
        this.setBounds(endCoordinateX, this.getBounds().y, this.getWidth(), this.getHeight());
        this.repaint();
        animationStartTime = System.currentTimeMillis();
        timer = new Timer(0, e -> {
            long currentTime = System.currentTimeMillis();
            long timeElapsedAfterLaunch = currentTime - animationStartTime;
            if (timeElapsedAfterLaunch > animationDuration) {
                timer.stop();
                timer.setRepeats(false);
                return;
            }
            float fraction = (float) timeElapsedAfterLaunch / animationDuration;
            int coordinateX = (int) ((endCoordinateX - startCoordinateX) * (1 - fraction) + startCoordinateX);
            this.setBounds(coordinateX, this.getBounds().y, this.getWidth(), this.getHeight());
            this.repaint();
            System.out.println("Running");
        });
        timer.start();
    }

}
