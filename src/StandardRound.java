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
}

    @Override
    public void collectAnswer() {
        String givenAnswer = parser.askForAnswer();

    }

    @Override
    public void giveCredits() {

    }

    public void playRound() {
        for (int question = 0; question < getNumberOfQuestions(); question++) {
            askQuestion();
            String givenAnswer = collectAnswer();
            giveCredits();
        }
    }
