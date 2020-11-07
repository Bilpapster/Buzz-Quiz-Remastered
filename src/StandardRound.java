import java.util.ArrayList;

public class StandardRound implements RoundI {

    private String name;
    private int numberOfQuestions;
    private ArrayList<PlayerTest> players;
    private QuestionManager questionTeller;
    private Parser parser;

    /**
     * Constructs a StandardRound object with given attirbutes
     *
     * @param name              the name of the round
     * @param numberOfQuestions the number of questions in the round
     * @param players           array list of the players involved in the round
     */
    public StandardRound(String name, int numberOfQuestions, ArrayList<PlayerTest> players) {
        this.name = name;
        this.numberOfQuestions = numberOfQuestions;
        this.players = players;
        this.questionTeller = new QuestionManager();
        questionTeller.createQuestions();
        this.parser = new Parser();
    }

    /**
     * Getter for the round name.
     *
     * @return the round name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the number of questions in the round.
     *
     * @return the number of questions in the round
     */
    @Override
    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    /**
     * Getter for the players involved in the round
     *
     * @return an array list containing the players involved in the round
     */
    @Override
    public ArrayList<PlayerTest> getPlayers() {
        return this.players;
    }

    /**
     * Displays the question to be answered, as well as the available choices.
     * The category, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionTeller.getNextQuestion().displayQuestion();
    }

    /**
     * Reads the answer given by player, executing data validation.
     *
     * @return a valid answer given by the player.
     */
    @Override
    public String readAnswer() {
        return parser.askForAnswer(questionTeller.getNextQuestion().getAnswerKeySet());
    }

    /**
     * Updates the points of the player (if needed).
     * Prints a message, based on the correctness of the given answer.
     * Discards the current question as answered, so that it does not show up again, during the game.
     *
     * @param givenAnswer the answer to compare against the correct one, in order to give credits or not
     */
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
        questionTeller.removeAnsweredQuestion();
    }
}


