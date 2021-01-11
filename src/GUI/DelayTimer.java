package GUI;

import javax.swing.*;


/**
 * An abstract class that represents a delay timer in our game. It can be used in order to delay the execution of
 * a specific block of code.
 *
 * @author Vasileios Papastergios
 */
public abstract class DelayTimer {

    /**
     * Constructs a <code>DelayTimer</code> object with given duration.
     *
     * @param durationInMillis the duration to pause execution between actions before and after.
     */
    public DelayTimer(int durationInMillis) {
        Timer timer = new Timer(durationInMillis, e -> {

            ((Timer) e.getSource()).stop();
            executeActionsAfterDelay();
        });
        timer.start();
        executeActionsBeforeDelay();
    }

    /**
     * Executes all actions needed before pausing execution.
     */
    protected void executeActionsBeforeDelay() {}

    /**
     * Executes all actions needed right after the pause.
     */
    protected abstract void executeActionsAfterDelay();
}
