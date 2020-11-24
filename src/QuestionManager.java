import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionManager {
    private ArrayList<Question> listOfQuestions;

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
     * Temporary function which fills the question list with hardcoded questions. Will be removed when we switch over to file parsing
     */
    public void createQuestions() {

        String[] answers1 = {"George Washington", "Donald J. Trump", "Lyndon B. Johnson","Abraham Lincoln"};
        addNewQuestion(new Question("Who was the first president of the US?", "George Washington", getQuestionAnswers(answers1), QuestionType.History, false));

        String[] answers2 = {"1988", "1987", "1990", "1992"};
        addNewQuestion(new Question("When were System of A Down formed?", "1988", getQuestionAnswers(answers2), QuestionType.Music, false));

        String[] answers3 = {"Paraguay", "Brazil", "Germany", "Uruguay"};
        addNewQuestion(new Question("What country won the very first FIFA World Cup in 1930?", "Uruguay", getQuestionAnswers(answers3), QuestionType.Sports, false));

        String[] answers4 = {"Sebastian Vettel", "Michael Schumacher", "Max Verstappen", "Valtteri Bottas"};
        addNewQuestion(new Question("Which racer holds the record for the most Grand Prix wins?", "Michael Schumacher", getQuestionAnswers(answers4), QuestionType.Sports, false));

        String[] answers5 = {"2004", "2008", "2009", "2007"};
        addNewQuestion(new Question("What year was the very first model of the iPhone released?", "2007", getQuestionAnswers(answers5), QuestionType.Technology, false));

        String[] answers6 = {"HyperText Transfer Protocol", "Highly Transposed Text Protocol", "Helios Transport Transcontinental Productions", "Hi-Tao Tsushimi Products"};
        addNewQuestion(new Question("What does HTTP stand for?", "HyperText Transfer Protocol", getQuestionAnswers(answers6), QuestionType.Technology, false));

        String[] answers7 = {"Alexander Fleming", "Florence Nightingale", "Alexander Papanikolaou", "Joseph Bohr"};
        addNewQuestion(new Question("Who discovered Penicillin?", "Alexander Fleming", getQuestionAnswers(answers7), QuestionType.Science, false));

        String[] answers8 = {"3", "4", "5", "1"};
        addNewQuestion(new Question("How many molecules of oxygen does ozone have?", "3", getQuestionAnswers(answers8), QuestionType.Science, false));

        String[] answers9 = {"China", "England", "Japan", "Brazil"};
        addNewQuestion(new Question("Which country invented tea?", "China", getQuestionAnswers(answers9), QuestionType.Food_and_Culture, false));

        String[] answers10 = {"Saigo Takamori", "Emperor Hirohito", "Shinzo Abe", "Emperor Akihito"};
        addNewQuestion(new Question("Who is widely considered to be the 'Last Samurai'?", "Saigo Takamori", getQuestionAnswers(answers10),QuestionType.History, false));

        String[] answers11 = {"10.000", "15.000", "20.000", "8.000"};
        addNewQuestion(new Question("How many taste buds does the human tongue have?", "10.000", getQuestionAnswers(answers11),QuestionType.Science, false));

        String[] answers12 = {"Bail", "Francis", "Bob", "Ben"};
        addNewQuestion(new Question("What was the name of the adoptive father of Princess Leia Organa from Star Wars?", "Bail", getQuestionAnswers(answers12), QuestionType.Movies_and_Series, false));

        String[] answers13 = {"Vilnius", "Riga", "Krakow", "Minsk"};
        addNewQuestion(new Question("What is the capital of Lithuania?", "Vilnius", getQuestionAnswers(answers13), QuestionType.Geography, false));

        String[] answers14 = {"Germany", "Netherlands", "Austria", "Luxembourg"};
        addNewQuestion(new Question("With which country does France NOT share a land border with?", "Austria", getQuestionAnswers(answers14), QuestionType.Geography, false));

        String[] answers15 = {"Industrial Metal", "Heavy Metal", "Power Metal", "Death Metal"};
        addNewQuestion(new Question("In which genre of metal do the songs of the german metal band 'Rammstein' belong to?", "Industrial Metal", getQuestionAnswers(answers15), QuestionType.Music, false));

        shuffleQuestions();

    }

    /**
     * Method that returns a hashMap containing the answers provided in the arguments, after they have been shuffled
     *
     * @param answerValues a String array containing the answers
     * @return a <code>HashMap</code> with alphabetically ordered keys and the shuffled answerValues as the values
     */
    private HashMap<String, String> getQuestionAnswers(String[] answerValues) {

        ArrayList<String> answers = new ArrayList<>();
        Collections.addAll(answers, answerValues);
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
