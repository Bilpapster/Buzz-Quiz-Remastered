import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<PlayerTest> players =new ArrayList<>();
        ArrayList<RoundI> rounds = new ArrayList<>();
        Parser parser = new Parser();
        PlayerTest player1 = new PlayerTest(parser.askForName());
        players.add(player1);

        StandardRound firstRound = new StandardRound("1st Round", 2, players);
        StandardRound secondRound = new StandardRound("2nd Round", 2, players);
        StandardRound thirdRound = new StandardRound("3rd Round", 2, players);

        rounds.add(firstRound);
        rounds.add(secondRound);
        rounds.add(thirdRound);

        for (RoundI round : rounds) {
            System.out.println("**********" + round.getName() + "**********");
            for (int question = 0; question < firstRound.getNumberOfQuestions(); question++) {
                firstRound.askQuestion();
                String givenAnswer = firstRound.readAnswer();
                firstRound.giveCredits(givenAnswer);
            }
            System.out.println();
            System.out.println();
        }

        player1.printScore();
    }

}
