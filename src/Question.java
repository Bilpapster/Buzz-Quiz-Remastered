import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * A class representing a single question in our quiz game
 */
public class Question {

    private String questionText, correctAnswer;
    private HashMap<String, String> answers;
    private boolean hasPicture;
    private String picture;
    private QuestionType questionType;

    /**
     * The first of two constructors used to create a Question object without a picture
     *
     * @param questionText the question text
     * @param correctAnswer the correct answer, depicted with a string
     * @param answers a hashmap containing both the letters for the questions and the questions
     * @param questionType the type of question (e.g. history, science etc)
     * @param hasPicture a boolean value indicating if the object has an image associated with it, in this case, it doesn't
     */
    public Question(String questionText, String correctAnswer, HashMap<String, String> answers, QuestionType questionType, boolean hasPicture) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.questionType = questionType;
        this.hasPicture = hasPicture;
    }

    /**
     * Identical with the first constructor, only difference is that this one accepts an image string
     *
     * @param picture an image string
     */
    public Question(String questionText, String correctAnswer, HashMap<String, String> answers, QuestionType questionType, boolean hasPicture, String picture) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.questionType = questionType;
        this.hasPicture = hasPicture;
        this.picture = picture;
    }

    /**
     * Return's a Question object's question text
     * @return the Question object's question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Return's a Question object's correct answer
     * @return the Question Object's correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the Question Object's question type
     * @return the Question Object's question type
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    public HashMap<String, String> getAnswers() {
        return answers;
    }

    public boolean hasPicture() {
        return hasPicture;
    }

    public String getPicture() {
        return picture;
    }

    public Set<String> getAnswerKeySet() { // lowers the cohesion of the class, need to look into it
        return answers.keySet();
    }

    public void displayCategory() {
        System.out.printf("--------Category: %s-------\n", questionType.toString());
    }

    /**
     * Pretty prints a Question object's question text
     */
    public void displayQuestionBody() {
        System.out.println("--------" + questionText + "--------");
    }

    /**
     * Pretty prints all the available answers for a Question object
     */
    public void displayOptions() {
        for(String i : answers.keySet())
            System.out.println(i + ") " + answers.get(i));
    }

//    public void displayQuestion() {
//        System.out.printf("--------Category: %s-------\n", questionType.toString());
//        System.out.println("--------" + questionText + "--------");
//        for(String i : answers.keySet())
//            System.out.println(i + ") " + answers.get(i));
//    }

    /**
     * A method that checks if the answer provided to it matches the question's correct answer
     *
     * @param playerChoice the answer that the object's correct answer will be checked against
     * @return <code>true</code> if the provided answer matches the Object's correct answer, else <code>false</code>
     */
    public boolean isCorrectAnswer(String playerChoice) {
        return answers.get(playerChoice).equals(correctAnswer);
    }

}

