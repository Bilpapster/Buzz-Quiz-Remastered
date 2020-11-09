import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HighscoreManager {
    FileManager fileManager;
    HashMap<String, Integer> fileHighscores;

    /**
     * The class constructor which initializes the FileManager attribute and fetches current highscores before the game starts
     *
     * @throws IOException
     */
    public HighscoreManager() throws IOException {
        fileManager = new FileManager();
        fileHighscores = fileManager.getHighscoresFromFile();
    }

    /**
     * Method that checks to see if a new highscore has been attained in the current gaming session by cross-checking with the fileHighscores <code>HashMap</code>,
     * if it finds a new player or an existing player with a new highscore, it updates the <code>HashMap</code> accordingly, and then calls <code>FileManager.updateHighScoresOnFile()</code>
     * to update the highscore file.
     *
     * @param players list of the players from the current session.
     */
    public void updateHighscores(ArrayList<Player> players) {
        for(Player i: players) {
            fileHighscores.putIfAbsent(i.getName(), i.getScore());
            if(fileHighscores.get(i.getName()) < i.getScore())
                fileHighscores.put(i.getName(), i.getScore());
        }
        fileManager.updateHighscoresOnFile(fileHighscores);
    }

}
