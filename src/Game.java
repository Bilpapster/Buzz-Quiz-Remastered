import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<RoundI> rounds;
    private QuestionManager questionManager;
    private Parser parser;

    public Game() {
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
    }

    public Game(ArrayList<Player> players, ArrayList<RoundI> rounds, Parser parser) {
        this.players = players;
        this.rounds = rounds;
        this.parser = parser;
    }

    public void initializeGamePlay() {
        players.add(new Player());
        rounds.add(new StandardRound(4, players, questionManager, parser));
        rounds.add(new StandardRound(3, players, questionManager, parser));
        rounds.add(new StandardRound(2, players, questionManager, parser));
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
            for (Player player : players) {
                player.printScore();
            }
        }
    }
}
