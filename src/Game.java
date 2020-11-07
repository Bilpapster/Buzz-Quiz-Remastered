import java.util.ArrayList;

public class Game {
    private ArrayList<PlayerTest> players;
    private ArrayList<RoundI> rounds;
    private Parser parser;

    public Game() {
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        parser = new Parser();
    }

    public Game(ArrayList<PlayerTest> players, ArrayList<RoundI> rounds, Parser parser) {
        this.players = players;
        this.rounds = rounds;
        this.parser = parser;
    }

    public void initializeGamePlay() {
        players.add(new PlayerTest());
        rounds.add(new StandardRound(4, players));
        rounds.add(new StandardRound(3, players));
        rounds.add(new StandardRound(2, players));
    }

    public void play() {
        int roundsCounter = 1;
        for (RoundI round : rounds) {
            System.out.println();
            System.out.println("**********" + " Round " + roundsCounter + " **********");
            round.printDescription();
            for (int question = 0; question < round.getNumberOfQuestions(); question++) {
                round.askQuestion();
                String givenAnswer = round.readAnswer();
                round.giveCredits(givenAnswer);
            }
            System.out.println();
            System.out.println("End of Round " + (roundsCounter++) + ".");
            for (PlayerTest player : players) {
                player.printScore();
            }
        }
    }
}
