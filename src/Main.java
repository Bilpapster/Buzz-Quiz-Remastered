import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<PlayerTest> players =new ArrayList<>();
        ArrayList<RoundI> rounds = new ArrayList<>();
        Parser parser = new Parser();
        PlayerTest player1 = new PlayerTest(parser.askForName());
        players.add(player1);

        StandardRound firstRound = new StandardRound(3, players);
        StandardRound secondRound = new StandardRound(2, players);
        StandardRound thirdRound = new StandardRound(4, players);

        rounds.add(firstRound);
        rounds.add(secondRound);
        rounds.add(thirdRound);

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
            player1.printScore();
            System.out.println();
        }
    }

}
