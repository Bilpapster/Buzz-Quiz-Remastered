package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitionWizard {

    private static TransitionWizard wizard = new TransitionWizard();

    private TransitionWizard() {
    }

    public static TransitionWizard getWizard() {
        return wizard;
    }

    public void scheduleForwardAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX, int initialDelay) {
        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runForwardAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX);
            }
        };
    }

    public void scheduleReverseAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX, int initialDelay) {
        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runReverseAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX);
            }
        };
    }

    public void runForwardAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX) {

        new Thread(() -> runAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX, false)).start();
    }

    public void runReverseAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX) {
        new Thread(() -> runAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX, true)).start();
    }

    private synchronized void runAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX, boolean isReverse) {
        new Thread(() -> {
            // in case the transition is forward, the component starts from the starting X coordinate
            int startingPosition = startCoordinateX;
            if (isReverse) {
                // in case the transition is reverse, the component starts from the ending X coordinate
                startingPosition = endCoordinateX;
            }

            transitioningComponent.setBounds(startingPosition, transitioningComponent.getY(), transitioningComponent.getWidth(), transitioningComponent.getHeight());
            transitioningComponent.setVisible(true);
            transitioningComponent.repaint();
            long animationStartTime = System.currentTimeMillis();

            Timer timer = new Timer(0, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long currentTime = System.currentTimeMillis();
                    long timeElapsedAfterLaunch = currentTime - animationStartTime;
                    if (timeElapsedAfterLaunch > animationDuration) {
                        timer.stop();
                        timer.setRepeats(false);
                        return;
                    }
                    // in case the transition is forward, the fraction is calculated normally
                    float fraction = (float) timeElapsedAfterLaunch / animationDuration;

                    if (isReverse) {
                        // if the transition is reverse, the fraction is substituted by its complementary to 1
                        fraction = 1 - fraction;
                    }

                    int coordinateX = (int) ((endCoordinateX - startCoordinateX) * (fraction) + startCoordinateX);
                    transitioningComponent.setBounds(coordinateX, transitioningComponent.getBounds().y, transitioningComponent.getWidth(), transitioningComponent.getHeight());
                    transitioningComponent.repaint(coordinateX, transitioningComponent.getBounds().y, transitioningComponent.getWidth(), transitioningComponent.getHeight());
                }
            });

            timer.start();
        }).start();
    }
}
