import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class QuestionManager {
    private ArrayList<Question> listOfQuestions;
    private FileManager files;

    public QuestionManager() throws IOException {
        listOfQuestions = new ArrayList<>();
        try {
            files = new FileManager();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void addNewQuestion(Question newQuestion) {
        listOfQuestions.add(newQuestion);
    }

    /**
     * Removes the first element of the questions list
     */
    public void removeAnsweredQuestion() {
        listOfQuestions.remove(0);
    }

    /**
     * Shuffles the list of question using <code>Collections.shuffle()</code>
     */
    private void shuffleQuestions() {
        Collections.shuffle(listOfQuestions);
    }

    /**
     * Method for acessing the size of the list of questios [TEMPORARY]
     * @return the number of remaining questions in the list
     */
    public int questionsRemaining() {
        return listOfQuestions.size();
    }

    /**
     * Returns the next question for the player(s)
     */
    public Question getNextQuestion() {
        Question returnQuestion = listOfQuestions.get(0);
        return returnQuestion;
    }

    /**
     * Creates question objects with data from the FileManager and pushes the questions to the list of questions
     */
    public void createQuestions() {

        ArrayList<String> questionsFromFile;
        questionsFromFile = files.getQuestionsFromFile();
        for(String i: questionsFromFile) {

            StringTokenizer tokenizedQuestion = new StringTokenizer(i,"/");
            ArrayList<String> tokenizedQuestionList = new ArrayList<>();
            while(tokenizedQuestion.hasMoreTokens())
                tokenizedQuestionList.add(tokenizedQuestion.nextToken());

            String questionText = tokenizedQuestionList.get(0);
            String questionAnswer = tokenizedQuestionList.get(tokenizedQuestionList.size()-2);
            QuestionType questionType = QuestionType.valueOf(tokenizedQuestionList.get(tokenizedQuestionList.size()-1));

            tokenizedQuestionList.remove(0);
            tokenizedQuestionList.remove(tokenizedQuestionList.size()-1);
            tokenizedQuestionList.remove(tokenizedQuestionList.size()-1);

            addNewQuestion(new Question(questionText, questionAnswer, getQuestionAnswers(tokenizedQuestionList), questionType, false));

        }

        shuffleQuestions();

    }

//    public HashSet<Question> getListOfQuestions() {
//        HashSet<Question> finalListOfQuestions = listOfQuestions;
//        return finalListOfQuestions;
//    }

    /**
     * Method that returns a hashMap containing the answers provided in the arguments, after they have been shuffled
     *
     * @param answerValues a String array containing the answers
     * @return a <code>HashMap</code> with alphabetically ordered keys and the shuffled answerValues as the values
     */
    private HashMap<String, String> getQuestionAnswers(ArrayList<String> answerValues) {

        ArrayList<String> answers = answerValues;
        Collections.shuffle(answers);

        HashMap<String, String> finalAnswers = new HashMap<>();
        Character answerIndex = 'a';
        for(String i: answers)
            finalAnswers.put((answerIndex++).toString(), i);
        return finalAnswers;

    }

    public void printAllQuestions() {
        for (Question i : listOfQuestions) {
            i.displayCategory();
            i.displayQuestionBody();
            i.displayOptions();
        }
    }

}
