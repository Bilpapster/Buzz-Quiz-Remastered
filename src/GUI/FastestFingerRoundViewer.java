package GUI;

import com.FastestFingerRoundLogic;
import com.Referee;
import com.StopTheClockRoundLogic;


/**
 * <p>A class that represents the front end of the round type "Fastest Finger" of the original
 * <a href="https://en.wikipedia.org/wiki/Buzz!:_Quiz_World">Buzz!: Quiz World</a> game. There are 5 questions in this
 * round. The round is timed and more than one players are required. The class is developed as a sub-class of the
 * <code>StopTheClockRoundViewer</code> class. There are no differences concerning the view of the round. The
 * only difference between the two type of rounds lies in the way credits are given to players.</p>
 * <p>At first, the player(s) are let to know the type and text of the coming question. A 3-seconds interval follows so
 * as for the player(s) to read the question. Right after, the available options are displayed and the timer starts
 * running!</p>
 *
 * @author Vasileios Papastergios
 * @see StopTheClockRoundLogic
 * @see RoundViewerI
 */
public class FastestFingerRoundViewer extends StopTheClockRoundViewer {
    public FastestFingerRoundViewer(Referee referee) {
        super(referee);
    }

    /**
     * Initializes the round logic core object. Needs to be overridden for every round type.
     */
    @Override
    protected void initializeRoundLogic() {
        this.roundLogic = new FastestFingerRoundLogic(5, referee);
    }
}
