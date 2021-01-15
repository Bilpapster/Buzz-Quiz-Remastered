package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A class that represents a transition/animation wizard in our game. The class offers animations that can be either
 * directly run or scheduled for later. The class follows the Singleton's design pattern.
 *
 * @author Vasileios Papastergios
 * @version 2021.01.15
 */
public class TransitionWizard {

    private static TransitionWizard wizard = new TransitionWizard();

    /**
     * Private constructor, so as the class cannot be instantiated. Access is accomplished through getWizard method.
     */
    private TransitionWizard() {
    }

    /**
     * Gives access to the one and only object of the class. It is enough for all transitions.
     *
     * @return the one and only instance of the class.
     */
    public static TransitionWizard getWizard() {
        return wizard;
    }

    /**
     * Schedules a forward animation.
     *
     * @param transitioningComponent the component to move.
     * @param animationDuration      the duration of the animation.
     * @param startCoordinateX       the initial x position
     * @param endCoordinateX         the ending x position
     * @param initialDelay           the initial delay
     */
    public void scheduleForwardAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX, int initialDelay) {
        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runForwardAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX);
            }
        };
    }

    /**
     * Schedules a reverse animation.
     *
     * @param transitioningComponent the component to move.
     * @param animationDuration      the duration of the animation.
     * @param startCoordinateX       the initial x position
     * @param endCoordinateX         the ending x position
     * @param initialDelay           the initial delay
     */
    public void scheduleReverseAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX, int initialDelay) {
        new DelayTimer(initialDelay) {
            @Override
            protected void executeActionsAfterDelay() {
                runReverseAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX);
            }
        };
    }

    /**
     * Runs a forward animation.
     *
     * @param transitioningComponent the component to move.
     * @param animationDuration      the duration of the animation.
     * @param startCoordinateX       the initial x position
     * @param endCoordinateX         the ending x position
     */
    public void runForwardAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX) {

        new Thread(() -> runAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX, false)).start();
    }

    /**
     * Runs a reverse animation.
     *
     * @param transitioningComponent the component to move.
     * @param animationDuration      the duration of the animation.
     * @param startCoordinateX       the initial x position
     * @param endCoordinateX         the ending x position
     */
    public void runReverseAnimation(JComponent transitioningComponent, int animationDuration, int startCoordinateX, int endCoordinateX) {
        new Thread(() -> runAnimation(transitioningComponent, animationDuration, startCoordinateX, endCoordinateX, true)).start();
    }

    /**
     * Runs an animation (generalized).
     *
     * @param transitioningComponent the component to move.
     * @param animationDuration      the duration of the animation.
     * @param startCoordinateX       the initial x position
     * @param endCoordinateX         the ending x position
     */
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
