package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * A class which represents a Timer, for use in rounds like {@code TimedRound} or {@code FastestFingerRound}
 *
 * @author Fotis Malakis
 * @see com.FastestFingerRoundLogic
 * @see com.StopTheClockRoundLogic
 */
public class TimerComponent {

    private JPanel panel;
    protected JLabel title;
    protected JLabel timerLabel;
    protected JLabel timeOverLabel;
    private SimpleDateFormat df = new SimpleDateFormat("s:SSS");
    private boolean isOver = false;
    private Timer timer;
    private long currentTime = 5000;

    /**
     * Initializes the timer component, by calling the <code>setUpPanel()</code> function
     * which handles the GUI building
     */
    public TimerComponent() {
        setUpPanel();
    }

    /**
     * Sets up the main panel
     */
    private void setUpPanel() {
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setBounds(180, 180, 500, 250);
        panel.setBackground(Color.darkGray);

//        setUpHeader();
        setUpTimerLabel();
        setUpTimer();
        setUpFooter();

        panel.setVisible(true);
    }

    /**
     * Sets up the header of the component
     */
    public void setUpHeader() {
        title = new JLabel();
        title.setText("Timed Round!");
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 24f));
        panel.add(title, BorderLayout.NORTH);
    }

    /**
     * Sets up the JLabel that will be displaying how much time is left
     * in real time
     */
    public void setUpTimerLabel() {
        timerLabel = new JLabel();
        timerLabel.setText(df.format(currentTime));
        timerLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.BOLD, 26f).deriveFont(Font.ITALIC));
        timerLabel.setForeground(Color.ORANGE);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(timerLabel, BorderLayout.CENTER);
    }

    /**
     * Sets up a new Timer object with a duration of 5000 milliseconds,
     * and awaits for the start of it, upon which it constantly ticks down
     * until it reaches zero.
     */
    private void setUpTimer() {
        timer = new Timer(10, e -> {

            timeOverLabel.setVisible(false);
            timer.setInitialDelay(0);

            currentTime -= 10;
            if (currentTime <= 0) {
                timeOverLabel.setVisible(true);
                isOver = true;
                timer.stop();
            }
            timerLabel.setText(df.format(currentTime));

        });
    }

    /**
     * Attempts to start the Timer if the timer hasn't yet been started.
     */
    public void startTimer() {
        if (!timer.isRunning()) {
            currentTime = 5000;
            timer.start();
        }
    }

    /**
     * Continues the timer from where it was left off
     */
    public void continueTimer() {
        timer.start();
    }

    /**
     * Returns the main panel of the component
     *
     * @return a <code>JPanel</code> which contains all elements of TimeComponent
     */
    public JPanel getMainPanel() {
        return panel;
    }

    /**
     * Method which returns whether the timer has reached 0 or not
     *
     * @return returns <code>true</code> if the timer has reached 0, otherwise
     * returns <code>false</code>
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * Stops the timer
     */
    public void stopTimer() {
        timer.stop();
        System.out.println(currentTime);
    }

    /**
     * Hides the TimerComponent panel
     *
     * @return <code>true</code> if the panel was previously shown and it has been hidden, or <code>false</code>
     * if the panel was already hidden.
     */
    public boolean hideTimer() {
        if (panel.isVisible()) {
            panel.setVisible(false);
            return true;
        }
        return false;
    }

    /**
     * Shows the TimerComponent panel
     *
     * @return <code>true</code> if the panel was previously hidden and it has been shown, or <code>false</code>
     * if the panel was already shown.
     */
    public boolean showTimer() {
        if (!panel.isVisible()) {
            panel.setVisible(true);
            return true;
        }
        return false;
    }

    /**
     * Sets up the footer of the panel which gets displayed as soon as the
     * timer reaches 0
     */
    private void setUpFooter() {
        timeOverLabel = new JLabel();
        timeOverLabel.setForeground(Color.white);
        timeOverLabel.setText("<html>Time has <font color=red><b>run out</b></font>!</html>");
        timeOverLabel.setVisible(false);
        timeOverLabel.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.SEMI_BOLD, 16f));
        timeOverLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(timeOverLabel, BorderLayout.SOUTH);
    }

    /**
     * Resets the time label of the timer. Removes a (possibly) visible time-over message.
     */
    public void resetTimerLabel() {
        timeOverLabel.setVisible(false);
        timerLabel.setText(df.format(5000));
    }

    /**
     * Simple accessor for the current time stamp on the timer.
     *
     * @return the time remaining until the timer reaches 0 in milliseconds.
     */
    public long getMillisAfterLaunch() {
        return currentTime;
    }
}
