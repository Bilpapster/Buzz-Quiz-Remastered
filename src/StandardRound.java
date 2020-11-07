import java.util.ArrayList;

public class StandardRound implements RoundI {

    private String name;
    private int numberOfQuestions;
    private ArrayList<PlayerTest> players;
    private QuestionManager questionTeller;
    private Parser parser;

    public StandardRound(String name, int numberOfQuestions, ArrayList<PlayerTest> players) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.players = players;
        this.questionTeller = new QuestionManager();
        questionTeller.createQuestions();
        this.parser = new Parser();
    }

    @Override
    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    @Override
    public ArrayList<PlayerTest> getPlayers() {
        return this.players;
    }

    @Override
    public void askQuestion() {
        System.out.println();
        questionTeller.getNextQuestion().displayQuestion();
    }

    @Override
    public String readAnswer() {
        return parser.askForAnswer();

    }

    @Override
    public void giveCredits(String givenAnswer) {
        if (questionTeller.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (PlayerTest player : players) {
                System.out.println("Correct!");
                player.updateScore(1000);
            }
        } else {
            System.out.println("Wrong...");
        }
    }

}


