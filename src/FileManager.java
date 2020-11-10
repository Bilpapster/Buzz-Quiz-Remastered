import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManager {

    private File highScoreFile, questionsFile;

    public FileManager() throws IOException {
        highScoreFile = new File("highscores.txt");
        questionsFile = new File("questions.txt");
        try {
            if (!highScoreFile.exists())
                highScoreFile.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the questions.txt file and fetches each line of it
     *
     * @return an <code>ArrayList&#60;String&#62;</code> containing the line strings of questions.txt
     */
    public ArrayList<String> getQuestionsFromFile() {
        ArrayList<String> questions = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(questionsFile);
            while(fileReader.hasNextLine()) {
                String questionString = fileReader.nextLine();
                questions.add(questionString);
            }
            fileReader.close();
        }   catch(IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Parses the highscores.txt file and fetches the Name:Score pairs from it
     *
     * @return a <code>HashMap&#60;String, Integer&#62;</code>  containing 'User':'score' key/pair values
     */
    public LinkedHashMap<String, Integer> getHighscoresFromFile() {
        LinkedHashMap<String, Integer> highscores = new LinkedHashMap<>();
        if (highScoreFile.length() == 0)
            return highscores;
        try {
            Scanner fileReader = new Scanner(highScoreFile);
            while(fileReader.hasNextLine()) {
                String temp = fileReader.nextLine();
                StringTokenizer highscoreLine = new StringTokenizer(temp,":");
                highscores.put(highscoreLine.nextToken(), Integer.parseInt(highscoreLine.nextToken()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return highscores;
    }

    /**
     * Sorts the values (scores) of the given HashMap using a temporary ArrayList&#60;Map.Entry&#60;String, Integer&#62;&#62; initialized
     * with the <code>newHighscores</code> entry set after executing <code>Collections.sort()</code> with the custom comparator method for
     * highscores {@link HighscoreComparator}, and then prints the sorted highscores in 'highscores.txt' (descending order)
     *
     * @param newHighscores a <code>HashMap&#60;String, Integer&#62; containing 'Name':'Score' keypair values</code>
     */
    public void updateHighscoresOnFile(LinkedHashMap<String, Integer> newHighscores) {
        try {
            FileWriter fileWriter = new FileWriter(highScoreFile);
            ArrayList<Map.Entry<String,Integer>> highscoreList = new ArrayList<>(newHighscores.entrySet());
            Collections.sort(highscoreList, new HighscoreComparator());
            LinkedHashMap<String, Integer> finalHighscores = new LinkedHashMap<>();
            for(Map.Entry<String, Integer> i: highscoreList)
                finalHighscores.put(i.getKey(), i.getValue());
            for(String i: finalHighscores.keySet())
                fileWriter.write(i + ":" + finalHighscores.get(i) + "\n");
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
