import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing the game itself. Acts like an overall-controller class.
 */
public class Game {
    private final int numberOfRounds;
    private final int numberOfQuestionsPerRound;
    private final ArrayList<Player> players;
    private final ArrayList<RoundI> rounds;
    private final QuestionManager questionManager;
    private final Parser parser;

    /**
     * Default constructor that creates a game object. By default, the number
     * of rounds are set to 3 and the number of questions in each one of them
     * is set to 5. The game mode is set to 1-player game.
     */
    public Game() {
        numberOfRounds = 3;
        numberOfQuestionsPerRound = 5;
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
        players.add(new Player());

        /* uncomment the following line for 2-player game */
//        players.add(new Player());
    }

    /**
     * Identical with the default constructor. The only difference is that
     * the number ofrounds as well as the number of questions in each can
     * be defined at object construction time.
     *
     * @param numberOfRounds            the number of rounds of a game
     * @param numberOfQuestionsPerRound the number of questions in each round
     * @param players                   the players involved in the game
     */
    public Game(int numberOfRounds, int numberOfQuestionsPerRound, ArrayList<Player> players) {
        this.numberOfRounds = numberOfRounds;
        this.numberOfQuestionsPerRound = numberOfQuestionsPerRound;
        this.players = players;
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
    }

    /**
     * A method that serves as initializer for the game itself. Selects in α random
     * way the type of each round and initializes them.
     */
    public void initializeGamePlay() {

        Random randomNumbersGenerator = new Random(System.currentTimeMillis());

        for (int round = 0; round < numberOfRounds; round++) {
            int randomNumber = (Math.abs(randomNumbersGenerator.nextInt()) % 3) + 1;
            switch (randomNumber) {
                case 2:
                    rounds.add(new BettingRound(numberOfQuestionsPerRound, players, questionManager, parser));
                    break;
                case 3:
                    rounds.add(new StopTheClockRound(numberOfQuestionsPerRound, players, questionManager, parser));
                    break;
                default:
                    rounds.add(new StandardRound(numberOfQuestionsPerRound, players, questionManager, parser));
            }
        }
    }

    /**
     * A method that serves as driving code for the whole game. Manages the flow of actions invoked inside rounds
     * as well as the succession between rounds.
     */
    public void play() {
        int roundsCounter = 1;
        for (RoundI round : rounds) {
            System.out.println();
            System.out.println("**********" + " Round " + roundsCounter + " **********");
            System.out.print(round.getDescription());
            System.out.print("Press enter to start round ");
            parser.getEnter();
            while (!round.isOver()) {
                round.askQuestion();
                round.readAnswers();
                round.giveCredits();
            }
            System.out.println();
            System.out.println("End of Round " + (roundsCounter++) + ".");
            for (Player player : players) {
                player.printScore();
            }
        }
    }
}