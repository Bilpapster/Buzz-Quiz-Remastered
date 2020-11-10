import java.io.IOException;
import java.util.*;

public class QuestionManager {
    private ArrayList<Question> listOfQuestions;
    private FileManager files;

    public QuestionManager() {
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
     * Creates question objects with data from the FileManager, after it has tokenized and parsed them and pushes the questions to the list of questions<br><br>
     * <b>NOTE:</b> This method will only work if the 'questions.txt' file uses the proprietary format the developers intended, altering the delimiter symbol
     * or the way the answer is formatted will result in the code needing to be overhauled.
     */
    public void createQuestions() {

        ArrayList<String> questionsFromFile;
        questionsFromFile = files.getQuestionsFromFile();
        for(String i: questionsFromFile) {

            StringTokenizer tokenizedQuestion = new StringTokenizer(i,"/"); // split question line from file using the '/' delimiter
            ArrayList<String> tokenizedQuestionList = new ArrayList<>();
            while(tokenizedQuestion.hasMoreTokens())
                tokenizedQuestionList.add(tokenizedQuestion.nextToken());

            String questionText = tokenizedQuestionList.get(0); // extract question text
            String questionAnswer = tokenizedQuestionList.get(tokenizedQuestionList.size()-4); // extract question answer
            QuestionType questionType = QuestionType.valueOf(tokenizedQuestionList.get(tokenizedQuestionList.size()-3)); // extract question type
            boolean hasPicture = !tokenizedQuestionList.get(tokenizedQuestionList.size() - 2).equals("false"); // extract whether or not it has an extra file associated with it
            String fileLocation = tokenizedQuestionList.get(tokenizedQuestionList.size()-1); // extract file location

            tokenizedQuestionList.remove(0); // remove question text

            for(int a = 0; a < 4; a++)
                tokenizedQuestionList.remove(tokenizedQuestionList.size()-1); // trim everything but the possible answers

            if (!hasPicture)
                addNewQuestion(new Question(questionText, questionAnswer, getQuestionAnswers(tokenizedQuestionList), questionType, false));
            else
                addNewQuestion(new Question(questionText, questionAnswer, getQuestionAnswers(tokenizedQuestionList), questionType, true, fileLocation));

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

    /**
     * Pretty prints all the components of every Question inside QuestionManager's listOfQuestions
     */
    public void printAllQuestions() {
        for (Question i : listOfQuestions) {
            i.displayCategory();
            i.displayQuestionBody();
            i.displayOptions();
        }
    }

}
