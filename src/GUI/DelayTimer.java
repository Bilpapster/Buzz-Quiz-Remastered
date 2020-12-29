package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DelayTimer {
    private Timer timer;

    public DelayTimer(int durationInMillis) {
        timer = new Timer(durationInMillis, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ((javax.swing.Timer) e.getSource()).stop();
                executeActionsAfterDelay();
            }
        });
        timer.start();
        executeActionsBeforeDelay();
    }

    protected abstract void executeActionsBeforeDelay();
    protected abstract void executeActionsAfterDelay();
}
