import java.util.ArrayList;

public class StandardRound implements RoundI {

    protected int numberOfQuestions;
    protected ArrayList<Player> players;
    protected QuestionManager questionManager;
    protected Parser parser;
    protected int pointsEarnedOnCorrectAnswer;

    /**
     * Constructs a StandardRound object with given attirbutes
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           array list of the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with player(s) via console
     */
    public StandardRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        this.numberOfQuestions = numberOfQuestions;
        this.players = players;
        this.questionManager = questionManager;
        this.parser = parser;
        this.pointsEarnedOnCorrectAnswer = 1000;
    }

    /**
     * Printing method for the round description. Written hard coded TEMPORARILY.
     */
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestions() + " questions.%n"
                + "For every correct answer, you earn " + this.getPointsEarnedOnCorrectAnswer() + " points!%n"
                + "Press enter to start round ");
        parser.getEnter();
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
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Getter for the variable storing the points earned on correct answer.
     * In this type of round, the points are the same for every question.
     *
     * @return the number of points earned on correct answer
     */
    public int getPointsEarnedOnCorrectAnswer() {
        return pointsEarnedOnCorrectAnswer;
    }

    /**
     * Setter for the variable storing the points earned on correct answer. Only for inside-class use.
     * In this type of round, the points are the same for every question.
     * However, it will have great usefulness for subclasses.
     * @param pointsEarnedOnCorrectAnswer the desired number of points to update the pointsEarnedOnCorrectAnswer variable.
     */
    protected void setPointsEarnedOnCorrectAnswer(int pointsEarnedOnCorrectAnswer) {
        this.pointsEarnedOnCorrectAnswer = pointsEarnedOnCorrectAnswer;
    }

    /**
     * Displays the question to be answered, as well as the available choices.
     * The category, the question itself and the available answers are displayed simultaneously.
     */
    @Override
    public void askQuestion() {
        System.out.println();
        questionManager.getNextQuestion().displayCategory();
        questionManager.getNextQuestion().displayQuestionBody();
        questionManager.getNextQuestion().displayOptions();
    }

    /**
     * Reads the answer given by player, executing data validation.
     *
     * @return a valid answer given by the player.
     */
    @Override
    public String readAnswer() {
        return parser.askForAnswer(questionManager.getNextQuestion().getAnswerKeySet());
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
        if (questionManager.getNextQuestion().isCorrectAnswer(givenAnswer)) {
            for (Player player : players) {
                System.out.println("Correct!");
                player.updateScore(getPointsEarnedOnCorrectAnswer());
            }
        } else {
            System.out.println("Wrong...");
        }
        questionManager.removeAnsweredQuestion();
    }

}


