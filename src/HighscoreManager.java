import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class HighscoreManager {
    FileManager fileManager;
    TreeMap<String, Integer> fileHighscores;

    public HighscoreManager() throws IOException {
        fileManager = new FileManager();
        fileHighscores = fileManager.getHighscoresFromFile();
    }

    public void updateHighscores(ArrayList<Player> players) {
        for(Player i: players) {
            fileHighscores.putIfAbsent(i.getName(), i.getScore());
            if(fileHighscores.get(i.getName()) < i.getScore())
                fileHighscores.put(i.getName(), i.getScore());
        }
        fileManager.updateHighscoresOnFile(fileHighscores);
    }

}
