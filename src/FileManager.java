import com.sun.source.tree.Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
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

    public TreeMap<String, Integer> getHighscoresFromFile() {
        TreeMap<String, Integer> highscores = new TreeMap<>();
        if (highScoreFile.length() == 0)
            return highscores;
        try {
            Scanner fileReader = new Scanner(highScoreFile);
            while(fileReader.hasNextLine()) {
                StringTokenizer highscoreLine = new StringTokenizer(fileReader.nextLine(),":");
                highscores.put(highscoreLine.nextToken(), Integer.parseInt(highscoreLine.nextToken()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return highscores;
    }

    public void updateHighscoresOnFile(TreeMap<String, Integer> newHighscores) {
        try {
            FileWriter fileWriter = new FileWriter(highScoreFile);
            //int placementIndex = 1;
            for(String i: newHighscores.keySet())
                fileWriter.write(i + ":" + newHighscores.get(i) + "\n");
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
