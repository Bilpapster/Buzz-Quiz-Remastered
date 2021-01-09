package GUI;

import com.Player;
import com.Referee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * A class that represents the frame of our game. This is the frame that the main game and the different types of rounds
 * are displayed to the player(s). The class is developed as a sub-class of the <code>JFrame</code> class.
 *
 * @author Vasileios Papastergios
 * @author Dimitrios-Fotios Malakis
 */
public class GameFrame extends JFrame {

    private final int numberOfRoundsInGame = 5;
    private final ArrayList<RoundViewerI> rounds = new ArrayList<>();
    private final ArrayList<Player> players;
    private final Referee referee;
    private int currentRoundIndex = -1;
    private Random randomNumbersGenerator = new Random(System.currentTimeMillis());

    /**
     * Constructs a game frame with for a game among the given players.
     *
     * @param players the players involved in game, in total.
     */
    public GameFrame(ArrayList<Player> players) {
        super("Buzz!: Quiz World!");
        this.setVisible(false);
        ImageIcon iconImage = new ImageIcon("src/resources/Buzz-Quiz-World_LOGO.jpg");
        this.setIconImage(iconImage.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);

        this.players = players;
        this.referee = new Referee(players);

        initializeRounds();
        this.setVisible(true);
        playNextRound();
    }

    /**
     * Initializes each one of the rounds of the game (5). Both in one-player and in two-player game, the first round
     * is always of type "Point Builder"/
     */
    private void initializeRounds() {

        /* the first round in both one-player and two-player game is Point Builder */
        rounds.add(new PointBuilderRoundViewer(referee));

        if (players.size() == 1) {
            initializeOnePlayerGame();
        } else {
            initializeTwoPlayerGame();
        }
    }

    /**
     * Initializes the remaining rounds (2-5) for a single-player game. The rounds are initialized in a random way
     * as one of the available round types for one player.
     */
    private void initializeOnePlayerGame() {
        for (int round = 1; round < numberOfRoundsInGame; round++) {
            int randomNumber = (Math.abs(randomNumbersGenerator.nextInt()) % 3) + 1;
            switch (randomNumber) {
                case 2:
                    rounds.add(new HighStakesRoundViewer(referee));
                    break;
                case 3:
                    rounds.add(new StopTheClockRoundViewer(referee));
                    break;
                default:
                    rounds.add(new PointBuilderRoundViewer(referee));
            }
        }
    }

    /**
     * Initializes the remaining rounds (2-5) for a two-player game. In two-player game mode, the last round (5) is
     * always of type "Boiling Point". The rest of the rounds (2-4) are initialized randomly as one of the available
     * round types for two players.
     */
    private void initializeTwoPlayerGame() {
        for (int round = 1; round < numberOfRoundsInGame - 1; round++) {
            int randomNumber = (Math.abs(randomNumbersGenerator.nextInt()) % 5) + 1;
            switch (randomNumber) {
                case 2:
                    rounds.add(new HighStakesRoundViewer(referee));
                    break;
                case 3:
                    rounds.add(new StopTheClockRoundViewer(referee));
                    break;
                case 4:
                    rounds.add(new FastestFingerRoundViewer(referee));
                default:
                    rounds.add(new PointBuilderRoundViewer(referee));
            }
        }
        /* the last round in a two-player game is always Boiling Point */
        rounds.add(new BoilingPointRoundViewer(referee));
    }

    /**
     * Takes care of moving on to the next round of the game. When the game is over, invokes a highScore frame.
     */
    public void playNextRound() {
        currentRoundIndex++;
        if (currentRoundIndex < rounds.size()) {
            referee.executeActionsBeforeNextRound();
            RoundViewerI currentRound = rounds.get(currentRoundIndex);
            currentRound.setParentFrame(this);
            this.setContentPane(currentRound.getRootPanel());
            currentRound.getRootPanel().requestFocus();
            currentRound.play();
        } else {
            new GameEndingWindow(players);
            dispose();
        }
    }
}
