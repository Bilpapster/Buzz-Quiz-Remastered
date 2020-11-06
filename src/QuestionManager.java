import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionManager {
    ArrayList<Question> listOfQuestions;

    public QuestionManager() {
        listOfQuestions = new ArrayList<>();
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
    public void shuffleQuestions() {
        Collections.shuffle(listOfQuestions);
    }

    /**
     * Temporary function which fills the question list with hardcoded questions. Will be removed when we switch over to file parsing
     */
    public void createQuestions() {

        HashMap<String, String> answer1 = new HashMap<>();
        answer1.put("a", "George Washington");
        answer1.put("b", "Donald J. Trump");
        answer1.put("c", "Lyndon B. Johnson");
        answer1.put("d", "Abraham Lincoln");
        addNewQuestion(new Question("Who was the first president of the US?", "George Washington", answer1, QuestionType.History, false));


        HashMap<String, String> answer2 = new HashMap<>();
        answer2.put("a", "1988");
        answer2.put("b", "1987");
        answer2.put("c", "1990");
        answer2.put("d", "1992");
        addNewQuestion(new Question("When were System of A Down formed?", "1988", answer2, QuestionType.Music, false));

//        HashSet<String> answer3 = new HashSet<>();
//        answer3.add("Hig's Bosson");
//        answer3.add("Carbon");
//        answer3.add("Hydrogen");
//        answer3.add("Oxygen");
//        addNewQuestion(new Question("Which is considered to be the first particle?", "Hig's Bosson", answer3, QuestionType.Science, false));
//
//        HashSet<String> answer4 = new HashSet<>();
//        answer4.add("Legionnaires");
//        answer4.add("Emperor's Guard");
//        answer4.add("Praetorian Guard");
//        answer4.add("Shock Troops of Rome");
//        addNewQuestion(new Question("Who were the Roman Emperor's Elite Guard?", "Praetorian Guard", answer4, QuestionType.Music, false));


        shuffleQuestions();

    }

//    public HashSet<Question> getListOfQuestions() {
//        HashSet<Question> finalListOfQuestions = listOfQuestions;
//        return finalListOfQuestions;
//    }

    public void printAllQuestions() {
        for(Question i: listOfQuestions)
            i.displayQuestion();
    }

}
