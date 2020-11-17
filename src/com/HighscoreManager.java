package com;

import java.io.IOException;
import java.util.*;

public class HighscoreManager {
    FileManager fileManager;
    LinkedHashMap<String, Integer> fileHighscores;

    /**
     * The class constructor which initializes the com.FileManager attribute and fetches current highscores before the game starts
     */
    public HighscoreManager() {
        try {
            fileManager = new FileManager();
        } catch(IOException e) {
            e.printStackTrace();
        }
        fileHighscores = fileManager.getHighscoresFromFile();
    }

    /**
     * Method that checks to see if a new highscore has been attained in the current gaming session by cross-checking with the fileHighscores <code>HashMap</code>,
     * if it finds a new player or an existing player with a new highscore, it updates the <code>HashMap</code> accordingly, and then calls <code>com.FileManager.updateHighScoresOnFile()</code>
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

    /**
     * Displays the highscores from the 'highscores.txt' in descending order
     */
    public void displayHighscores() {
        LinkedHashMap<String, Integer> highscoreTable = fileManager.getHighscoresFromFile();
        System.out.println("-------------HIGHSCORE LEADERBOARD-------------");
        for(String i: highscoreTable.keySet())
            System.out.println(i + ": " + highscoreTable.get(i) + " points");
    }

}
