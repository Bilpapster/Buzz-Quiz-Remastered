import java.util.ArrayList;
import java.util.HashMap;

public class StandardRound implements RoundI {

    protected int numberOfQuestionsInRound;
    protected int numberOfQuestionsRemaining;
    protected ArrayList<Player> players;
    protected HashMap<Player, String> answersGivenByPlayers;
    protected QuestionManager questionManager;
    protected Parser parser;
    protected int creditPoints;

    /**
     * Constructs a StandardRound object with given attributes
     *
     * @param numberOfQuestions the number of questions in the round
     * @param players           array list of the players involved in the round
     * @param questionManager   a question-managing object, responsible for the questions of the round
     * @param parser            a parsing object responsible for communicating with player(s) via console
     */
    public StandardRound(int numberOfQuestions, ArrayList<Player> players, QuestionManager questionManager, Parser parser) {
        this.numberOfQuestionsInRound = numberOfQuestions;
        this.numberOfQuestionsRemaining = numberOfQuestions;

        this.answersGivenByPlayers = new HashMap<>();
        for (Player player : players) {
            answersGivenByPlayers.put(player, "No answer yet");
        }

        this.players = players;
        this.questionManager = questionManager;
        this.parser = parser;
        this.creditPoints = 1000;
    }

    /**
     * Printing method for the round description. Written hard coded TEMPORARILY.
     */
    public void printDescription() {
        System.out.printf("In this round you are going to be asked " + this.getNumberOfQuestionsInRound() + " questions.%n"
                + "For every correct answer, you earn " + this.getCreditPoints() + " points!%n"
                + "Press enter to start round ");
        parser.getEnter();
    }

    @Override
    public Boolean isOver() {
        return !(this.numberOfQuestionsRemaining > 0);
    }

    /**
     * Getter for the number of questions in the round.
     *
     * @return the number of questions in the round
     */
    public int getNumberOfQuestionsInRound() {
        return this.numberOfQuestionsInRound;
    }

    public int getNumberOfQuestionsRemaining() {
        return this.numberOfQuestionsRemaining;
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
    public int getCreditPoints() {
        return creditPoints;
    }

    /**
     * Setter for the variable storing the points earned on correct answer. Only for inside-class use.
     * In this type of round, the points are the same for every question.
     * However, it will have great usefulness for subclasses.
     *
     * @param creditPoints the desired number of points to update the pointsEarnedOnCorrectAnswer variable.
     */
    protected void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
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
     */
    @Override
    public void readAnswers() {
        for (Player player : players) {
            System.out.print(player.getName() + ", it is your turn. ");
            answersGivenByPlayers.put(player, parser.askForAnswer(questionManager.getNextQuestion().getAnswerKeySet()));
        }
    }

    /**
     * Updates the points of the player (if needed).
     * Prints a message, based on the correctness of the given answer.
     * Discards the current question as answered, so that it does not show up again, during the game.
     */
    @Override
    public void giveCredits() {

        for (Player player : players) {
            if (questionManager.getNextQuestion().isCorrectAnswer(answersGivenByPlayers.get(player))) {
                executeActionsOnCorrectAnswer(player);
            } else {
                executeActionsOnWrongAnswer(player);
            }
        }
        executeActionsOnEndOfQuestion();
    }

    protected void executeActionsOnCorrectAnswer(Player player) {
        System.out.print(player.getName() + ": Correct! +" + this.getCreditPoints());
        player.updateScore(this.getCreditPoints());
        System.out.println();
    }

    protected void executeActionsOnWrongAnswer(Player player) {
        System.out.print(player.getName() + ": Wrong ...");
        System.out.println();
    }

    protected void executeActionsOnEndOfQuestion() {
        this.numberOfQuestionsRemaining--;
        questionManager.removeAnsweredQuestion();
    }
}


